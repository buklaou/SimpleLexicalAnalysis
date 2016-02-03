import java.util.Scanner;

/**
 * Created by Raffi on 1/29/2016.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /*Menu is created here. The menu allows 3 inputs.
        * One to reprint the menu, one to enter a value, and one to quit. */
        boolean quit = false;
        int choice;

        System.out.println("Simple Lexical Analysis");
        System.out.println("-----------------------");
        System.out.println();
        printInstructions();

        while (!quit) {
            System.out.println();
            System.out.println("Enter your choice.");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 0:
                    printInstructions();
                    break;
                case 1:
                    System.out.println("Enter a value.");
                    String n = scanner.nextLine();
                    input(n);
                    break;
                case 2:
                    quit = true;
                    break;

            }
        }
    }

    /*This method inputs the String parameter and uses the hasScientificNotation, isFloatingNumber, and isOctalNumber
    * methods to determine what kind it was. */
    public static void input(String n) {
        /*This if statement checks to see if there is only one period entered in the string. If there is more than one
        * it returns false, otherwise it continues.*/
        if (tooManyPeriods(n)) {
        } else {
            if (hasScientificNotation(n)) {
                System.out.println(n + " is a scientific notation number.");
            } else if (isFloatingNumber(n)) {
                System.out.println(n + " is a floating point number.");
            } else if (isOctalNumber(n)) {
                System.out.println(n + " is an octal number.");
            } else if (isDecimalNumber(n)){
                System.out.println(n + " is a decimal number.");
            } else {
                System.out.println(n + " is an error.");
            }
        }
    }

    /*This method returns a boolean to the input method.*/
    public static boolean isDecimalNumber(String n) {
        /*The string parameter is stored into a char array named characterArray.*/
        char[] characterArray = n.toCharArray();

        /*First value in the characterArray is stored in the char variable, firstCharacter.
        * firstCharacter's data type is changed into a String.*/
        char firstCharacter = characterArray[0];
        String firstStringCharacter = String.valueOf(firstCharacter);

        if(!n.contains(".") && !n.contains("E") && !firstStringCharacter.equals("0")) {
            return true;
        }
        return false;
    }

    /*This method returns a boolean to the input method.*/
    public static boolean hasScientificNotation(String n) {
        /*The string parameter is stored into a char array named characterArray.*/
        char[] characterArray = n.toCharArray();

        /*The String must contain a period and a capital E in order to execute the following if statement.
        * It also checks for variations of scientific notation such as, 1.0e3 or 1.0*10^3. */
        if (n.contains(".") && n.contains("E") || n.contains(".") && n.contains("e")
                || n.contains(".") && n.contains("*") && n.contains("^")
                || n.contains("E")) {

            /*isThereADigitAfterE boolean variable is to flag if the last value is a digit.
            * eLocation int variable to keep track of the location of E in the characterArray.
            * lastCharacter int variable to keep track of the last character in the array.*/
            boolean isThereADigitAfterE = false;
            int eLocation = 0;
            int lastCharacter = characterArray.length - 1;

            /*This for loop goes through all the characters in the array, finds which character matches "E"
            * and stores it in the variable eLocation.*/
            for (int i = 0; i < characterArray.length; i++) {
                char currentChar = n.charAt(i);
                String currentString = String.valueOf(currentChar);
                if (currentString.equals("E")) {
                    eLocation = i;
                }
            }

           /*This for loop starts from the value stored in eLocation and iterates forward. If the
           * last character is a digit, the variable isThereADigitAfterE is changed to true, otherwise
           * it stays false.*/
            for (int i = eLocation; i < lastCharacter; i++) {
                if (Character.isDigit(characterArray[lastCharacter])) {
                    isThereADigitAfterE = true;
                }
            }

            boolean flag = true;

            /*While the flag variable is true, it will execute until the for loop
            * has reached the characterArray.length limit.*/
            while (flag) {

                /*This if-else statement first checks if the characterArray
                * contains a period & makes sure that each value is a digit.*/
                if (isThereADigitAfterE) {
                    flag = true;

                } else {
                    /*If any of the values stored in characterArray do not meet
                    * the expressions above, the flag value is set to false.*/
                    return false;
                }
                break;
            }
            /*If flag carries true after the for loop, the method returns true.
            * If it carries false after the for loop, the method returns false.*/
            if (flag) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /*This method returns a boolean to the input method.*/
    public static boolean isFloatingNumber(String n) {

        /*The string parameter is stored into a char array named characterArray.*/
        char[] characterArray = n.toCharArray();

        /*This if statement checks to see if there is only one period entered in the string. If there is more than one
        * it returns false, otherwise it continues.*/
        if (n.contains(".")) {

            boolean flag = true;

            /*This for loop goes through the character array and checks for comparisons.*/
            for (int i = 0; i < characterArray.length; i++) {

                /*Stores first character in the array in the variable first character.*/
                char currentCharacter = characterArray[i];
                /*Storing the char firstCharacter into a String variable.*/
                String currentStringCharacter = String.valueOf(currentCharacter);

                /*This if statement goes to the next iteration if the first value is "." or a "-".*/
                if (currentStringCharacter.equals(".") || currentStringCharacter.equals("-")) {
                    i++;
                }

                /*currentChar stores the current value in the character array.*/
                char currentChar = n.charAt(i);
                char firstChar = n.charAt(0);
                String firstStringChar = String.valueOf(firstChar);

                /*While the flag variable is true, it will execute until the for loop
                * has reached the characterArray.length limit.*/
                while (flag) {

                    /*This if-else statement first checks if the characterArray
                    * contains a period & makes sure that each value is a digit.
                    * It also checks if the first character is a negative while checking for a period.*/
                    if (Character.isDigit(currentChar)
                            || firstStringChar.equals("-") && n.contains(".") && Character.isDigit(currentChar)) {
                        flag = true;
                    } else {
                    /*If any of the values stored in characterArray do not meet
                    * the expressions above, the flag value is set to false.*/
                        return false;
                    }
                    break;
                }
            }


            /*If flag carries true after the for loop, the method returns true.
            * If it carries false after the for loop, the method returns false.*/
            if (flag) {
                return true;
            } else {
                return false;
            }
        }

        /*This method returns false as default.*/
        return false;
    }

    /*This method returns a boolean to the input method.*/
    public static boolean isOctalNumber(String n) {

        /*The string parameter is stored into a char array named characterArray.*/
        char[] characterArray = n.toCharArray();

        /*Stores first character in the array in the variable first character.*/
        char firstCharacter = characterArray[0];
        /*Storing the char firstCharacter into a String variable.*/
        String stringFirstCharacter = String.valueOf(firstCharacter);

        /*The first character of the character array that was formed from the
        * string parameter must equal 0 in order to continue. If it is not equal
        * to 0, this method returns false automatically.*/
        if (stringFirstCharacter.equals("0")) {

            boolean flag = true;

            /*This for loop goes through the character array and checks for
            * comparisons.*/
            for (int i = 0; i < characterArray.length; i++) {

                /*currentChar stores the current value in the character array.*/
                char currentChar = n.charAt(i);

                /*While the flag variable is true, it will execute until the for loop
                * has reached the characterArray.length limit.*/
                while (flag) {
                    /*This if-else statement first checks if the numbers in the
                    * characterArray are from 0-7. It then checks if it does not
                    * contain a period & makes sure that each value is a digit.*/
                    if (Character.getNumericValue(characterArray[i]) <= 7 &&
                            Character.getNumericValue(characterArray[i]) >= 0 &&
                            !n.contains(".") && Character.isDigit(currentChar)) {
                        /*If it passes these expressions, the value of flag remains true.*/
                        flag = true;
                    } else {
                        /*If any of the values stored in characterArray do not meet
                        * the expressions above, the flag value is set to false.*/
                        return false;
                    }
                    break;
                }
            }

            /*If flag carries true after the for loop, the method returns true.
            * If it carries false after the for loop, the method returns false.*/
            if (flag) {
                return true;
            } else {
                return false;
            }
        }
        /*This method returns false as default.*/
        return false;
    }

    /*This method checks the passed in String parameter for the number of periods it has. If it has more than one,
    * it returns false and an error message.*/
    public static boolean tooManyPeriods(String n) {
        /*The string parameter is stored into a char array named characterArray.*/
        char[] characterArray = n.toCharArray();

        /*Initially, the numberOfPeriods int variable is set to 0.*/
        int numberOfPeriods = 0;

        /*This for loop goes through the characterArray.*/
        for (int i = 0; i < characterArray.length; i++) {

            /*Values are stored into curretChar and the data type is changed from
            * char to String in order to make a comparison.*/
            char currentChar = n.charAt(i);
            String stringFirstCharacter = String.valueOf(currentChar);

            if (stringFirstCharacter.equals(".")) {
                /*If any value within the array matches "." it will increase the numberOfPeriods by 1.*/
                numberOfPeriods++;
            }

            /*If the numberOfPeriods is over 1, it will return true and print an error message.*/
            if (numberOfPeriods > 1) {
                System.out.println(n + " is an error. Too many points entered.");
                return true;
            }
        }
        /*This method initially assumes that there are not too many periods which is why it
        * returns false by default.*/
        return false;
    }

    /*This method prints menu instructions.*/
    public static void printInstructions() {
        System.out.println("Menu\n" +
                "0 - Print Instructions.\n" +
                "1 - Enter new value.\n" +
                "2 - Quit.");
    }

}

