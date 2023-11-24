package currencyConverter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowTest {

    @org.junit.jupiter.api.Test
    void convert() {
        /*
        Test boite noire:
        Approche 1 - partition du domain:
        D1 = {0 <= d <= 1 000 000}, D2 = {0 > d}. D3 = {1000000 < d}
        DIVISE1 = {USD, CAD, GBP, EUR, CHF, AUD}, DIVISE2 = {CHY, JPY}, DIVISE3 = {TEST1, TEST2}
         */
        Random random = new Random();
        Double d1 = Double.parseDouble(String.format("%.1f",random.nextDouble(1000000 + 1)));
        Double d2 =  - Double.parseDouble(String.format("%.1f",random.nextDouble(1000000)));
        Double d3 =  Double.parseDouble(String.format("%.1f",random.nextDouble(10000000 - 1000000 + 1) + 1000000));
        String[] DIVISE1 = {"US Dollar", "Canadian Dollar", "British Pound", "Euro", "Swiss Franc", "Austrilian Dollar"};
        String[] DIVISE2 = {"Chinese Yuan Renminbi", "Japanese Yen", "TEST1", "TEST2"};
        Double d_frontier1 = -0.1;
        Double d_frontier2 = 0.0;
        Double d_frontier3 = 1000000.0;
        Double d_frontier4 = 1000000.1;
        String div1 = DIVISE1[random.nextInt(DIVISE1.length)];
        String div2 = DIVISE1[random.nextInt(DIVISE1.length)];
        String div3 = DIVISE2[random.nextInt(DIVISE2.length)];

        ArrayList<currencyConverter.Currency> cur = currencyConverter.Currency.init();

        System.out.println("Test currencyConverter.MainWindow.convert");
        String valideDiv1 = "";
        String valideDiv2 = "";
        //partition du domaine des entrées en classes d’équivalence
        int i = 0;
        while(i < 10) {
            System.out.println("-----------------Iteration: " + (i + 1) + "-------------");
            System.out.println("currency1: " + div1 + ", currency2: " + div2 + ", amount: " + d1);
            System.out.println("Result: " + MainWindow.convert(div1, div2, cur, d1));

            System.out.println("currency1: " + div2 + ", currency2: " + div3 + ", amount: " + d1);
            System.out.println("Result: " + MainWindow.convert(div2, div3, cur, d1));


            System.out.println("currency1: " + div1 + ", currency2: " + div2 + ", amount: " + d2);
            System.out.println("Result: " + MainWindow.convert(div1, div2, cur, d2));

            System.out.println("currency1: " + div2 + ", currency2: " + div3 + ", amount: " + d2);
            System.out.println("Result: " + MainWindow.convert(div2, div3, cur, d2));


            System.out.println("currency1: " + div1 + ", currency2: " + div2 + ", amount: " + d3);
            System.out.println("Result: " + MainWindow.convert(div1, div2, cur, d3));

            System.out.println("currency1: " + div2 + ", currency2: " + div3 + ", amount: " + d3);
            System.out.println("Result: " + MainWindow.convert(div2, div3, cur, d3));

            if(valideDiv1.equals("") && MainWindow.convert(div1, div2, cur, d1) != 0.0)
            {
                valideDiv1 = div1;
                valideDiv2 = div2;
            }
            div1 = DIVISE1[random.nextInt(DIVISE1.length)];
            div2 = DIVISE1[random.nextInt(DIVISE1.length)];
            div3 = DIVISE2[random.nextInt(DIVISE2.length)];
            i++;
        }

        System.out.println("------------------------------------------------");
        //analyse des valeurs frontières
        if(!valideDiv1.equals("")) {
            System.out.println("Border value test 1: currency1: " + valideDiv1 + ", currency2: " + valideDiv2 + ", amount: " + d_frontier1);
            System.out.println("Result: " + MainWindow.convert(valideDiv1, valideDiv2, cur, d_frontier1));

            System.out.println("Border value test 1: currency1: " + valideDiv1 + ", currency2: " + valideDiv2 + ", amount: " + d_frontier2);
            System.out.println("Result: " + MainWindow.convert(valideDiv1, valideDiv2, cur, d_frontier2));

            System.out.println("Border value test 1: currency1: " + valideDiv1 + ", currency2: " + valideDiv2 + ", amount: " + d_frontier3);
            System.out.println("Result: " + MainWindow.convert(valideDiv1, valideDiv2, cur, d_frontier3));

            System.out.println("Border value test 1: currency1: " + valideDiv1 + ", currency2: " + valideDiv2 + ", amount: " + d_frontier4);
            System.out.println("Result: " + MainWindow.convert(valideDiv1, valideDiv2, cur, d_frontier4));
        }else {
            System.out.println("No valid divise found, Border value test failed");
        }
    }
}