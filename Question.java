// OOP Topic: Abstract Class & Classes & Method Overloading
public abstract class Question {
    protected String questionText;
    protected String difficulty;
    protected int correctOption; // 1, 2, 3, or 4

    // Constructor
    public Question(String questionText, String difficulty, int correctOption) {
        this.questionText = questionText;
        this.difficulty = difficulty;
        this.correctOption = correctOption;
    }

    // Abstract method: Must be implemented by child classes
    public abstract void displayQuestion();

    // Concrete Getter Methods
    public String getQuestionText() {
        return questionText;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    // Method Overloading 1: checkAnswer using integer input
    public boolean checkAnswer(int selectedOption) {
        return selectedOption == this.correctOption;
    }

    // Method Overloading 2: checkAnswer using String input
    public boolean checkAnswer(String selectedOptionStr) {
        try {
            int selected = Integer.parseInt(selectedOptionStr.trim());
            return checkAnswer(selected);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
