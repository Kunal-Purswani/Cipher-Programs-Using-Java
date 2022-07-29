import java.util.Scanner;

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

    public static void main(String[] args) {
        CaesarCipher cc = new CaesarCipher();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "--------------- Menu --------------\n\tPress 1 to Encrypt.\n\tPress 2 to Decrypt.\n\tPress 3 to Exit.\n-----------------------------------\nEnter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    System.out.println("Enter text to encrypt: ");
                    String text = sc.nextLine();
                    System.out.println(cc.encryptText(text));
                    break;
                case 2:
                    System.out.println("Enter cipher to decrypt: ");
                    String cipher = sc.nextLine();
                    System.out.println(cc.decryptText(cipher));
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