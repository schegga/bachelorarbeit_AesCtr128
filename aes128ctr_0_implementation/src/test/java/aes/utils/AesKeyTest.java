package aes.utils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AesKeyTest {
    @Test
    void testValidKeyLengths() {
        // Test valid key lengths with byte-constructed keys
        byte[] key128 = new byte[16];

        AesKey128 aesKey128 = new AesKey128(key128);

        assertEquals(128, aesKey128.getKeyLength());

        // Test valid key lengths with string-constructed keys
        aesKey128 = new AesKey128("1234567812345678");

        assertEquals(128, aesKey128.getKeyLength());
    }

    @Test
    void testGetKeyBytes() {
        // Test that the key bytes are returned correctly
        // "1234567812345678" in ASCII is 49 50 51 52 53 54 55 56 49 50 51 52 53 54 55 56
        AesKey128 aesKey = new AesKey128("1234567812345678");
        byte[] keyBytes = new byte[] {49, 50, 51, 52, 53, 54, 55, 56, 49, 50, 51, 52, 53, 54, 55, 56}; 
        assertArrayEquals(keyBytes, aesKey.getKeyBytes());
    }

    @Test
    void testGetKeyString() {
        // Test that the key string is returned correctly
        // "1234567812345678" in ASCII is 49 50 51 52 53 54 55 56 49 50 51 52 53 54 55 56
        byte[] aesKeyBytes = new byte[] {49, 50, 51, 52, 53, 54, 55, 56, 49, 50, 51, 52, 53, 54, 55, 56}; 
        AesKey128 aesKey = new AesKey128(aesKeyBytes);
        assertEquals("1234567812345678", aesKey.getKeyString());
    }

    @Test
    void testGetKeyBytesAsciiDecString() {
        AesKey128 aesKey = new AesKey128("1234567812345678");
        String keyBytesAsciiDecString = "49 50 51 52 53 54 55 56 49 50 51 52 53 54 55 56 ";
        assertEquals(keyBytesAsciiDecString, aesKey.getKeyBytesAsAsciiDecString());
    }

    @Test
    void testGetKeyBytesAsciiHexString() {
        AesKey128 aesKey = new AesKey128("1234567812345678");
        String keyBytesAsciiHexString = "31 32 33 34 35 36 37 38 31 32 33 34 35 36 37 38 ";
        assertEquals(keyBytesAsciiHexString, aesKey.getKeyBytesAsAsciiHexString());
    }
}
