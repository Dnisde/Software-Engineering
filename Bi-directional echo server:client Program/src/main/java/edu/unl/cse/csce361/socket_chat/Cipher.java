package edu.unl.cse.csce361.socket_chat;

public interface Cipher {

    public String encipher(String plaintext);

    public String decipher(String ciphertext);
}
