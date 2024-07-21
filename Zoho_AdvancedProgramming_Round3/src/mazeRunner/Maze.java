package mazeRunner;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    char[][] maze;

    public Maze(int row, int column) {
        this.maze = new char[row][column];
        initializeMaze();
    }

    private void initializeMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = 'O';
            }
        }
    }

    public void printMaze() {
        for (char[] row : maze) {
            for (char column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    public boolean putTreasure(int row, int column) {
        row--;
        column--;
        if (row >= maze.length || column >= maze[0].length) return false;
        maze[row][column] = 'T';
        return true;
    }

    private static class Node {
        int row, column, steps;
        Node previous;

        Node(int row, int column, int steps, Node previous) {
            this.row = row;
            this.column = column;
            this.steps = steps;
            this.previous = previous;
        }
    }

    public boolean putMonster(int row, int column) {
        row--;
        column--;
        if (row >= maze.length || column >= maze[0].length) return false;
        maze[row][column] = 'M';
        return true;
    }

    public void shortestPathPrintMaze(Node node) {
        Node current = node;
        while (current != null) {
            char value = maze[current.row][current.column];
            if (value != 'A' && value != 'T') maze[current.row][current.column] = 'P';
            current = current.previous;
        }
        System.out.println("Maze with path:");
        printMaze();
    }

    public int shortestPath(int row, int column) {
        row--;
        column--;
        if (row >= maze.length || column >= maze[0].length) return -1;
        if (maze[row][column] == 'T') return 0;
        maze[row][column] = 'A';

        int rowLength = maze.length;
        int columnLength = maze[0].length;

        boolean[][] visited = new boolean[rowLength][columnLength];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(row, column, 0, null));
        visited[row][column] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            for (int[] direction : directions) {
                int nextRow = current.row + direction[0];
                int nextColumn = current.column + direction[1];

                if (nextRow >= 0 && nextRow < rowLength && nextColumn >= 0 && nextColumn < columnLength && !visited[nextRow][nextColumn]
                        && maze[nextRow][nextColumn] != 'M'
                ) {
                    if (maze[nextRow][nextColumn] == 'T') {
                        shortestPathPrintMaze(current);
                        return current.steps + 1;
                    }
                    Node nextNode = new Node(nextRow, nextColumn, current.steps + 1, current);
                    queue.add(nextNode);
                    visited[nextRow][nextColumn] = true;
//                    maze[nextRow][nextColumn] = 'A';
//                    printMaze();
//                    System.out.println("---------------------------------");
                }
            }
        }
        return -1;
    }
}
