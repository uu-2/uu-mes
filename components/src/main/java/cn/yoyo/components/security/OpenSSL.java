package cn.yoyo.components.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class OpenSSL {
    public static byte[] padBytes(byte[] key, int length) {
        byte[] paddedKey = new byte[length];
        System.arraycopy(key, 0, paddedKey, 0, Math.min(key.length, length));
        for (int i = key.length; i < length; i++) {
            paddedKey[i] = (byte) 0x00;
        }
        return paddedKey;
    }

    public static String encrypt(String data, String password, String iv) throws Exception {
        return encrypt(data, password, iv, "AES", "AES/CBC/PKCS5Padding");
    }

    public static String decrypt(String data, String password, String iv) throws Exception {
        return decrypt(data, password, iv, "AES", "AES/CBC/PKCS5Padding");
    }

    public static String encrypt(String data, String password, String iv, String alg, String tf) throws Exception {

        byte[] key = padBytes(password.getBytes(), 32);

        Cipher cipher = Cipher.getInstance(tf);

        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, alg),
                new IvParameterSpec(iv.getBytes()));

        String base64Str = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));


        return base64Str;
    }

    public static String decrypt(String data, String password, String iv, String alg, String tf) throws Exception {
        byte[] key = padBytes(password.getBytes(), 32);

        Cipher cipher = Cipher.getInstance(tf);

        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, alg),
                new IvParameterSpec(iv.getBytes()));

        byte[] b = cipher.doFinal(Base64.getDecoder().decode(data));


        return new String(b);
    }
}
