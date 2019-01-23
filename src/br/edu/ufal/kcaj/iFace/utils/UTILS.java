package br.edu.ufal.kcaj.iFace.utils;

import java.awt.*;

public abstract class UTILS {
    public static String urlImg = "/home/edge/Documentos/iface/src/br/edu/ufal/kcaj/iFace/assets/";

    public static Color foregroundFontColor = new Color(176, 224, 230);
    public static Color backgroundColorSecondWindows = new Color(23, 63, 95);
    public static Color selectedItemColor = new Color(110, 197, 233);
    public static String toHtmlH3(String text) {
        return "<html><h3>" + text + "</h3></html>";
    }
    public static String toHtmlH2(String text) {
        return "<html><h2>" + text + "</h2></html>";
    }
    public static String toHtmlParagraph(String text) {return "<html><p>" + text + "</p></html>";}
}
