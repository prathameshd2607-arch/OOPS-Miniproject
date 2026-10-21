// OOP Topics: Method Overloading, Interface, Classes
public class ScoreCard implements ScoreEvaluatorInterface {

    // Overloaded Method 1: Basic summary with score out of 5
    @Override
    public void displayScore(int score) {
        System.out.println("\n==========================================");
        System.out.println("               FINAL SCORE                ");
        System.out.println("==========================================");
        System.out.println("Score: " + score + " / 5");
        System.out.println("==========================================");
    }

    // Overloaded Method 2: Detailed summary with score, total, and percentage
    @Override
    public void displayScore(int score, int total) {
        double percentage = ((double) score / total) * 100;
        System.out.println("\n==========================================");
        System.out.println("               FINAL SCORE                ");
        System.out.println("==========================================");
        System.out.println("Score: " + score + " out of " + total);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("==========================================");
    }

    // Overloaded Method 3: Full detailed scorecard with player name, grade, and remarks
    @Override
    public void displayScore(int score, int total, String playerName) {
        double percentage = ((double) score / total) * 100;
        String grade;
        String remarks;

        if (percentage >= 80) {
            grade = "A+";
            remarks = "Excellent mastery of OOP concepts!";
        } else if (percentage >= 60) {
            grade = "B";
            remarks = "Good performance, but review tricky topics.";
        } else if (percentage >= 40) {
            grade = "C";
            remarks = "Fair attempt. Keep practicing!";
        } else {
            grade = "F";
            remarks = "Needs improvement. Review the core concepts.";
        }

        System.out.println("\n==========================================");
        System.out.println("            OFFICIAL SCORECARD            ");
        System.out.println("==========================================");
        System.out.println("Candidate Name : " + playerName);
        System.out.println("Marks Scored   : " + score + " / " + total);
        System.out.printf("Percentage     : %.2f%%\n", percentage);
        System.out.println("Grade          : " + grade);
        System.out.println("Feedback       : " + remarks);
        System.out.println("==========================================");
    }
}
