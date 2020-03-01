class p3
{
    public static void main(String[] args){
        int n = 100;
        int s = 0;
        for (int i = 3; i <= n; i++)
            if (i % 5 == 0 || i % 3 == 0)
                s += i;
        System.out.println(s);

    }
}