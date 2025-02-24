import java.util.*;
import java.io.*;

public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Get user input
        System.out.print("Enter the minimum password length: ");
        int minLength = scanner.nextInt();
        System.out.print("Enter the maximum password length: ");
        int maxLength = scanner.nextInt();

        System.out.print("Include lowercase letters? (y/n): ");
        boolean includeLower = scanner.next().equalsIgnoreCase("y");
        System.out.print("Include uppercase letters? (y/n): ");
        boolean includeUpper = scanner.next().equalsIgnoreCase("y");
        System.out.print("Include numbers? (y/n): ");
        boolean includeNumbers = scanner.next().equalsIgnoreCase("y");
        System.out.print("Include symbols? (y/n): ");
        boolean includeSymbols = scanner.next().equalsIgnoreCase("y");

        System.out.print("Do you want to define custom characters? (y/n): ");
        boolean customChars = scanner.next().equalsIgnoreCase("y");
        String customSet = "";

        if (customChars) {
            System.out.print("Enter custom character set: ");
            customSet = scanner.next();
        }

        // Step 2: Generate the password
        String password = generatePassword(minLength, maxLength, includeLower, includeUpper, includeNumbers, includeSymbols, customSet);

        // Step 3: Display the password and its strength
        System.out.println("Generated Password: " + password);
        String strength = evaluatePasswordStrength(password);
        System.out.println("Password Strength: " + strength);

        // Step 4: Save to file (optional)
        System.out.print("Do you want to save the password to a file? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            saveToFile(password);
        }

        scanner.close();
    }

    // Password generation logic
    private static String generatePassword(int minLength, int maxLength, boolean includeLower, boolean includeUpper, boolean includeNumbers, boolean includeSymbols, String customSet) {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{}|;:,.<>?";

        StringBuilder characterSet = new StringBuilder();
        if (includeLower) characterSet.append(lowerCase);
        if (includeUpper) characterSet.append(upperCase);
        if (includeNumbers) characterSet.append(numbers);
        if (includeSymbols) characterSet.append(symbols);
        if (!customSet.isEmpty()) characterSet.append(customSet);

        Random random = new Random();
        int length = random.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(characterSet.charAt(random.nextInt(characterSet.length())));
        }

        return password.toString();
    }

    // Password strength evaluation logic
    private static String evaluatePasswordStrength(String password) {
        // Here you can use an external library to evaluate password strength like zxcvbn
        // For simplicity, we'll use a basic check
        if (password.length() < 8) {
            return "Weak";
        } else if (password.length() < 12) {
            return "Medium";
        } else {
            return "Strong";
        }
    }

    // Save the password to a file
    private static void saveToFile(String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("password.txt"))) {
            writer.write(password);
            System.out.println("Password saved to password.txt");
        } catch (IOException e) {
            System.out.println("Error saving password: " + e.getMessage());
        }
    }
}
