package snakeGame;

public class Node {
    private int row = 0;
    private int column = 0;

    Node(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
