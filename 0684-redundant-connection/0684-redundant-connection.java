class Solution {

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        int[] parent = new int[n + 1];

        // All'inizio ogni nodo è un gruppo separato.
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            // Se u e v hanno la stessa radice,
            // l'arco corrente crea un ciclo.
            if (find(parent, u) == find(parent, v)) {
                return edge;
            }

            union(parent, u, v);
        }

        return new int[0];
    }

    private int find(int[] parent, int node) {
        // Risale fino alla radice del gruppo.
        while (parent[node] != node) {
            node = parent[node];
        }

        return node;
    }

    private void union(int[] parent, int u, int v) {
        int rootU = find(parent, u);
        int rootV = find(parent, v);

        // Collega il gruppo di v al gruppo di u.
        parent[rootV] = rootU;
    }
}

