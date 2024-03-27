package helpers;

import java.util.Random;

public class PasswordGenerator {
    public static void main(String[] args) {
        System.out.println("psw : " + generateString());
    }
    public static String generateString() {
        StringBuilder stringBuilder = new StringBuilder();


        for (int i = 0; i < 4; i++) {
            char upperCaseChar = (char) ('A' + Math.random() * ('Z' - 'A' + 1));
            stringBuilder.append(upperCaseChar);
        }


        for (int i = 0; i < 5; i++) {
            char lowerCaseChar = (char) ('a' + Math.random() * ('z' - 'a' + 1));
            stringBuilder.append(lowerCaseChar);
        }


        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10);
            stringBuilder.append(digit);
        }


        String specialChars = "~$_-";
        int specialCharsCount = 1 + random.nextInt(3);
        for (int i = 0; i < specialCharsCount; i++) {
            int index = random.nextInt(specialChars.length());
            char specialChar = specialChars.charAt(index);
            stringBuilder.append(specialChar);
        }

        return stringBuilder.toString();
    }

//    public static void main(String[] args) {
//        System.out.println("RESULT: "+Math.random());
//    }
}