package org.models.codecs;

import java.util.BitSet;

/** Кодек кода Рида-Соломона */
public class RSCodec extends Codec{
    /** Длина кодового слова */
    public final int N;
    /** Длина информационного блока */
    public final int K;
    // Длина G(x)
    private final int GX_LENGTH;
    // Число бит для числа поля Галуа
    private final int NUM_BITS;
    // Поле Галуа
    private final org.models.GaluaField galua;
    // Таблица r(x)-e(x)
    private final int[] SYNDROMS;
    
    /**
     * Кодек Рида-Соломона
     * @param n длина кодового слова
     * @param k длина инфослова
     * @param numBits  число бит целого числа
     */
    public RSCodec(int n, int k, int numBits){
        galua = new org.models.GaluaField(0b1011, 8);
        GX_LENGTH = galua.GX.length;
        N = n;
        K = k;
        NUM_BITS = numBits;
        SYNDROMS = createSyndTable();
    }
    
    /** Кодирование Cx=Ax*Gx
     * @param Ax информация в виде BitSet
     * @return
     */
    @Override
    public BitSet encode(BitSet Ax) {
        // Коррекция длины A(x) для деления на NUM_BITS без остатка
        int rem = NUM_BITS - ((Ax.length() - 1) % NUM_BITS);
        if(rem != NUM_BITS) Ax.set(Ax.length() + rem - 1);       
        // BitSet массив -> unt[] массив
        int[] iAx = createIntArray(Ax);
        // коррекция размера iAx для деления на K без остатка
        rem = K - iAx.length%K;
        if(rem != K) iAx = java.util.Arrays.copyOf(iAx, iAx.length+rem);
        // Sx = Ax * Gx
        int[] numAx = new int[K];
        int[] numCx;
        int[] iCx = new int[iAx.length * N/K];
        for(int ci=0, ai=0; ai<iAx.length; ai+=K){
            for(int i=0, mi=ai; mi<ai+K; mi++, i++) numAx[i] = iAx[mi];
            numCx = galua.multiplyPolynoms(numAx, galua.GX);
            for(int si=0; si<numCx.length; si++) iCx[ci++] = numCx[si];
        }
        return createBitSet(iCx);    
    }

    /**
     * Декодирование Ax=Cx/Gx
     * @param Cx кодовое слово в форме BitSet
     * @return 
     */
    @Override
    public BitSet decode(BitSet Cx) {
        int[] iCx = this.createIntArray(Cx);   // Cx как int[]
        int[] numСx = new int[N];              // Cx[i]
        int[] iAx = new int[iCx.length * K/N]; // Ax как int[]
        int[] numAx;                           // Ax[i]
        org.models.GaluaField.DivisionResult divRes;      // результат деления
        for(int ai=0, ci=0; ci<iCx.length; ci+=N){
            for(int si=0, i=ci; i<ci+N;) numСx[si++] = iCx[i++];
            divRes = galua.dividePolynoms(numСx, galua.GX);
            numAx = fixError(numСx, divRes);
            for(int i=0; i<numAx.length; i++) iAx[ai++] = numAx[i];
        }
        return createBitSet(iAx);
    }
    
    /**
     * Исправление ошибок в РС коде
     * @param Cx кодовое слово 
     * @param divRes результат деления
     * @return 
     */
    private int[] fixError(int[] Cx, org.models.GaluaField.DivisionResult divRes) {
        int[] Ax = divRes.quotient;
        int remWeight = 0;
        if(divRes.reminder[0] != 0){
            for(int el : divRes.reminder) if(el != 0) remWeight++;
            if(remWeight > 1){
                int rx = 0; // int форма остатка деления
                // массив divRes.reminder -> число rx
                for(int i=0; i<divRes.reminder.length; i++) rx += divRes.reminder[i]*Math.pow(10, i);
                int ex = SYNDROMS[rx]; // e(x) как int число
                int[] Ex = new int[N]; // e(x) как массив
                for(int i=0; i<Ex.length; i++, ex/=10) Ex[i] = ex%10;
                for(int i=0; i<Cx.length; i++) Cx[i] ^= Ex[i];
                Ax = galua.dividePolynoms(Cx, galua.GX).quotient;
            }
        }
        return Ax;
    }

    /**
     * Создает int[] массив из BitSet
     * @param src
     * @return 
     */
    private int[] createIntArray(BitSet src){
        int[] arr = new int[(src.length() - 1)/NUM_BITS];
        int ai = 0, exp;
        int hexp = (int) Math.pow(2, NUM_BITS-1); // высшая двоичная степень числа в СС10
        for(int i=0; i<src.length()-1; i+=NUM_BITS, ai++){
            exp = hexp;
            for(int k=i; k<i+NUM_BITS; k++, exp/=2) arr[ai] += (src.get(k)?1:0) * exp;
        }
        return arr;
    }   
    
    /**
     * Создает BitSet из целочисленного массива
     * @param src
     * @return 
     */
    private BitSet createBitSet(int[] src){
        BitSet arr = new BitSet();
        arr.set(src.length * this.NUM_BITS);
        int ai = 0;
        for(int i=0; i<src.length; i++){
            int exp = (int) Math.pow(2, NUM_BITS-1);
            for(int k=0; k<this.NUM_BITS; k++, exp /= 2, ai++){
                if(src[i] >= exp){
                    arr.set(ai);
                    src[i] -= exp;
                }
            }
        }
        return arr;
    }        
    
    /**
     * Создает таблицу e(x)|r(x)
     * @return 
     */
    private int[] createSyndTable(){
        org.models.GaluaField.DivisionResult divRes;
        int n1 = GX_LENGTH-1; // размер остатка деления = 2
        int[] Ex = new int[N];
        int rx;       // reminder как число
        int ex;       // ex как число
        int[] sndr = new int[galua.P*(n1-1)*10]; // n1-1 - число исправляемых байт
        for(int i=n1; i<N; i++){ 
            for(Ex[i]=0; Ex[i]<galua.P; Ex[i]++){
                divRes = galua.dividePolynoms(Ex, galua.GX);
                ex = Ex[i];
                for(int z=0; z<i; z++) ex*=10;
                rx = divRes.reminder[0] + 10*divRes.reminder[1];
                sndr[rx] = ex;
            }
            Ex[i]= 0;
        }
        return sndr;
    }
    
}
