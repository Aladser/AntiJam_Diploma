package org.views;

import java.util.BitSet;

/**
 * Показ битового массива
 * @author Aladser
 */
public abstract class ArrayShow {
    /*
    array - массив для показа
    number - число бит
    separator - количество бит для группировки
    name - имя массива
    */
    public static String show(BitSet array, int number, int separator, String name){
        String result = name + "\n";
        for(int i=0; i<number; i++){
            if( i%separator == 0 && i!=0) result += " ";
            result += (array.get(i) == true) ? 1 : 0;
        }
        return result;
    }
}
