package org.models;

import java.util.BitSet;

/**
 * Двоично-десятичные преобразования
 * @author Aladser
 */
public abstract class BinDecConverting {
    /**
     * Десятичное число в двоичное
     * @param number
     * @return 
     */
    public static BitSet decToBin(int number){
        System.out.print(Integer.toBinaryString(number)+"(");
        int orderNumber = 0; // число двоичных разрядов
        if(number==0) orderNumber=1;
        int a = number;
        while(a != 0){
            a >>= 1;
            orderNumber++;
        }
        int order = (int) Math.pow(2, orderNumber-1); // старший разряд
        BitSet result = new BitSet();
        result.set(orderNumber);   
        a = number;
        for(int i=0; i<orderNumber; i++){
            if(a >= order){
                result.set(i);
                a -= order; 
            }
            order /= 2;
        }
        for(int j=0; j<orderNumber; j++) System.out.print(result.get(j)?1:0);
        System.out.println(")");
        return result;
    }
    
    public static int binToDec(BitSet number){
        return 1;
    }
}
