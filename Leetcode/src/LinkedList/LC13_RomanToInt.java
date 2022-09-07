package Leetcode.src.LinkedList;

import java.util.HashMap;
import java.util.Map;

public class LC13_RomanToInt {
    public int romanToInt(String s)
    {
        int i,n=0,j;
        for(i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            if(ch=='C' &&(i+1<s.length()) && (s.charAt(i+1)=='D'))
            {
                n+=400;
                i++;
            }
            else if(ch=='C' &&(i+1<s.length()) && s.charAt(i+1)=='M')
            {
                n+=900;
                i++;
            }
            else if(ch=='X' &&(i+1<s.length()) && s.charAt(i+1)=='L')
            {
                n+=40;
                i++;
            }
            else if(ch=='X' &&(i+1<s.length()) && s.charAt(i+1)=='C')
            {
                n+=90;
                i++;
            }
            else if(ch=='I' &&(i+1<s.length()) && s.charAt(i+1)=='V')
            {
                n+=4;
                i++;
            }
            else if(ch=='I' &&(i+1<s.length()) && s.charAt(i+1)=='X')
            {
                n+=9;
                i++;
            }
            else if(ch=='M')
                n+=1000;
            else if(ch=='D')
                n+=500;
            else if(ch=='C')
                n+=100;
            else if(ch=='L')
                n+=50;
            else if(ch=='X')
                n+=10;
            else if(ch=='V')
                n+=5;
            else
                n+=1;
        }
        return n;
    }

    public int romanToInt2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        map.put('0', 0);

        s = s + "0";

        int num = 0;
        for(int i =0; i< s.length()-1; ){
            Character curr = s.charAt(i);
            Character next = s.charAt(i+1);
            int curVal = map.get(curr);
            int nextVal = map.get(next);
            if(curr == 'I' && (next == 'V' || next == 'X')){
                num = num + nextVal - curVal;
                i += 2;
            }else if(curr == 'X' && (next == 'L' || next == 'C')){
                num = num + nextVal - curVal;
                i += 2;
            }else if(curr == 'C' && (next == 'D' || next == 'M')){
                num = num + nextVal - curVal;
                i += 2;
            }else{
                num += curVal;
                i++;
            }

        }
        return num;
    }

    public int romanToInt3(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        s = s.replaceAll("IV", "IIII");
        s = s.replaceAll("IX", "VIIII");
        s = s.replaceAll("XL", "XXXX");
        s = s.replaceAll("XC", "LXXXX");
        s = s.replaceAll("CD", "CCCC");
        s = s.replaceAll("CM", "DCCCC");

        int num = 0;
        for(char c: s.toCharArray()){
            num += map.get(c);
        }
        return num;
    }
}
