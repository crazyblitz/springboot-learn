package com.ley.springboot.commons.security;


import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.UUID;

/**
 * digest utility class
 *
 * @author liuenyuan
 **/
@CommonsLog
public class DigestsUtils {

    /**
     * 160位(16进制字符串: 40)
     **/
    private static final String SHA1 = "SHA-1";

    /**
     * 128位(16进制字符串: 32)
     **/
    private static final String MD5 = "MD5";


    /**
     * SHA-1; encrypt without (salt and iterations)
     *
     * @param bytes encrypt byte array
     **/
    public static byte[] sha1(byte[] bytes) {
        return digest(bytes, SHA1, (byte[]) null, 1);
    }

    /**
     * SHA-1; encrypt without iterations
     *
     * @param bytes encrypt byte array
     * @param salt  encrypt salt
     **/
    public static byte[] sha1(byte[] bytes, byte[] salt) {
        return digest(bytes, SHA1, salt, 1);
    }

    /**
     * SHA-1;
     *
     * @param bytes      encrypt byte array
     * @param salt       encrypt salt
     * @param iterations encrypt iterations
     **/
    public static byte[] sha1(byte[] bytes, byte[] salt, int iterations) {
        return digest(bytes, SHA1, salt, iterations);
    }

    /**
     * MD5
     **/
    public static byte[] md5(byte[] bytes) {
        return digest(bytes, MD5, (byte[]) null, 1);
    }

    /**
     * MD5
     **/
    public static byte[] md5(byte[] bytes, byte[] salt) {
        return digest(bytes, MD5, salt, 1);
    }

    /**
     * MD5
     **/
    public static byte[] md5(byte[] bytes, byte[] salt, int iterations) {
        return digest(bytes, MD5, salt, iterations);
    }


    /**
     * 对salt生成的字节是平台默认编码
     *
     * @see Charset#defaultCharset()
     **/
    public static byte[] generateSalt() {
        return UUID.randomUUID().toString().getBytes();
    }


    /**
     * digest encode md5
     *
     * @param newStr     new str
     * @param oldMd5     old md5 bytes
     * @param salt       md5 salt
     * @param iterations md5 digest iterations
     * @see Arrays
     **/
    public static boolean decodeMd5(byte[] newStr, byte[] oldMd5, byte[] salt, int iterations) {
        return decode(MD5, newStr, oldMd5, salt, iterations);
    }


    /**
     * digest encode sha-1
     *
     * @param newStr     new str
     * @param oldSha1    old sha-1 bytes
     * @param salt       sha-1 salt
     * @param iterations sha-1 digest iterations
     * @see Arrays
     **/
    public static boolean decodeSha1(byte[] newStr, byte[] oldSha1, byte[] salt, int iterations) {
        return decode(SHA1, newStr, oldSha1, salt, iterations);
    }


    /**
     * digest encode
     *
     * @param newStr     new str
     * @param oldDigest  old digest bytes
     * @param salt       md5 salt
     * @param iterations md5 digest iterations
     * @see Arrays
     **/
    private static boolean decode(String algorithm, byte[] newStr, byte[] oldDigest, byte[] salt, int iterations) {
        if (MD5.equalsIgnoreCase(algorithm)) {
            byte[] newDigest = md5(newStr, salt, iterations);
            log.info("encode new md5 digest: " + ByteHexUtils.bytes2Hex(newDigest));
            if (Arrays.equals(newDigest, oldDigest)) {
                return true;
            } else {
                return false;
            }
        } else {
            byte[] newDigest = sha1(newStr, salt, iterations);
            log.info("encode new sha1 digest: " + ByteHexUtils.bytes2Hex(newDigest));
            if (Arrays.equals(newDigest, oldDigest)) {
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * digest with salt and iterations
     **/
    private static byte[] digest(byte[] bytes, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest e = MessageDigest.getInstance(algorithm);
            if (salt != null) {
                e.update(salt);
            }
            byte[] result = e.digest(bytes);
            for (int i = 1; i < iterations; ++i) {
                e.reset();
                result = e.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String testString = "123456";
        String salt = UUID.randomUUID().toString();
        byte[] md5 = md5(testString.getBytes(), salt.getBytes(), 1);
        log.info(ByteHexUtils.bytes2Hex(md5));
        if (decodeMd5("123456".getBytes(), md5, salt.getBytes(), 1)) {
            log.info("密码正确");
        } else {
            log.info("密码错误");
        }


        byte[] sha1 = sha1(testString.getBytes());
        log.info(ByteHexUtils.bytes2Hex(sha1));
        if (decodeSha1("123456".getBytes(), sha1, null, 1)) {
            log.info("密码正确");
        } else {
            log.info("密码错误");
        }
    }
}
