import java.util.*;

public class HealthKiosk {
    public static void main(String[] args) {
        // Task 0 - Project setup Scanner
        Scanner sc = new Scanner(System.in); System.out.println("Welcome to Ashesi Health Kiosk!");

        System.out.println();

        // Task 1 - Service Router
        System.out.print("Enter service code (P/L/T/C): ");

        String codeInput = sc.nextLine();

        char code = ' ';

        if (codeInput.length() > 0) {
            code = Character.toUpperCase(codeInput.charAt(0));
            switch (code) {
                case 'P' ->  System.out.println("Go to: Pharmacy Desk");
                case 'L' ->  System.out.println("Go to: Lab Desk");
                case 'T' ->  System.out.println("Go to: Triage Desk");
                case 'C' ->  System.out.println("Go to: Counseling Desk");
                default -> System.out.println("Invalid service code");
            }
        } else {
            System.out.println("Invalid service code");
        }

        System.out.println();

        // Task 2 - Mini Health Metric
        System.out.print("Enter health metric (1 = BMI, 2 = Dosage round-up, 3 = Trig helper): ");
        int metricChoice = sc.nextInt();

        double bmi = 0;
        double degrees = 0;
        int tablets = 0;
        double bmiRounded = 0;

        if (metricChoice == 1) {
            System.out.print("Enter weight (kg): ");
            double weight = sc.nextDouble();
            System.out.print("Enter height (m): ");
            double height = sc.nextDouble();

            bmi = weight / Math.pow(height, 2);
            bmiRounded = Math.round(bmi * 10) / 10.0;

            System.out.print("BMI: " + bmiRounded + " Category: ");
            if (bmiRounded < 18.5) {
                System.out.println("Underweight");
            } else if (bmiRounded <= 24.9) {
                System.out.println("Normal");
            } else if (bmiRounded <= 29.9) {
                System.out.println("Overweight");
            } else {
                System.out.println("Obese");
            }
        } else if (metricChoice == 2) {
            System.out.print("Enter required dosage (mg): ");
            double dosage = sc.nextDouble();
            tablets = (int) Math.ceil(dosage / 250.0);
            System.out.println("Number of tablets: " + tablets);
        } else if (metricChoice == 3) {
            System.out.print("Enter angle in degrees: ");
            degrees = sc.nextDouble();
            double radians = Math.toRadians(degrees);

            double sinVal = Math.round(Math.sin(radians) * 1000) / 1000.0;
            double cosVal = Math.round(Math.cos(radians) * 1000) / 1000.0;

            System.out.printf("sin(%.3f) = %.3f\n", degrees, sinVal);
            System.out.printf("cos(%.3f) = %.3f\n", degrees, cosVal);
        } else {
            System.out.println("Invalid health metric option");
        }

        System.out.println();

        // Task 3 — ID Sanity Check
        char randomChar = (char) ('A' + (int)(Math.random() * 26));

        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int digit = 3 + (int)(Math.random() * 7);
            digits.append(digit);
        }

        String shortID = randomChar + digits.toString();
        System.out.println("Generated ID: " + shortID);

        if (shortID.length() != 5) {
            System.out.println("Invalid length");
        } else if (!Character.isLetter(shortID.charAt(0))) {
            System.out.println("Invalid: first char must be a letter");
        } else if (!(Character.isDigit(shortID.charAt(1)) &&
                Character.isDigit(shortID.charAt(2)) &&
                Character.isDigit(shortID.charAt(3)) &&
                Character.isDigit(shortID.charAt(4)))) {
            System.out.println("Invalid: last 4 must be digits");
        } else {
            System.out.println("ID OK");
        }

        System.out.println();

        // Task 4 — "Secure" Display Code
        System.out.print("Enter your first name: ");
        String firstName = sc.next();

        char baseChar = Character.toUpperCase(firstName.charAt(0));

        char shiftedChar = (char) ('A' + (baseChar - 'A' + 2) % 26);

        String lastTwo = shortID.substring(shortID.length() - 2);

        int metricValue = 0;
        if (metricChoice == 1) {
            metricValue = (int) Math.round(bmi);
        } else if (metricChoice == 2) {
            metricValue = tablets;
        } else if (metricChoice == 3) {
            metricValue = (int) Math.round(Math.sin(Math.toRadians(degrees)) * 100);
        }

        String displayCode = shiftedChar + lastTwo + "-" + metricValue;
        System.out.println("Display Code: " + displayCode);

        System.out.println();

        // Task 5 — Service Summary
        String summary = switch (Character.toUpperCase(code)) {
            case 'P' -> "PHARMACY | ID=" + shortID + " | Code=" + displayCode;
            case 'L' -> "LAB | ID=" + shortID + " | Code=" + displayCode;
            case 'T' -> "TRIAGE | ID=" + shortID + " | BMI=" + bmiRounded + " | Code=" + displayCode;
            case 'C' -> "COUNSELING | ID=" + shortID + " | Code=" + displayCode;
            default -> "Invalid service, no summary available.";
        };

        System.out.println(summary);

        sc.close();
    }
}