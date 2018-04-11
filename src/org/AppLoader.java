package org;

import java.util.BitSet;
import org.models.codecs.RSCodec;

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

        RSCodec rsc = new RSCodec(4, 2, 3);
        int[] src = {0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,1};
        BitSet binSrc = new BitSet();
        binSrc.set(src.length);
        for(int i=0; i<src.length; i++) if(src[i]==1) binSrc.set(i);
        BitSet code = rsc.encode(binSrc);        
        BitSet res = rsc.decode(code);
        for(int i=0; i<res.length(); i++){
            if(res.get(i) != binSrc.get(i)){
                System.out.println("Ошибка передачи");
                break;
            }
            if(i == res.length()-1) System.out.println("Отправленные и полученные данные равны");
        }
        
        /* Вызов главного окна */
        //pf = new org.views.ProgressFrame();
        //new org.views.MainFrame().setVisible(true);
    }
    
    
    
}
