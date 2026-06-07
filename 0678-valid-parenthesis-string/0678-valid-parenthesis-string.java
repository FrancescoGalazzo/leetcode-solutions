class Solution {

   public boolean checkValidString(String s) {
    int min = 0, max = 0;

    for (char c : s.toCharArray()) {
        if (c == '(') {
            min++;
            max++;
        } else if (c == ')') {
            min--;
            max--;
        } else { // '*'
            min--; // * trattato come ')'
            max++; // * trattato come '('
        }

        if (max < 0) return false; // troppi ')' anche nel caso migliore
        min = Math.max(min, 0);    // count negativo è impossibile
    }

    return min == 0; // 0 è raggiungibile?
}
}
