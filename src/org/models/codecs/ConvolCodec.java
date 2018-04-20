package org.models.codecs;

import java.util.BitSet;
import org.models.BinOperations;

/**
 * Кодек сверточного кода
 */
public class ConvolCodec extends Codec{
    /**
     * Длина кодового слова
     */
    public final int N;
    /**
     * Длина инфоблока
     */
    public final int K;
    /**
     * Число бит в блоке A(x)
     */
    public final int NUM_BITS;
    private int num_blocks;   // число блоков
    
    public ConvolCodec(int n, int k, int numbits){
        N = n;
        K = k;
        NUM_BITS = numbits;
        num_blocks = k / NUM_BITS;
    }
    
    @Override
    public BitSet encode(BitSet Ax) {
        BitSet Cx = new BitSet();
        Cx.set((Ax.length()-1)*N/K);
        int ai=0, ci=0;
        // коррекция размера для деления без остатка
        int rem = NUM_BITS - (Ax.length()-1)%NUM_BITS; 
        if(rem != NUM_BITS) Ax.set(Ax.length()+rem-1);
        //System.out.println(BinOperations.showBitSet(Ax, 3));
        // кодирование
        int checkBit = 0;
        for(; ai<Ax.length()-1; ai++){
            // XOR инфобит
            if(Ax.get(ai)){
                Cx.set(ci);
                checkBit ^= 1;
            }
            ci++;
            // XOR предыдуших бит
            if((ai+1)%NUM_BITS==0){
                if((ai+1) >= 6){
                    if(Ax.get(ai-5)) checkBit^=1;
                    if(Ax.get(ai-4)) checkBit^=1;
                }
                if((ai+1)>6){
                    if(Ax.get(ai-6)) checkBit^=1;
                    if(Ax.get(ai-8)) checkBit^=1;
                }
                if(checkBit == 1) Cx.set(ci);
                ci++;
                checkBit = 0;
            }
        }
        //System.out.println(BinOperations.showBitSet(Cx, 4));
        return Cx;
    }

    @Override
    public BitSet decode(BitSet Cx) {
        System.out.println("Cx = "+BinOperations.showBitSet(Cx, 4));
        
        BitSet Ax = new BitSet();
        Ax.set((Cx.length()-1)*K/N);
        int ci=0, ai=0;
        // Декодирование
        int checkBit = 0;
        for(; ci<Cx.length()-1; ci++, ai++){
            if(Cx.get(ci)){
                Ax.set(ai);
                checkBit^=1;
            }
            if((ai+1)%3==0){
                ci++;
                if(ai+1 >= 6){
                    if(Cx.get(ci-7)) checkBit^=1;
                    if(Cx.get(ci-6)) checkBit^=1;
                }
                if(ai+1 > 6){
                    if(Cx.get(ci-11)) checkBit^=1;
                    if(Cx.get(ci-9)) checkBit^=1;
                }
                checkBit ^= Cx.get(ci)?1:0;
                System.out.println("syndrom = " + checkBit);
                checkBit=0;
            }
        }
        
        System.out.println("Ax = "+BinOperations.showBitSet(Ax, 3));
        return Ax;
    }

    @Override
    public int fixError(int number, int w) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
