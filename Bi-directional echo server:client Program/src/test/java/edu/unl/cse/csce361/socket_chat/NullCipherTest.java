package edu.unl.cse.csce361.socket_chat;

import org.junit.Test;
import static org.junit.Assert.*;

public class NullCipherTest {

    @Test
    public void testNullEncipher() {
        //arrange
        NullCipher cipher = new NullCipher();
        String test = "Test";
        //act
        cipher.encipher(test);
        //assert
        assertEquals("Test", test);
    }

    @Test
    public void testNullDecipher() {
        //arrange
        NullCipher cipher = new NullCipher();
        String test = "Test";
        //act
        cipher.decipher(test);
        //assert
        assertEquals("Test", test);
    }
}
