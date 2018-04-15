package org;

import java.util.Arrays;
import org.models.GaluaField;
import org.models.codecs.RSCodec;

/**
 * Точка входа
 */
public class AppLoader {
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
        
        RSCodec codec = new RSCodec(4,2,3);
        int[] Cx = {4,0,4,4}; 
        int[] Ex = new int[4];
        GaluaField.DivisionResult divRes;
        System.out.println("Варианты ошибок в " + Arrays.toString(Cx));
        for(int i=0; i<codec.galua.P; i++){
            Cx[2] = i;
            Ex[2]= i;
            divRes = codec.galua.dividePolynoms(Cx, codec.galua.GX);
            System.out.print(Arrays.toString(Cx) + " / " + Arrays.toString(codec.galua.GX) + " = " + Arrays.toString(divRes.quotient) + " ост." + Arrays.toString(divRes.reminder));
            if(divRes.reminder[0]==0){
                System.out.print("   ");
            }
            divRes = codec.galua.dividePolynoms(Ex, codec.galua.GX);
            System.out.println(";     " + Arrays.toString(Ex) + " / " + Arrays.toString(codec.galua.GX) + " = " + Arrays.toString(divRes.quotient) + " ост." + Arrays.toString(divRes.reminder));
        }
        Cx[2]=4;
        Ex[2]=0;
        for(int i=0; i<codec.galua.P; i++){
            Cx[3] = i;
            Ex[3] = i;
            divRes = codec.galua.dividePolynoms(Cx, codec.galua.GX);
            System.out.print(Arrays.toString(Cx) + " / " + Arrays.toString(codec.galua.GX) + " = " + Arrays.toString(divRes.quotient) + " ост." + Arrays.toString(divRes.reminder));
            if(divRes.reminder[0]==0){
                System.out.print("   ");
            }
            divRes = codec.galua.dividePolynoms(Ex, codec.galua.GX);
            System.out.println(";     " + Arrays.toString(Ex) + " / " + Arrays.toString(codec.galua.GX) + " = " + Arrays.toString(divRes.quotient) + " ост." + Arrays.toString(divRes.reminder));
        }
        
        int[][] a = new int[3][5];
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[0].length; j++) a[i][j]= j;
        }
        
        /* Вызов главного окна */
        //new org.views.MainFrame().setVisible(true);
    } 
}
