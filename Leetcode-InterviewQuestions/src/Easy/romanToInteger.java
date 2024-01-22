package Easy;

import java.util.HashMap;

public class romanToInteger {
    public static void main(String[] args) {
        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }
    public static int romanToInt(String s) {
        HashMap<Character,Integer> store=new HashMap();
        store.put('I',1);
        store.put('V',5);
        store.put('X',10);
        store.put('L',50);
        store.put('C',100);
        store.put('D',500);
        store.put('M',1000);
        int sum=0,l=s.length();
        for (int i = 0; i < l; i++) {
         if (i+1<l && store.get(s.charAt(i))< store.get(s.charAt(i+1))){
             sum-=store.get(s.charAt(i));
         }else{
             sum+=store.get(s.charAt(i));
         }
        }
        return sum;
    }
}
