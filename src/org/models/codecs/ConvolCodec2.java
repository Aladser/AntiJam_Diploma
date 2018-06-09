package org.models.codecs;

import java.util.BitSet;

public class ConvolCodec2 extends Codec{

    @Override
    public BitSet encode(BitSet message) {
        System.out.println("Нет кода");
        System.exit(42);
        return null;
    }

    @Override
    public BitSet decode(BitSet encodeMessage) {
        System.out.println("Нет кода");
        System.exit(42);
        return null;
    }
    
}
