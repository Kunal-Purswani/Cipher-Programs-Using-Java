import java.util.Scanner;

public class ProductCipher {
    CaesarCipher cc = new CaesarCipher();
    RailFenceCipher rfc = new RailFenceCipher();

    public String encrypt(String plainText) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter depth for Rail Fence Cipher: ");
        int depth = Integer.parseInt(sc.nextLine());
        String caesar = cc.encryptText(plainText);
        System.out.println("Caesar Cipher Output: " + caesar);
        return "Encrypted Text: " + rfc.encryptText(caesar, depth);
    }

    public String decrypt(String plainText) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter depth for Rail Fence Cipher: ");
        int depth = Integer.parseInt(sc.nextLine());
        String rfcOutput = rfc.decryptText(plainText, depth);
        System.out.println("Rail Fence Cipher Output: " + rfcOutput);
        return "Decrypted Text: " + cc.decryptText(rfcOutput);
    }

    public static void main(String[] args) {
        ProductCipher pc = new ProductCipher();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\nProgrammed By Kunal Purswani Roll No. 59\n\n--------------- Menu --------------\n\tPress 1 to Encrypt.\n\tPress 2 to Decrypt.\n\tPress 3 to Exit.\n-----------------------------------\nEnter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    System.out.println("Enter text to encrypt: ");
                    String text = sc.nextLine();
                    System.out.println(pc.encrypt(text));
                    break;
                case 2:
                    System.out.println("Enter cipher to decrypt: ");
                    String cipher = sc.nextLine();
                    System.out.println(pc.decrypt(cipher));
                    break;
                case 3:
                    System.out.println("Thank you for running this program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please make a valid choice.");
                    break;
            }
        }
    }
}

class CaesarCipher {
    public String encryptText(String plainText) {
        String encryptedText = "";
        plainText = plainText.toLowerCase();
        int len = plainText.length();
        for (int i = 0; i < len; i++) {
            int charCode = (int) plainText.charAt(i);
            if (charCode > 96 && charCode < 123) {
                charCode -= 97;
                charCode = ((charCode + 3) % 26) + 97;
            }
            char enChar = (char) charCode;
            encryptedText += "" + enChar;
        }
        return encryptedText;
    }

    public String decryptText(String encryptedText) {
        String decryptedText = "";
        encryptedText = encryptedText.toLowerCase();
        int len = encryptedText.length();
        for (int i = 0; i < len; i++) {
            int charCode = ((int) encryptedText.charAt(i));
            if (charCode > 96 && charCode < 123) {
                charCode -= 97;
                charCode = (charCode - 3) % 26;
                if (charCode < 0)
                    charCode += 26 + 97;
                else
                    charCode += 97;
            }
            char deChar = (char) charCode;
            decryptedText += "" + deChar;
        }
        return decryptedText;
    }
}

class RailFenceCipher {
    public String encryptText(String plainText, int depth) {
        String encryptedText = "";
        boolean dirDown = false;
        plainText = plainText.toLowerCase();
        String[][] mat = new String[depth][plainText.length()];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < plainText.length(); j++)
                mat[i][j] = "*";
        }
        int row = 0;
        for (int i = 0; i < plainText.length(); i++) {
            if (row == 0 || row == depth - 1) {
                dirDown = !dirDown;
            }
            mat[row][i] = "" + plainText.charAt(i);
            row = dirDown ? row + 1 : row - 1;
        }
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < plainText.length(); j++) {
                if (!mat[i][j].equals("*"))
                    encryptedText += mat[i][j];
            }
        }
        return encryptedText;
    }

    public String decryptText(String encryptedText, int depth) {
        String decryptedText = "";
        boolean dirDown = false;
        encryptedText = encryptedText.toLowerCase();
        String[][] mat = new String[depth][encryptedText.length()];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < encryptedText.length(); j++)
                mat[i][j] = "*";
        }
        int row = 0;
        for (int i = 0; i < encryptedText.length(); i++) {
            if (row == 0 || row == depth - 1)
                dirDown = !dirDown;
            mat[row][i] = ".";
            row = dirDown ? row + 1 : row - 1;
        }
        int index = 0;
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < encryptedText.length(); j++) {
                if (mat[i][j].equals(".")) {
                    mat[i][j] = "" + encryptedText.charAt(index);
                    index++;
                }
            }
        }
        row = 0;
        dirDown = false;
        for (int i = 0; i < encryptedText.length(); i++) {
            if (row == 0 || row == depth - 1)
                dirDown = !dirDown;
            decryptedText += mat[row][i];
            row = dirDown ? row + 1 : row - 1;
        }
        return decryptedText;
    }
}