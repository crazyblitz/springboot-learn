package com.ley.springboot.commons.security;

import javax.crypto.Cipher;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA utility class
 *
 * @author liuenyuan
 **/
public class RsaUtils {

    private static final String RSA = "RSA";

    //生成公钥和私钥

    /**
     * 首先初始化{@link KeyPairGenerator},并生成{@link KeyPair},
     * 得到{@link KeyPair},便可以通过getPublic和getPrivate分别获取
     * 公钥和私钥.为了方便保存,将其使用Base64编码转换为String类型的打印字符
     **/
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        //默认512,如果是1024位,需要到官网下载不限长度的加密jar包
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    /**
     * get public key
     **/
    public static String getPublicKeyStr(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return new String(Base64Utils.encode(bytes));
    }

    /**
     * get primary key
     **/
    public static String getPrivateKeyStr(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return new String(Base64Utils.encode(bytes));
    }

    /**
     * 将String类型的密钥转换为PublicKey和PrivateKey对象
     **/
    public static PublicKey string2PublicKey(String publicKeyStr) throws Exception {
        byte[] keyBytes = Base64Utils.decodeFromString(publicKeyStr);
        //使用ASN.1为公钥编码
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }


    /**
     * 将String类型的密钥转换为{@link PrivateKey}
     **/
    public static PrivateKey string2PrivateKey(String privateKeyStr) throws Exception {
        byte[] keyBytes = Base64Utils.decodeFromString(privateKeyStr);
        //使用ASN.1为私钥编码
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }


    /**
     * 使用公钥加密
     **/
    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    /**
     * 使用私钥解密
     **/
    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(content);
        return bytes;
    }

    public static void main(String[] args) throws Exception {
        final String testString = "1234567";

        //获得密钥对
        KeyPair keyPair = getKeyPair();

        //获得私钥字符串
        String privateKeyStr = getPrivateKeyStr(keyPair);
        System.out.println("primary key string: " + privateKeyStr);
        System.out.println();

        //获得公钥字符串
        String publicKeyStr = getPublicKeyStr(keyPair);
        System.out.println("public key string: " + publicKeyStr);
        System.out.println();

        //获取私钥和公钥
        PublicKey publicKey = string2PublicKey(publicKeyStr);
        PrivateKey privateKey = string2PrivateKey(privateKeyStr);
        System.out.println();

        //使用公钥加密
        byte[] bytes = publicEncrypt(testString.getBytes(), publicKey);
        System.out.println("RSA公钥加密: " + new String(Base64Utils.encode(bytes)));
        System.out.println();

        //使用私钥解密
        System.out.println("RSA私钥解密: " + new String(privateDecrypt(bytes, privateKey),
                Charset.defaultCharset()));
    }
}
