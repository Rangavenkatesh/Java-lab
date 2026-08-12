import java.util.Scanner;
public class SentenceAnalyzer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            String sentence = scanner.nextLine();
            analyzeSentence(sentence, 'J');
        }
    }
    public static void analyzeSentence(String sentence, char targetChar) {
        String[] words = sentence.trim().split("\\s+");
        int wordCount = words.length;
        int firstSpaceIndex = sentence.indexOf(" ");
        int lastSpaceIndex = sentence.lastIndexOf(" ");
        String firstWord = (firstSpaceIndex != -1) ? sentence.substring(0, firstSpaceIndex) : sentence;
        String lastWord = (lastSpaceIndex != -1) ? sentence.substring(lastSpaceIndex + 1) : sentence;
        int firstCharIndex = sentence.indexOf(targetChar);
        int lastCharIndex = sentence.lastIndexOf(targetChar);
        int comparisonResult = firstWord.compareTo(lastWord);
        char[] charArray = sentence.toCharArray();
        char firstChar = sentence.charAt(0);
        int asciiValue = (int) firstChar;
        String asciiString = String.valueOf(asciiValue);
        System.out.println("Word Count: " + wordCount);
        System.out.println("First Word: " + firstWord);
        System.out.println("Last Word: " + lastWord);
        System.out.println("First '" + targetChar + "' index: " + firstCharIndex);
        System.out.println("Last '" + targetChar + "' index: " + lastCharIndex);
        System.out.println("Comparison (first vs last word): " + comparisonResult);
        System.out.println("First Character ('" + charArray[0] + "') ASCII Value: " + asciiString);
    }
}