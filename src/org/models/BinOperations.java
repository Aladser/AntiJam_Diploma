package org.models;
import java.util.BitSet;

/**
 * Двоично-десятичные переводы
 * @author Aladser
 */
public abstract class BinOperations {
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
    
    /**
     * Считает число двоичных разрядов
     * @param num
     * @return 
     */
    public static int countBinaryOrders(int num){
        if(num==0 || num==1) return 1;
        int numOrders = 0;
        while(num != 0){
            num >>= 1;
            numOrders++;
        }
        return numOrders;
    }
    
    /**
     * Сравнивает два BitSet
     * @param arr1 первый массив
     * @param arr2 второй массив
     * @return число разных разрядов
     */
    public static int equalBitSets(BitSet arr1, BitSet arr2){
        if(arr1.length() != arr2.length()) return -1;
        int result = 0;
        for(int i=0; i<arr1.length()-1; i++){
            if(arr1.get(i) != arr2.get(i))result++;
        }
        return result;
    }
    
    /**
     * Добавляет недостающие нули коду
     * @param code код
     * @param numOrders требуемая разрядность
     * @return 
     */
    public static BitSet addZeroToCode(BitSet code, int numOrders){
        BitSet res = new BitSet();
        res.set(numOrders);
        int j = numOrders - code.length() + 1;
        for(int i=0; i<code.length()-1; i++, j++)
            if(code.get(i)) res.set(j);
        return res;
    }
    
    /**
     * Один сдвиг битов влево
     * @param number число
     * @param orders разрядность числа
     * @return 
     */
    public static int shifLefttBits(int number, int orders){
        int highOrd = (int) Math.pow(2, orders);
        number <<= 1;
        if(number >= highOrd) number -= highOrd-1;
        return number;
    }
    
    /**
     * Один сдвиг битов вправо
     * @param number число
     * @param orders разрядность числа
     * @return 
     */
    public static int shifRightBits(int number, int orders){
        if(number<<31!=0) number += Math.pow(2, orders);
        return number>>=1;
    }
}
