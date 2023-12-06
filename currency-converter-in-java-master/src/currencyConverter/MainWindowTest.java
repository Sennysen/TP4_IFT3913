package currencyConverter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowTest {

    @org.junit.jupiter.api.Test
    void convertNoir() {
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
    @org.junit.jupiter.api.Test
    void convertBlanche() {
        /*
        Test boite blanche:
        currencies  = {USD, GBP, EUR, CHF, CHY, JPY}
        currencyConverter.MainWindow.convert(String, String, ArrayList<Currency>, Double)

        cas 1: currency1 not in currencies currency 2 not in currencies
        cas 2: currency1 not in currencies currency 2 in currencies

        cas 3: currency1 in currencies and currency2 not in currencies
        cas 4: currency1 in currencies & currency2 in currencies
         */

        ArrayList<currencyConverter.Currency> currencies = currencyConverter.Currency.init();

        // Print each currency in the array list

        System.out.println("Test Boite Blanche currencyConverter.MainWindow.convert");
        System.out.println("------------------------------------------------");
        //cas 1: currency1 not in currencies currency 2 not in currencies
        double amt = 100.00;


        String currency1 = "Australian Dollar";
        String currency2 = "Canadian Dollar";

        String title = "cas 1: currency1 not in currencies currency 2 not in currencies";
        MainWindowTest.this.Test2String(currency1,currency2,amt,title);
        //cas 2: currency1 not in currencies currency 2 in currencies suppose to give the same result as cas 1

        currency1 = "Australian Dollar";
        currency2 = "Japanese Yen";

        title = "cas 2: currency1 not in currencies currency 2 in currencies";
        MainWindowTest.this.Test2String(currency1,currency2,amt,title);

        //cas 3: currency1 in currencies & currency2 not in currencies
        currency1 = "US Dollar";
        currency2 = "Canadian Dollar";

        title = "cas 3: currency1 in currencies & currency2 not in currencies";
        MainWindowTest.this.Test2String(currency1,currency2,amt,title);

        //cas 4: currency1 in currencies & currency2 in currencies
        currency1 = "US Dollar";
        currency2 = "Euro";

        title = "cas 4: currency1 in currencies & currency2 in currencies";
        MainWindowTest.this.Test2String(currency1,currency2,amt,title);

        // couverture des i chemins
        System.out.println("------------------------------------------------");
        System.out.println("couverture des i chemins");


        ArrayList<currencyConverter.Currency> currencies1 = new ArrayList<currencyConverter.Currency>();
        //on saute la boucle → Cas de test: currencies = []
        System.out.println("on saute la boucle → Cas de test: currencies = []");
        System.out.println("Size of currencies" + currencies1.size() +"Result: " + MainWindow.convert(currency1, currency2, currencies, amt));

        currencies1.add( new currencyConverter.Currency("US Dollar", "USD") );
        for (Currency currency : currencies) {
            currency.defaultValues();
        }
        System.out.println("une itération de la boucle → Cas de test: T = [e1]");
        System.out.println("Size of currencies" + currencies1.size() +"Result: " + MainWindow.convert(currency1, currency2, currencies, amt));

        currencies1.add( new currencyConverter.Currency("Chinese Yuan Renminbi", "CNY") );
        for (Currency currency : currencies) {
            currency.defaultValues();
        }
        System.out.println("deux itérations → Cas de test: T = [e1, e2]");
        System.out.println("Size of currencies" + currencies1.size() +"Result: " + MainWindow.convert(currency1, currency2, currencies, amt));

        currencies1.add( new Currency("Euro", "EUR") );
        currencies1.add( new Currency("British Pound", "GBP") );
        currencies1.add( new Currency("Swiss Franc", "CHF") );
        currencies1.add( new Currency("Japanese Yen", "JPY") );
        for (Currency currency : currencies) {
            currency.defaultValues();
        }
        System.out.println("m itérations (m < 6) → Cas de test: T = [e1 , e2 ,..em]");
        System.out.println("Size of currencies" + currencies1.size() +"Result: " + MainWindow.convert(currency1, currency2, currencies, amt));


        currencies1.add( new Currency("Australian Dollar", "AUD") );
        currencies1.add( new Currency("Canadian Dollar", "CAD") );
        currencies1.add( new Currency("Peso Mexicain", "MXN") );
        System.out.println("7, 8 et 9 iterations → Cas de test:\n" +
                "    T = [e1,e2,..em,.. e7]\n" +
                "    T = [e1,e2,..em,.., e7, e8]\n" +
                "    T = [e1,e2,..em,.., e7, e8, e9]");
        System.out.println("Size of currencies" + currencies1.size() +"Result: " + MainWindow.convert(currency1, currency2, currencies, amt));



    }


    protected void Test2String(String currency1, String currency2, Double amt, String title){
        ArrayList<currencyConverter.Currency> currencies = currencyConverter.Currency.init();

        System.out.println(title);
        System.out.println("------------------------------------------------");
        System.out.println("currency1: " + currency1 + ", currency2: " + currency2 + ", amount: " + amt);
        System.out.println("Result: " + MainWindow.convert(currency1, currency2, currencies, amt));
    }
}