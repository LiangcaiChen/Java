import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Mechine {
    public static void main(String[] args) {
        // String cipher = "QEBNRFZHYOLTKCLUGRJMPLSBOQEBIXWVALD";
        Scanner s = new Scanner(System.in);
        System.out.println("Do you want to encrypt or decrypt? (en/de)");
        String input = s.nextLine();
        if (input.equals("en")) {
          printEn();
        } else if (input.equals("de")) {
          printDe();
        } else {
          System.out.println("Invalid input");
        }
    }

    public static char[] alphaGenerate() {
      char[] alphaArr = new char[26];
      for(int i = 65; i < 91; i++) {
         alphaArr[i - 65] = (char)i;
      }
      return alphaArr;
    }

    public static int shiftNumber() {
      Scanner s = new Scanner(System.in);
      System.out.println("Insert the number you want to left shift");
      int input = s.nextInt();
      if (input < 27) {
        return input;
      } else {
        System.out.println("Invalid input");
        return 0;
      }
    }

    public static char[] leftshift(int leftShiftNumber) {
      char[] shiftedArr = new char[26];
      System.arraycopy(alphaGenerate(), 26 - leftShiftNumber, shiftedArr, 0, leftShiftNumber);
      System.arraycopy(alphaGenerate(), 0, shiftedArr, leftShiftNumber, 26 - leftShiftNumber);
      return shiftedArr;
    }

    public static char[] decrypt(char[] shiftedArr, char[] cipherArr) {
      for (int i = 0; i < cipherArr.length; i++) {
        for (int j = 0; j < 26; j++) {
          if (cipherArr[i] == shiftedArr[j]) {
            System.out.println("change " + cipherArr[i] + " to " + alphaGenerate()[j]);
            cipherArr[i] = alphaGenerate()[j];
            break;
          }
        }
      }
      return cipherArr;
    }

    public static char[] removeSpace() {
      System.out.println("Type what you want to insert");
      Scanner s = new Scanner(System.in);
      char[] insert = s.nextLine().toUpperCase().replaceAll("\\s","").toCharArray();
      return insert;
    }

    public static char[] encrypt(char[] shiftedArr, char[] insertArr) {
      for (int i = 0; i < insertArr.length; i++) {
        for (int j = 0; j < 26; j++) {
          if (insertArr[i] == alphaGenerate()[j]) {
            System.out.println("change " + insertArr[i] + " to " + shiftedArr[j]);
            insertArr[i] = shiftedArr[j];
            break;
          }
        }
      }
      return insertArr;
    }

    public static void printDe() {
      char[] decrypted = decrypt(leftshift(shiftNumber()), removeSpace());
      System.out.println("This is the decrypted format :");
      System.out.println(decrypted);
    }

    public static void printEn () {
      char[] encrypted = encrypt(leftshift(shiftNumber()), removeSpace());
      System.out.println("This is the encrypted format :");
      System.out.println(encrypted);
    }
}
