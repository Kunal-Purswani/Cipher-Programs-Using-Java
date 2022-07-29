import java.util.Scanner;

class AdditiveCipher {
    public int key(Scanner sc) {
        int key;
        System.out.print("Enter key: ");
        key = Integer.parseInt(sc.nextLine());
        while (!(key > 0 && key < 26)) {
            if (!(key < 26)) {
                System.out.print("Please enter a key less than 26: ");
                key = Integer.parseInt(sc.nextLine());
            }
            if (!(key > 0)) {
                System.out.print("Please enter a key greater than 0: ");
                key = Integer.parseInt(sc.nextLine());
            }
        }
        return key;
    }

    public String encrypt(String plainText, int key) {
        String encryptedText = "";
        plainText = plainText.toLowerCase();
        for (int i = 0; i < plainText.length(); i++) {
            int charCode = (int) plainText.charAt(i);
            if (charCode > 96 && charCode < 123) {
                charCode -= 97;
                charCode += key;
                charCode = charCode % 26;
                charCode += 97;
            }
            encryptedText += (char) charCode;
        }
        return encryptedText;
    }

    public String decrypt(String cipherText, int key) {
        String decryptedText = "";
        cipherText = cipherText.toLowerCase();
        for (int i = 0; i < cipherText.length(); i++) {
            int charCode = (int) cipherText.charAt(i);
            if (charCode > 96 && charCode < 123) {
                charCode -= 97;
                charCode += key;
                charCode = charCode % 26;
                charCode += 97;
            }
            decryptedText += (char) charCode;
        }
        return decryptedText;
    }

    public static void main(String[] args) {
        AdditiveCipher ac = new AdditiveCipher();
        Scanner sc = new Scanner(System.in);
        int enKey = 0;
        while (true) {
            System.out.print(
                    "\n--------------- Menu --------------\n\tPress 1 to Encrypt.\n\tPress 2 to Decrypt.\n\tPress 3 to Exit.\n-----------------------------------\nEnter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    System.out.println("Enter text to encrypt: ");
                    String text = sc.nextLine();
                    enKey = ac.key(sc);
                    System.out.println("Encrypted Text: " + ac.encrypt(text, enKey));
                    break;
                case 2:
                    System.out.println("Enter cipher to decrypt: ");
                    String cipher = sc.nextLine();
                    System.out.println("Decrypted Text: " + ac.decrypt(cipher, (26 - enKey)));
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
