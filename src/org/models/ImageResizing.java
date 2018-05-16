package org.models;

import java.awt.image.BufferedImage;


/**
 * Уменьшение изображения
 */
public abstract class ImageResizing {
    /** Уменьшает изображение до размеров окна
     * @param original изображение для уменьшения
     * @param dWidth новая высота окна 
     * @param dHeight новая ширина окна
     * @return 
    */
    public static BufferedImage exec(BufferedImage original, double dWidth, double dHeight){
        double k, nW=dWidth, nH=dHeight; // коэффициент уменьшения, новые высота и ширина
        if(original.getHeight() > dHeight){
            nW = dHeight;
        }
        if(nW > dWidth){
            k = dWidth / nW;
            nH = k * dHeight;
        }
        BufferedImage res = new BufferedImage((int)nW, (int)nH, BufferedImage.TYPE_3BYTE_BGR);
        java.awt.Graphics2D g = res.createGraphics();
        g.drawImage(original, 0, 0, (int)nW, (int)nH, null);
        g.dispose();
        int m = (res.getWidth()*res.getHeight()*24);
        return res;
    }
}
