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
    
    /**
     * Умножение элементов поле Галуа
     * @param num1
     * @param num2
     * @return 
     */
    public int multilpy(int num1, int num2){
        if(num1==0 || num2==0) return 0;
        int exp = FIELD[num1] + FIELD[num2];
        if(exp >= P-1) exp-=P-1;
        return indexOf(FIELD, exp);
    }
    
    /**
     * Деление элементов поле Галуа
     * @param num1
     * @param num2
     * @return 
     * @throws org.models.codecs.GaluaField.ZeroDivisionException 
     */
    public int divide(int num1, int num2) throws ZeroDivisionException{
        if(num2==0) throw new ZeroDivisionException();
        if(num1==0) return 0;
        int exp = FIELD[num1] - FIELD[num2];
        if(exp < 0) exp+=P-1;        
        return indexOf(FIELD, exp);
    }
    
    /**
     * Исключение деления на ноль
     */
    public class ZeroDivisionException extends Exception{
        public ZeroDivisionException(){super("Делить на 0 нельзя");}
    }
    
    /**
     * Умножение полиномов в арифметике поля Галуа
     * @param pol1
     * @param pol2
     * @return 
     */
    public int[] multilpyPolynoms(int[] pol1, int[]pol2){
        int[] res;
        
        System.out.print("[");
        for(int i=0; i<pol1.length; i++){
            System.out.print(pol1[i]);
            if(i<pol1.length-1) System.out.print(",");
        }
        System.out.print("] * [ ");
        for(int i=0; i<pol2.length; i++){
            System.out.print(pol2[i]);
            if(i<pol2.length-1) System.out.print(",");
        }
        System.out.print("] = ");
        
        // размер произведения = сумма старших степеней + 1
        res = new int[pol1.length + pol2.length - 1];
        for(int i=0; i<pol1.length; i++){
            for(int j=0; j<pol2.length; j++)
                // умножение к-ов в поле Галуа
                // индекс = сумма индексов
                res[i+j] ^= multilpy(pol1[i], pol2[j]);
        }
        
        System.out.print("[");
        for(int i=0; i<res.length; i++){
            System.out.print(res[i]);
            if(i<res.length-1)System.out.print(",");
        }
        System.out.println("]");
        
        return res;
    }
    
    public void dividePolynoms(int[] division, int[]divider){
        System.out.print(polynomToString(division));
        System.out.print(" / ");
        System.out.print(polynomToString(divider));
        int di1 = division.length - 1; // к-т делимого старшей степени
        final int di2 = divider.length - 1;  // к-т делителя старшей степени
        int qi = di1-di2;            // к-т частного старшей степени
        int[] quot = new int[qi+1];
        System.out.print(" = ");
        System.out.print(polynomToString(quot));
        System.out.println("\n\n\n\n\n\n\n\n\n");
        
        int ind;
        int k;
        int[] mult;
        while(qi>=0){
            k = division[di1] / divider[di2];
            ind = di1 - di2;
            quot[qi--] = k;
            mult = new int[ind+1];
            mult[mult.length-1] = k;
            division = multilpyPolynoms(divider, mult);
            
            break;
        }
        
    }
    
    public String polynomToString(int[] pol){
        String res = "[";
        for(int i=0; i<pol.length; i++){
            res += pol[i];
            if(i<pol.length-1)res+=",";
        }
        res += "]";
        return res;
    }
    
}
