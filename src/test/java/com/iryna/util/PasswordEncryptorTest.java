package com.iryna.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptorTest {

    @Test
    void encryptPassword() {
        String hash = PasswordEncryptor.encryptPassword("IDKFA");
        assertNotNull(hash);
        assertEquals("-53-1001178-721420-21568-9211089-163313498375-229958510460-27120-31496-341", hash);
    }
}