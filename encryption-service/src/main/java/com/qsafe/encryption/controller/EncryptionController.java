package com.qsafe.encryption.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/encryption")
public class EncryptionController {

    @PostMapping("/encrypt")
    public String encrypt(@RequestBody String plaintext) {
        // Placeholder encryption logic
        return "Encrypted: " + plaintext;
    }

    @PostMapping("/decrypt")
    public String decrypt(@RequestBody String encryptedText) {
        // Placeholder decryption logic
        return "Decrypted: " + encryptedText;
    }
}