package edu.unl.cse.csce361.socket_chat;

public class XORCipher implements Cipher {
    @Override
    public String encipher(String plaintext) {
        String encrypted = "";
        char key = 'A';

        for(int i = 0; i < plaintext.length(); i++) {
            char xor = (char) (plaintext.charAt(i) ^ key);
            encrypted = encrypted + xor;
        }
        return encrypted;
    }

    @Override
    public String decipher(String ciphertext) {
        String decrypted = "";
        char key = 'A';
        for(int i = 0; i < ciphertext.length(); i++) {
            char xor = (char) (ciphertext.charAt(i) ^ key);
            decrypted = decrypted + xor;
        }
        return decrypted;
    }
}
