package org.views;

import java.util.BitSet;
import org.models.ImageBits;

/**
 * Показ битового массива
 * @author Aladser
 */
public class ArrayShow {
    public static void show(BitSet array, int number, int separator, String name){
        System.out.println(name);
        for(int i=0; i<number; i++){
            if( i%separator == 0 && i!=0) System.out.print(" ");            
            System.out.print( ImageBits.getBit( array.get(i)) );
        }
    }
}
