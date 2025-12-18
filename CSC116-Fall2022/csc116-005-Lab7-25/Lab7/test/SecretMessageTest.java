import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests SecretMessage program, which encrypts and decrypts messages
 * 
 * @author Jessica Young Schmidt
 * @author Benjamin Uy
 * @author Bryson Brading
 * @author Eric Chin
 */
public class SecretMessageTest {

    /**
     * Tests swapCharacter method - If first and last characters are letters, swap
     * first and last characters of message.
     */
    @Test
    public void testSwapCharacterEncryptHello() {
        assertEquals("oellH", SecretMessage.swapCharacter("Hello"),
                "Tests swapCharacter if first and last characters are letters");
    }

    /**
     * Test swapCharacter method - If first character is digit, swap second and 
     * next to last characters of message.
     */
    @Test
    public void testSwapCharacterEncrypt5Hey() {
        assertEquals("5eHy",SecretMessage.swapCharacter("5Hey"),
            "Tests swapCharacter if first character is a digit");
    }

    /**
     * Test swapCharacter method - If first character is letter and last character
     * is digit, no swap. 
     */
    @Test
    public void testSwapCharacterEncryptHey5() {
        assertEquals("Hey5", SecretMessage.swapCharacter("Hey5"),
            "Test swapCharacter if first character is letter and last character is digit");
    }    

    /**
     * Tests moveCharacter method - Move character at index (length - 2) to the end
     * of the string
     */
    @Test
    public void testMoveCharacterEncryptHello() {
        assertEquals("oelHl", SecretMessage.moveCharacter("oellH"),
                "Tests moveCharacter for string of length 5");
    }

    /**
     * Tests moveCharacter method - Move character at index (length - 2) to the end
     * of the string
     */
    @Test
    public void testMoveCharacterEncryptWordle() {
        assertEquals("eordWl", SecretMessage.moveCharacter("eordlW"),
                "Tests moveCharacter for string of length 6");
    }

    /**
     * Tests moveCharacter method - Move character at index (length - 2) to the end
     * of the string
     */
    @Test
    public void testMoveCharacterEncryptGreetings() {
        assertEquals("sreetinGg", SecretMessage.moveCharacter("sreetingG"),
                "Tests moveCharacter for string of length 9");
    }

    /**
     * Tests swapSubstrings method - If length is odd and not divisible by three,
     * leave middle character as is and swap substrings before and after it.
     */
    @Test
    public void testSwapSubstringsEncryptHello() {
        assertEquals("Hlloe", SecretMessage.swapSubstrings("oelHl"),
                "Tests swapSubstrings if length is odd and not divisible by three");
    }

    /**
     * Tests swapSubstrings method - If length is odd and divisible by three,
     * swap first third of message with last third of message.
     */
    @Test
    public void testSwapSubstringsEncryptGreetings() {
        assertEquals("nGgetisre", SecretMessage.swapSubstrings("sreetinGg"),
            "Test swapSubstrings if length is odd and divisible by three");
    }

    /**
     * Tests swapSubstrings method - If length is even, swap first half of 
     * message with second half of message.
     */
    @Test
    public void testSwapSubstringsEncryptWordle() {
        assertEquals("dWleor", SecretMessage.swapSubstrings("eordWl"),
            "Test swapSubstrings if length is even");
    }
    
    /**
     * Tests encrypt method - where (1) first and last characters are letters and
     * (2) length is odd and not divisible by three
     */
    @Test
    public void testEncryptHello() {
        assertEquals("Hlloe", SecretMessage.encrypt("Hello"), "Tests encrypting Hello to Hlloe");
    }

    /**
     * Tests encrypt method where method contains only letters and length is two
     */
    @Test
    public void testEncryptHi() {
        assertEquals("Hi", SecretMessage.encrypt("Hi"), "Tests encrypting Hi to Hi");
    }

    /**
     * Tests encrypt method - where (1) first chracter is a digit and last character is a letter 
     * and (2) length is even
     */
    @Test
    public void testEncrypt5Hey() {
        assertEquals("yH5e", SecretMessage.encrypt("5Hey"), "Tests encrypting 5Hey to yH5e");
    }

    /**
     * Tests decrypt method - where (1) length is odd and not divisible by three and
     * (2) first and last characters are letters
     */
    @Test
    public void testDecryptHlloe() {
        assertEquals("Hello", SecretMessage.decrypt("Hlloe"), "Tests decrypting Hlloe to Hello");
    }

    /**
     * Tests decrypt method where method contains only letters and length is two
     */
    @Test
    public void testDecryptHi() {
        assertEquals("Hi", SecretMessage.decrypt("Hi"), "Tests decrypting Hi to Hi");
    }

    /**
     * Tests decrypt method - where (1) lenth is six and (2) contains only letters
     */
    @Test
    public void testDecryptdWleor() {
        assertEquals("Wordle", SecretMessage.decrypt("dWleor"), 
            "Tests decrypting dWleor to Wordle");
    }
    
    /**
     * Tests moveCharacter method for invalid length
     */
    @Test
    public void testMoveCharacterInvalid() {
        Exception exception = assertThrows(IllegalArgumentException.class,
            () -> SecretMessage.moveCharacter("A"));
        assertEquals("Invalid string", exception.getMessage(),
                "Tests invalid length for moveCharacter method");
    }
}
