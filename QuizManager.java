// OOP Topics: Array of Objects, Exception Handling, Classes
import java.util.Scanner;

public class QuizManager {
    // Array of Objects holding all loaded questions
    private Question[] allQuestions;
    private ScoreCard scoreCard;

    public QuizManager(Question[] allQuestions) {
        this.allQuestions = allQuestions;
        this.scoreCard = new ScoreCard();
    }

    // Filters questions by selected difficulty into a new Array of 5 Objects
    public Question[] getQuestionsByDifficulty(String difficulty) throws QuizException {
        // OOP Topic: Array of Objects (Set of exactly 5 questions)
        Question[] selectedQuestions = new Question[5];
        int count = 0;

        for (int i = 0; i < allQuestions.length; i++) {
            if (allQuestions[i] != null && allQuestions[i].getDifficulty().equalsIgnoreCase(difficulty)) {
                selectedQuestions[count] = allQuestions[i];
                count++;
                if (count == 5) {
                    break; // Picked 5 questions
                }
            }
        }

        // Exception Handling: Verify at least 5 questions were found
        if (count < 5) {
            throw new QuizException("Found only " + count + " question(s) for difficulty '" 
                + difficulty + "'. A minimum of 5 questions is required to start the quiz!");
        }

        return selectedQuestions;
    }

    // Runs the 5-question quiz session
    public void startQuiz(String difficulty, String playerName, Scanner scanner) throws QuizException {
        // Getting Array of 5 Question Objects
        Question[] quizQuestions = getQuestionsByDifficulty(difficulty);
        int score = 0;

        System.out.println("\n==========================================");
        System.out.println(" Starting 5-Question Quiz: [" + difficulty.toUpperCase() + " Level]");
        System.out.println("==========================================");

        // Iterating over the Array of Objects
        for (int i = 0; i < quizQuestions.length; i++) {
            System.out.println("\n------------------------------------------");
            System.out.println("Question " + (i + 1) + " of " + quizQuestions.length + ":");
            quizQuestions[i].displayQuestion();

            // Exception Handling inside user input validator
            int userChoice = getValidUserChoice(scanner);

            // Checking answer
            if (quizQuestions[i].checkAnswer(userChoice)) {
                System.out.println(">> Correct Answer! (+1 mark)");
                score++;
            } else {
                System.out.println(">> Incorrect! The correct answer was Option " 
                    + quizQuestions[i].getCorrectOption());
            }
        }

        // Displaying final result using Method Overloading
        // Method Overloading demonstration:
        // scoreCard.displayScore(score); // Overload 1
        // scoreCard.displayScore(score, quizQuestions.length); // Overload 2
        scoreCard.displayScore(score, quizQuestions.length, playerName); // Overload 3 (Full scorecard)
    }

    // Helper method to safely read and validate user's MCQ choice with Exception Handling
    private int getValidUserChoice(Scanner scanner) {
        while (true) {
            System.out.print("Enter your answer (1-4): ");
            String input = scanner.nextLine().trim();

            try {
                // Exception Handling: parsing user input
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println(">> Error: Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println(">> Error: Invalid input! Only numbers (1, 2, 3, or 4) are allowed.");
            }
        }
    }
}
