package org.views;

import java.util.BitSet;

/**
 * Показ битового массива
 */
public abstract class ArrayShow {
    /**
     * Возвращает указанный массив длиной n в виде строки
     * @param array массив
     * @param n число показываемых бит
     * @param separator группировка по separator бит
     * @param name имя массива
     * @return 
     */
    public static String show(BitSet array, int n, int separator, String name){
        String result = name + "\n";
        for(int i=0; i<n; i++){
            if( i%separator == 0 && i!=0) result += " | ";
            if( i%(separator*5) == 0 && i!=0) result += "\n";
            result += (array.get(i) == true) ? 1 : 0;
        }
        result+="\n\n";
        return result;
    }
}
