package currencyConverter;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    @Test
    void convert() {
        /*
        Test boite noire:
        Approche 1 - partition du domain:
        D1 = {0 <= d <= 1 000 000}, D2 = {0 > d}. D3 = {1000000 < d}
         */
        Random random = new Random();
        Double d1 = Double.parseDouble(String.format("%.1f",random.nextDouble(1000000 + 1)));
        Double d2 =  - Double.parseDouble(String.format("%.1f",random.nextDouble(1000000)));
        Double d3 =  Double.parseDouble(String.format("%.1f",random.nextDouble(10000000 - 1000000 + 1) + 1000000));
        Double ex_val =  Double.parseDouble(String.format("%.1f",random.nextDouble(1000000 + 1)));
        Double d_frontier1 = -0.1;
        Double d_frontier2 = 0.0;
        Double d_frontier3 = 1000000.0;
        Double d_frontier4 = 1000000.1;

        System.out.println("Amount: " + d1 + ", exchange value: " + ex_val);
        System.out.println("Result: " + Currency.convert(d1, ex_val));

        System.out.println("Amount: " + d2 + ", exchange value: " + ex_val);
        System.out.println("Result: " + Currency.convert(d2, ex_val));


        System.out.println("Amount: " + d3 + ", exchange value: " + ex_val);
        System.out.println("Result: " + Currency.convert(d3, ex_val));

        System.out.println("Border amount: " + d_frontier1 + ", exchange value: " + ex_val);
        System.out.println("Result: " + Currency.convert(d_frontier1, ex_val));


        System.out.println("Border amount: " + d_frontier2 + ", exchange value: " + ex_val);
        System.out.println("Result: " + Currency.convert(d_frontier2, ex_val));

        System.out.println("Border amount: " + d_frontier3 + ", exchange value: " + ex_val);
        System.out.println("Result: " + Currency.convert(d_frontier3, ex_val));

        System.out.println("Border amount: " + d_frontier4 + ", exchange value: " + ex_val);
        System.out.println("Result: " + Currency.convert(d_frontier4, ex_val));
    }
}