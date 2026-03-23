public class Conversion {

    /** Foot to meter conversion*/
    public static double footToMeter(double foot) {
        return 0.305 * foot;
    }

    /** Meter to foot conversion */
    public static double meterToFoot(double meter) {
        return 3.279 * meter;
    }

    public static void main(String[] args) {
        System.out.println("Java Exercise 6.9 - Conversions");
        System.out.println("Feet     Meters   |   Meters     Feet");
        System.out.println("-----------------------------------------");

        double foot = 1.0;
        double meter = 20.0;

        for (int i = 0; i < 10; i++) {
            double metersConverted = footToMeter(foot);
            double feetConverted = meterToFoot(meter);

            System.out.printf("%4.1f     %6.3f   |   %6.1f     %6.3f%n",
                    foot, metersConverted, meter, feetConverted);
      
            foot++;           
            meter += 5;
        }
    }
}