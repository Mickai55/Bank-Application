class p4
{
    public static int fact(int a)
    {
        int r = 1;
        for (int i = 1; i <= a; i++)
            r *= i;
        return r;
    }
    public static void main(String[] args){
        int n = 4;
        System.out.println(fact(n));

    }
}