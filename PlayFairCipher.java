import java.util.Scanner;

class Helper {
    public String[][] makeMatrix(String key) {
        String[][] mat = new String[5][5];
        String alphas = "abcdefghiklmnopqrstuvwxyz";
        String str = "";
        int firstIndex, lastIndex;
        key = key.toLowerCase().replaceAll("\\s+", "").replaceAll("j+", "i");
        key += alphas;
        for (int i = 0; i < key.length(); i++) {
            firstIndex = key.indexOf(key.charAt(i));
            lastIndex = key.lastIndexOf(key.charAt(i));
            while (firstIndex != lastIndex) {
                if (lastIndex == key.length() - 1) {
                    key = key.substring(0, key.length() - 1);
                } else {
                    key = key.substring(0, lastIndex) + key.substring(lastIndex + 1, key.length());
                }
                lastIndex = key.lastIndexOf(key.charAt(i));
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = "" + key.charAt((i * 5) + j);
            }
        }
        return mat;
    }

    public int checkRow(String mat[][], int row, String elem) {
        int pos = -1;
        for (int i = 0; i < 5; i++) {
            if (mat[row][i].equals(elem)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int checkColumn(String mat[][], int col, String elem) {
        int pos = -1;
        for (int i = 0; i < 5; i++) {
            if (mat[i][col].equals(elem)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int getColumn(String mat[][], String elem) {
        int pos = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mat[i][j].equals(elem)) {
                    pos = j;
                    break;
                }
            }
        }
        return pos;
    }

    public int getRow(String mat[][], String elem) {
        int pos = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (mat[i][j].equals(elem)) {
                    pos = i;
                    break;
                }
            }
        }
        return pos;
    }

    public String insertBogus(String plainText) {
        String modifiedText = plainText.toLowerCase();
        if (modifiedText.indexOf("j") != -1) {
            System.out.println("Note: J found, replaced by I");
            modifiedText = modifiedText.replaceAll("j+", "i");
        }
        modifiedText = modifiedText.replaceAll("\\s+", "");
        for (int i = 0; i < modifiedText.length() - 1; i += 2) {
            if (modifiedText.charAt(i) == modifiedText.charAt(i + 1)) {
                modifiedText = modifiedText.substring(0, i + 1) + "x"
                        + modifiedText.substring(i + 1, modifiedText.length());
            }
        }
        if (modifiedText.length() % 2 != 0)
            modifiedText += "x";
        return modifiedText;
    }
}

class PlayFairCipher {
    public String encrypt(Scanner sc, String plainText) {
        Helper helper = new Helper();
        String encryptedText = "";
        System.out.print("Enter keyword: ");
        String mat[][] = helper.makeMatrix(sc.nextLine());
        plainText = helper.insertBogus(plainText);
        for (int i = 0; i < plainText.length(); i += 2) {
            String currLetter = "" + plainText.charAt(i), nextLetter = "" + plainText.charAt(i + 1);
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (mat[j][k].equals(currLetter)) {
                        int row = helper.checkRow(mat, j, nextLetter);
                        int col = helper.checkColumn(mat, k, nextLetter);
                        if (row != -1) {
                            encryptedText += mat[j][(k + 1) % 5] + mat[j][(row + 1) % 5];
                        } else if (col != -1) {
                            encryptedText += mat[(j + 1) % 5][k] + mat[(col + 1) % 5][k];
                        } else {
                            encryptedText += mat[j][helper.getColumn(mat, nextLetter)]
                                    + mat[helper.getRow(mat, nextLetter)][k];
                        }
                    }
                }
            }
        }
        return encryptedText;
    }

    public String decrypt(Scanner sc, String plainText) {
        Helper helper = new Helper();
        String decryptedText = "";
        System.out.print("Enter keyword: ");
        String mat[][] = helper.makeMatrix(sc.nextLine());
        for (int i = 0; i < plainText.length(); i += 2) {
            String currLetter = "" + plainText.charAt(i), nextLetter = "" + plainText.charAt(i + 1);
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (mat[j][k].equals(currLetter)) {
                        int row = helper.checkRow(mat, j, nextLetter);
                        int col = helper.checkColumn(mat, k, nextLetter);
                        if (row != -1) {
                            if (k == 0 && row != 0) {
                                decryptedText += mat[j][4] + mat[j][(row - 1) % 5];
                            }
                            if (row == 0 && k != 0) {
                                decryptedText += mat[j][(k - 1) % 5] + mat[j][4];
                            }
                            if (row == 0 && k == 0) {
                                decryptedText += mat[j][4] + mat[j][4];
                            }
                            if (row != 0 && k != 0) {
                                decryptedText += mat[j][k - 1] + mat[j][row - 1];
                            }
                        } else if (col != -1) {
                            if (j == 0 && col != 0) {
                                decryptedText += mat[4][k] + mat[col - 1][k];
                            }
                            if (col == 0 && j != 0) {
                                decryptedText += mat[j - 1][k] + mat[4][k];
                            }
                            if (col == 0 && j == 0) {
                                decryptedText += mat[4][k] + mat[4][k];
                            }
                            if (col != 0 && j != 0) {
                                decryptedText += mat[j - 1][k] + mat[col - 1][k];
                            }
                        } else {
                            decryptedText += mat[j][helper.getColumn(mat, nextLetter)]
                                    + mat[helper.getRow(mat, nextLetter)][k];
                        }
                    }
                }
            }
        }
        return decryptedText;
    }

    public static void main(String[] args) {
        PlayFairCipher pfc = new PlayFairCipher();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(
                    "\n--------------- Menu --------------\n\tPress 1 to Encrypt.\n\tPress 2 to Decrypt.\n\tPress 3 to Exit.\n-----------------------------------\nEnter your choice: ");
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1:
                    System.out.println("Enter text to encrypt: ");
                    String text = sc.nextLine();
                    System.out.println("Encrypted Text: " + pfc.encrypt(sc,text));
                    break;
                case 2:
                    System.out.println("Enter cipher to decrypt: ");
                    String cipher = sc.nextLine();
                    System.out.println("Decrypted Text: " + pfc.decrypt(sc,cipher));
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