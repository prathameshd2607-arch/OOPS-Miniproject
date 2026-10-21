import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================================");
        System.out.println("         JAVA OOPS MCQ QUIZ APPLICATION          ");
        System.out.println("=================================================");

        System.out.print("Enter candidate/student name: ");
        String playerName = scanner.nextLine().trim();
        if (playerName.isEmpty()) {
            playerName = "Student";
        }

        QuestionLoaderInterface loader = new FileQuestionLoader();
        Question[] questions = null;

        while (questions == null) {
            System.out.println("\n-------------------------------------------------");
            System.out.println("File Upload / Selection:");
            System.out.println("Enter the path of the MCQ questions file.");
            System.out.println("(Press [ENTER] to use default included file: 'questions.txt')");
            System.out.print("File Path: ");
            String filePath = scanner.nextLine().trim();

            if (filePath.isEmpty()) {
                filePath = "questions.txt";
            }

            try {
                questions = loader.loadQuestions(filePath);
                System.out.println(">> Success: Loaded " + questions.length + " total questions from '" + filePath + "'!");
            } catch (FileNotFoundException e) {
                System.out.println(">> FILE NOT FOUND ERROR: " + e.getMessage());
                System.out.println("Please check the path and filename, then try again.");
            } catch (QuizException e) {
                System.out.println(">> QUIZ DATA ERROR: " + e.getMessage());
                System.out.println("Please provide a valid question file.");
            } catch (IOException e) {
                System.out.println(">> I/O ERROR: Unable to read file - " + e.getMessage());
            } catch (Exception e) {
                System.out.println(">> GENERAL ERROR: " + e.getMessage());
            }
        }

        QuizManager quizManager = new QuizManager(questions);
        boolean playAgain = true;

        while (playAgain) {
            String selectedDifficulty = "";
            while (true) {
                System.out.println("\n-------------------------------------------------");
                System.out.println("Select Question Difficulty Level:");
                System.out.println("  1. Easy");
                System.out.println("  2. Medium");
                System.out.println("  3. Hard");
                System.out.print("Enter choice (1, 2, or 3): ");

                String choice = scanner.nextLine().trim();
                if (choice.equals("1")) {
                    selectedDifficulty = "Easy";
                    break;
                } else if (choice.equals("2")) {
                    selectedDifficulty = "Medium";
                    break;
                } else if (choice.equals("3")) {
                    selectedDifficulty = "Hard";
                    break;
                } else {
                    System.out.println(">> Invalid choice! Please select 1, 2, or 3.");
                }
            }

            try {
                quizManager.startQuiz(selectedDifficulty, playerName, scanner);
            } catch (QuizException e) {
                System.out.println(">> ERROR STARTING QUIZ: " + e.getMessage());
            }

            System.out.print("\nWould you like to try another difficulty? (yes/no): ");
            String retry = scanner.nextLine().trim().toLowerCase();
            if (!retry.equals("yes") && !retry.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("\nThank you for taking the quiz, " + playerName + "! Goodbye.");
        scanner.close();
    }
}
