package snakeGame;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// CODE UPDATED. CHECK MAIN CLASS COMMENT FOR MORE DETAILS
public class Snake {
    private char[][] snakeBoard = null;
    private Queue<Node> queue = new LinkedList<Node>();
    private Queue<Node> food = new LinkedList<Node>();  // to store the food positions

    Snake(int row, int col) {
        snakeBoard = new char[row][col];
        queue.add(new Node(0,0));
        /* Old code where we place all the food at the same time
        this.snakeBoard[1][0] = 'X';
        this.snakeBoard[2][2] = 'X';
        this.snakeBoard[3][4] = 'X';
        this.snakeBoard[5][2] = 'X';  */

        // Positions of food (X) to display
        food.add(new Node(1,0));
        food.add(new Node(2,2));
        food.add(new Node(3,4));
        food.add(new Node(5,2));
        food.add(new Node(4,5));
        // Display first food (X)
        displayFood(food.poll());
    }

    public void snakeMove(int row, int col) {
        if(row >= 0 && row < snakeBoard.length && col >=0 && col < snakeBoard.length) {
            /* Snake bites itself (Code repositioned)
            if(snakeBoard[row][col] == '.') {
                System.out.println("Game Over!!!");
                System.exit(0);
            } */
            queue.add(new Node(row, col));
            // Remove the tail
            if(snakeBoard[row][col] != 'X') {
                Node node = queue.poll();
                int r = node.getRow();
                int c = node.getColumn();
                snakeBoard[r][c] = '\0';
            }

            // if current position contains food (X), display next food
            if(snakeBoard[row][col] == 'X') {
                // If there is no food remaining to display. Game over
                if(food.isEmpty()) {
                    moveForwardAndPrint(row, col);
                    System.out.println("Game Over!!!");
                    System.exit(0);
                }
                displayFood(food.poll());
            }

            // Snake bites itself
            if(snakeBoard[row][col] == '.') {
                System.out.println("Game Over!!!");
                System.exit(0);
            }
            // Placing '.' and printing the snakeBoard combined in a single method
            moveForwardAndPrint(row, col);

            if(!queue.isEmpty()) {
                System.out.print("Enter a position : ");
                Scanner s = new Scanner(System.in);
                char direction = s.next().charAt(0);

                if (direction == 'U') {
                    snakeMove(--row, col);
                }
                if (direction == 'D') {
                    snakeMove(++row, col);
                }
                if (direction == 'R') {
                    snakeMove(row, ++col);
                }
                if (direction == 'L') {
                    snakeMove(row, --col);
                }
            }
        } else {
            System.out.println("Invalid move");
            System.exit(0);
        }
    }

    // Displays new food in appropriate position
    public void displayFood(Node node) {
            int r = node.getRow();
            int c = node.getColumn();
            snakeBoard[r][c] = 'X';
    }

    public void moveForwardAndPrint(int row, int col) {
        snakeBoard[row][col] = '.';
        printSnake();
    }

    public void printSnake() {
        for (char[] chars : snakeBoard) {
            for (int j = 0; j < snakeBoard[0].length; j++) {
                System.out.print(chars[j] + " ");
            }
            System.out.println();
        }
    }
}
