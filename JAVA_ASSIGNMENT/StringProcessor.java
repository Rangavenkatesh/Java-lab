import java.util.Arrays;
import java.util.Scanner;

public class StringProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = "A man a plan a canal Panama";
        String str2 = "listen";
        String str3 = "silent";
        if (scanner.hasNextLine()) {
            String line1 = scanner.nextLine();
            if (!line1.trim().isEmpty()) str1 = line1;
        }
        if (scanner.hasNextLine()) {
            String line2 = scanner.nextLine();
            if (!line2.trim().isEmpty()) str2 = line2;
        }
        if (scanner.hasNextLine()) {
            String line3 = scanner.nextLine();
            if (!line3.trim().isEmpty()) str3 = line3;
        }
        String cleanedStr1 = str1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        boolean isAlphanumeric = cleanedStr1.matches("[a-zA-Z0-9]+");
        boolean isPalindrome = isPalindrome(cleanedStr1);
        boolean isAnagram = isAnagram(str2, str3);
        boolean containsPlan = str1.toLowerCase().contains("plan");
        String heapString = new String("plan");
        String poolString = heapString.intern();
        String literalString = "plan";
        boolean hashCodesEqual = (heapString.hashCode() == poolString.hashCode()) && (poolString == literalString);
        String linePalindrome = String.format("Palindrome Check: \"%s\" -> %b", str1, isPalindrome);
        String lineAnagram = String.format("Anagram Check: \"%s\" & \"%s\" -> %b", str2, str3, isAnagram);
        String lineReport = String.format("Formatted Report: [Palindrome: %b | Anagram: %b]", isPalindrome, isAnagram);
        String lineContains = String.format("Contains 'plan': %b", containsPlan);
        String lineHashCode = String.format("HashCodes equal for interned strings: %b", hashCodesEqual);
        String finalReport = String.join("\n", linePalindrome, lineAnagram, lineReport, lineContains, lineHashCode);
        System.out.println(finalReport);
    }

    private static boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    private static boolean isAnagram(String s1, String s2) {
        String cleanS1 = s1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String cleanS2 = s2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        if (cleanS1.length() != cleanS2.length()) {
            return false;
        }
        char[] arr1 = cleanS1.toCharArray();
        char[] arr2 = cleanS2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }
}