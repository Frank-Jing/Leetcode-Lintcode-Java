package Leetcode.src.MathBitOther;

public class LC470_Rand10UsingRand7 {
    public int rand10() {
        int a,b;
        do{
            a = rand7();
        }while(a==7);

        do{
            b = rand7();
        }while(b > 5);

        return (a&1) == 1 ? b : b+5;
    }

    public int rand7(){
        int max = 7;
        int min = 1;
        int range = max - min + 1;

        return (int)(Math.random() * range) + min;
    }
}
