package org.models;

/**
 * Поле Галуа
 */
public class GaluaField {
    private final int Z;              // неприводимый многочлен
    private final int P;              // число элементов поля Галуа
    private final int[] FIELD;        // поле Галуа
    public final int[] GX = {4,6,1}; // G(x)
    
    public GaluaField(int z, int p){
        Z = z; // полином порождающий
        P = p; // число элементов
        // {Создание поля Галуа}
        FIELD = new int[P];
        FIELD[0] = -1;
        int[] arr = new int[P];
        for(int i=0; i<P; i++) arr[i] = PolynomDivision.mod((int)Math.pow(2, i), Z);
        for(int i=1; i<P; i++) FIELD[i] = indexOf(arr, i);
        // {/Создание поля Галуа}
    }
    
    // поиск индекса элемента массива
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
     * @throws org.models.GaluaField.ZeroDivisionException 
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
        public ZeroDivisionException(){
            super("Делить на 0 нельзя");
        }
    }
    
    /**
     * "+" и "-" полиномов в арифметике поля Галуа
     * @param pol1
     * @param pol2
     * @return 
     */
    public int[] xorPolynoms(int[] pol1, int[] pol2){
        int minSize = Math.min(pol1.length, pol2.length);
        int maxSize = Math.max(pol1.length, pol2.length);
        int[] xorRes = new int[maxSize];
        for(int i=0; i<minSize; i++)xorRes[i] = pol1[i]^pol2[i];
        int size = xorRes.length;
        for(int i= xorRes.length-1; i>=0; i--)
            if(xorRes[i]==0) size--;
            else break;
        int[] res;
        if(size == 0) res = new int[1];
        else {
            res = new int[size];
            System.arraycopy(xorRes, 0, res, 0, size); 
        }
        return res;
    }
    
    /**
     * Умножение полиномов в арифметике поля Галуа
     * @param pol1
     * @param pol2
     * @return 
     */
    public int[] multiplyPolynoms(int[] pol1, int[]pol2){
        int[] res;
        // размер произведения = сумма старших степеней + 1
        res = new int[pol1.length + pol2.length - 1];       
        
        for(int i=0; i<pol1.length; i++){
            for(int j=0; j<pol2.length; j++)
                // умножение к-ов в поле Галуа
                // индекс = сумма индексов
                res[i+j] ^= multilpy(pol1[i], pol2[j]);
        }
        return res;
    }
    
    /**
     * Деление полиномов в арифметике поля Галуа
     * @param division
     * @param divider 
     */
    public int[] dividePolynoms(int[] division, int[]divider){
        int flagZero = 0;  
        //[проверка на то, что массив имеет все к-ты 0]
        for(int el : division){
            if(el != 0){
                flagZero=1;
                break;
            }
        }
        if(flagZero == 0) return new int[division.length - divider.length+1];
        //[/проверка на то, что массив имеет все к-ты 0]
        
        int di1;                                    // к-т делимого старшей степени
        final int di2 = divider.length - 1;         // к-т делителя старшей степени
        int qi = division.length - divider.length;  // к-т частного старшей степени
        int[] quot = new int[qi+1];
        
        int ind;
        int k;
        int[] mult;
        int[] subdivision;
        while(division.length >= divider.length){
            di1 = division.length - 1;
            k = division[di1] / divider[di2];
            ind = di1 - di2;
            quot[qi--] = k;
            mult = new int[ind+1];
            mult[mult.length-1] = k;
            subdivision = multiplyPolynoms(divider, mult);
            division = xorPolynoms(division, subdivision);
        }
        return quot;
    }
    
    /**
     * Полином как строка
     * @param pol
     * @return 
     */
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
