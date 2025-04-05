package CS.Learning_Java.Java_beginner_2h_250404_250405;

import java.util.Scanner;
public class Condition_statement {
    public static void main(String[] args) {
        // ==
        int x = 1;
        Boolean isRight = x< 2 && x >= 1; // From left to right
        // ||: or
        // !: not
        if (x<=2){

        }else if (x > 3){

        }else{

        }
        String income = x > 2 ? "Yes" : "No";

        switch (income) {
            case "Yes":
                
                break;
        
            case "No":
                break;

            default:
                System.out.println("Hey");
        }
        // mini-program
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number: ");
        int number = scanner.nextInt();
        // put specific conditions on the above;
        // more general ones are at bottom
        if (number%5 == 0 && number %3 == 0) {
            System.out.println("FizzBuzz");
        }else if (number%3 ==0){
            System.out.println("Buzz");
        }else if (number%5 ==0){
            System.out.println("Fizz");
        }else{
            System.out.println(number);
        }
        // Another way
        if (number%5 == 0){
            if (number%3 == 0){
                System.out.println("FizzBuzz");
            }else{
                System.out.println("Fizz");
            }
        }
        // Loops
        for (int i = 0; i< 5; i++){//i--
            System.out.println("Hello World");
        }
        int i = 0;
        while(i>=0){
            System.out.println("Hi");
            i--;
        }
        // != compares memory address

        String input = "";
        Scanner scanner_2 = new Scanner(System.in);// inside loop will create more Scanners unnecessarily
        while (true){
            System.out.println("Input: ");
            input = scanner_2.next().toLowerCase();
            if (input.equals("quit")){
                continue;
            }
            if (input.equals("quit")){
                break; // true: break
            }
            System.out.println(input);
        }
        // Do while loop: at least executed once
        do{
            System.out.println("Input: ");
            input = scanner_2.next().toLowerCase();
            System.out.println(input);
        }while(!input.equals("quit"));

        String[] fruits = {"Apple", "Orange", "Banana"};

        for (int i = 0; i < fruits.length; i++){// i = fruits.length; i >0; i--
            System.out.println(fruits[i]);
        }

        for(String fruit: fruits){
            System.out.println(fruit);
        }

  
    }
}
