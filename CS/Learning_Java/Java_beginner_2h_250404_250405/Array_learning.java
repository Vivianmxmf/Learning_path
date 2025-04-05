package CS.Learning_Java.Java_beginner_2h_250404_250405;


import java.util.Arrays;

public class Array_learning {
    public static void main(String[] args){
        int[] numbers = new int[5];
        numbers[0] =1;
        numbers[1] =2;
        System.out.println(numbers);// print out the address
        
        System.out.println(Arrays.toString(numbers));// the content

        int[] number = {1,2,3,4,5};
        System.out.println(numbers.length);

        int[][] num = new int[2][3];// int[][] num = {{1,2,3},{4,5,6}};
        num[0][0] =1;

        final float PI =3.14F;// PI is a constant now
        // math expression:  +,-,*,/(int returns int (floor))
        int result = 10 / 3;//returns 3
        double result_1 = (double) 10 /(double) 3;

        int x= 1;
        ++x;// increment first
        x++;// increment later

        //casting
        //implicit casting
        // byte > short> int>long>float>double
        short y = 1;// 2 bytes
        int z = y+2; // z is 3 : int is 4 bytes
        int z_2 = (int)y +2;// explicit casting

        String i = "1";
        Short.parseShort(i);
        Integer.parseInt(i);

}
