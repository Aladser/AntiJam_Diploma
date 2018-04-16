package org.models.codecs;

import java.util.BitSet;
import org.models.BinOperations;
import org.models.GaluaField;

/**
 * Кодек кода Рида-Соломона
 */
public class RSCodec extends Codec{
    /**
     * Длина кодового слова
     */
    public final int N;
    /**
     * Длина информационного блока
     */
    public final int K;
    /**
     * Длина G(x)
     */
    public final int GX_LENGTH;
    /**
     * Число бит для числа поля Галуа
     */
    public final int NUM_BITS;
    /**
     * Поле Галуа
     */
    public final org.models.GaluaField galua;
    /**
     * Таблица r(x)-e(x)
     */
    public final int[] syndroms;
    
    /**
     * Кодек Рида-Соломона
     * @param n длина кодового слова
     * @param k длина инфослова
     * @param numBits  число бит для создания целого числа
     */
    public RSCodec(int n, int k, int numBits){
        galua = new org.models.GaluaField(0b1011, 8);
        N = n;
        K = k;
        NUM_BITS = numBits;
        GX_LENGTH = galua.GX.length;
        syndroms = createSyndTable();
    }

    /** Кодирование
     * @param msg информация в виде BitSet
     * @return
     */
    @Override
    public BitSet encode(BitSet msg) {
        // коррекция размера битового массива для возможности деления на блоки по NUM_BITS бит
        msg = this.correctBitSetLength(msg, NUM_BITS);
        //System.out.println("A (x) = " + BinOperations.showBitSet(msg, 3));
        // битовый массив -> целочисленный массив
        int[] iMsg = createIntArray(msg);
        // коррекция размера целочисленного массива для возможности деления на блоки по K
        int rem = K - iMsg.length%K;
        if(rem != K) iMsg = java.util.Arrays.copyOf(iMsg, iMsg.length+rem);
        // Sx = Ax * Gx
        int[] Ax = new int[K];
        int[] Sx;
        int[] iCode = new int[iMsg.length * N/K];
        for(int ci=0, i=0; i<iMsg.length; i+=K){
            for(int ai=0, mi=i; mi<i+this.K; mi++, ai++) Ax[ai] = iMsg[mi];
            Sx = galua.multiplyPolynoms(Ax, galua.GX);
            for(int si=0; si<Sx.length; si++) iCode[ci++] = Sx[si];
        }
        // целочисленный массив -> битовый массив
        //System.out.println("C (x) = " + BinOperations.showBitSet(createBitSet(iCode), 3));
        return createBitSet(iCode);    
    }

    /**
     * Декодирование
     * @param code кодовое слово в форме BitSet
     * @return 
     */
    @Override
    public BitSet decode(BitSet code) {
        //System.out.println("C'(x) = " + BinOperations.showBitSet(code, 3));
        // битовый массив -> целочисленный массив
        int[] iCode = this.createIntArray(code);
        // Ax = Sx / Gx
        int[] iMsg = new int[iCode.length * K/N];
        int[] Sx = new int[N];
        int[] Ax;
        for(int mi=0, ci=0; ci<iCode.length; ci+=N){
            for(int si=0, i=ci; i<ci+N; i++) Sx[si++] = iCode[i];
            Ax = galua.dividePolynoms(Sx, galua.GX).quotient;
            for(int i=0; i<Ax.length; i++) iMsg[mi++] = Ax[i];
        }
        // целочисленный массив -> битовый массив
        BitSet res = createBitSet(iMsg);
        //System.out.println("A (x) = " + BinOperations.showBitSet(res, 3));
        return res;
    }

    @Override
    public int fixError(int number, int w) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Создает целое число из отрезка битового массива
     * @param src битовый массив
     * @param sind стартовый индекс отрезка
     * @param length длина отрезка
     * @return 
     */
    public int createIntegerFromBitSet(BitSet src, int sind, int length){
        int res = 0;
        int exp = (int) Math.pow(2, length-1);
        for(int i=sind; i<sind+length; i++, exp/=2) res += (src.get(i)?1:0) * exp; 
        return res;
    }
    
    /**
     * Создает BitSet из числа
     * @param num число
     * @return 
     */
    private BitSet createBitSetFromInteger(int num){
        BitSet res = new BitSet();
        res.set(this.NUM_BITS);
        int exp = (int) Math.pow(2, this.NUM_BITS-1);
        for(int i=0; i<this.NUM_BITS; i++){
            if(num >= exp){
                res.set(i);
                num -= exp;
            }
            exp /= 2;
        }
        return res;
    }
    
    /** 
     * Коррекция битового массива для возможности деления на блоки по blockSize бит
     * @param src битовый массив
     * @param blockSize размер блока
     * @return 
     */
    private BitSet correctBitSetLength(BitSet src, int blockSize){
        int lsrc = src.length() - 1;
        int rem = blockSize - lsrc % blockSize;
        if(rem == blockSize) rem = 0;
        else src.set(src.length() + rem - 1);
        return src;
    }

    /**
     * Создает целочисленный массив из BitSet
     * @param src
     * @return 
     */
    private int[] createIntArray(BitSet src){
        int[] res = new int[(src.length() - 1)/NUM_BITS];
        int ri = 0;
        for(int i=0; i<src.length()-1; i+=NUM_BITS) res[ri++] = createIntegerFromBitSet(src, i, NUM_BITS);
        return res;
    } 
    
    /**
     * Создает BitSet из целочисленного массива
     * @param src
     * @return 
     */
    private BitSet createBitSet(int[] src){
        BitSet res = new BitSet();
        res.set(src.length * this.NUM_BITS);
        int ri = 0; // индекс по res
        BitSet num;
        for(int i=0; i<src.length; i++){
            num = createBitSetFromInteger(src[i]);
            for(int j=0; j<NUM_BITS; j++){
                if(num.get(j)) res.set(ri);
                ri++;
            }
        }
        return res;
    }
    
    /**
     * Создает таблицу e(x)->r(x)
     * @return 
     */
    private int[] createSyndTable(){
        GaluaField.DivisionResult divRes;
        int n1 = GX_LENGTH-1; // размер остатка деления = 2
        int[] Ex = new int[N];
        int rx;       // reminder как число
        int ex;       // ex как число
        
        int[] sndr = new int[galua.P*(n1-1)*10]; // в данном примере длина равна 80, так как числа 0-79
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
