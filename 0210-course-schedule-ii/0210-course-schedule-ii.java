class Solution {
public int[] findOrder(int numCourses, int[][] prerequisites) {
    List<List<Integer>> graph = new ArrayList<>();
    int[] inDegree = new int[numCourses];

    for (int i = 0; i < numCourses; i++) {
        graph.add(new ArrayList<>());
    }

    for (int[] pair : prerequisites) {
        int course = pair[0];
        int prerequisite = pair[1];

        graph.get(prerequisite).add(course); // prerequisito -> corso
        inDegree[course]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < numCourses; i++) {
        if (inDegree[i] == 0) {
            queue.add(i);
        }
    }

    int[] order = new int[numCourses];
    int index = 0;

    while (!queue.isEmpty()) {
        int course = queue.poll();
        order[index++] = course; // qui costruisci l'ordinamento

        for (int next : graph.get(course)) {
            inDegree[next]--;
            if (inDegree[next] == 0) {
                queue.add(next);
            }
        }
    }

    // Se non hai processato tutti i corsi, c'è un ciclo
    return index == numCourses ? order : new int[0];
}
}