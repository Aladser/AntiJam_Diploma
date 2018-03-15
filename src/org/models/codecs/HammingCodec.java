package org.models.codecs;
import java.util.BitSet;
import static org.models.codecs.Codec.getInteger;

// Кодек кода Хэмминга
public class HammingCodec extends Codec{
    /** Encoding
     * @param message - сообщение для кодирования
     * @return  */
    public BitSet encode(BitSet message){
	int length = ((message.length()) * 7) / 4;
	BitSet encodeMessage = new BitSet(length);
        encodeMessage.set(length);   // Установка конца битового массива
	for(int i = 0, ei = 0; i < message.length() - 1; i += 4){
            // infobite writing
            for (int k = i; k < i + 4; k++, ei++) {encodeMessage.set(ei, message.get(k));}
            // checkbite writing
            encodeMessage.set(ei++, (message.get(i) ^ message.get(i + 2) ^ message.get(i + 3)));
            encodeMessage.set(ei++, (message.get(i) ^ message.get(i + 1) ^ message.get(i + 2)));
            encodeMessage.set(ei++, (message.get(i + 1) ^ message.get(i + 2) ^ message.get(i + 3)));
	}			
	return encodeMessage;
    }
	
    /** Decoding
     * @param encodeMessage - сигнал для декодирования
     * @return  */
    public BitSet decode(BitSet encodeMessage){				
        // syndrom computation
        for (int i = 0, sindrom = 0; i < encodeMessage.length() - 1; i += 7){
            sindrom = getInteger(encodeMessage.get(i) ^ encodeMessage.get(i + 2) ^ encodeMessage.get(i + 3) ^ encodeMessage.get(i + 4)) << 2;
            sindrom += getInteger(encodeMessage.get(i) ^ encodeMessage.get(i + 1) ^ encodeMessage.get(i + 2) ^ encodeMessage.get(i + 5)) << 1;
            sindrom += getInteger(encodeMessage.get(i + 1) ^ encodeMessage.get(i + 2) ^ encodeMessage.get(i + 3) ^ encodeMessage.get(i + 6)); 
            switch (sindrom){
                case 0:
                    break;
                case 1:
                    encodeMessage.flip(i + 6);
                    break; 
                case 2:
                    encodeMessage.flip(i + 5);
                    break; 
                case 3:
                    encodeMessage.flip(i + 1);
                    break;
                case 4:
                    encodeMessage.flip(i + 4);
                    break;
                case 5:
                    encodeMessage.flip(i + 3);
                    break;
                case 6:
                    encodeMessage.flip(i);
                    break;
                case 7:
                    encodeMessage.flip(i + 2);
                    break;
            }      
        }
        
        // message getting
	int length = ((encodeMessage.length() - 1) * 4) / 7;
        BitSet decodeMessage = new BitSet(length);
        for (int i = 0, ei = 0; ei < encodeMessage.length() - 1; ei += 7){
            for (int k = ei; k < ei + 4; k++) { decodeMessage.set(i++, encodeMessage.get(k)); }
        } 
        return decodeMessage;
    }

    @Override
    public int getGX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
