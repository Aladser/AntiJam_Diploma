package org.models.codecs;

/**
 * Арифметика полей Галуа
 */
public class GaluaArith {
    private final int Z; // неприводимый многочлен
    private final int P; // число элементов поля Галуа
    private final int[] FIELD; // поле Галуа
    
    public GaluaArith(int z, int p){
        Z = z;
        P = p;
        FIELD = createField();
        //for(int i=0; i<FIELD.length; i++) System.out.println(i+" -> "+FIELD[i]);
    }
    
    private int[] createField(){
        int[] res = new int[P];
        res[0] = -1;
        int[] arr = new int[P];
        for(int i=0; i<P; i++) arr[i] = PolynomDivision.mod((int)Math.pow(2, i), Z);
        for(int i=1; i<P; i++) res[i] = indexOf(arr, i);
        return res;
    }
    
    private int indexOf(int[] array, int elem){
        for(int i=0; i<array.length; i++)if(array[i]==elem) return i;
        return -1;
    }
}
