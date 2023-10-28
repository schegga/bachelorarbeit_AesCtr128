import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import aes.modes.CtrAes128;
import aes.utils.AesKey128;

public class Benchmark {
    public static void main(String[] args) {
        System.out.println("Benchmark-Start\n");

        try {
            //----------- Initializing for Benchmarktests -----------
            // Choose the file you want to encrypt from the folder: 0_testdateien/0_input
            String fileName = "input_10000KB.txt";

            String projectRoothPath = System.getProperty("user.dir");       
            Path inputPath = Paths.get(projectRoothPath + "/../aes128ctr_1_testdateien_benchmark/0_input", fileName);
            Path outputPathSequential = Paths.get(projectRoothPath + "/../aes128ctr_1_testdateien_benchmark/1_encrypt/", fileName.replace("input", "output_sequential"));
            Path outputPathParallel = Paths.get(projectRoothPath + "/../aes128ctr_1_testdateien_benchmark/1_encrypt/", fileName.replace("input", "output_parallel"));
            Path outputPathJavax = Paths.get(projectRoothPath + "/../aes128ctr_1_testdateien_benchmark/1_encrypt/", fileName.replace("input", "output_javax"));

            // Initialize CtrAes128
            CtrAes128 ctrAes128 = new CtrAes128();

            // Set 128-bit Key and 128-bit Nonce
            byte[] keyByte = new byte[] {
                (byte)0x2B, (byte)0x7E, (byte)0x15, (byte)0x16, (byte)0x28, (byte)0xAE, (byte)0xD2, (byte)0xA6, 
                (byte)0xAB, (byte)0xF7, (byte)0x15, (byte)0x88, (byte)0x09, (byte)0xCF, (byte)0x4F, (byte)0x3C
            };
            int[] nonce = {0xF0F1F2F3, 0xF4F5F6F7, 0xF8F9FAFB, 0xFCFDFEFF};
            
            // Initialize AesKey128 for the own implementation to make sure the key is 128 bit long
            AesKey128 aesKey128 = new AesKey128(keyByte);

            // WarmUp - Encryption: Get System Ready for Benchmarktests (this time will not be measured)
            // Path for warming up
            Path inputWarmUpPath = Paths.get(projectRoothPath + "/../aes128ctr_1_testdateien_benchmark/0_input/input_10000KB.txt");
            byte[] plaintext = Files.readAllBytes(inputWarmUpPath);
            for(int i = 0; i < 5; i++){
                ctrAes128.ctrAes128EncryptionParallel(plaintext, aesKey128, nonce, 8);
                ctrAes128.ctrAes128EncryptionSequential(plaintext, aesKey128, nonce);
            }
            
            //----------- Benchmarktests -----------
            System.out.println("\nEncryption: " + fileName + " \n");

            // input for the encryption
            plaintext = Files.readAllBytes(inputPath);


            // Sequential-Implementation: set the number of iterations, the for loop encrypts the plaintext this many times and calculates the average time
            int iterationsSequential = 20;
            System.out.println("Sequential Times [ms] (" + iterationsSequential + " Iterations):");

            byte[] encryptedSequential = new byte[plaintext.length];
            long startSequential, endSequential;
            double averageSequentialTime = 0;
            for(int i = 0; i < iterationsSequential; i++){
                startSequential = System.nanoTime();
                encryptedSequential = ctrAes128.ctrAes128EncryptionSequential(plaintext, aesKey128, nonce);
                endSequential = System.nanoTime();
                System.out.println("\t" + (endSequential - startSequential)/1E06);
                averageSequentialTime += ((endSequential - startSequential)/1E06);
            }
            System.out.println("Average Sequential Time [ms]:\t" + averageSequentialTime/iterationsSequential + "\n");
            Files.write(outputPathSequential, encryptedSequential);


            // Parallel-Implementation: set the number of threads and the number of iterations, the for loop encrypts the plaintext this many times and calculates the average time
            int iterationsParallel = 20;
            int numThreads = 32;  
            System.out.println("Parallel Times [ms] - " + numThreads + " Threads (" + iterationsParallel + " Iterations):");

            byte[] encryptedParallel = new byte[plaintext.length];
            long startParallel, endParallel;
            double averageParallelTime = 0;
            for(int i = 0; i < iterationsParallel; i++){
                startParallel = System.nanoTime();
                encryptedParallel = ctrAes128.ctrAes128EncryptionParallel(plaintext, aesKey128, nonce, numThreads);
                endParallel = System.nanoTime();
                System.out.println("\t" + (endParallel - startParallel)/1E06);
                averageParallelTime += ((endParallel - startParallel)/1E06);
            }
            System.out.println("Average Parallel Time:\t\t" + averageParallelTime/iterationsParallel + "\n");
            Files.write(outputPathParallel, encryptedParallel);


            // javax.crypto: set the number of iterations, the for loop encrypts the plaintext this many times and calculates the average time
            int iterationsJavaX = 20;
            System.out.println("javax.crypto Times [ms] (" + iterationsJavaX + " Iterations):");

            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            byte[] iv = new byte[] {
                    (byte)0xF0, (byte)0xF1, (byte)0xF2, (byte)0xF3, (byte)0xF4, (byte)0xF5, (byte)0xF6, (byte)0xF7,
                    (byte)0xF8, (byte)0xF9, (byte)0xFA, (byte)0xFB, (byte)0xFC, (byte)0xFD, (byte)0xFE, (byte)0xFF
                };
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", "SunJCE");  

            byte[] encryptedJavax = new byte[plaintext.length];
            long startJavax, endJavax;
            double averageJavaXTime=0;
            for(int i = 0; i < iterationsJavaX; i++){
                startJavax = System.nanoTime();
                cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
                encryptedJavax = cipher.doFinal(plaintext);
                endJavax = System.nanoTime();
                System.out.println("\t" + (endJavax - startJavax)/1E06);
                averageJavaXTime += ((endJavax - startJavax)/1E06);
            }
            System.out.println("Average javax.crypto Time [ms]:\t" + averageJavaXTime/iterationsJavaX + "\n");
            Files.write(outputPathJavax, encryptedJavax);

        } catch (Exception e) {
            e.printStackTrace();
        }
        String javaVersion = System.getProperty("java.version");
        System.out.println("\nused java-version for this application: " + javaVersion);

        System.out.println("\nBenchmark-End" );
        
    }

}