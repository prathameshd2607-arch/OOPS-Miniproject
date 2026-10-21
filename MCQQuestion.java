// OOP Topic: Inheritance & Classes
public class MCQQuestion extends Question {
    // Array of 4 options
    private String[] options;

    // Constructor using super() to call abstract parent class constructor
    public MCQQuestion(String questionText, String[] options, int correctOption, String difficulty) {
        super(questionText, difficulty, correctOption);
        this.options = options;
    }

    // Implementing the abstract method from Question class
    @Override
    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + ". " + options[i]);
        }
    }

    // Getter for options array
    public String[] getOptions() {
        return options;
    }
}
