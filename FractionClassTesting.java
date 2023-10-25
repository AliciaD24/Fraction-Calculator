package Classes.FractionCalculator;

class FractionClassTesting {

    /**
     * Tests the Fraction.add method
     */
    static void testAdd(){
        System.out.println(new Fraction(-11, 5).add(new Fraction(-2, 4))); // -2 7/10
        System.out.println(new Fraction(-1, 11, 5).add(new Fraction(-2, 3, 4))); // -5 19/20
        System.out.println(new Fraction(-1).add(new Fraction(-2))); // -3
        System.out.println(new Fraction(-1).add(new Fraction(0))); // -1
    }

    /**
     * Tests the Fraction.subtract method
     */
    static void testSubtract(){
        System.out.println(new Fraction(-11, 5).subtract(new Fraction( -2, 4))); // -1 7/10
        System.out.println(new Fraction(-1, 11, 5).subtract(new Fraction(-2, 3, 4))); // -9/20
        System.out.println(new Fraction(-1).subtract(new Fraction(-2))); // 1
        System.out.println(new Fraction(-1).subtract(new Fraction(0))); // -1
    }

    /**
     * Tests the Fraction.multiply method
     */
    static void testMultiply(){
        System.out.println(new Fraction(-11, 5).multiply(new Fraction(-2, 4))); // 1 1/10
        System.out.println(new Fraction(-1, 11, 5).multiply(new Fraction(-2, 3, 4))); // 8 4/5
        System.out.println(new Fraction(-1).multiply(new Fraction(-2))); // 2
        System.out.println(new Fraction(-1).multiply(new Fraction(0))); // 0
    }

    /**
     * Tests the Fraction.divide method
     */
    static void testDivide(){
        System.out.println(new Fraction(-11, 5).divide(new Fraction(-2, 4))); // 4 2/5
        System.out.println(new Fraction(-1, 11, 5).divide(new Fraction(-2, 3, 4))); // 1 9/55
        System.out.println(new Fraction(-1).divide(new Fraction(-2))); // 1/2
        System.out.println(new Fraction(-1).divide(new Fraction(1))); // -1
    }

    /**
     * Tests the Fraction.valueOf method
     */
    static void testValueOf(){
        System.out.println(Fraction.valueOf("1/2")); // 1/2
        System.out.println(Fraction.valueOf("10/-12")); // -5/6
        System.out.println(Fraction.valueOf("-120/2")); // -60
        System.out.println(Fraction.valueOf("-51/4")); // -12 3/4
        System.out.println(Fraction.valueOf("0/5")); // 0
        System.out.println(Fraction.valueOf("-0/5")); // 0
        System.out.println(Fraction.valueOf("-4/2")); // -2
        System.out.println(Fraction.valueOf("-1204/120")); //-10 1/30
        System.out.println(Fraction.valueOf("-2 4/18")); // -2 2/9
        System.out.println(Fraction.valueOf("-2")); // -2
        System.out.println(Fraction.valueOf("-2 110/18")); // -8 1/9
    }

    public static void main(String[] args) {
        testValueOf();
        testAdd();
        testSubtract();
        testMultiply();
        testDivide();
    }
}
