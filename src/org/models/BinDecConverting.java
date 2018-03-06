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
        System.out.print(number+" = ");
        BitSet result = new BitSet();
        int orderNumber = 0; // число двоичных разрядов
        if(number==0) orderNumber=1;
        int a = number;
        while(a != 0){
            a >>= 1;
            orderNumber++;
        }
        int order = (int) Math.pow(2, orderNumber-1); // старший разряд
        result.set(orderNumber);   
        a = number;
        for(int i=0; i<orderNumber; i++){
            if(a >= order){
                result.set(i);
                a -= order; 
            }
            order /= 2;
        }
        return result;
    }
    
    /**
     * Двоичное число в десятичное
     * @param number
     * @return 
     */
    public static int binToDec(BitSet number){
        int result = 0;
        // highDegree = (размер массива-1)
        int highDegree = number.length() - 2;
        for(int i=0; i<highDegree+1; i++) result += (number.get(i)?1:0) * Math.pow(2, highDegree-i);
        return result;
    }
}
