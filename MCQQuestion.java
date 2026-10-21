public class MCQQuestion extends Question {
    private String[] options;

    public MCQQuestion(String questionText, String[] options, int correctOption, String difficulty) {
        super(questionText, difficulty, correctOption);
        this.options = options;
    }
    @Override
    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + ". " + options[i]);
        }
    }
    public String[] getOptions() {
        return options;
    }
}
