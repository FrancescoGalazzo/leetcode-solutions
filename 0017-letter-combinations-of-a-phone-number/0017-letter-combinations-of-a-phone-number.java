class Solution {

 private static final String[] TASTIERA = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };
    // La nostra tastiera del telefono fissa (Mappatura indici -> lettere)
   

    public List<String> letterCombinations(String digits) {
        List<String> risultato = new ArrayList<>();
        
        // Caso limite: se l'input è vuoto, restituiamo subito una lista vuota
        if (digits == null || digits.isEmpty()) {
            return risultato;
        }
        
        // Facciamo partire il viaggio dallo slot/indice 0
        backtrack(digits, 0, new StringBuilder(), risultato);
        return risultato;
    }

    private void backtrack(String digits, int indice, StringBuilder percorsoAttuale, List<String> risultato) {
        // 1. IL TRAGUARDO (Caso Base)
        // Se l'indice ha raggiunto la lunghezza delle cifre, la combinazione è completa!
        if (indice == digits.length()) {
            risultato.add(percorsoAttuale.toString());
            return;
        }

        // Recuperiamo il carattere numerico corrente (es. '2')
        char cifraChar = digits.charAt(indice);
        // Lo convertiamo in un numero intero sottraendo il codice ASCII di '0' (es. '2' - '0' = 2)
        int numeroTasto = cifraChar - '0';
        
        // Otteniamo la stringa di lettere corrispondente a quel tasto (es. 2 -> "abc")
        String lettereDisponibili = TASTIERA[numeroTasto];

        // 2. LE OPZIONI DISPONIBILI
        // Questo ciclo for riparte da i = 0 per ogni livello ricorsivo
        for (int i = 0; i < lettereDisponibili.length(); i++) {
            char letteraScelta = lettereDisponibili.charAt(i);

            // 3. L'AZIONE (Mettiamo la lettera nello zaino)
            percorsoAttuale.append(letteraScelta);

            // 4. IL TUFFO (Avanziamo rigidamente di 1 casella/slot)
            backtrack(digits, indice + 1, percorsoAttuale, risultato);

            // 5. IL RIPRISTINO (Backtracking: ripuliamo lo zaino)
            percorsoAttuale.deleteCharAt(percorsoAttuale.length() - 1);
        }
    
    }
}