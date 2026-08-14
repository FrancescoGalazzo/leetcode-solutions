import java.util.ArrayList;
import java.util.List;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // graph[x] = lista dei prerequisiti del corso x
        List<List<Integer>> graph = new ArrayList<>();

        // Crea numCourses liste vuote: una per ogni corso
        for (int course = 0; course < numCourses; course++) {
            graph.add(new ArrayList<>());
        }

        // [a, b] significa: a richiede b
        // Quindi memorizziamo l'arco a -> b
        for (int[] pair : prerequisites) {
            int course = pair[0];       // a
            int prerequisite = pair[1]; // b

            graph.get(course).add(prerequisite);
        }

        /*
         * state[i]:
         * 0 = corso mai visitato
         * 1 = corso nel percorso DFS corrente
         * 2 = corso completamente controllato, senza cicli
         */
        int[] state = new int[numCourses];

        // Il grafo può avere più componenti scollegate:
        // controlliamo ogni corso.
        for (int course = 0; course < numCourses; course++) {
            if (!dfs(course, graph, state)) {
                return false; // ciclo trovato
            }
        }

        return true; // nessun ciclo
    }

    private boolean dfs(int course, List<List<Integer>> graph, int[] state) {
        // Il corso è già nel percorso ricorsivo corrente:
        // siamo tornati indietro -> ciclo.
        if (state[course] == 1) {
            return false;
        }

        // Questo corso e tutti i suoi prerequisiti erano già validi.
        if (state[course] == 2) {
            return true;
        }

        // Inizio a verificare questo corso.
        state[course] = 1;

        // Visita tutti i prerequisiti del corso corrente.
        for (int prerequisite : graph.get(course)) {
            if (!dfs(prerequisite, graph, state)) {
                return false;
            }
        }

        // Tutti i prerequisiti sono stati verificati senza cicli.
        state[course] = 2;

        return true;
    }
}