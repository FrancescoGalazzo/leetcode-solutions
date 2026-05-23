class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        
        // Passaggio 1: Calcola i prodotti prefissi da sinistra a destra
        // ans[i] conterrà il prodotto di tutti gli elementi prima di i
        ans[0] = 1; 
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        
        // Passaggio 2: Moltiplica per i prodotti suffissi da destra a sinistra
        // Usiamo una variabile accumulatore per tracciare il prodotto da destra
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = ans[i] * suffixProduct; // Prefisso * Suffisso
            suffixProduct *= nums[i];        // Aggiorna il suffisso per il prossimo elemento
        }
        
        return ans;
    }
}