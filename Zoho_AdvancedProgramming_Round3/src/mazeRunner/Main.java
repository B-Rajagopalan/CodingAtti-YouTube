package mazeRunner;

public class Main {
    public static void main(String[] args) {
        Maze maze = new Maze(6, 6);
        maze.putTreasure(2, 4);
        int shortestPath = maze.shortestPath(5, 1);
        maze.printMaze();
        System.out.println("The shortest path is : " + shortestPath);
    }
}
