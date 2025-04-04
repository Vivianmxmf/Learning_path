package CS.Learning_Java.Java_beginner_2h_250404.HelloWorld.src;

import java.util.Date;
import java.awt.*;
public class Main {
    public static void main(String[] args) { // void: not returning any value
        int age = 30;// Have a type when defining the variable// primitive type: short, int, long, float, double,char, boolean
        age = 35;
        long viewsCount = 3_123_456_789L;
        float price = 10.99F;
        char letter = 'A';
        byte num = 1;
        Date now = new Date();
        now.getTime();
        Point pt1 = new Point(1,1);// reference to the same object, but the primitive only copys value (they are independent)
        Point pt2 = pt1;

        //String is reference type in java
        String message = "Hello World" + "!";
        System.out.println(message.endsWith("!"));// returns a boolean, same to startWith; length();indexOf("H")(DNE: -1); replace("!","*")(return a new string since string is immutable);
        //toUppercase();trim()(some space before the string)
        // \n(a new line) \t(a tab)
        System.out.println("Hello, World!");//sout+ tab
    }
}