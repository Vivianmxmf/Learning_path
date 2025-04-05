package CS.Learning_Java.Java_beginner_2h_250404;

import java.lang.Math;
import java.text.NumberFormat;
import java.util.Scanner;
public class Math_class {
    // Math.round();
    // Math.ceil();
    // Mathc.max();
    //Math.random(); *100(num is in range of 1-100)
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    String result = currency.format(1234567.891);
    System.out.println(result);// $1234567.891

    NumberFormat percent = NumberFormat.getPercentInstance();
    String result_2 = currency.format(0.1); // method chaining: NumberFormat percent = NumberFormat.getPercentInstance.format(0.1);
    System.out.println(result_2);// 10%

    // Reading input
    Scanner scanner = new Scanner(System.in);
    System.out.print("Age:");
    byte age = scanner.nextByte();//nextDouble;nextFloat()// String name = scanner.next()//nextLine() // trim() gets out of all spaces
    System.out.println("You are " + age);

     
}
