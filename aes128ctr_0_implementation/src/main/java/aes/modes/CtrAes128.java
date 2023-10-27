package aes.modes;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import aes.core.AesBlockCipher;
import aes.utils.AesKey128;

public class CtrAes128 {

    // Initialize AesBlockCipher for the AES-128-Blockcipher
    private AesBlockCipher aesBlockCipher = new AesBlockCipher();
    
    // empty constructor
    public CtrAes128() {}
    
//--------------------------Function for the sequential implementation of AES-128 CTR---------------------------------//
    /**
     * Encrypts the plaintext with AES-128-CTR (sequential implementation)
     * @param plaintext The plaintext to be encrypted
     * @param aesKey128 The 128-bit key for the AES-128-CTR encryption (AesKey128 is used to make sure the key is 128 bit long)
     * @param nonce The 128-bit nonce for the AES-128-CTR encryption
     * @return The ciphertext
     */
    public byte[] ctrAes128EncryptionSequential(byte[] plaintext, AesKey128 aesKey128, int[] nonce) {
        // Calculate Expanded Keys
        int[] expandedKeys = aesBlockCipher.expandKey128(aesKey128.getKeyBytes());
        // Initialize ciphertext array where the result will be stored
        byte[]ciphertext = new byte[plaintext.length];
        // Encrypt plaintext
        ctrAes128Encryption(plaintext, ciphertext, expandedKeys, nonce, 0, plaintext.length);
        return ciphertext;
    }

//--------------------------Function for the parallel implementation of AES-128 CTR---------------------------------//
    /**
     * Encrypts the plaintext with AES-128-CTR (parallel implementation)
     * @param plaintext The plaintext to be encrypted
     * @param aesKey128 The 128-bit key for the AES-128-CTR encryption (AesKey128 is used to make sure the key is 128 bit long)
     * @param nonce The 128-bit nonce for the AES-128-CTR encryption
     * @param numThreads The number of threads to be used for the parallel encryption
     * @return The ciphertext
     */
    public byte[] ctrAes128EncryptionParallel(byte[] plaintext, AesKey128 aesKey128, int[] nonce, int numThreads) {
        // Calculate Expanded Keys
        int[] expandedKeys = aesBlockCipher.expandKey128(aesKey128.getKeyBytes());
        // Initialize ciphertext array where the result will be stored
        byte[]ciphertext = new byte[plaintext.length];
        // Initialize ExecutorService for parallel encryption
        ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(numThreads);
        // Calculate how many blocks each thread will encrypt
        int blocksPerThread = plaintext.length / (16*numThreads);
        for(int i = 0; i < numThreads; i++){
            final int start = i * blocksPerThread * 16;
            int endX = (i + 1) * blocksPerThread * 16;
            if(i == numThreads - 1){
                endX = plaintext.length;
            }
            final int end = endX;
            executor.submit( () -> {
                ctrAes128Encryption(plaintext, ciphertext, expandedKeys, nonce, start, end);
            });
        }
        executor.shutdown();
        // Wait until all threads are finished
        try {        
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return ciphertext;
    }

//--------------------------Core-Functions for the serial and parallel implementation of AES-128 CTR---------------------------------//    
    /**
     * Encrypts the plaintext with AES-128-CTR
     * @param plaintext The plaintext to be encrypted
     * @param ciphertext The ciphertext where the result will be stored
     * @param expandedKeys The expanded keys for the AES-128-CTR encryption
     * @param nonce The 128-bit nonce for the AES-128-CTR encryption
     * @param start The start index of the part of the plaintext to be encrypted
     * @param end The end index of the part of the plaintext to be encrypted
     */
    public void ctrAes128Encryption(byte[] plaintext, byte[]ciphertext, int[] expandedKeys, int[] nonce, int start, int end) {
        // add start/16 to a copy of nonce
        int[] nonceCopy = Arrays.copyOf(nonce, nonce.length);
        addToNonce(nonceCopy, start/16);
        while (start < end) {

            // encrypt nonce
            byte[] encryptedNonce = aesBlockCipher.blockEncryption128(nonceCopy, expandedKeys);
    
            // determine the length of data to process in this iteration
            int length = Math.min(16, end - start);
    
            // XOR with the next 'length' bytes of plaintext
            for (int i = 0; i < length; i++) {
                int blockStart = start + i;
                ciphertext[blockStart] = (byte) (encryptedNonce[i] ^ plaintext[blockStart]);
            }
    
            // increment nonce
            addToNonce(nonceCopy, 1);
    
            // increment start
            start += length;
        }
    }
    
    /**
     * Adds x to the 128-bit nonce. The nonce is an integer array of length 4 but is treated as a 128-bit integer.
     * Handling the possible carry of the addition from int to int is done in this function.
     * @param nonce The 128-bit nonce
     * @param x The value to be added to the nonce
     */
    public void addToNonce(int[] nonce, int x) {
        long sum = ((long) nonce[3] & 0xFFFFFFFFL) + x; // Treat nonce[3] as unsigned
        nonce[3] = (int) sum; // Store the lower 32 bits
    
        // Handle carry
        int carry = (int) (sum >>> 32);
        int i = 2;
        while (i >= 0 && carry != 0) {
            sum = ((long) nonce[i] & 0xFFFFFFFFL) + carry;
            nonce[i] = (int) sum;
            carry = (int) (sum >>> 32);
            i--;
        }
    }
}