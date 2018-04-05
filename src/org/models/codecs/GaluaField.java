package org.models.codecs;

/**
 * Арифметика полей Галуа
 */
public class GaluaField {
    private final int Z;              // неприводимый многочлен
    private final int P;              // число элементов поля Галуа
    private final int[] FIELD;        // поле Галуа
    private final int[] GX = {4,6,1}; // G(x)
    
    public GaluaField(int z, int p){
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
    
    public int multilpy(int num1, int num2){
        int exp = FIELD[num1] + FIELD[num2];
        if(exp >= P-1) exp-=P-1;
        return indexOf(FIELD, exp);
    }
    
    /**
     * Умножение полиномов в арифметике поля Галуа
     * @param pol1
     * @param pol2
     * @return 
     */
    public int[] multilpyPolynoms(int[] pol1, int[]pol2){
        int[] res;
        // размер произведения = сумма старших степеней + 1
        res = new int[pol1.length + pol2.length - 1];
        for(int i=0; i<pol1.length; i++){
            for(int j=0; j<pol2.length; j++)
                // умножение к-ов в поле Галуа
                // индекс = сумма индексов
                res[i+j] ^= multilpy(pol1[i], pol2[j]);
        }
        
        for(int i=0; i<res.length; i++){
            System.out.print(res[i]);
            if(i<res.length-1)System.out.print(",");
        }
        System.out.println();
        
        return res;
    }
    
    
}
