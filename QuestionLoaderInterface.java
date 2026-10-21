// OOP Topic: Interface
public interface QuestionLoaderInterface {
    // Contract method to load questions from a file into an Array of Objects
    Question[] loadQuestions(String filePath) throws Exception;
}
