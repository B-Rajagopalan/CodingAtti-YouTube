package mazeRunner;

import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    char[][] maze; // 2D array representing the maze
    int[][] triggers; // Array of trigger positions

    // Constructor to initialize the maze with given dimensions and triggers
    public Maze(int row, int column, int[][] triggers) {
        this.maze = new char[row][column];
        this.triggers = triggers;
        initializeMaze();
    }

    // Method to initialize the maze with 'O' in all positions
    private void initializeMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j] = 'O';
            }
        }
    }

    // Method to print the current state of the maze
    public void printMaze() {
        for (char[] row : maze) {
            for (char column : row) {
                System.out.print(column + " ");
            }
            System.out.println();
        }
    }

    // Method to place a treasure ('T') at the given position (1-based index)
    public boolean putTreasure(int row, int column) {
        row--; // Convert to 0-based index
        column--;
        if (row >= maze.length || column >= maze[0].length) return false; // Check for out-of-bounds
        maze[row][column] = 'T';
        return true;
    }

    // Inner class representing a node in the maze for pathfinding
    private static class Node {
        int row, column, steps; // Position and steps taken to reach this node
        Node previous; // Previous node in the path

        Node(int row, int column, int steps, Node previous) {
            this.row = row;
            this.column = column;
            this.steps = steps;
            this.previous = previous;
        }
    }

    // Method to place a monster ('M') at the given position (1-based index)
    public boolean putMonster(int row, int column) {
        row--; // Convert to 0-based index
        column--;
        if (row >= maze.length || column >= maze[0].length) return false; // Check for out-of-bounds
        maze[row][column] = 'M';
        return true;
    }

    // Method to print the maze with the shortest path marked
    public void shortestPathPrintMaze(Node node) {
        Node current = node;
        while (current != null) {
            char value = maze[current.row][current.column];
            if (value != 'A' && value != 'T') maze[current.row][current.column] = 'P'; // Mark the path with 'P'
            current = current.previous; // Move to the previous node in the path
        }
        System.out.println("Maze with path:");
        printMaze(); // Print the maze with the path
    }

    // Method to check if a position is a trigger
    public boolean isTrigger(int row, int column) {
        for (int[] trigger : triggers) {
            if (trigger[0] - 1 == row && trigger[1] - 1 == column)
                return true; // Check if the position matches any trigger
        }
        return false;
    }

    // Method to find the shortest path from the given position (1-based index) to the treasure
    public int shortestPath(int row, int column) {
        row--; // Convert to 0-based index
        column--;
        if (row >= maze.length || column >= maze[0].length) return -1; // Check for out of bounds
        if (maze[row][column] == 'T') return 0; // If the starting position is the treasure
        maze[row][column] = 'A'; // Mark the starting position as visited

        int rowLength = maze.length;
        int columnLength = maze[0].length;

        boolean[][] visited = new boolean[rowLength][columnLength]; // Track visited positions
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Possible movements: down, up, right, left
        Queue<Node> queue = new LinkedList<>(); // Queue for BFS
        queue.add(new Node(row, column, 0, null)); // Add the starting position to the queue
        visited[row][column] = true; // Mark the starting position as visited

        while (!queue.isEmpty()) {
            Node current = queue.poll(); // Get the next node from the queue
            for (int[] direction : directions) {
                int nextRow = current.row + direction[0];
                int nextColumn = current.column + direction[1];

                // Check if the next position is within bounds and not visited
                if (nextRow >= 0 && nextRow < rowLength && nextColumn >= 0 && nextColumn < columnLength && !visited[nextRow][nextColumn] &&
                        // Monster and trigger check
                        ((maze[nextRow][nextColumn] == 'M' && isTrigger(nextRow, nextColumn)) || maze[nextRow][nextColumn] != 'M')) {
                    if (maze[nextRow][nextColumn] == 'T') { // If the treasure is found
                        shortestPathPrintMaze(current); // Print the path
                        return current.steps + 1; // Return the number of steps to the treasure
                    }
                    Node nextNode = new Node(nextRow, nextColumn, current.steps + 1, current); // Create a new node for the next position
                    queue.add(nextNode); // Add the new node to the queue
                    visited[nextRow][nextColumn] = true; // Mark the new position as visited
                }
            }
        }
        return -1; // Return -1 if the treasure is not reachable
    }
}
