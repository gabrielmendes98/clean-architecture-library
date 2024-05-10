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
import java.util.Random;

public class Password extends ValueObject {
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private final byte[] salt;
    private final String hashedPassword;

    private Password(char[] password, byte[] salt) {
        this.salt = salt;
        this.hashedPassword = hashPassword(password, this.salt);
        Arrays.fill(password, ' '); // Clear the password array after use
    }

    private Password(String hashedPassword, byte[] salt) {
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    public static Password create(final String password) {
        return new Password(password.toCharArray(), generateSalt());
    }

    public static Password create(final String password, final byte[] salt) {
        return new Password(password.toCharArray(), salt);
    }

    public static Password from(final String hashedPassword, final byte[] salt) {
        return new Password(hashedPassword, salt);
    }

    // Method to generate a random salt
    private static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[SALT_LENGTH];
        random.nextBytes(saltBytes);
        return saltBytes;
    }

    // Method to verify if a provided plain text password matches the stored hashed password
    public static boolean verifyPassword(String plainPassword, String hashedPassword, byte[] salt) {
        var newHashedPassword = Password.create(plainPassword, salt);
        return newHashedPassword.getValue().equals(hashedPassword);
    }

    public static String generateRandomPassword() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()-_=+";

        String allChars = upperCaseLetters + lowerCaseLetters + digits + specialChars;

        Random random = new SecureRandom();
        StringBuilder passwordBuilder = new StringBuilder();

        passwordBuilder.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        passwordBuilder.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        passwordBuilder.append(digits.charAt(random.nextInt(digits.length())));
        passwordBuilder.append(specialChars.charAt(random.nextInt(specialChars.length())));

        int passwordLength = PasswordValidator.MIN_PASSWORD_LENGTH;
        while (passwordBuilder.length() < passwordLength) {
            passwordBuilder.append(allChars.charAt(random.nextInt(allChars.length())));
        }

        char[] passwordChars = passwordBuilder.toString().toCharArray();
        for (int i = 0; i < passwordChars.length; i++) {
            int randomIndex = random.nextInt(passwordChars.length);
            char temp = passwordChars[i];
            passwordChars[i] = passwordChars[randomIndex];
            passwordChars[randomIndex] = temp;
        }

        return new String(passwordChars);
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

    public byte[] getSalt() {
        return salt;
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

}
