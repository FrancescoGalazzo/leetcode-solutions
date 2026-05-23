class Solution {
    public int subarraySum(int[] nums, int k) {
         // 1. Il contatore globale dei sottoarray validi
        int count = 0;
        int currentSum = 0;
        
        // 2. La mappa: Chiave = Somma accumulata, Valore = Frequenza
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Caso limite fondamentale: una somma pari a 0 è stata vista 1 volta all'inizio
        map.put(0, 1);
        
        // 3. Scorriamo l'array un'unica volta (O(N))
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i]; // Aggiorna la somma accumulata
            
            int target = currentSum - k;
            
            // Se abbiamo incontrato 'target' nel passato, aggiungiamo la sua frequenza al totale
            if (map.containsKey(target)) {
                count += map.get(target);
            }
            
            // Aggiorna la frequenza della somma attuale nella mappa
            // Se non esisteva, getOrDefault restituisce 0 e aggiungiamo 1
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }
        
        // 4. Restituiamo il contatore cumulativo, NON la frequenza della mappa
        return count;
    }
}