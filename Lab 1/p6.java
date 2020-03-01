class p6
{
    static int fib(int a)
    {
        if (a <= 1)
            return a;
        else
            return fib(a - 1) + fib(a - 2);
    }
    public static void main(String[] args){
        System.out.println(fib(4));

    }
}