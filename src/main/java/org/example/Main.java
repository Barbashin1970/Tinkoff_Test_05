package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // количество городов
        int m = scanner.nextInt(); // количество дорог
        int[][] graph = new int[n][n]; // граф n x n
        int maxDist = 0; // максимальный путь
        for (int i = 0; i < m; i++) {
            int v = scanner.nextInt() - 1;
            int u = scanner.nextInt() - 1;
            int w = scanner.nextInt();
            graph[v][u] = w;
            graph[u][v] = w;
            maxDist = Math.max(maxDist, w); // обновляем максимальный путь
        }

        int l = 0;
        int r = maxDist;

        while (l < r) {
            int mid = (l + r + 1) / 2; // выбираем середину
            int[] visited = new int[n];
            int cnt = 0;

            // запускаем DFS для каждой непосещенной вершины
            for (int i = 0; i < n; i++) {
                if (visited[i] == 0) {
                    dfs(graph, visited, i, mid);
                    cnt++;
                }
            }

            // если количество компонент связности равно исходному количеству штатов
            // можно уничтожить дороги длиной не более x
            if (cnt == 1) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(l);
    }

    // рекурсивный DFS
    public static void dfs(int[][] graph, int[] visited, int v, int x) {
        visited[v] = 1;

        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] <= x && visited[i] == 0) {
                dfs(graph, visited, i, x);
            }
        }
    }
}
