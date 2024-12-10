package com.qsafe.encryption.util;

import org.bouncycastle.pqc.jcajce.provider.BouncyCastlePQCProvider;
import org.bouncycastle.pqc.jcajce.spec.KyberParameterSpec;

import java.security.*;

public class KeyManager {
    private static KeyPair keyPair;

    static {
        try {
            Security.addProvider(new BouncyCastlePQCProvider());
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Kyber", "BCPQC");
            keyPairGenerator.initialize(KyberParameterSpec.kyber512);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Error generating Kyber key pair", e);
        }
    }

    public static PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public static PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }
}