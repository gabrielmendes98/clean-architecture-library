package com.mylibrary.domain.valueobjects.password;

import com.mylibrary.domain.exceptions.NoStacktraceException;
import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.valueobjects.ValueObject;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class Password extends ValueObject {
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private final byte[] salt;
    private final String hashedPassword;

    private Password(char[] password) {
        this.salt = generateSalt();
        this.hashedPassword = hashPassword(password, this.salt);
        Arrays.fill(password, ' '); // Clear the password array after use
    }

    public static Password from(final String password) {
        return new Password(password.toCharArray());
    }

    @Override
    public String getValue() {
        return hashedPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Password that = (Password) o;
        return getValue().equals(that.getValue());
    }

    public void validate(String plainPassword, ValidationHandler handler) {
        new PasswordValidator(this, plainPassword, handler).validate();
    }

    // Method to generate a random salt
    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[SALT_LENGTH];
        random.nextBytes(saltBytes);
        return saltBytes;
    }

    // Method to hash the password using PBKDF2
    private String hashPassword(char[] password, byte[] salt) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
            Arrays.fill(password, ' '); // Clear the password array after use
            byte[] hashedBytes = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new NoStacktraceException("Error hashing password");
        }
    }

    // Method to verify if a provided plain text password matches the stored hashed password
    public boolean verifyPassword(String password) {
        char[] passwordChars = password.toCharArray();
        String hashedAttempt = hashPassword(passwordChars, this.salt);
        Arrays.fill(passwordChars, ' '); // Clear the password array after use
        return hashedAttempt != null && hashedAttempt.equals(this.hashedPassword);
    }
}
