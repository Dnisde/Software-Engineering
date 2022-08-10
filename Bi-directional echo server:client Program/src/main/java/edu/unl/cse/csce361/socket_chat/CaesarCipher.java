package edu.unl.cse.csce361.socket_chat;

public class CaesarCipher implements Cipher {
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String encipher(String plaintext) {
        plaintext = plaintext.toLowerCase();
        String ciphertext = "";
        for(int i = 0; i < plaintext.length(); i++) {
            int alphabetVal = alphabet.indexOf(plaintext.charAt(i));
            int modifiedVal = (alphabetVal + 5) % 26;
            char c = alphabet.charAt(modifiedVal);
            ciphertext = ciphertext + c;
        }
        return ciphertext;
    }

    @Override
    public String decipher(String ciphertext) {
        ciphertext = ciphertext.toLowerCase();
        String plaintext = "";
        for(int i = 0; i < ciphertext.length(); i++) {
            int alphabetVal = alphabet.indexOf(ciphertext.charAt(i));
            int modifiedVal = (alphabetVal - 5) % 26;
            if(modifiedVal < 0) {
                modifiedVal += 26;
            }
            char c = alphabet.charAt(modifiedVal);
            plaintext = plaintext + c;
        }
        return plaintext;
    }
}
