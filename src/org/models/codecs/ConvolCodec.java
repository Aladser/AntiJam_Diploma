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
    private final int num_blocks;   // число блоков
    
    public ConvolCodec(int n, int k, int numbits){
        K = k;
        N = n;
        NUM_BITS = numbits;
        num_blocks = k / NUM_BITS;
    }
    
    @Override
    public BitSet encode(BitSet Ax) {
        BitSet Cx = new BitSet();
        int ai=0, ci=0;
        // коррекция размера для деления без остатка
        int rem = NUM_BITS - (Ax.length()-1)%NUM_BITS; 
        if(rem != NUM_BITS) Ax.set(Ax.length()+rem-1);
        Cx.set((Ax.length()-1)*N/K);
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
        return Cx;
    }

    @Override
    public BitSet decode(BitSet Cx) {
        int S = 0;
        int si = 0;
        System.out.print(BinOperations.showBitSet(Cx, 4));
        
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
                
                S += checkBit;
                si++;
                if(si == num_blocks){
                    if(S < 2){
                        System.out.println(" | 00"+Integer.toBinaryString(S)+" | "+S);
                    }
                    else if(S < 4)
                        System.out.println(" | 0"+Integer.toBinaryString(S)+" | "+S);
                    else
                        System.out.println(" | "+Integer.toBinaryString(S)+" | "+S);
                    
                }
                S<<=1;
                
                checkBit=0;
            }
        }
        
        //System.out.println("Ax  "+BinOperations.showBitSet(Ax, 3));
        int ind = Ax.length()-1-((Ax.length()-1)%8);
        for(int i=ind+1; i<Ax.length(); i++) Ax.clear(i);
        
        //System.out.println(BinOperations.showBitSet(Ax, 3));
        return Ax;
    }
    
    public void createSyndrTable(){
        int[] syndroms = new int[N];
        BitSet Ax = new BitSet(); 
        int[] arr = {0,0,0, 0,0,0, 0,0,0};
        Ax.set(arr.length);
        for(int i=0; i<Ax.length()-1; i++) if(arr[i]==1) Ax.set(i);
        BitSet Cx = encode(Ax);
        
        System.out.println("Ex             | S");
        for(int i=0; i<12; i++){
            Cx.set(i);
            decode(Cx);
            Cx.clear(i);
        }
        
        
    }
   
}
