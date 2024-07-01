package snakeGame;

public class Main {
    public static void main(String[] args) {
        /* Recursive approach
        Snake snake = new Snake(6,6);
        snake.snakeMove(0,0); */

        //Iterative approach
        SnakeIterative snakeIterative = new SnakeIterative(6,6);
        snakeIterative.initiateSnake();
    }
}

/* CODE UPDATE IN SNAKE CLASS (After posting the video)
New Scenario : -
---> We have added a scenario where interviewer might ask to place the food (X) in each position dynamically.
---> It means that after the Snake eats the first food, we must display the second food
---> This is similar to the actual nokia snake game where new food displays after eating the current food
---> We have used Queue data structure named 'food' to store the food positions.
---> Each time when snake eats the food (X), we are taking the next position from the queue
and displaying the new food (X) in appropriate position
---> If there is no food remaining in the queue, Game is over

Code Improvements: -
---> Added a new method 'moveForwardAndPrint' for repeatable codes
---> 'while(!queue.isEmpty())' changed to 'if(!queue.isEmpty())' since we are using recursion, not iteration
(Line no : 67 in Snake class)
---> Added iterative approach in SnakeIterative class (Better than recursion in memory management)

Fixed Bugs: -
Snake bites itself condition repositioned : -
---> The condition checking 'if snake bites itself' is repositioned
---> It is now placed after the removal of tail
---> To understand this, let us imagine the nostalgic nokia snake game
---> Imagine a scenario where snake rounds itself. It has its tail right before its head
---> If snake attempts to move forward, the tail moves first and then head moves to that position
---> Same logic applied here. Removal of tail happens first and then head is placed to that position
---> If we check 'if snake bites itself' before even removing the tail, then it will be true and Game will be over
---> This is a potential bug found after posting the video. You can reach me on LinkedIn for further clarifications
 */