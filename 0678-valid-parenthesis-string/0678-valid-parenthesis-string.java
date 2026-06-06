class Solution {
    
    public boolean checkValidString(String s) {
        int n = s.length();
        
        // Matrice quadrata di sicurezza per mappare lo spazio degli stati [index][count]
        // Inizializzata automaticamente con tutti valori 'null'
        Boolean[][] memo = new Boolean[n][n];
        
        // Facciamo partire il motore ricorsivo dalla fotografia iniziale:
        // index = 0 (primo carattere) e count = 0 (nessuna parentesi aperta)
        return esplora(s, 0, 0, memo);
    }

    private boolean esplora(String s, int index, int count, Boolean[][] memo) {
        // 1. CASI BASE (I Controlli di validità immediata)
        
        // Se il contatore scende sotto zero, abbiamo troppe ')'. Ramo interrotto.
        if (count < 0) {
            return false;
        }

        // Se siamo arrivati alla fine della stringa, è valida solo se lo stack si è svuotato perfettamente
        if (index == s.length()) {
            return count == 0;
        }

        // 2. IL PASSO DI LETTURA (Déjà-vu)
        // Se abbiamo già calcolato il futuro per questa coppia [index][count], restituiamo il verdetto pronto
        if (memo[index][count] != null) {
            return memo[index][count];
        }

        char c = s.charAt(index);
        boolean risultato = false;

        // 3. LE STRADE DECISIONALI (La Ricorsione)
        if (c == '(') {
            // Caso Obbligatorio: avanziamo nel tempo (index+1) e nello stack (count+1)
            risultato = esplora(s, index + 1, count + 1, memo);
        } 
        else if (c == ')') {
            // Caso Obbligatorio: avanziamo nel tempo (index+1) e consumiamo una aperta (count-1)
            risultato = esplora(s, index + 1, count - 1, memo);
        } 
        else if (c == '*') {
            // Caso Jolly: l'albero si ramifica nelle 3 vie parallele unite dall'operatore OR (||)
            boolean viaApre   = esplora(s, index + 1, count + 1, memo);
            boolean viaChiude = esplora(s, index + 1, count - 1, memo);
            boolean viaVuota  = esplora(s, index + 1, count, memo);
            
            risultato = viaApre || viaChiude || viaVuota;
        }

        // 4. IL PASSO DI SCRITTURA (Salvataggio sul quaderno)
        // Prima di uscire dalla funzione, registriamo il verdetto finale per questo stato
        memo[index][count] = risultato;

        return risultato;
    }
}