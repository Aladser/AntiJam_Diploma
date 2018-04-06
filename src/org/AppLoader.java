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
        
        int[] pol = new int[2];
        int[] a = new int[2];
        for(int i=0; i<8; i++){
            pol[0] = i;
            a[0] = i;
            for(int j=0; j<8; j++){
                pol[1] = j;
                a[1] = j;
                System.out.print( galua.polynomToString(pol) );
                System.out.print("*");
                System.out.print( galua.polynomToString(galua.GX) );
                System.out.print("=");
                System.out.print( galua.polynomToString(pol) );
                System.out.print("   ");
                System.out.print( galua.polynomToString(pol) );
                System.out.print("/");
                System.out.print( galua.polynomToString(galua.GX) );
                System.out.print("=");
                System.out.println( galua.polynomToString(pol) );
            }
        }
        
        /* Вызов главного окна */
        //pf = new org.views.ProgressFrame();
        //new org.views.MainFrame().setVisible(true);
    }
    
}
