package br.edu.ufal.kcaj.iFace.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JButtonUtils {


    public static void modelHover(JButton jb) {
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt){
                jb.setContentAreaFilled(true);
                jb.setBackground(Color.white);
                jb.setForeground(new Color(32, 99, 155));
            }
            @Override
            public void mouseExited(MouseEvent evt){
                jb.setContentAreaFilled(false);
                JButtonUtils.paintButtons(jb);
            }
        });
    }

    public static void paintButtons(JButton jb){
        jb.setForeground(new Color(176, 224, 230));
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);
    }

    public static void allignButtons(JButton jb){
        jb.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public static void configButton(JButton ...jbs) {
        for(JButton jb: jbs) {
            JButtonUtils.allignButtons(jb);
            JButtonUtils.paintButtons(jb);
            JButtonUtils.modelHover(jb);
        }
    }


}
