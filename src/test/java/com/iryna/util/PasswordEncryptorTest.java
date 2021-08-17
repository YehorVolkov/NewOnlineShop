package com.iryna.util;

import com.iryna.security.PasswordEncryptor;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PasswordEncryptorTest {

    @Test
    void encryptPassword() {
        String uuid= "5ec4c118-e4d2-4042-8a6d-5f807f5630a6";
        PasswordEncryptor passwordEncryptor = new PasswordEncryptor();
        String hash = passwordEncryptor.encryptPassword("IDKFA", uuid);
        assertNotNull(hash);
        assertEquals("22114-89114-512-24-80-89-234838-5436112-12096-35127-98102557-11-29124-43-19117-111107-114", hash);
    }
}