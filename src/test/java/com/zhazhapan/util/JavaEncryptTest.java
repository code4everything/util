package com.zhazhapan.util;

import com.zhazhapan.util.encryption.JavaEncrypt;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertTrue;

/**
 * @author pantao
 * @date 2018/1/18
 */
public class JavaEncryptTest {

    @Test
    public void testSha() throws NoSuchAlgorithmException {
        assertTrue("7c4a8d09ca3762af61e59520943dc26494f8941b".equals(JavaEncrypt.sha("123456", 16)));
    }

    @Test
    public void testSha256() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println(JavaEncrypt.sha256("123456"));
    }
}
