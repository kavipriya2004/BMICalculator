import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

class BMICalculator {

    private static final double UNDERWEIGHT_THRESHOLD = 18.5;
    private static final double NORMAL_WEIGHT_THRESHOLD = 25;
    private static final double OVERWEIGHT_THRESHOLD = 30;
    private static final double MAX_HEIGHT_CM = 280;
    private static final double MAX_WEIGHT_KG = 1000;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            double weight = getInputDouble("Enter your weight in kilograms: ", scanner);
            double heightInCm = getInputDouble("Enter your height in centimeters: ", scanner);
            int age = getInputInt("Enter your age in years: ", scanner);

            double heightInM = heightInCm / 100.0;

            double bmi = calculateBMI(weight, heightInM);

            System.out.println("Your BMI is: " + bmi);
            System.out.println("BMI Category for age " + age + ": " + interpretBMI(bmi, age));
        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }

    private static double getInputDouble(String prompt, Scanner scanner) throws InvalidInputException {
        while (true) {
            try {
                System.out.print(prompt);
                double input = scanner.nextDouble();

                // Validate input
                if (input < 0) {
                    throw new InvalidInputException("Input must be a non-negative value.");
                }

                if (prompt.contains("height") && input > MAX_HEIGHT_CM) {
                    throw new InvalidInputException("Height must be 280 centimeters or less.");
                }
if (prompt.contains("weight") && input > MAX_WEIGHT_KG) {
                    throw new InvalidInputException("Weight must be 1000 kilograms or less.");
                }

                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private static int getInputInt(String prompt, Scanner scanner) throws InvalidInputException {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();

                // Validate input
                if (input < 0) {
                    throw new InvalidInputException("Input must be a non-negative integer value.");
                }

                if (prompt.contains("age") && input > 100) {
                    throw new InvalidInputException("Age must be 100 or less.");
                }

                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private static double calculateBMI(double weight, double height) {
        return weight / (height * height);
    }

    private static String interpretBMI(double bmi, int age) {
        if (age < 18) {
            if (bmi < UNDERWEIGHT_THRESHOLD) {
                return "Underweight";
            } else if (bmi < NORMAL_WEIGHT_THRESHOLD) {
                return "Normal Weight";
            } else if (bmi < OVERWEIGHT_THRESHOLD) {
                return "Overweight";
            } else {
                return "Obese";
            }
        } else {
            if (bmi < UNDERWEIGHT_THRESHOLD) {
                return "Underweight";
            } else if (bmi < NORMAL_WEIGHT_THRESHOLD) {
                return "Normal Weight";
            } else if (bmi < OVERWEIGHT_THRESHOLD) {
                return "Overweight";
            } else {
                return "Obese";
            }
        }
    }
}
