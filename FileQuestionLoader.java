import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileQuestionLoader implements QuestionLoaderInterface {

    @Override
    public Question[] loadQuestions(String filePath) throws QuizException, IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("File not found at path: " + filePath);
        }
        int lineCount = 0;
        BufferedReader countReader = null;
        try {

            countReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = countReader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lineCount++;
                }
            }
        } finally {
            
            if (countReader != null) {
                countReader.close();
            }
        }

        if (lineCount == 0) {
            throw new QuizException("The questions file is empty: " + filePath);
        }

        Question[] questionList = new MCQQuestion[lineCount];
        int index = 0;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();
                if (line.isEmpty()) {
                    continue; // Skip empty lines
                }

                String[] parts = line.split("\\|");
                if (parts.length != 7) {
                    throw new QuizException("Format error at line " + lineNumber 
                        + ": Expected 7 fields separated by '|', found " + parts.length);
                }

                String difficulty = parts[0].trim();
                String questionText = parts[1].trim();
                String[] options = new String[] {
                    parts[2].trim(),
                    parts[3].trim(),
                    parts[4].trim(),
                    parts[5].trim()
                };

                int correctOption;
                try {
                    correctOption = Integer.parseInt(parts[6].trim());
                    if (correctOption < 1 || correctOption > 4) {
                        throw new QuizException("Line " + lineNumber + ": Correct option must be between 1 and 4.");
                    }
                } catch (NumberFormatException e) {
                    throw new QuizException("Line " + lineNumber + ": Invalid number for correct option -> " + parts[6]);
                }

                questionList[index] = new MCQQuestion(questionText, options, correctOption, difficulty);
                index++;
            }
        } finally {
          
            if (reader != null) {
                reader.close();
            }
        }

        return questionList;
    }
}
