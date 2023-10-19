package Classes.FractionCalculator;

import java.util.Scanner;

class Fraction {
    private int numerator;
    private int denominator;

    /** Constructor for a Fraction with a numerator and denominator value
     * @param numerator represents the numerator
     * @param denominator represents the denominator
     */
    Fraction(int numerator, int denominator) throws IllegalArgumentException{
        // this. is used when the argument has the same name as the parameter you're assigning it to which correlates to the attributes of the function.
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }


    /** Constructor for a Fraction with a whole number, numerator and denominator value
     * @param whole represents the whole number
     * @param numerator represents the numerator
     * @param denominator represents the denominator
     */
    Fraction(int whole, int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannoe be zero");
        }

        if (whole < 0){
            numerator = -1 * (Math.abs(whole) * denominator + numerator);
        }
        else{
            numerator = whole * denominator + numerator;
        }

        this.numerator = getSimplifiedNumerator(numerator, denominator);
        this.denominator = getSimplifiedDenominator(numerator, denominator);
    }


    /** Constructor for a Fraction made up of only a whole number
     * @param whole represents the whole number
     */
    Fraction(int whole){
        numerator = whole;
        denominator = 1;
    }

    /** Returns the greatest common denominator of two denominators
     * @param num1 represents the first of the two denominators
     * @param num2 represents the second of the two denominators
     * @return returns the greatest common denominator between the two denominators
     */
    static int gcd(int num1, int num2){
        if (num1 == 0 || num2 == 0){
            return Math.max(Math.abs(num1), Math.abs(num2));
        }
        int lower = Math.min(Math.abs(num1), Math.abs(num2));
        while (num1 % lower != 0 || num2 % lower != 0){
            lower --;
        }
        return lower;
    }

    /** Returns the most simplified version of the numerator using the greatest common denominator method
     * @param numerator represents the value of the original numerator
     * @param denominator represents the value of the denominator
     * @return returns the simplified version of the numerator using the denominator
     */
    static int getSimplifiedNumerator(int numerator, int denominator) throws IllegalArgumentException{
        if (denominator == 0){
            throw new IllegalArgumentException("Denominator cannot be zero");
        }
        
        if (numerator * denominator < 0){
            return -1 * Math.abs(numerator) / gcd(numerator, denominator);
        }
        return Math.abs(numerator) / gcd(numerator, denominator);
    }


    /** Returns the most simplified version of the denominator using the greatest common denominator method
     * @param numerator represents the value of the numerator
     * @param denominator represents the value of the original denominator
     * @return returns the simplified version of the numerator using the denominator
     */
    static int getSimplifiedDenominator(int numerator, int denominator){
        return Math.abs(denominator) / gcd(numerator, denominator);
    }


    /** Gets the numerator
     * @return returns the numerator
     */
    int getNumerator(){
        return numerator;
    }

    /** Gets the denominator
     * @return returns the denominator
     */
    int getDenominator(){
        return denominator;
    }

    /** Gets the whole value
     * @return returns the whole value
     */
    int getWhole(){
        return numerator / denominator;
    }

    /** Sets the numerator to a new value
     * @param numeratorVal represents new numerator value
     */
    void setNumerator(int numeratorVal){
        numerator = numeratorVal;
    }

    /** Sets the denominator to a new value
     * @param denominatorVal represents new denominator value
     */
    void setDenominator(int denominatorVal){
        denominator = denominatorVal;
    }
    
    /** Checks if a Fraction is equal to another Fraction
     * @param other represents another Fraction
     * @return boolean 
     */
    boolean equals(Fraction other){
        return numerator == other.numerator && denominator == other.denominator;
    }

    
    /** Returns the String value of a Fraction formatted to be read as a fraction
     * @return String version of the Fraction
     */
    public String toString(){
        if (numerator % denominator == 0){
            return String.valueOf(getWhole());
        }
        else if (Math.abs(numerator) < Math.abs(denominator)){
            return String.format("%d/%d", numerator, denominator);
        }
        int whole = getWhole();
        int newNum = Math.abs(numerator) % Math.abs(denominator);
        return String.format("%d %d/%d", whole, newNum, denominator);
    }

    
    /** Clones a Fraction into a new Fraction object
     * @return Fraction with identical values to the Fraction it was called on
     */
    public Fraction clone(){
        return new Fraction(numerator, denominator);
    }

    /** Checks if the numerator is less than zero
     * @return boolean true if less than zero
     */
    boolean lessThanZero(){
        return numerator < 0;
    }

    /** Checks if the numerator is greater than zero
     * @return boolean true if greater than zero
     */
    boolean greaterThanZero(){
        return numerator > 0;
    }

    /** Adds two fractions together
     * @param other Represents second Fraction
     * @return Returns the result of adding two Fractions
     */
    Fraction add(Fraction other){
        //a/b + c/d
        //= (a *d + b * c) / b * d
        
        int commonDenominator = denominator * other.denominator; 
        int numeratorOfResult = numerator * other.denominator + other.numerator * denominator;
        return new Fraction(numeratorOfResult, commonDenominator);
    }

    /** Subtracts one Fraction from another
     * @param other Represents second Fraction
     * @return Returns the result of subtracting one Fraction from the other
     */
    Fraction subtract(Fraction other){
        return this.add(new Fraction(-1 * other.numerator, denominator));
    }

    Fraction multiply(Fraction other){
        return new Fraction(numerator * other.numerator, denominator * other.denominator);
    }

    Fraction divide(Fraction other){
        return new Fraction(numerator * other.denominator, denominator * other.numerator);
    }

    static Fraction valueOf(String expression){
        return new Fraction(1); //FINISH THISSS
    }

    static void testAdd(){
        Fraction f = new Fraction(-3, 4);
        Fraction g = new Fraction(1, 4, 5);
        System.out.println(f.add(g)); // 1 1/20
    }

    Fraction nextFraction(String fraction){
        /*
        int indexOfPlus = equation.indexOf("+");
        int indexOfMinus = equation.indexOf("-");
        int indexOfAsterisk = equation.indexOf("*");
        int indexOfDivision = equation.indexOf(" / ");
        int indexOfFirstOpperation = Math.min(Math.min(indexOfAsterisk, indexOfDivision), Math.min(indexOfMinus, indexOfPlus));
        String fraction = equation.substring(0, indexOfFirstOpperation);
        */
        if (fraction.contains(" ")){
            int whole = Integer.valueOf(fraction.split(" ")[0]);
            int numerator = Integer.valueOf((fraction.split(" ")[1]).split("/")[0]);
            int denominator = Integer.valueOf((fraction.split(" ")[1]).split("/")[0]);
            return new Fraction(whole, numerator, denominator);
        }
        else if (!fraction.contains("/")){
            return new Fraction(Integer.valueOf(fraction));
        }
        else{
            int numerator = Integer.valueOf(fraction.split("/")[0]);
            int denominator = Integer.valueOf(fraction.split("/")[0]);
            return new Fraction(numerator, denominator);
        }
    }
    /*
    void bedmass(String equation){
        int indexOfPlus = equation.indexOf("+");
        int indexOfMinus = equation.indexOf("-");
        int indexOfAsterisk = equation.indexOf("*");
        int indexOfDivision = equation.indexOf(" / ");
        int indexOfFirstOpperation = Math.min(indexOfAsterisk, indexOfDivision);
        
    }
    */

    /* 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try{
            int num = input.nextInt();
            int den = input.nextInt();
            Fraction f = new Fraction(num, den);
            System.out.println(f);
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        input.close();
        */
        public static void main(String[] args) {
            Scanner s = new Scanner("2 - 1/2 + 5/4 * 7/9");
            s.useDelimiter(" ");
            System.out.println(s.next());
            System.out.println(s.next("[0-9] \\+ [0-9]"));

        }
    }




