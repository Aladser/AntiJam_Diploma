package org.views;

import java.util.BitSet;
import org.models.ImageBits;

/**
 * Показ битового массива
 * @author Aladser
 */
public class ArrayShow {
    public static String show(BitSet array, int number, int separator, String name){
        String result = name + "\n";
        for(int i=0; i<number; i++){
            if( i%separator == 0 && i!=0) result += " ";
            result += ImageBits.getBit( array.get(i));
        }
        return result;
    }
}
