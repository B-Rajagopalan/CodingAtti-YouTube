package brickBreaker;

import java.util.HashMap;
import java.util.Map;

public class BrickBreaker {
    private static String wall =  "w";
    private static String brick =  "1";
    private static String ground =  "g";
    private static String ball = "o";

    private static Map<Integer, Integer> bricksWithLife = new HashMap<>();
    private static int[] ballPos = null;
    private static int ballLife = 5;

    private String[][] gameBoard = null;

    BrickBreaker(int row, int col) {
        gameBoard = new String[row][col];
        prepareBoard();
        // ball placement
        gameBoard[row-1][(col/2)] = ball; // bottom center
        ballPos = new int[]{row-1, col/2};
    }

    public void placeBricks(int row, int col, int life) {
        gameBoard[row][col] = brick;
        int exactPosition = getExactBallPosition(row, col); // returns exact position
        bricksWithLife.put(exactPosition, life);
    }

    public void initiateBall(int ballRow, int ballCol, int rowDirection, int colDirection) {
        moveDirection(ballRow, ballCol, rowDirection, colDirection);
        // show ground if ball pos changed
        if(!gameBoard[ballRow][ballCol].equals(ball)) gameBoard[ballRow][ballCol] = ground;
    }

    private void moveDirection(int ballRow, int ballCol, int rowDirection, int colDirection) {
        while(!gameBoard[ballRow][ballCol].equals(wall)) {
            if(gameBoard[ballRow][ballCol].equals(brick)) {
                ballGoesDown(ballRow, ballCol);
                return;
            }
            movingIllusion(ballRow,ballCol); // move the ball
            ballCol += colDirection;
            ballRow += rowDirection;
        }

        wallHit(ballRow, ballCol); // show ball in the place of wall

        // if touched wall, change the col direction(opposite) and row is 0 (ball needs to move in same row)
        rowDirection = 0;
        colDirection = colDirection*-1;

        if(colDirection == 0)     // means ball is in straight line. There is no possible of bricks in st line
            ballGoesDown(ballRow+1, ballCol);
        else
            moveDirection(ballRow, ballCol + colDirection, rowDirection, colDirection);
    }

    private int getExactBallPosition(int row, int col) {
        return (row * gameBoard[0].length) + col + 1;

        /*  Exact position calculation. Ex: 10th position, 26th position etc.
            1 2 3 4 5 6 7
            8 9 10......
            ..............
         */
    }

    private void wallHit(int ballRow, int ballCol) {
        gameBoard[ballRow][ballCol] = ball;
        printGameBoard();
        sleepForOneSec();
        gameBoard[ballRow][ballCol] = wall;
    }

    private void ballGoesDown(int ballRow, int ballCol) {
        while(ballRow != gameBoard.length) {
            movingIllusion(ballRow, ballCol); // move the ball
            ballRow++;
        }
        ballPos = new int[]{ballRow-1, ballCol};
        gameBoard[ballPos[0]][ballPos[1]] = ball; // new ball position
    }

    private void movingIllusion(int ballRow, int ballCol) {
        if(gameBoard[ballRow][ballCol].equals(brick)) {
            reduceBrickAndBallLife(ballRow, ballCol);
            if(bricksWithLife.get(getExactBallPosition(ballRow, ballCol)) == 0) {
                gameBoard[ballRow][ballCol] = " ";
            }
        }
        else {
            gameBoard[ballRow][ballCol] = ball;
            printGameBoard();
            gameBoard[ballRow][ballCol] = " ";
            sleepForOneSec();
        }
    }

    private void reduceBrickAndBallLife(int ballRow, int ballCol) {
        int exactPosition = getExactBallPosition(ballRow, ballCol);
        ballLife--;  // if ball hits brick, ball's life reduces

        if(ballLife >= 0) // if ball life is not negative
            bricksWithLife.put(exactPosition, bricksWithLife.get(exactPosition) - 1);
    }

    private void prepareBoard() {
        for(int row=0;row<gameBoard.length;row++) {
            for(int col=0;col< gameBoard[0].length;col++) {
                if(row == 0 || col == 0 || col == gameBoard[0].length-1) {
                    gameBoard[row][col] = wall;
                }
                else if(row == gameBoard.length-1) {
                    gameBoard[row][col] = ground;
                }
                else {
                    gameBoard[row][col] = " ";
                }
            }
        }
    }

    public void printGameBoard() {
        for(String[] str : gameBoard) {
            for(String s : str) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }

    private void sleepForOneSec() {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.getCause();
        }
    }

    public int[] getBallPosition() {
        return ballPos;
    }

    public int getBallLife() {
        return ballLife;
    }
}
