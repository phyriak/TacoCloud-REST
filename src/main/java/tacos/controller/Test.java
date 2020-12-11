package tacos.controller;

import tacos.model.Order;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by phyriak on 18/11/2020
 */
public class Test {
    public static void main(String[] args) throws ParseException {

   /*     String string = "2020-10-17 12:34:09";
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(string);


        Date test = new Date(new Date().getTime()+ (60*2*1000));

        System.out.println(new Date().toString());
        System.out.println(test.toString());
*/
        String qrCodeInput ="WTPWarszawa_A9561324343223";
        Long i = Long.parseLong(qrCodeInput.replaceAll("[^0-9]", ""));
        System.out.println("Result from qr Code: "+ i);


        String substring = qrCodeInput.substring(qrCodeInput.lastIndexOf("_") + 1);
        System.out.println("Result from qr Code: "+ substring);

        String sut = "379354508162306";
        Test test= new Test();
        boolean valid = test.isValid(sut);
        System.out.println("Test, is valid: "+valid);


        String lineNumberFromQRCode = test.getLineNumberFromQRCode(qrCodeInput);
        System.out.println(lineNumberFromQRCode);

    }


    private String getLineNumberFromQRCode(String qrCodeInput) {


        //Dopoki Warszawa nie wysle specyfikacji co jest w tym kodzie QR zwracamy pewna linie ktora tam istnieje
      return   qrCodeInput!=null?qrCodeInput.replaceAll("[^0-9]", "") : null;

    }
    public boolean isValid(String a) {
        if (a.isEmpty()) {
            return false;
        } else {
            long number = Long.parseLong(a);
            return (getSize(number) >= 13 &&
                    getSize(number) <= 16) &&
                    (prefixMatched(number, 4) ||
                            prefixMatched(number, 5) ||
                            prefixMatched(number, 37) ||
                            prefixMatched(number, 6)) &&
                    ((sumOfDoubleEvenPlace(number) +
                            sumOfOddPlace(number)) % 10 == 0);
        }
    }

    // Get the result from Step 2
    public static int sumOfDoubleEvenPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2)
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);

        return sum;
    }

    // Return this number if it is a single digit, otherwise,
    // return the sum of the two digits
    public static int getDigit(int number)
    {
        if (number < 9)
            return number;
        return number / 10 + number % 10;
    }

    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number)
    {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2)
            sum += Integer.parseInt(num.charAt(i) + "");
        return sum;
    }

    // Return true if the digit d is a prefix for number
    public static boolean prefixMatched(long number, int d)
    {
        return getPrefix(number, getSize(d)) == d;
    }

    // Return the number of digits in d
    public static int getSize(long d)
    {
        String num = d + "";
        return num.length();
    }

    // Return the first k number of digits from
    // number. If the number of digits in number
    // is less than k, return number.
    public static long getPrefix(long number, int k)
    {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }

}
