package training.leetcode.algorithm.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution210 {
    /*
     There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array
     prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

     For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

     Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of
     them. If it is impossible to finish all courses, return an empty array.

     Explanation

        Graph Construction: We build a graph where each node (course) points to the courses that require it as a prerequisite.
         This helps in traversing dependencies.

        In-Degree Array: The in-degree array keeps track of the number of prerequisites each course has. Courses with an
        in-degree of zero can be taken immediately.

        Queue Processing: We start by enqueueing all courses with no prerequisites. For each course processed, we reduce
        the in-degree of its dependents. If any dependent's in-degree becomes zero, it is enqueued.

        Result Validation: After processing, if all courses are included in the result, we return the result. Otherwise, a
        cycle is detected, and we return an empty array.

        This approach efficiently checks for feasible course schedules using topological sorting, ensuring optimal performance
        with a time complexity of O(V + E), where V is the number of courses and E is the number of prerequisites.
        The space complexity is O(V + E) to store the graph and in-degrees.

        Example 1:

        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: [0,1]
        Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

        Example 2:

        Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
        Output: [0,2,1,3]
        Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
        So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

        Example 3:

        Input: numCourses = 1, prerequisites = []
        Output: [0]

     */

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        int[] result = new int[numCourses];

        for (int[] pre : prerequisites) {
            int target = pre[0], requirement = pre[1];
            graph.get(requirement).add(target);
            inDegree[target]++;
        }
        for (int course = 0; course < numCourses; course++) {
            if (inDegree[course] == 0) {
                queue.offer(course);
            }
        }

        int index = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[index++] = course;

            for (int neighbor : graph.get(course)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        if (index == numCourses) {
            return result;
        } else {
            return new int[0];
        }
    }


    private static int n = 0;

    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        n = numCourses;
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[1]).add(prerequisite[0]);
        }
        int[] res = new int[numCourses];
        int[] visited = new int[numCourses];
        int[]  visited2 = new int[numCourses];
        for (int v = 0; v < numCourses; v++) {
            if (visited2[v] == 0 && dfs(graph, v, visited, visited2, res)) {
                return new int[0];
            }
        }
        return n == 0 ? res : new int[0];
    }

    private boolean dfs(List<List<Integer>> graph, int v, int[] visited, int[] visited2, int[] res) {
        visited[v] = 1;
        visited2[v] = 1;
        for (Integer ch : graph.get(v)) {
            if (visited2[ch] == 0 && dfs(graph, ch, visited, visited2, res)) {
                return true;
            } else if (visited[ch] == 1) {
                return true;
            }
        }
        res[--n] = v;
        visited[v] = 0;
        return false;
    }
}
