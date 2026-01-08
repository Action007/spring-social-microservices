package com.social.microservices.iam_service.security.encrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String first_password = encoder.encode("Test111!");
        String second_password = encoder.encode("Test222!");
        String third_password = encoder.encode("Test333!");

        System.err.println("Hashed first_password: " + first_password);
        System.err.println("Hashed second_password: " + second_password);
        System.err.println("Hashed third_password: " + third_password);
    }
}
