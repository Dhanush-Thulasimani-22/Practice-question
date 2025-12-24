import java.util.*;
public class Constants {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the sentence");
        String sentence = sc.nextLine();

        if (!sentence.matches("[A-Za-z\\s]+")) {
            System.out.println(sentence + " is an invalid sentence");
            return;
        }

        String s = sentence.toLowerCase();
        String vowels = "aeiou";
        StringBuilder consonants = new StringBuilder();
        long product = 1;

        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch) && vowels.indexOf(ch) == -1) {
                consonants.append(ch);
                product *= (int) ch;
            }
        }

        if (consonants.length() == 0) {
            System.out.println("No consonants present in the sentence");
        } else {
            System.out.println("The output string is " + consonants.toString() + product);
        }
    }
}
