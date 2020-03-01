import java.lang.Math;

class p5
{
    static boolean prim(int a)
    {
        if (a < 2)
            return false;
        else
            for (int i = 2; i < Math.sqrt(a); i++)
                if (a % i == 0)
                    return false;
        return true;
    }
    public static void main(String[] args){
        System.out.println(prim(12));

    }
}