package org.models;

import java.util.BitSet;

/**
 * Деление полиномов
 * @author Aladser
 */
public abstract class PolynomDivision {
    public static void execute(BitSet arr){
        int n = 7;                            // длина кодового слова
        int k = 4;                            // длина инфокодового слова
        int divider = 0b1011;                 // делитель
        int[] quotient = new int[k];          // частное
        int reminder = 0;                     // остаток
        
        // result[0] - частное деления
        // result[1] - остаток деления
        int[] result = new int[2];            // {частное, остаток}
        
        // Деление полиномов: поиск первой единицы делимого
        // Количество шагов цикла = n-k - столько раз можно сдвинуться до конца массива
        // (arr.get(i)?1:0)
        int i=0, j=0;
        for(; i < (n-k+1); i++){
            if(arr.get(i)){
                int division = 8*(arr.get(i)?1:0) + 4*(arr.get(i+1)?1:0) + 2*(arr.get(i+2)?1:0) + (arr.get(i+3)?1:0);
                reminder = division ^ divider;
                System.out.print( Integer.toBinaryString( division ) + " / 1011 = ");
                i += (k-1);
                quotient[j++]=1;
                break;
            }
            quotient[j++]=0;
        }
        System.out.println(Integer.toBinaryString(reminder) + "\n-----");
        
        // Деление полиномов (4,3): продолжение
        while( i < n-1 ){
            // reminder = XXX
            if(reminder > 3){
                if( i+1 >= n ) break;
                System.out.println("XXX");
                reminder <<= 1;
                reminder += (arr.get(i+1)?1:0);
                i++;
                quotient[j++]=1;
            }
            // reminder = XX
            else if(reminder > 1){
                if( i+2 >= n ) break;
                System.out.println("XX");
                reminder <<= 2;
                reminder += 2 * (arr.get(i+1)?1:0);
                reminder += (arr.get(i+2)?1:0);
                i+=2;
                quotient[j++]=0;
                quotient[j++]=1;
            }
            // reminder = 1
            else if(reminder == 1){
                if( i+3 >= n ) break;
                System.out.println("X");
                reminder <<= 3;
                reminder += 4 * (arr.get(i+1)?1:0);
                reminder += 2 * (arr.get(i+2)?1:0);
                reminder += (arr.get(i+3)?1:0);
                i+=3;
                quotient[j++]=0;
                quotient[j++]=0;
                quotient[j++]=1;
            }
            // reminder = 0
            else{
                if( i+3 >= n ) break;
                System.out.println("0-----");
                j++;
                i+=3;
                quotient[j++]=0;
            }
            
            System.out.print( Integer.toBinaryString( reminder ) + " / 1011 = ");
            reminder ^= divider;
            System.out.println( Integer.toBinaryString(reminder) + "\n-----");
        }
        
        System.out.print( (arr.get(0)?1:0) );
        System.out.print( (arr.get(1)?1:0) );
        System.out.print( (arr.get(2)?1:0) );
        System.out.print( (arr.get(3)?1:0) );
        System.out.print( (arr.get(4)?1:0) );
        System.out.print( (arr.get(5)?1:0) );
        System.out.print( (arr.get(6)?1:0) );
        System.out.print( " / 1011 = " );
        result[0] = NumberCoup.execute( 8*quotient[0] + 4*quotient[1] + 2*quotient[2] + quotient[3], 2, 4 );
        result[1] = reminder;                                                                                
        if(result[0] > 7)
            System.out.print("");
        else if(result[0] > 3)
            System.out.print("0");
        else if(result[0] > 1)
            System.out.print("00");
        else
            System.out.print("000");
        System.out.print( Integer.toBinaryString(result[0]) );
        System.out.println( " (ост. " + Integer.toBinaryString(result[1]) + ")" );
    }        
}
