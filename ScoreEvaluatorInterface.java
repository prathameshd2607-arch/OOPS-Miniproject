// OOP Topic: Interface & Method Overloading declaration
public interface ScoreEvaluatorInterface {
    // Overloaded Method 1: Only score
    void displayScore(int score);

    // Overloaded Method 2: Score and total questions
    void displayScore(int score, int total);

    // Overloaded Method 3: Score, total questions, and player name
    void displayScore(int score, int total, String playerName);
}
