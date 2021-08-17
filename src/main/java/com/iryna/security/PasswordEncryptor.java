package com.iryna.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

    private static final String SALT = "Once upon a time, a mighty king lived in a palace in " +
            "the shadow of a dark, mysterious forest. He had only one child," +
            " a beautiful little girl with long, owing hair, and her favorite " +
            "plaything was a bright golden ball that looked just like the sun " +
            "in the sky. Day after day, she would run and skip under the shadow " +
            "of the huge forest trees, tossing and bouncing her ball to amuse herself." +
            " She liked to pretend that her ball was indeed the sun and that the whole" +
            " wide world was hers to play with.";

    public static String encryptPassword(String password, String generatedSalt) {

        StringBuilder passwordStringBuilder = new StringBuilder();
        passwordStringBuilder.append(generatedSalt);
        passwordStringBuilder.append(password);
        passwordStringBuilder.append(SALT);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = messageDigest.digest(passwordStringBuilder.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                stringBuilder.append(String.valueOf(hash[i]));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
