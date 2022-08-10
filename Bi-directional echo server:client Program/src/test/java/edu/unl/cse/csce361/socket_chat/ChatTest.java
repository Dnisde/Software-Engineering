package edu.unl.cse.csce361.socket_chat;

import java.io.ByteArrayInputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.SocketException;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

public class ChatTest {
    static class MockChat extends Chat {
        boolean exitCalled = false;
        int actualExitCode = 0;

        @Override
        protected void exit(int exitCode) {
            exitCalled = true;
            actualExitCode = exitCode;
        }
    }

    private MockChat chatter;
    private String newLine;

    private BufferedReader remoteInput;
    private PrintStream remoteOutput;
    private ByteArrayOutputStream remoteOutputSpy;
    private BufferedReader localInput;
    private PrintStream localOutput;
    private ByteArrayInputStream localInputSpy;
    private ByteArrayOutputStream localOutputSpy;
    private ResourceBundle bundle;

    @Before
    public void setUp() {
        chatter = new MockChat();
        newLine = System.getProperty("line.separator");
        bundle = ResourceBundle.getBundle("socketchat");
        localOutputSpy = new ByteArrayOutputStream();
        localOutput = new PrintStream(localOutputSpy);
    }

    @Test
    public void testCommunicateOneMessageCatchesIOException() {
        // arrange
        Reader stubReader = new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("IOException from stubReader");
            }

            @Override
            public void close() throws IOException {
            }
        };
        remoteInput = new BufferedReader(stubReader);
        int expectedExitCode = 1;
        String expectedOutput = "Connection dropped: java.io.IOException: IOException from stubReader" + newLine;
        // act
        boolean actualReturn = chatter.communicateOneMessage(null, remoteInput, localOutput, null, false);
        // assert
        assertTrue(chatter.exitCalled);
        assertEquals(expectedExitCode, chatter.actualExitCode);
        assertEquals(expectedOutput, localOutputSpy.toString());
    }

    @Test
    public void testCommunicateOneMessageLocalOrigin() {
        // arrange
        InputStreamReader stubReader = new InputStreamReader(new ByteArrayInputStream("Hello".getBytes()));
        localInput = new BufferedReader(stubReader);
        ByteArrayOutputStream remoteOutputSpy = new ByteArrayOutputStream();
        remoteOutput = new PrintStream(remoteOutputSpy);
        boolean expectreturn = true;
        
        // act
        boolean actualreturn = chatter.communicateOneMessage(localInput, null, null, remoteOutput, true);
        // assert
        assertEquals("Hello" + newLine, remoteOutputSpy.toString());
        assertEquals(expectreturn, actualreturn);
    }
    
    @Test
    public void testCommunicateOneMessageKeyword() throws IOException {
        // arrange
    	/* 
    	 * handle an keyword into the communicate message
    	 */
        InputStreamReader stubReader = new InputStreamReader(new ByteArrayInputStream("EXIT".getBytes()));
        localInput = new BufferedReader(stubReader);
        ByteArrayOutputStream remoteOutputSpy = new ByteArrayOutputStream();
        remoteOutput = new PrintStream(remoteOutputSpy);
        
        // act
        boolean actualReturn = chatter.communicateOneMessage(localInput, null, null, remoteOutput, true);
        /* 
         * test with substitutes a local message of keyword will return an terminating state to the program, 
         * For here, it is "EXIT"
         */
        boolean keywordReturn = chatter.handleKeyword("EXIT", true, localInput, remoteOutput);

        // assert
        assertEquals(keywordReturn, actualReturn);
    }


    @Test
    public void testCommunicateOneMessageRemoteMessage() {
        //arrange
    	InputStreamReader stubReader = new InputStreamReader(new ByteArrayInputStream("Remote".getBytes()));
        remoteInput = new BufferedReader(stubReader);
        ByteArrayOutputStream localOutputSpy = new ByteArrayOutputStream();
        localOutput = new PrintStream(localOutputSpy);
        String expectedOutput = "Remote" + newLine;
        boolean expectreturn = true;
        int expectedExitCode = 0;
        //act
        /*
         * Test with remote message with null localInput
         */
        boolean actualreturn = chatter.communicateOneMessage(null, remoteInput, localOutput, null, false);
        
        //assert
        assertFalse(chatter.exitCalled);
        assertEquals(expectedExitCode, chatter.actualExitCode);
        assertEquals(expectedOutput, localOutputSpy.toString());
        assertEquals(expectreturn, actualreturn);
    }
    
    @Test
    public void testCommunicateOneMessageDropConnection() throws IOException {
        // arrange
    	/* 
    	 * handle an EXIT keyword into the communicate message, and terminate 
    	 */
    	// arrange
        Reader reader = new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("IOException from drop connection");
            }

            @Override
            public void close() throws IOException {
            }
        };
        localInput = new BufferedReader(reader);
        String expectedOutput = "Connection dropped: " + "java.io.IOException: IOException from drop connection" + newLine;
        int expectedExitCode = 1;
        // act
        boolean actualReturn = chatter.communicateOneMessage(localInput, remoteInput, localOutput, remoteOutput, true);
        // assert
        assertTrue(chatter.exitCalled);
        assertEquals(expectedExitCode, chatter.actualExitCode);
        assertEquals(expectedOutput, localOutputSpy.toString());
    }
    

}