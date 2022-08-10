package edu.unl.cse.csce361.socket_chat;

import org.junit.Test;
import static org.junit.Assert.*;

public class XORCipherTest {

    @Test
    public void testXOREncipher() {
        //arrange
        XORCipher cipher = new XORCipher();
        String test = "test";
        //act
        String output = cipher.encipher(test);
        //assert
        assertEquals("5$25", output);
    }

    @Test
    public void testXORDecipher() {
        //arrange
        XORCipher cipher = new XORCipher();
        String test = "test";
        //act
        String output = cipher.decipher(test);
        //assert
        assertEquals("5$25", output);
    }
}
