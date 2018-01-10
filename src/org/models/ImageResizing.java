/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.models;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Изменяет размеры изображения до размеров окна приложения
 * @author Aladser
 */
public abstract class ImageResizing {
    public static ImageIcon execute(BufferedImage original, int dHeight, int dWidth){
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
        ImageIcon icon = new ImageIcon(original.getScaledInstance((int)width, (int)height, BufferedImage.SCALE_DEFAULT));    
        return icon;
    }
}
