package org.models.codecs;

import java.util.BitSet;

public class RSCodec extends Codec{
    public final int N; // длина кодового слова
    public final int K; // длина инфослова
    public final int NUM_BITS; // число бит для числа кодового слова
    private final org.models.GaluaField galua; // поле Галуа
    
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
    }

    /** Кодирование
     * @param msg информация в виде BitSet
     * @return
     */
    @Override
    public BitSet encode(BitSet msg) {   
        // коррекция размера битового массива для возможности деления на блоки по NUM_BITS бит
        msg = this.correctBitSetLength(msg, NUM_BITS);
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
        return createBitSet(iCode);    
    }

    /**
     * Декодирование
     * @param code кодовое слово в форме BitSet
     * @return 
     */
    @Override
    public BitSet decode(BitSet code) {
        // битовый массив -> целочисленный массив
        int[] iCode = this.createIntArray(code);
        // Ax = Sx / Gx
        int[] iMsg = new int[iCode.length * K/N];
        int[] Sx = new int[N];
        int[] Ax;
        for(int mi=0, ci=0; ci<iCode.length; ci+=N){
            for(int si=0, i=ci; i<ci+N; i++) Sx[si++] = iCode[i];
            Ax = galua.dividePolynoms(Sx, galua.GX);
            for(int i=0; i<Ax.length; i++) iMsg[mi++] = Ax[i];
        }
        // целочисленный массив -> битовый массив
        BitSet res = createBitSet(iMsg);
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
        src.set(src.length() + rem - 1);
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
}
