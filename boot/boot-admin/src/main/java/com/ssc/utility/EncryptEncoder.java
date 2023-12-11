package com.ssc.utility;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Component;

@Component
public class EncryptEncoder {

    private String secretKey;
    private final Encoder encoder = Base64.getEncoder();
    private final Decoder decoder = Base64.getDecoder();

    public String encryptString(String target) {
        try {
            return new String(encoder.encode(cipher(Cipher.ENCRYPT_MODE, secretKey)
                .doFinal(target.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String decryptString(String target) {
        try {
            return new String(cipher(Cipher.DECRYPT_MODE, secretKey).doFinal(
                decoder.decode(target.getBytes(StandardCharsets.UTF_8))
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Cipher cipher(int mode, String secretKey)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
            "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(
            secretKey.substring(0, 16).getBytes(StandardCharsets.UTF_8));
        instance.init(mode, keySpec, ivParameterSpec);
        return instance;
    }

}
