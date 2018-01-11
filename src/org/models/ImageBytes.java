package org.models;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.BitSet;

/** Изображение как байтовый массив */
public class ImageBytes {	
    public final byte[] bytes;
    public final int height;
    public final int width;
    public final int numColors;   

    public ImageBytes(BufferedImage image){
        // The image to a byte array
        bytes = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();	
        height = image.getHeight();
        width = image.getWidth();
	numColors = (image.getColorModel().getPixelSize()) / 8;
    }
	
    public ImageBytes(BitSet bits, int aWidth, int aHeight){
        bytes = bits.toByteArray();
        height = aHeight;
        width = aWidth;
 
        if(bits.get(bits.length() - 1) && bits.get(bits.length() - 2))
            numColors = 3;
	else
            numColors = 1;    	  
    }
	
    // Возвращает целое значение
    private static int unsigned(byte i){ 
	return i>=0 ? i : 256+i; 
    }
    // Создает целочисденный цвет
    private int createColor(byte R, byte G, byte B){
	return (unsigned(R) << 16) + (unsigned(G) << 8) + unsigned(B) + (255 << 24);
    }
    private int createColor(byte pixel){
	return (unsigned(pixel) << 16) + (unsigned(pixel) << 8) + unsigned(pixel) + (255 << 24);
    }
	
    // Возвращает изображение
    public BufferedImage toImage() throws java.io.IOException{
	BufferedImage restoreImage = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
	int j = (numColors == 4) ? 1 : 0; // определяет наличие альфа-канала
	for (int color = 0, y = 0; y < height; y++){ 
            for (int x = 0; x < width; x++){
		if(numColors == 1) 
                    color = createColor(bytes[j]);
		else 
                    color = createColor(bytes[j+2], bytes[j+1], bytes[j]);
		restoreImage.setRGB( x, y, color );
		j += numColors; 
	    } 
	}
	return restoreImage;
    }

    // Возвращает битовый массив
    public BitSet toBitArray(){       
	BitSet bits = BitSet.valueOf(bytes);
        bits.set(bytes.length * 8 - 1);
	return bits;
    }
	
    // Возвращает N байтов массива
    public String toString(int n){
	String result = "{";
        if(n < 1 || n >= bytes.length) return "Error. Uncorrect length";
	for(int i=0; i<n; i++){
            result+= unsigned(bytes[i])+"|";
	}
        return result + "}\n";
    }
    // Количество цветов
    public int numberOfColors(){
	return numColors;
    }
	
}
