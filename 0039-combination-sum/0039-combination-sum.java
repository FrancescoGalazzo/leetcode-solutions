class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        explore(0, candidates, target, new ArrayList<>(), result);
        return result;
    }

    private void explore(int i, int[] candidates, int targetR, List<Integer> subset, List<List<Integer>> result){

        if (targetR == 0){
            result.add(new ArrayList<>(subset));
            return;
        }

        if(targetR < 0 || i >= candidates.length)
            return;

        subset.add(candidates[i]);
        explore(i, candidates, targetR-candidates[i], subset, result);

        subset.remove(subset.size()-1);

        explore(i+1, candidates, targetR, subset, result);
    }
}