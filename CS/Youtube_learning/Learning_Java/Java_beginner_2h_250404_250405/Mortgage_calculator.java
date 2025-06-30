package CS.Learning_Java.Java_beginner_2h_250404_250405;

import java.util.Scanner;
import java.lang.Math;
import java.lang.String;
import java.text.NumberFormat;


public class Mortgage_calculator {

    final byte MONTHS_IN_YEAR = 12;
    final byte PERCENT = 100;

    Scanner scanner = new Scanner(System.in);

    // System.out.print("Principal: ");
    // int principal = scanner.nextInt();

    // System.out.print("Annual Interest Rate: ");
    // float annualInterest = scanner.nextFloat();
    // float monthlyInterest = annualInterest/PERCENT/MONTHS_IN_YEAR;

    
    // System.out.print("Period (Years):  ");
    // byte years = scanner.nextByte();
    // int numberOfPayments = years * MONTHS_IN_YEAR;

    // double mortgage = principal *(monthlyInterest* Math.pow(1+monthlyInterest,numberOfPayments))/
    // (Math.pow(1+monthlyInterest, numberOfPayments)-1);

    // String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
    // System.out.println("Mortgage: " + mortgageFormatted);

    int principal = 0;
    while(true){
        System.out.print("Principal: ");
        principal = scanner.nextInt();
        if (principal >= 1000 && principal<= 1000000){
            break; 
        }
        System.err.println("Enter a value bewteen 1000 and 1000000");
    }

}