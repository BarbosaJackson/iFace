package br.edu.ufal.kcaj.iFace.utils;

import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.*;

public abstract class ViewAPI {
    public static void configScreen(JFrame jf, int width, int height) {
        jf.setUndecorated(true);
        jf.setLayout(null);
        jf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jf.setResizable(false);
        jf.setSize(width, height);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    public static void configScreen(JPanel jp, int width, int height) {
        jp.setLayout(null);
        jp.setSize(width, height);
        jp.setVisible(true);
    }

    public static void addItems(Container screen, Component... items) {
        for(Component item : items) {
            screen.add(item);
        }
    }

    public static void paint(Component... components) {
        for(Component c : components) {
            c.setForeground(UTILS.foregroundFontColor);
        }
    }
}
