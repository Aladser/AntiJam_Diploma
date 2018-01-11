package org.models;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.BitSet;

/**
 * Изображение как битовый массив
 * @author Aladser
 */
public class ImageBits {
    public final BitSet bits;   // битовый массив
    public final int width;     // ширина изображения
    public final int height;    // высота изображения
    public final int numColors; //число цветов изображения
    
    public ImageBits(byte[] bytes, int width, int height){
	bits = BitSet.valueOf(bytes);
        bits.set(bytes.length * 8 - 1);
        this.width = width;
        this.height = height;
        if( bits.get(bits.length() - 1) && bits.get(bits.length() - 2) )
            numColors = 3;
	else
            numColors = 1;
    }
    public ImageBits(BufferedImage image){
        byte[] bytes = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();	
        this.width = image.getWidth();
        this.height = image.getHeight();
        bits = BitSet.valueOf(bytes);
        bits.set(bytes.length * 8 - 1);
        numColors = (image.getColorModel().getPixelSize()) / 8;
    }
    
    public ImageBits(BitSet bits, int width, int height){
	this.bits = bits;
        bits.set(width * height * 8 - 1);
        this.width = width;
	this.height = height;
        numColors = 3;        
    }
    
    public BufferedImage toImage() throws java.io.IOException{
        byte[] bytes = bits.toByteArray();
        BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );        
	int j = (numColors == 4) ? 1 : 0; // определяет наличие альфа-канала
	for (int color = 0, y = 0; y < height; y++){ 
            for (int x = 0; x < width; x++){
		if(numColors == 1) 
                    color = createColor(bytes[j]);
		else 
                    color = createColor(bytes[j+2], bytes[j+1], bytes[j]);
		image.setRGB( x, y, color );
		j += numColors; 
	    } 
	}
	return image;        
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
}
