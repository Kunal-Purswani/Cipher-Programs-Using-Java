import java.util.Scanner;

class Helper {
    public String getKey(String keyword, int len) {
        keyword = keyword.replaceAll("\\s+", "").toLowerCase();
        String key = "";
        int mul = len / keyword.length();
        int add = len % keyword.length();
        for (int i = 0; i < mul; i++)
            key += keyword;
        for (int i = 0; i < add; i++)
            key += keyword.charAt(i);
        return key;
    }
}

class VigenereCipher {
    public String encrypt(Scanner sc, String plainText) {
        Helper helper = new Helper();
        plainText = plainText.replaceAll("\\s+", "").toLowerCase();
        String encryptedText = "";
        System.out.print("Enter keyword: ");
        String key = sc.nextLine();
        key = helper.getKey(key, plainText.length());
        for (int i = 0; i < plainText.length(); i++) {
            int textCharCode = (int) plainText.charAt(i) - 97;
            int keyCharCode = (int) key.charAt(i) - 97 + textCharCode;
            keyCharCode %= 26;
            keyCharCode += 97;
            encryptedText += (char) keyCharCode;
        }
        return encryptedText;
    }

    public String decrypt(Scanner sc, String cipher) {
        Helper helper = new Helper();
        cipher = cipher.replaceAll("\\s+", "").toLowerCase();
        String decryptedText = "";
        System.out.print("Enter keyword: ");
        String key = sc.nextLine();
        key = helper.getKey(key, cipher.length());
        for (int i = 0; i < cipher.length(); i++) {
            int cipherCharCode = (int) cipher.charAt(i) - 97;
            int keyCharCode = cipherCharCode - ((int) key.charAt(i) - 97) + 26;
            keyCharCode %= 26;
            keyCharCode += 97;
            decryptedText += (char) keyCharCode;
        }
        return decryptedText;
    }

    public static void main(String[] args) {
        VigenereCipher vc = new VigenereCipher();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\n--------------- Menu --------------\n\tPress 1 to Encrypt.\n\tPress 2 to Decrypt.\n\tPress 3 to Exit.\n-----------------------------------\nEnter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    System.out.print("Enter text to encrypt: ");
                    String text = sc.nextLine();
                    System.out.println("Encrypted Text: " + vc.encrypt(sc, text));
                    break;
                case 2:
                    System.out.print("Enter cipher to decrypt: ");
                    String cipher = sc.nextLine();
                    System.out.println("Decrypted Text: " + vc.decrypt(sc, cipher));
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