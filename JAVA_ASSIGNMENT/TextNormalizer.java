import java.util.Scanner;
public class TextNormalizer {
    public static String normalizeAndValidate(String input) {
        if (input == null) {
            return "EMPTY_STRING";
        }

        String cleaned = input.strip().trim();

        if (cleaned.isEmpty() || cleaned.equals("")) {
            return "EMPTY_STRING";
        }

        if (cleaned.equalsIgnoreCase("admin")) {
            return "RESERVED_WORD";
        }

        cleaned = cleaned.replace("_", " ");

        String lowerCaseStr = cleaned.toLowerCase();
        if (lowerCaseStr.startsWith("temp") && lowerCaseStr.endsWith("user")) {
            cleaned = cleaned.concat("_FLAGGED");
        }
        return cleaned;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            System.out.println(normalizeAndValidate(userInput));
        }
    }
}