import java.lang.Math;

class p7
{
    static boolean prim(int a)
    {
        if (a < 2)
            return false;
        else
            for (int i = 2; i <= Math.sqrt(a); i++)
                if (a % i == 0)
                    return false;
        return true;
    }

    static int pfp(int n)
    {
        for (int i = n / 2 + 1; i >= 1; i--)
            if (n % i == 0 && prim(i))
                return i;
        return 0;
    }

    public static void main(String[] args){
        int n = 100;
        System.out.println(pfp(n));
        
    }
}