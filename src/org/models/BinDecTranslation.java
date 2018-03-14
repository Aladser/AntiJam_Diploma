package org.models;
import java.util.BitSet;

/**
 * Двоично-десятичные переводы
 * @author Aladser
 */
public abstract class BinDecTranslation {
    /**
     * Десятичное => двоичное
     * @param number число
     * @return 
     */
    public static BitSet decToBin(int number){     
        BitSet result = new BitSet();
        int numOrders = countBinaryOrders(number);
        result.set(numOrders);
        int exp = (int) Math.pow(2, numOrders - 1); 
        int ind = 0;
        while(number != 0){
            if(number >= exp){
                result.set(ind);
                number -= exp;
            }
            ind++;
            exp/=2;
            numOrders--;
        }
        return result;
    }
       
    /**
     * Десятичное => двоичное
     * @param number число
     * @param k требуемая разрядность
     * @return 
     */
    public static BitSet decToBin(int number, int k){     
        BitSet result = new BitSet();
        result.set(k);
        int numOrders = countBinaryOrders(number);
        int exp = (int) Math.pow(2, numOrders - 1); 
        int ind = k - numOrders;
        while(number != 0){
            if(number >= exp){
                result.set(ind);
                number -= exp;
            }
            ind++;
            exp/=2;
            numOrders--;
        }
        return result;
    }
    
    /**
     * Двоичное => десятичное
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
    
    // Считает число двоичных разрядов
    public static int countBinaryOrders(int num){
        if(num==0 || num==1) return 1;
        int numOrders = 0;
        while(num != 0){
            num >>= 1;
            numOrders++;
        }
        return numOrders;
    }
}
