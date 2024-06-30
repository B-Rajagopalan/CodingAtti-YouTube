package snakeGame;

public class Main {
    public static void main(String[] args) {
        Snake snake = new Snake(6,6);
        snake.snakeMove(0,0);
    }
}

/* CODE UPDATE
---> We have added a scenario where interviewer might ask to place the food (X) in each position dynamically.
---> It means that after the Snake eats the first food, we must display the second food
---> This is similar to the actual nokia snake game where new food displays after eating the current food
---> We have used Queue data structure named 'food' to store the food positions.
---> Each time when snake eats the food (X), we are taking the next position from the queue
and displaying the new food (X) in appropriate position
 */