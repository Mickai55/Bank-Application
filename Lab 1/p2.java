import java.util.Scanner;

class p2
{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.print("Input first integer:");
        int a = in.nextInt();
        System.out.print("Input second integer:");
        int b = in.nextInt();

        System.out.println(a != b);
        System.out.println(a < b);
        System.out.println(a <= b);

    }
}