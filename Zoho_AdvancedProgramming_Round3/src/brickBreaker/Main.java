package brickBreaker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BrickBreaker brickBreaker = new BrickBreaker(7,7);
        brickBreaker.placeBricks(2,2,2);
        brickBreaker.placeBricks(2,3,2);
        brickBreaker.placeBricks(2,4,2);
        brickBreaker.placeBricks(3,2,2);
        brickBreaker.placeBricks(3,3,2);
        brickBreaker.placeBricks(3,4,2);


        while(true) {
            brickBreaker.printGameBoard();

            if(brickBreaker.getBallLife() <= 0) {
                System.out.println("Ball life over");
                System.exit(0);
            }

            System.out.println("Enter the direction : ");
            String direction = new Scanner(System.in).next();

            switch(direction) {
                case "lt" -> {
                    int[] ballPos = brickBreaker.getBallPosition();
                    brickBreaker.initiateBall(ballPos[0],ballPos[1],-1,-1);
                }
                case "rt" -> {
                    int[] ballPos = brickBreaker.getBallPosition();
                    brickBreaker.initiateBall(ballPos[0],ballPos[1],-1,1);
                }
                case "st" -> {
                    int[] ballPos = brickBreaker.getBallPosition();
                    brickBreaker.initiateBall(ballPos[0],ballPos[1],-1,0);
                }
                default -> {
                    System.out.println("Choose valid direction");
                }
            }
        }
    }
}