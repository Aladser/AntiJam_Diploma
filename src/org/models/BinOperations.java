package org.models;
import java.util.BitSet;

/**
 * Операции над двоичными числами
 */
public abstract class BinOperations {
    /**
     * Десятичное => двоичное
     * @param number число
     * @return 
     */
    public static BitSet decToBin(int number){
        BitSet res = new BitSet();   
        int numOrders = countBinaryOrders(number);
        int exp = (int) Math.pow(2, numOrders - 1);    
        int ind = 0;
        res.set(numOrders); 
        while(number != 0){
            if(number >= exp){
                res.set(ind);
                number -= exp;
            }
            ind++;
            exp/=2;
        } 
        return res;
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
        int highDegree = number.length() - 2;
        int exp = (int) Math.pow(2, highDegree); // разряды десятичного числа
        for(int i=0; i<highDegree+1; i++){
            result += (number.get(i)?1:0) * exp;
            exp/=2;
        }
        return result;
    }
    
    /**
     * Считает число двоичных разрядов
     * @param number число
     * @return 
     */
    public static int countBinaryOrders(int number){
        if(number==0 || number==1) return 1;
        int numOrders = 0;
        while(number != 0){
            number >>= 1;
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
    
    /**
     * Возвращает битовый массив в виде строки
     * @param array массив
     * @param sep группировка по separator бит
     * @return 
     */
    public static String showBitSet(BitSet array, int sep){
        String result = "";
        for(int i=0; i<array.length()-1; i++){
            if( i%sep == 0 && i!=0) result += "|";
            result += array.get(i)?1:0;
        }
        return result;        
    }
    
    /**
    * Возвращает битовый массив в виде строки
    * @param array массив
    * @return 
    */
    public static String showBitSet(BitSet array){
        String result = "";
        for(int i=0; i<array.length()-1; i++) result += array.get(i)?1:0;
        return result;        
    }
}
