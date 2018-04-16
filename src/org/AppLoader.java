package org;

import java.util.Arrays;
import java.util.BitSet;
import org.models.BinOperations;
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
        int[] iBits = {1,0,0,0,0,1};
        BitSet bits = new BitSet();
        bits.set(iBits.length);
        for(int i=0; i<iBits.length; i++) if(iBits[i]==1)bits.set(i);
        bits = codec.encode(bits);
        bits.clear(11);
        codec.decode(bits);
        
        int[] Cx = {4,0,1,4}; // исходное C(x)
        System.out.print(Arrays.toString(Cx) + " -> ");
        // divRes.reminder = C(x)modG(x) - остаток от деления
        GaluaField.DivisionResult divRes = codec.galua.dividePolynoms(Cx, codec.galua.GX);
        // массив divRes.reminder -> число rx 
        int rx = 0;
        for(int i=0; i<divRes.reminder.length; i++) rx += divRes.reminder[i]*Math.pow(10, i);
        // ex = syndroms[rx] - полином ошибки
        int ex = codec.syndroms[rx];
        // число ex -> массив E(x)
        int[] Ex = new int[Cx.length];
        for(int i=0; i<Ex.length; i++, ex/=10) Ex[i] = ex%10;
        // C(x) XOR E(x)
        Cx = codec.galua.xorPolynoms(Cx, Ex);
        System.out.println(Arrays.toString(Cx));
        
        
        /* Вызов главного окна */
        new org.views.MainFrame().setVisible(true);
    } 
}
