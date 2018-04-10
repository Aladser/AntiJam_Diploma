package org.models.codecs;

import java.util.BitSet;

public class RSCodec extends Codec{
    public final int N; // длина кодового слова
    public final int K; // длина инфослова
    public final int NUM_BITS; // число бит для числа кодового слова
    private int intArraySize; // размер инофокодого слова
    private final GaluaField galua; // поле Галуа
    
    /**
     * Кодекс Рида-Соломона
     * @param n длина кодового слова
     * @param k длина инфослова
     * @param numBits  число бит для создания целого числа
     */
    public RSCodec(int n, int k, int numBits){
        galua = new GaluaField(0b1011, 8);
        N = n;
        K = k;
        NUM_BITS = numBits;
    }

    /** Кодирование
     * @param msg инфоблок
     * @return Sx - кодовое слово
     */
    @Override
    public BitSet encode(BitSet msg) {
        System.out.print("Исходные байты\n  ");
        System.out.println( BinOperations.showBitSet(msg, 8) );
        System.out.println("Кодирование РС-кодом(4,2)");
        msg = correctBitSetLength(msg, NUM_BITS); // коррекция msg для деления на блоки
        System.out.print("Исходная информация:\n  {");
        System.out.println( BinOperations.showBitSet(msg, this.NUM_BITS) + "}");
        intArraySize = (msg.length()-1)/this.NUM_BITS;       // запоминается размер массива
        int[] iMsg = createIntArray(msg); // создает int массив из msg
        System.out.print("  {");
        for(int i=0; i<iMsg.length; i++){
            System.out.print(iMsg[i]);
            if(i<iMsg.length-1)System.out.print("  |");
        }
        System.out.print( "}\nКодовое слово в GF(p)="+(int)Math.pow(2, this.NUM_BITS)+"\n  {");
        int[] iRes = new int[iMsg.length * N/K];
        int[] Ax = new int[K]; // инфослово
        int[] Sx;              // кодовое слово
        for(int ri=0, i=0; i<iMsg.length; i+=K){
            for(int ai=0, mi=i; mi<i+this.K; mi++, ai++) Ax[ai] = iMsg[mi];
            Sx = galua.multiplyPolynoms(Ax, galua.GX);
            for(int si=0; si<Sx.length; si++) iRes[ri++] = Sx[si];
        }
        for(int i=0; i<iRes.length; i++){
            System.out.print(iRes[i]);
            if(i<iRes.length-1) System.out.print("  |");
        }
        System.out.println("}");
        BitSet res = createBitSet(iRes);
        System.out.println("  {"+BinOperations.showBitSet(res, this.NUM_BITS)+"}");
        return res;
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
     * Создает целочисленный массив на 
     * @param src
     * @return 
     */
    private int[] createIntArray(BitSet src){
        int[] res = new int[(src.length() - 1)/NUM_BITS];
        int ri = 0; // индекс по res
        for(int i=0; i<src.length()-1; i+=NUM_BITS) res[ri++] = createIntegerFromBitSet(src, i, NUM_BITS);
        intArraySize = res.length;
        int rem = K - res.length%K;
        return java.util.Arrays.copyOf(res, res.length+rem);
    } 
    
    /**
     * Создает BitSet из целочисленного массива
     * @param src целочисленный массив
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
