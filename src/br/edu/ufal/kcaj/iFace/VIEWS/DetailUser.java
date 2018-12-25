package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.Pair;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;


public class DetailUser extends JFrame {
    private JLabel nameL, username;
    private Container screen;
    private JPanel detailsPainel;
    private JScrollPane jp;
    public DetailUser(User u, JFrame parent) {
        screen = getContentPane();
        nameL = new JLabel(UTILS.toHtmlParagraph("Nome: " + u.getName()));
        username = new JLabel(UTILS.toHtmlParagraph("Nome de usuario: " + u.getUsername()));
        detailsPainel = new JPanel(null);
        for (Pair<String, String> p : u.getDetails()) {
            detailsPainel.add(new JLabel(UTILS.toHtmlH3(p.first)));
            detailsPainel.add(new JLabel(UTILS.toHtmlH3(p.second)));
        }
        jp = new JScrollPane(detailsPainel);

    }
    private void position() {
        nameL.setBounds(20, 20, 300, 20);
        username.setBounds(20, 50, 300, 20);
        detailsPainel.setBounds(20, 80, 400, 400);
    }
    public void start() {
        position();
        ViewAPI.addItems(screen, nameL, username, detailsPainel);
//        ViewAPI.addItems(detailsPainel);
        ViewAPI.configScreen(this, 350, 280);
//        actions();
    }
}
