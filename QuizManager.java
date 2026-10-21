import java.util.Scanner;
public class QuizManager {
    private Question[] allQuestions;
    private ScoreCard scoreCard;

    public QuizManager(Question[] allQuestions) {
        this.allQuestions = allQuestions;
        this.scoreCard = new ScoreCard();
    }
    public Question[] getQuestionsByDifficulty(String difficulty) throws QuizException {
        Question[] selectedQuestions = new Question[5];
        int count = 0;

        for (int i = 0; i < allQuestions.length; i++) {
            if (allQuestions[i] != null && allQuestions[i].getDifficulty().equalsIgnoreCase(difficulty)) {
                selectedQuestions[count] = allQuestions[i];
                count++;
                if (count == 5) {
                    break; 
                }
            }
        }
        if (count < 5) {
            throw new QuizException("Found only " + count + " question(s) for difficulty '" 
                + difficulty + "'. A minimum of 5 questions is required to start the quiz!");
        }
        return selectedQuestions;
    }
    public void startQuiz(String difficulty, String playerName, Scanner scanner) throws QuizException {
        Question[] quizQuestions = getQuestionsByDifficulty(difficulty);
        int score = 0;

        System.out.println("\n==========================================");
        System.out.println(" Starting 5-Question Quiz: [" + difficulty.toUpperCase() + " Level]");
        System.out.println("==========================================");
        for (int i = 0; i < quizQuestions.length; i++) {
            System.out.println("\n------------------------------------------");
            System.out.println("Question " + (i + 1) + " of " + quizQuestions.length + ":");
            quizQuestions[i].displayQuestion();
            int userChoice = getValidUserChoice(scanner);

            if (quizQuestions[i].checkAnswer(userChoice)) {
                System.out.println(">> Correct Answer! (+1 mark)");
                score++;
            } else {
                System.out.println(">> Incorrect! The correct answer was Option " 
                    + quizQuestions[i].getCorrectOption());
            }
        }
        scoreCard.displayScore(score, quizQuestions.length, playerName);
    private int getValidUserChoice(Scanner scanner) {
        while (true) {
            System.out.print("Enter your answer (1-4): ");
            String input = scanner.nextLine().trim();

            try {
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
