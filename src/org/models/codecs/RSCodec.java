package org.models.codecs;

import java.util.Arrays;
import java.util.BitSet;

public class RSCodec extends Codec{
    public final int N;
    public final int K;
    public final int NUM_BITS;
    private int intArraySize;
    
    /**
     * Кодекс Рида-Соломона
     * @param n длина кодового слова
     * @param k длина инфослова
     * @param numBits  число бит для создания целого числа
     */
    public RSCodec(int n, int k, int numBits){
        N = n;
        K = k;
        NUM_BITS = numBits;
    }
    
    @Override
    public BitSet encode(BitSet message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BitSet decode(BitSet encodeMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int fixError(int number, int w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getGX() {
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
     * Коррекция битового массива для возможности деления на блоки по blockSize бит
     * @param src битовый массив
     * @param blockSize размер блока
     * @return 
     */
    public BitSet correctBitSetLength(BitSet src, int blockSize){
        int lsrc = src.length() - 1;
        int rem = blockSize - lsrc % blockSize;
        src.set(src.length() + rem - 1);
        return src;
    }

    /**
     * Создает целочисленный массив на 
     * @param src
     * @return 
     */
    public int[] createIntArray(BitSet src){
        int[] res = new int[(src.length() - 1)/NUM_BITS];
        int ri = 0; // индекс по res
        for(int i=0; i<src.length()-1; i+=NUM_BITS) res[ri++] = createIntegerFromBitSet(src, i, NUM_BITS);
        intArraySize = res.length;
        int rem = K - res.length%K;
        return Arrays.copyOf(res, res.length+rem);
    } 
}
