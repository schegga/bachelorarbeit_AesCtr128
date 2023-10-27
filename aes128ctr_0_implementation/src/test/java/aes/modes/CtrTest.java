package aes.modes;

import org.junit.jupiter.api.Test;

import aes.core.AesBlockCipher;
import aes.utils.AesKey128;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;


public class CtrTest {
    
    @Test
    void ctrAesEncryption128Test1(){
        // test vector: https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/AES_CTR.pdf
        byte[] plaintext = new byte[]{
            (byte)0x6B, (byte)0xC1, (byte)0xBE, (byte)0xE2, (byte)0x2E, (byte)0x40, (byte)0x9F, (byte)0x96,
            (byte)0xE9, (byte)0x3D, (byte)0x7E, (byte)0x11, (byte)0x73, (byte)0x93, (byte)0x17, (byte)0x2A,
            (byte)0xAE, (byte)0x2D, (byte)0x8A, (byte)0x57, (byte)0x1E, (byte)0x03, (byte)0xAC, (byte)0x9C,
            (byte)0x9E, (byte)0xB7, (byte)0x6F, (byte)0xAC, (byte)0x45, (byte)0xAF, (byte)0x8E, (byte)0x51,
            (byte)0x30, (byte)0xC8, (byte)0x1C, (byte)0x46, (byte)0xA3, (byte)0x5C, (byte)0xE4, (byte)0x11,
            (byte)0xE5, (byte)0xFB, (byte)0xC1, (byte)0x19, (byte)0x1A, (byte)0x0A, (byte)0x52, (byte)0xEF,
            (byte)0xF6, (byte)0x9F, (byte)0x24, (byte)0x45, (byte)0xDF, (byte)0x4F, (byte)0x9B, (byte)0x17,
            (byte)0xAD, (byte)0x2B, (byte)0x41, (byte)0x7B, (byte)0xE6, (byte)0x6C, (byte)0x37, (byte)0x10
        };

        byte[] ciphertext = new byte[64];

        byte[] keyByte = new byte[] {
            (byte)0x2B, (byte)0x7E, (byte)0x15, (byte)0x16, (byte)0x28, (byte)0xAE, (byte)0xD2, (byte)0xA6, 
            (byte)0xAB, (byte)0xF7, (byte)0x15, (byte)0x88, (byte)0x09, (byte)0xCF, (byte)0x4F, (byte)0x3C
        };
        AesBlockCipher aesBlockCipher = new AesBlockCipher();
        int[] expandedKeys = aesBlockCipher.expandKey128(keyByte);

        int[] nonce = new int[]{
            0xF0F1F2F3,
            0xF4F5F6F7,
            0xF8F9FAFB,
            0xFCFDFEFF
        };

        byte[] expectedCiphertext = new byte[]{
            (byte) 0x87, (byte) 0x4D, (byte) 0x61, (byte) 0x91, (byte) 0xB6, (byte) 0x20, (byte) 0xE3, (byte) 0x26,
            (byte) 0x1B, (byte) 0xEF, (byte) 0x68, (byte) 0x64, (byte) 0x99, (byte) 0x0D, (byte) 0xB6, (byte) 0xCE,
            (byte) 0x98, (byte) 0x06, (byte) 0xF6, (byte) 0x6B, (byte) 0x79, (byte) 0x70, (byte) 0xFD, (byte) 0xFF,
            (byte) 0x86, (byte) 0x17, (byte) 0x18, (byte) 0x7B, (byte) 0xB9, (byte) 0xFF, (byte) 0xFD, (byte) 0xFF,
            (byte) 0x5A, (byte) 0xE4, (byte) 0xDF, (byte) 0x3E, (byte) 0xDB, (byte) 0xD5, (byte) 0xD3, (byte) 0x5E,
            (byte) 0x5B, (byte) 0x4F, (byte) 0x09, (byte) 0x02, (byte) 0x0D, (byte) 0xB0, (byte) 0x3E, (byte) 0xAB,
            (byte) 0x1E, (byte) 0x03, (byte) 0x1D, (byte) 0xDA, (byte) 0x2F, (byte) 0xBE, (byte) 0x03, (byte) 0xD1,
            (byte) 0x79, (byte) 0x21, (byte) 0x70, (byte) 0xA0, (byte) 0xF3, (byte) 0x00, (byte) 0x9C, (byte) 0xEE
        };
        
        CtrAes128 ctrAes128 = new CtrAes128();
        ctrAes128.ctrAes128Encryption(plaintext, ciphertext, expandedKeys, nonce, 0, 64);

        assertArrayEquals(expectedCiphertext, ciphertext);

    }

    @Test
    void ctrAesEncryption128Test2(){
        // test vector: https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/AES_CTR.pdf
        byte[] plaintext = new byte[]{
            (byte)0x6B, (byte)0xC1, (byte)0xBE, (byte)0xE2, (byte)0x2E, (byte)0x40, (byte)0x9F, (byte)0x96,
            (byte)0xE9, (byte)0x3D, (byte)0x7E, (byte)0x11, (byte)0x73, (byte)0x93, (byte)0x17, (byte)0x2A,
            (byte)0xAE, (byte)0x2D, (byte)0x8A, (byte)0x57, (byte)0x1E, (byte)0x03, (byte)0xAC, (byte)0x9C,
            (byte)0x9E, (byte)0xB7, (byte)0x6F, (byte)0xAC, (byte)0x45, (byte)0xAF, (byte)0x8E, (byte)0x51,
            (byte)0x30, (byte)0xC8, (byte)0x1C, (byte)0x46, (byte)0xA3, (byte)0x5C, (byte)0xE4, (byte)0x11,
            (byte)0xE5, (byte)0xFB, (byte)0xC1, (byte)0x19, (byte)0x1A, (byte)0x0A, (byte)0x52, (byte)0xEF,
            (byte)0xF6, (byte)0x9F, (byte)0x24, (byte)0x45, (byte)0xDF, (byte)0x4F, (byte)0x9B, (byte)0x17,
            (byte)0xAD, (byte)0x2B, (byte)0x41, (byte)0x7B, (byte)0xE6, (byte)0x6C, (byte)0x37, (byte)0x10
        };

        byte[] ciphertext = new byte[64];

        byte[] keyByte = new byte[] {
            (byte)0x2B, (byte)0x7E, (byte)0x15, (byte)0x16, (byte)0x28, (byte)0xAE, (byte)0xD2, (byte)0xA6, 
            (byte)0xAB, (byte)0xF7, (byte)0x15, (byte)0x88, (byte)0x09, (byte)0xCF, (byte)0x4F, (byte)0x3C
        };
        AesBlockCipher aesBlockCipher = new AesBlockCipher();
        int[] expandedKeys = aesBlockCipher.expandKey128(keyByte);

        int[] nonce = new int[]{
            0xF0F1F2F3,
            0xF4F5F6F7,
            0xF8F9FAFB,
            0xFCFDFEFF
        };

        byte[] expectedCiphertext = new byte[]{
            (byte) 0x87, (byte) 0x4D, (byte) 0x61, (byte) 0x91, (byte) 0xB6, (byte) 0x20, (byte) 0xE3, (byte) 0x26,
            (byte) 0x1B, (byte) 0xEF, (byte) 0x68, (byte) 0x64, (byte) 0x99, (byte) 0x0D, (byte) 0xB6, (byte) 0xCE,
            (byte) 0x98, (byte) 0x06, (byte) 0xF6, (byte) 0x6B, (byte) 0x79, (byte) 0x70, (byte) 0xFD, (byte) 0xFF,
            (byte) 0x86, (byte) 0x17, (byte) 0x18, (byte) 0x7B, (byte) 0xB9, (byte) 0xFF, (byte) 0xFD, (byte) 0xFF,
            (byte) 0x5A, (byte) 0xE4, (byte) 0xDF, (byte) 0x3E, (byte) 0xDB, (byte) 0xD5, (byte) 0xD3, (byte) 0x5E,
            (byte) 0x5B, (byte) 0x4F, (byte) 0x09, (byte) 0x02, (byte) 0x0D, (byte) 0xB0, (byte) 0x3E, (byte) 0xAB,
            (byte) 0x1E, (byte) 0x03, (byte) 0x1D, (byte) 0xDA, (byte) 0x2F, (byte) 0xBE, (byte) 0x03, (byte) 0xD1,
            (byte) 0x79, (byte) 0x21, (byte) 0x70, (byte) 0xA0, (byte) 0xF3, (byte) 0x00, (byte) 0x9C, (byte) 0xEE
        };
        
        CtrAes128 ctrAes128 = new CtrAes128();
        ctrAes128.ctrAes128Encryption(plaintext, ciphertext, expandedKeys, nonce, 0, 16);
        ctrAes128.ctrAes128Encryption(plaintext, ciphertext, expandedKeys, nonce, 16, 32);
        ctrAes128.ctrAes128Encryption(plaintext, ciphertext, expandedKeys, nonce, 32, 48);
        ctrAes128.ctrAes128Encryption(plaintext, ciphertext, expandedKeys, nonce, 48, 64);
        
        assertArrayEquals(expectedCiphertext, ciphertext);

    }

    @Test
    void ctrAesEncryption128SequentielTest(){
        // test vector: https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/AES_CTR.pdf
        byte[] plaintext = new byte[]{
            (byte)0x6B, (byte)0xC1, (byte)0xBE, (byte)0xE2, (byte)0x2E, (byte)0x40, (byte)0x9F, (byte)0x96,
            (byte)0xE9, (byte)0x3D, (byte)0x7E, (byte)0x11, (byte)0x73, (byte)0x93, (byte)0x17, (byte)0x2A,
            (byte)0xAE, (byte)0x2D, (byte)0x8A, (byte)0x57, (byte)0x1E, (byte)0x03, (byte)0xAC, (byte)0x9C,
            (byte)0x9E, (byte)0xB7, (byte)0x6F, (byte)0xAC, (byte)0x45, (byte)0xAF, (byte)0x8E, (byte)0x51,
            (byte)0x30, (byte)0xC8, (byte)0x1C, (byte)0x46, (byte)0xA3, (byte)0x5C, (byte)0xE4, (byte)0x11,
            (byte)0xE5, (byte)0xFB, (byte)0xC1, (byte)0x19, (byte)0x1A, (byte)0x0A, (byte)0x52, (byte)0xEF,
            (byte)0xF6, (byte)0x9F, (byte)0x24, (byte)0x45, (byte)0xDF, (byte)0x4F, (byte)0x9B, (byte)0x17,
            (byte)0xAD, (byte)0x2B, (byte)0x41, (byte)0x7B, (byte)0xE6, (byte)0x6C, (byte)0x37, (byte)0x10
        };

        byte[] keyByte = new byte[] {
            (byte)0x2B, (byte)0x7E, (byte)0x15, (byte)0x16, (byte)0x28, (byte)0xAE, (byte)0xD2, (byte)0xA6, 
            (byte)0xAB, (byte)0xF7, (byte)0x15, (byte)0x88, (byte)0x09, (byte)0xCF, (byte)0x4F, (byte)0x3C
        };
        AesKey128 aesKey128 = new AesKey128(keyByte);

        byte[] expectedCiphertext = new byte[]{
            (byte) 0x87, (byte) 0x4D, (byte) 0x61, (byte) 0x91, (byte) 0xB6, (byte) 0x20, (byte) 0xE3, (byte) 0x26,
            (byte) 0x1B, (byte) 0xEF, (byte) 0x68, (byte) 0x64, (byte) 0x99, (byte) 0x0D, (byte) 0xB6, (byte) 0xCE,
            (byte) 0x98, (byte) 0x06, (byte) 0xF6, (byte) 0x6B, (byte) 0x79, (byte) 0x70, (byte) 0xFD, (byte) 0xFF,
            (byte) 0x86, (byte) 0x17, (byte) 0x18, (byte) 0x7B, (byte) 0xB9, (byte) 0xFF, (byte) 0xFD, (byte) 0xFF,
            (byte) 0x5A, (byte) 0xE4, (byte) 0xDF, (byte) 0x3E, (byte) 0xDB, (byte) 0xD5, (byte) 0xD3, (byte) 0x5E,
            (byte) 0x5B, (byte) 0x4F, (byte) 0x09, (byte) 0x02, (byte) 0x0D, (byte) 0xB0, (byte) 0x3E, (byte) 0xAB,
            (byte) 0x1E, (byte) 0x03, (byte) 0x1D, (byte) 0xDA, (byte) 0x2F, (byte) 0xBE, (byte) 0x03, (byte) 0xD1,
            (byte) 0x79, (byte) 0x21, (byte) 0x70, (byte) 0xA0, (byte) 0xF3, (byte) 0x00, (byte) 0x9C, (byte) 0xEE
        };

        int[] nonce = {0xF0F1F2F3, 0xF4F5F6F7, 0xF8F9FAFB, 0xFCFDFEFF};

        CtrAes128 ctrAes128 = new CtrAes128();
        byte[] ciphertext = ctrAes128.ctrAes128EncryptionSequential(plaintext, aesKey128, nonce);
        byte[] first64BytesOfCiphertext = Arrays.copyOfRange(ciphertext, 0, 64);

        assertArrayEquals(expectedCiphertext, first64BytesOfCiphertext);

    }

    @Test
    void ctrAesEncryption128ParallelTest(){
        // test vector: https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/AES_CTR.pdf
        byte[] plaintext = new byte[]{
            (byte)0x6B, (byte)0xC1, (byte)0xBE, (byte)0xE2, (byte)0x2E, (byte)0x40, (byte)0x9F, (byte)0x96,
            (byte)0xE9, (byte)0x3D, (byte)0x7E, (byte)0x11, (byte)0x73, (byte)0x93, (byte)0x17, (byte)0x2A,
            (byte)0xAE, (byte)0x2D, (byte)0x8A, (byte)0x57, (byte)0x1E, (byte)0x03, (byte)0xAC, (byte)0x9C,
            (byte)0x9E, (byte)0xB7, (byte)0x6F, (byte)0xAC, (byte)0x45, (byte)0xAF, (byte)0x8E, (byte)0x51,
            (byte)0x30, (byte)0xC8, (byte)0x1C, (byte)0x46, (byte)0xA3, (byte)0x5C, (byte)0xE4, (byte)0x11,
            (byte)0xE5, (byte)0xFB, (byte)0xC1, (byte)0x19, (byte)0x1A, (byte)0x0A, (byte)0x52, (byte)0xEF,
            (byte)0xF6, (byte)0x9F, (byte)0x24, (byte)0x45, (byte)0xDF, (byte)0x4F, (byte)0x9B, (byte)0x17,
            (byte)0xAD, (byte)0x2B, (byte)0x41, (byte)0x7B, (byte)0xE6, (byte)0x6C, (byte)0x37, (byte)0x10
        };

        byte[] keyByte = new byte[] {
            (byte)0x2B, (byte)0x7E, (byte)0x15, (byte)0x16, (byte)0x28, (byte)0xAE, (byte)0xD2, (byte)0xA6, 
            (byte)0xAB, (byte)0xF7, (byte)0x15, (byte)0x88, (byte)0x09, (byte)0xCF, (byte)0x4F, (byte)0x3C
        };
        AesKey128 aesKey128 = new AesKey128(keyByte);

        byte[] expectedCiphertext = new byte[]{
            (byte) 0x87, (byte) 0x4D, (byte) 0x61, (byte) 0x91, (byte) 0xB6, (byte) 0x20, (byte) 0xE3, (byte) 0x26,
            (byte) 0x1B, (byte) 0xEF, (byte) 0x68, (byte) 0x64, (byte) 0x99, (byte) 0x0D, (byte) 0xB6, (byte) 0xCE,
            (byte) 0x98, (byte) 0x06, (byte) 0xF6, (byte) 0x6B, (byte) 0x79, (byte) 0x70, (byte) 0xFD, (byte) 0xFF,
            (byte) 0x86, (byte) 0x17, (byte) 0x18, (byte) 0x7B, (byte) 0xB9, (byte) 0xFF, (byte) 0xFD, (byte) 0xFF,
            (byte) 0x5A, (byte) 0xE4, (byte) 0xDF, (byte) 0x3E, (byte) 0xDB, (byte) 0xD5, (byte) 0xD3, (byte) 0x5E,
            (byte) 0x5B, (byte) 0x4F, (byte) 0x09, (byte) 0x02, (byte) 0x0D, (byte) 0xB0, (byte) 0x3E, (byte) 0xAB,
            (byte) 0x1E, (byte) 0x03, (byte) 0x1D, (byte) 0xDA, (byte) 0x2F, (byte) 0xBE, (byte) 0x03, (byte) 0xD1,
            (byte) 0x79, (byte) 0x21, (byte) 0x70, (byte) 0xA0, (byte) 0xF3, (byte) 0x00, (byte) 0x9C, (byte) 0xEE
        };

        int[] nonce = {0xF0F1F2F3, 0xF4F5F6F7, 0xF8F9FAFB, 0xFCFDFEFF};

        CtrAes128 ctrAes128 = new CtrAes128();
        byte[] ciphertext = ctrAes128.ctrAes128EncryptionParallel(plaintext, aesKey128, nonce, 3);
        byte[] first64BytesOfCiphertext = Arrays.copyOfRange(ciphertext, 0, 64);

        assertArrayEquals(expectedCiphertext, first64BytesOfCiphertext);

    }


}
