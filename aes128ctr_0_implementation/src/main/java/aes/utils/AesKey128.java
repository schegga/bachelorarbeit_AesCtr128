package aes.utils;

import java.io.UnsupportedEncodingException;

public class AesKey128 {
    private byte[] keyBytes;
    private int keyLength;  // represents 128 bits

    
    /**
     * Constructor that accepts a string
     * @param keyString is the key as a string
     */
    public AesKey128(String keyString) {
        this.keyBytes = stringToBytes(keyString);
        this.keyLength = keyBytes.length * 8;  // Convert byte length to bit length
        validateKeyLength();
    }

    /**
     * Constructor that accepts a byte array
     * @param keyBytes is the key as a byte array
     */
    public AesKey128(byte[] keyBytes) {
        this.keyBytes = keyBytes;
        this.keyLength = keyBytes.length * 8;  // Convert byte length to bit length
        validateKeyLength();
    }

    /**
     * Helper method to convert a string to a byte array
     * @param input is the string to convert
     * @return the byte array
     */
    private byte[] stringToBytes(String input) {
        try {
            return input.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            // This should never happen as "US-ASCII" is always supported.
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to retrieve the key as a string
     * @return the key as a string
     */
    public String getKeyString() {
        try {
            return new String(keyBytes, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            // This should never happen as "US-ASCII" is always supported.
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to retrieve the key as a byte array
     * @return the key as a byte array
     */
    public byte[] getKeyBytes() {
        return keyBytes;
    }

    /**
     * Method to retrieve the key length in bits
     * @return the key length in bits
     */
    public int getKeyLength() {
        return keyLength;
    }

    /**
     * Method to validate the key length
     */
    private void validateKeyLength() {
        if (keyLength != 128 ) {
            throw new IllegalArgumentException("Invalid AES key length: " + keyLength);
        }
    }

     /**
      * Method to get the key bytes as an ASCII string representation with its decimal values
      * @return the key bytes as an ASCII string representation with its decimal values
      */
     public String getKeyBytesAsAsciiDecString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : keyBytes) {
            sb.append(String.format("%d ", b));
        }
        return sb.toString();
    }

    /**
     * Method to get the key bytes as an ASCII string representation with its hex values
     * @return the key bytes as an ASCII string representation with its hex values
     */
    public String getKeyBytesAsAsciiHexString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : keyBytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }
}
