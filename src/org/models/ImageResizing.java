package org.models;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Изменяет размеры изображения до размеров окна приложения
 * @author Aladser
 */
public abstract class ImageResizing {
    // dHeight - высота окна, dWidth - ширина окна
    public static ImageIcon execute(BufferedImage original, int dWidth, int dHeight){
        double K1 = 1, K2 = 1;
        double height = original.getHeight();
        double width = original.getWidth();
        if(height > dHeight){
            K1 = dHeight / height;
            if( width * K1 > dWidth ){
                K2 = dWidth / (width * K1);
            }
        }
        width *= (K1*K2);
        height *= (K1*K2);
        ImageIcon image = new ImageIcon(original.getScaledInstance((int)width, (int)height, BufferedImage.SCALE_DEFAULT));    
        return image;
    }
}
