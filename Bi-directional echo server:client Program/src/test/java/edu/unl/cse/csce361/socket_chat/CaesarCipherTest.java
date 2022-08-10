package edu.unl.cse.csce361.socket_chat;

import org.junit.Test;
import static org.junit.Assert.*;

public class CaesarCipherTest {

    @Test
    public void testCaesarEncipher() {
        //arrange
        CaesarCipher cipher = new CaesarCipher();
        String test = "test";
        //act
        String output = cipher.encipher(test);
        //assert
        assertEquals("yjxy", output);
    }

    @Test
    public void testCaesarDecipher() {
        //arrange
        CaesarCipher cipher = new CaesarCipher();
        String test = "yjxy";
        //act
        String output = cipher.decipher(test);
        //assert
        assertEquals("test", output);
    }
}
