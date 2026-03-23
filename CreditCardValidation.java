import java.util.Scanner;

public class CreditCardValidation {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a credit card number: ");
        long number = input.nextLong();

        if (isValid(number)) {
            System.out.println(number + " is valid");
        } else {
            System.out.println(number + " is invalid");
        }
    }

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        return (getSize(number) >= 13 && getSize(number) <= 16) &&
               (prefixMatched(number, 4) ||
                prefixMatched(number, 5) ||
                prefixMatched(number, 6) ||
                prefixMatched(number, 37)) &&
               ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
    }

    /**Double every second digit from right to left */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = Long.toString(number);

        for (int i = num.length() - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(num.charAt(i));
            sum += getDigit(digit * 2);
        }
        return sum;
    }

    /**Return number if single, otherwise if the result is two digits, return sum of digits */
    public static int getDigit(int number) {
        if (number < 10)
            return number;
        return number / 10 + number % 10;
    }

    /**Return sum of odd place digits */
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String num = Long.toString(number);

        for (int i = num.length() - 1; i >= 0; i -= 2) {
            sum += Character.getNumericValue(num.charAt(i));
        }
        return sum;
    }

    /**Return true if the number d is a prefixfor number */
    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

    /**Return the number of digits in d */
    public static int getSize(long d) {
        return Long.toString(d).length();
    }

    /**Return the first k digits of number */
    public static long getPrefix(long number, int k) {
        String num = Long.toString(number);
        if (num.length() < k)
            return number;
        return Long.parseLong(num.substring(0, k));
    }
}
