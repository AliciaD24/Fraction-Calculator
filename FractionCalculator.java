package Classes.FractionCalculator;

import java.util.Scanner;

class FractionCalculator {

    /** Determines if the expression entered is in the correct format to be processed by the fraction calculator
     * @param expression represents the expression that is being checked
     * @return returns a boolean that is true if the expression qualifies as valid input. Otherwise returns false
     */
    static boolean validInput(String expression){
        String mixedFractionRegex = "-{0,1}[1-9][0-9]* [1-9][0-9]*\\/-{0,1}[1-9][0-9]*";
        String wholeFractionRegex = "-{0,1}[1-9][0-9]*";
        String improperFractionRegex = "-{0,1}[1-9][0-9]*\\/-{0,1}[1-9][0-9]*";
        String decimalRegex = "-{0,1}[0-9]+\\.[0-9]+";
        String fractionPattern = String.format("((%s)|(%s)|(%s)|(%s))", mixedFractionRegex, wholeFractionRegex, improperFractionRegex, decimalRegex);
        String validEquation = String.format("%s( ([\\+\\-\\*\\/]) %s)*", fractionPattern, fractionPattern);
        return expression.matches(validEquation);
    }

    /** Separates the first fraction in any form in the given expression
     * @param expresion represents the expression from which the first fraction will be taken
     * @return returns the first instance of a fraction in the expression
     * @throws IllegalArgumentException throws the exception if the expression entered does not match the valid input regex
     */
    static String firstFraction(String expression) throws IllegalArgumentException{
        if (!validInput(expression)){
            throw new IllegalArgumentException("Not a valid expression");
        }
        Scanner equation  = new Scanner(expression);
        equation.useDelimiter(" [\\+\\-\\*\\/] ");
        String firstFraction = equation.next();
        equation.close();
        return firstFraction;
    }

    /** Separates the last fraction in any form in the given expression
     * @param expresion represents the expression from which the last fraction will be taken
     * @return returns the last instance of a fraction in the expression
     * @throws IllegalArgumentException throws the exception if the expression entered does not match the valid input regex
     */
    static String lastFraction(String expression) throws IllegalArgumentException{
        if (!validInput(expression)){
            throw new IllegalArgumentException("Not a valid expression");
        }
        Scanner equation  = new Scanner(expression);
        equation.useDelimiter(" [\\+\\-\\*\\/] ");
        String value = "";
        while (equation.hasNext()){
            value = equation.next();
        }
        equation.close();
        return value;
    }

    /** Determines if the string entered is a fraction in any of the accepted formats (proper, improper, mixed, whole, or decimal)
     * @param str represents the string that is being checked
     * @return returns a boolean that is true if the string can be converted to a fraction. Otherwise returns false
     */
    static boolean isFraction(String str){
        String mixedFractionRegex = "-{0,1}[1-9][0-9]* [1-9][0-9]*\\/-{0,1}[1-9][0-9]*";
        String wholeFractionRegex = "-{0,1}[1-9][0-9]*";
        String improperFractionRegex = "-{0,1}[1-9][0-9]*\\/-{0,1}[1-9][0-9]*";
        String decimalRegex = "-{0,1}[0-9]+\\.[0-9]+";
        return str.matches(mixedFractionRegex) || str.matches(wholeFractionRegex) || str.matches(improperFractionRegex) || str.matches(decimalRegex);
    }

    /** Performs an operation based on the arguments passed
     * @param firstFraction represents the first fraction in the mathematical operation
     * @param secondFraction represents the second fraction in the mathematical operation
     * @param operator represents the operator in the mathematical operation
     * @return returns the value of the first fraction added to, subtracted from, multiplied by or divided by the second fraction depending on the operator provided in the arguments
     */
    static Fraction resultingFraction(Fraction firstFraction, Fraction secondFraction, String operator){
        Fraction resultingFraction = new Fraction(0);
        if (operator.equals("*")){
            resultingFraction = firstFraction.multiply(secondFraction);
        }
        else if (operator.equals("/")){
            resultingFraction = firstFraction.divide(secondFraction);
        }
        else if (operator.equals("+")){
            resultingFraction = firstFraction.add(secondFraction);
        }
        else {
            resultingFraction = firstFraction.subtract(secondFraction);
        }
        return resultingFraction;
    }

    /** Uses the given expression to substitute the values of two fractions and an operator for their sum, difference, product or dividend based on the operator
     * @param expression represents the expression that is being evaluated
     * @param delimiterRegex represents either multiplication/division regex or addition/subtraction regex to be able to follow the rules of bedmass
     * @return returns a string with one of the operations complete, following the rules of bedmass
     * @throws IllegalArgumentException throws the exception if the expression entered does not match the valid input regex
     */
    static String equationProcessing(String expression, String delimiterRegex) throws IllegalArgumentException{
        if (!validInput(expression)){
            throw new IllegalArgumentException("Not a valid expression");
        }
        Scanner equation = new Scanner(expression);
        equation.useDelimiter(delimiterRegex);
        String firstPart = equation.next(); 
        String secondPart = equation.next(); 
        Fraction firstFraction = new Fraction(0);
        if (isFraction(firstPart)){
            firstFraction = Fraction.valueOf(firstPart);
        }
        else{
            firstFraction = Fraction.valueOf(lastFraction(firstPart));
        }
        Fraction secondFraction = Fraction.valueOf(firstFraction(secondPart));
        Fraction resultingFraction = resultingFraction(firstFraction, secondFraction, expression.substring(firstPart.length() + 1, firstPart.length() + 2));
        String endOfExpression = expression.substring(firstPart.length() + firstFraction(secondPart).length() + 3);
        equation.close();
        String newFirstPart = firstPart.replace(lastFraction(firstPart).toString(), "");
        return newFirstPart + resultingFraction.toString() + endOfExpression;
    }

    /** Counts how many occurences of the specified character sequence appear in a string
     * @param str represents the string being checked for the specified character sequence
     * @param charactersToCount represents the character sequence that is being counted within the string
     * @return returns the number of occurences of the specified character sequence in the string
     */
    static int count(String str, String charactersToCount){
        int count = 0;
        for (int i = 0; i < str.length() - 2 ; i++){
            if (str.substring(i, i + 3).matches(charactersToCount)){
                count ++;
            }
        }
        return count;
    }

    /** Completely solves an expression containing any number of addition, subtraction, multiplication or division of whole numbers, decimal numbers, mixed, improper or proper fractions returning the answer to the equation
     * @param expression represents the expression being solved
     * @return returns the answer to the equation formed from the expression
     * @throws IllegalArgumentException throws the exception if the expression entered does not match the valid input regex
     */
    static Fraction solve(String expression) throws IllegalArgumentException{
        if (!validInput(expression)){
            throw new IllegalArgumentException("Not a valid expression");
        }
        String initialExpression = expression;
        for (int i = 0; i < count(initialExpression, " [\\/\\*] "); i ++){
            expression = equationProcessing(expression, " [\\*\\/] ");
        }
        for (int i = 0; i < count(initialExpression, " [\\+\\-] "); i ++){
            expression = equationProcessing(expression, " [\\+\\-] ");
        }
        return Fraction.valueOf(expression);
    }

    /**
     * Menu for the fraction calculator
     */
    static void menu(){
        Scanner input = new Scanner(System.in);
            System.out.println("Welcome to Fraction Calculator!\n");
            System.out.println("Enter your equation using mixed fractions, proper fractions, improper fractions, whole numbers or decimals using +, -, * or / as your operators.\nWhen you have no further questions for the calculator enter 'quit' to exit.\nMake sure to separate each operator from the numbers using a space! (Ex: 2.4 - 1/2 + 5/4 * 7/9 / 8/2 - 12.2 * 3)\n");
            System.out.println("Enter an equation:");
            String expression = input.nextLine(); 
            while (!expression.equals("quit")){
                while (!validInput(expression)){
                    System.out.println("Please enter valid input.");
                    expression = input.nextLine();
                }
                System.out.println("Answer: " + solve(expression));
                System.out.println("\nEnter an equation:");
                expression = input.nextLine();
            }
            input.close();
    }
        public static void main(String[] args) {
            menu();
        }
}
