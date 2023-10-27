package aes.core;

import org.junit.jupiter.api.Test;

import aes.utils.AesKey128;

import static org.junit.jupiter.api.Assertions.*;

public class AesBlockCipherTest {

    @Test
    void testCircularLeftShift() {
        // Original value: 0x67 20 46 75 (32-bit representation)
        int originalValue = 0x67204675; // In decimal: 1732584197, in 32-bit binary: 01100111 00100000 01000110 01110101
        
        // Expected value after circular left shift by 8 bits: 0x20 46 75 67
        // A hex value with 2 digits represents 8 bits (2^8 = 256 = 16^2)
        int expectedValue = 0x20467567; // In decimal: 541210983, in 32-bit binary: 00100000 01000110 01110101 01100111
    
        AesBlockCipher aesBlockCipher = new AesBlockCipher();
        int shiftedValue = aesBlockCipher.circularLeftShift(originalValue, 8);
    
        assertEquals(expectedValue, shiftedValue);
    }
    
    @Test
    void testCircularLeftShiftNegativeValue() {
        // Original value: 0x87 20 46 75 (The most significant bit is 1, indicating a negative value)
        int originalValue = 0x87204675; // In decimal: -2062379019, in 32-bit binary: 10000111 00100000 01000110 01110101
        
        // Expected value after circular left shift by 8 bits: 0x20 46 75 87
        int expectedValue = 0x20467587; // In decimal: 541210759, in 32-bit binary: 00100000 01000110 01110101 10000111
    
        AesBlockCipher aesBlockCipher = new AesBlockCipher();
        int shiftedValue = aesBlockCipher.circularLeftShift(originalValue, 8);
    
        assertEquals(expectedValue, shiftedValue);
    }    

    @Test
    public void testBlockEncryption128() {
        AesBlockCipher aesBlockCipher = new AesBlockCipher();

        // test vector 1 from: https://nvlpubs.nist.gov/nistpubs/fips/nist.fips.197.pdf Appendix B (page 33)
        byte[] key1 = new byte[]{
            (byte)0x2b, (byte)0x7e, (byte)0x15, (byte)0x16,
            (byte)0x28, (byte)0xae, (byte)0xd2, (byte)0xa6,
            (byte)0xab, (byte)0xf7, (byte)0x15, (byte)0x88,
            (byte)0x09, (byte)0xcf, (byte)0x4f, (byte)0x3c
        };
        int[] expandedKeys1 = aesBlockCipher.expandKey128(key1);
        int[] state1 = new int[]{
            0x3243f6a8, 0x885a308d, 0x313198a2, 0xe0370734
        };
        // expected cipher in byte[]
        byte[] expectedCipherText1 = new byte[]{
            (byte)0x39, (byte)0x25, (byte)0x84, (byte)0x1d,
            (byte)0x02, (byte)0xdc, (byte)0x09, (byte)0xfb,
            (byte)0xdc, (byte)0x11, (byte)0x85, (byte)0x97,
            (byte)0x19, (byte)0x6a, (byte)0x0b, (byte)0x32
        };
        assertArrayEquals(expectedCipherText1, aesBlockCipher.blockEncryption128(state1, expandedKeys1));

        // test vector 2 from: https://nvlpubs.nist.gov/nistpubs/fips/nist.fips.197.pdf Appendix C.1 (page 35)
        byte[] key2 = new byte[]{
            (byte)0x00, (byte)0x01, (byte)0x02, (byte)0x03,
            (byte)0x04, (byte)0x05, (byte)0x06, (byte)0x07,
            (byte)0x08, (byte)0x09, (byte)0x0a, (byte)0x0b,
            (byte)0x0c, (byte)0x0d, (byte)0x0e, (byte)0x0f
        };
        int[] expandedKeys2 = aesBlockCipher.expandKey128(key2);

        int[] state2 = new int[]{
            0x00112233, 0x44556677, 0x8899aabb, 0xccddeeff
        }; 

        byte[] expectedCipherText2 = new byte[]{
            (byte)0x69, (byte)0xc4, (byte)0xe0, (byte)0xd8,
            (byte)0x6a, (byte)0x7b, (byte)0x04, (byte)0x30,
            (byte)0xd8, (byte)0xcd, (byte)0xb7, (byte)0x80,
            (byte)0x70, (byte)0xb4, (byte)0xc5, (byte)0x5a
        };
        assertArrayEquals(expectedCipherText2, aesBlockCipher.blockEncryption128(state2, expandedKeys2));

        // test vector 3 from: https://www.kavaliro.com/wp-content/uploads/2014/03/AES.pdf
        AesKey128 aesKey1 = new AesKey128("Thats my Kung Fu");
        int[] expandedKeys3 = aesBlockCipher.expandKey128(aesKey1.getKeyBytes());
        int[] state3 = new int[]{
            0x54776f20, 0x4f6e6520, 0x4e696e65, 0x2054776f
        };
        
        //expected cipher in byte[]
        byte[] expectedCipherText3 = new byte[]{
            (byte)0x29, (byte)0xc3, (byte)0x50, (byte)0x5f,
            (byte)0x57, (byte)0x14, (byte)0x20, (byte)0xf6,
            (byte)0x40, (byte)0x22, (byte)0x99, (byte)0xb3,
            (byte)0x1a, (byte)0x02, (byte)0xd7, (byte)0x3a
        };
        assertArrayEquals(expectedCipherText3, aesBlockCipher.blockEncryption128(state3, expandedKeys3));

        // test vector 4 from: https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/AES_CTR.pdf
        // first input for ctr was tested, means: initial counter is eqaul to the input in aesEncrypt128
        // key: 2B7E1516 28AED2A6 ABF71588 09CF4F3C
        byte[] key4 = new byte[]{
            (byte)0x2b, (byte)0x7e, (byte)0x15, (byte)0x16,
            (byte)0x28, (byte)0xae, (byte)0xd2, (byte)0xa6,
            (byte)0xab, (byte)0xf7, (byte)0x15, (byte)0x88,
            (byte)0x09, (byte)0xcf, (byte)0x4f, (byte)0x3c
        };
        int[] expandedKeys4 = aesBlockCipher.expandKey128(key4);

        int[] state4 = new int[]{
            0xF0F1F2F3, 0xF4F5F6F7, 0xF8F9FAFB, 0xFCFDFEFF
        };
        
        //expected cipher in byte[]
        byte[] expectedCipherText4 = new byte[]{
            (byte)0xEC, (byte)0x8C, (byte)0xDF, (byte)0x73,
            (byte)0x98, (byte)0x60, (byte)0x7C, (byte)0xB0,
            (byte)0xF2, (byte)0xD2, (byte)0x16, (byte)0x75,
            (byte)0xEA, (byte)0x9E, (byte)0xA1, (byte)0xE4
        };
        assertArrayEquals(expectedCipherText4, aesBlockCipher.blockEncryption128(state4, expandedKeys4));
    }
}

