package Classes.FractionCalculator;

class FractionCalculatorTesting {
    
    /**
     * Testing the solve method from the fraction calculator
     */
    static void solveTesting(){
        System.out.println(FractionCalculator.solve("9 + 12")); // 21
        System.out.println(FractionCalculator.solve("1/5 - 8/3 * 63 - 429/3 / 4 + 12/11 - 2.5")); // -204 211/220
        System.out.println(FractionCalculator.solve("3 9/2 + 7/3 - 12 * 9/2 * 1/3 / 2.4 + 3.8")); // 6 2/15
        System.out.println(FractionCalculator.solve("32/47 - 11/14 * 7/80 + 61.19 / 2 1/17")); // 30 438173/1316000
        System.out.println(FractionCalculator.solve("8/4 / 9 * 3.4 - 2")); // -1 11/45
        System.out.println(FractionCalculator.solve("-2.5 * 1/8 - 9.2 * -2/3")); // 5 197/240
        System.out.println((FractionCalculator.solve("9.8 * 3 / -5/2 + -12 + 19.3"))); // -4 23/50
        System.out.println(FractionCalculator.solve("9.726 * 1/2 - 11/10 / 3")); // -4 1489/3000
        System.out.println(FractionCalculator.solve("76.2 / 5/3 * 8.1 - 12")); // 358 83/25
        System.out.println(FractionCalculator.solve("1.93 - 12/19 * 22.5 / 16 - 42.09")); // -41 183/3800
    }

    public static void main(String[] args) {
        solveTesting();
    }

}
