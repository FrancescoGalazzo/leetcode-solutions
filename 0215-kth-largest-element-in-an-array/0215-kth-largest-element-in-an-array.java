class Solution {
 private final Random rand = new Random();

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int l, int r, int target) {
        if (l == r) return nums[l];

        // 1. Scegli un pivot casuale
        int pivotIdx = l + rand.nextInt(r - l + 1);
        int pivot = nums[pivotIdx];

        // 2. Partizione a 3 vie (Dutch National Flag)
        int i = l;
        int lt = l;
        int gt = r;

        while (i <= gt) {
            if (nums[i] < pivot) {
                swap(nums, i++, lt++);
            } else if (nums[i] > pivot) {
                swap(nums, i, gt--); // Non incrementi i perché devi controllare il nuovo elemento scambiato
            } else {
                i++;
            }
        }

        // Ora l'array è diviso così:
        // nums[l...lt-1] contiene elementi < pivot
        // nums[lt...gt]   contiene elementi == pivot
        // nums[gt+1...r]  contiene elementi > pivot

        // 3. Controlla in quale dei 3 segmenti cade il target
        if (target >= lt && target <= gt) {
            return nums[target]; // Il target cade nella zona degli elementi uguali al pivot
        } else if (target < lt) {
            return quickSelect(nums, l, lt - 1, target); // Cerca a sinistra
        } else {
            return quickSelect(nums, gt + 1, r, target); // Cerca a destra
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
    }
}