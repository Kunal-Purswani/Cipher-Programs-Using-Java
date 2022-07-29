import java.util.Scanner;

class RailFenceCipher {

    public String encryptText(String plainText, int depth) {
        String encryptedText = "";
        boolean dirDown = false;
        plainText = plainText.toLowerCase();
        String[][] mat = new String[depth][plainText.length()];
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < plainText.length(); j++) {
                mat[i][j] = "*";
            }
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
            for (int j = 0; j < encryptedText.length(); j++) {
                mat[i][j] = "*";
            }
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

    public static void main(String[] args) {
        RailFenceCipher rfc = new RailFenceCipher();
        Scanner sc = new Scanner(System.in);
        int depth;
        while (true) {
            System.out.print(
                    "--------------- Menu --------------\n\tPress 1 to Encrypt.\n\tPress 2 to Decrypt.\n\tPress 3 to Exit.\n-----------------------------------\nEnter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    System.out.println("Enter text to encrypt: ");
                    String text = sc.nextLine();
                    System.out.println("Enter depth for Rail Fence Cipher: ");
                    depth = Integer.parseInt(sc.nextLine());
                    System.out.println(rfc.encryptText(text, depth));
                    break;
                case 2:
                    System.out.println("Enter cipher to decrypt: ");
                    String cipher = sc.nextLine();
                    System.out.println("Enter depth for Rail Fence Cipher: ");
                    depth = Integer.parseInt(sc.nextLine());
                    System.out.println(rfc.decryptText(cipher, depth));
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
