package com.qsafe.encryption.controller;

import com.qsafe.encryption.util.KeyManager;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.web.bind.annotation.*;
import java.security.*;
import java.util.Base64;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String plaintext) throws Exception {
        // Step 1: Use Kyber public key to simulate secure key exchange
        PublicKey publicKey = KeyManager.getPublicKey();
        SecretKey aesKey = generateAESKey(); // Generate AES key for encryption

        // Encrypt AES key using Kyber public key (Simulated)
        byte[] encryptedAESKey = encryptAESKey(publicKey, aesKey);

        // Step 2: Encrypt the plaintext using AES key
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encryptedData = aesCipher.doFinal(plaintext.getBytes());

        // Return Base64-encoded AES key and encrypted data
        return Base64.getEncoder().encodeToString(encryptedAESKey) + ":" +
                Base64.getEncoder().encodeToString(encryptedData);
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedPayload) throws Exception {
        // Split the payload into AES key and encrypted data
        String[] parts = encryptedPayload.split(":");
        byte[] encryptedAESKey = Base64.getDecoder().decode(parts[0]);
        byte[] encryptedData = Base64.getDecoder().decode(parts[1]);

        // Step 1: Decrypt AES key using Kyber private key
        PrivateKey privateKey = KeyManager.getPrivateKey();
        SecretKey aesKey = decryptAESKey(privateKey, encryptedAESKey);

        // Step 2: Decrypt data using AES key
        Cipher aesCipher = Cipher.getInstance("AES");
        aesCipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] decryptedData = aesCipher.doFinal(encryptedData);

        return new String(decryptedData);
    }

    // Utility method to generate an AES key
    private SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        return keyGen.generateKey();
    }

    // Simulated encryption of AES key using Kyber public key
    private byte[] encryptAESKey(PublicKey publicKey, SecretKey aesKey) {
        // Simulating key encryption; in production, use proper Kyber encryption
        return aesKey.getEncoded();
    }

    // Simulated decryption of AES key using Kyber private key
    private SecretKey decryptAESKey(PrivateKey privateKey, byte[] encryptedAESKey) {
        // Simulating key decryption; in production, use proper Kyber decryption
        return new SecretKeySpec(encryptedAESKey, "AES");
    }
}