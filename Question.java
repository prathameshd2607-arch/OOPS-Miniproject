public abstract class Question {
    protected String questionText;
    protected String difficulty;
    protected int correctOption; 

    public Question(String questionText, String difficulty, int correctOption) {
        this.questionText = questionText;
        this.difficulty = difficulty;
        this.correctOption = correctOption;
    }
    public abstract void displayQuestion();
    public String getQuestionText() {
        return questionText;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getCorrectOption() {
        return correctOption;
    }
    public boolean checkAnswer(int selectedOption) {
        return selectedOption == this.correctOption;
    }
    public boolean checkAnswer(String selectedOptionStr) {
        try {
            int selected = Integer.parseInt(selectedOptionStr.trim());
            return checkAnswer(selected);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
