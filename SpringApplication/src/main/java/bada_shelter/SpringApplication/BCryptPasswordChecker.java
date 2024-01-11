package bada_shelter.SpringApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordChecker {

    public static void main(String[] args) {
        String password = "admin"; // Zmień na swoje zamierzane hasło
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hashedPassword = passwordEncoder.encode(password);

        System.out.println("Hasło: " + password);
        System.out.println("Zahaszowane hasło: " + hashedPassword);

        // Możesz również sprawdzić, czy podane hasło jest zgodne z zahaszowanym hasłem
        boolean isPasswordMatch = passwordEncoder.matches(password, hashedPassword);
        System.out.println("Czy hasło pasuje do zahaszowanego hasła? " + isPasswordMatch);
    }
}
