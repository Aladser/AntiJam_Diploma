package org;

import org.models.codecs.GaluaField;

/**
 * Точка входа
 */
public class AppLoader {
    public static org.views.ProgressFrame pf;
    public static void main(String args[]){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(org.views.MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        GaluaField galua = new GaluaField(0b1011, 8);
        int[] pol = {1, 4};
        System.out.print( galua.polynomToString(pol) );
        System.out.print(" * ");
        System.out.print( galua.polynomToString(galua.GX) );
        System.out.print(" = ");
        pol = galua.multiplyPolynoms(pol, galua.GX);
        System.out.println( galua.polynomToString(pol) );
        //galua.dividePolynoms(pol, galua.GX);
        
        /* Вызов главного окна */
        //pf = new org.views.ProgressFrame();
        //new org.views.MainFrame().setVisible(true);
    }
    
}
