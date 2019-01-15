package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.JButtonUtils;
import br.edu.ufal.kcaj.iFace.utils.Pair;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class DetailUser extends JFrame {
    private JLabel nameL, username, details;
    private Container screen;
    private JPanel detailsPanel;
    private JScrollPane jp;
    private ArrayList<JLabel> titles, description;
    private JButton exit;
    public DetailUser(User u, JFrame parent) {
        screen = getContentPane();
        nameL = new JLabel(UTILS.toHtmlParagraph("Nome: " + u.getName()));
        username = new JLabel(UTILS.toHtmlParagraph("Nome de usuario: " + u.getUsername()));
        detailsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        titles = new ArrayList<>();
        description = new ArrayList<>();
        exit = new JButton(UTILS.toHtmlH3("Fechar"));
        details = new JLabel(UTILS.toHtmlH3("Caracteristicas" + (u.getDetails().size() == 0 ? ": Você ainda não adicionou nenhuma caracteristica." : ": ")));
        int i = 0;
        for (Pair<String, String> p : u.getDetails()) {
            titles.add(new JLabel(UTILS.toHtmlH3(p.first+ ": ")));
            description.add(new JLabel(UTILS.toHtmlH3(p.second)));
            detailsPanel.add(titles.get(i));
            detailsPanel.add(description.get(i));
            i++;
        }
        jp = new JScrollPane();
        jp.setViewportView(detailsPanel);

    }
    private void position() {
        nameL.setBounds(20, 20, 300, 20);
        username.setBounds(20, 50, 300, 20);
        exit.setBounds(400, 10, 100,20);
        details.setBounds(20, 80, 400, titles.size() > 0 ? 20 : 50);
        jp.setBounds(20, 110, 400, titles.size() > 0 ? titles.size() <= 4 ? titles.size() * 50 : 200 : 0);
        int y = 10;
        for(int i = 0; i < titles.size(); i++) {
            titles.get(i).setBounds(20, y, 100, 40);
            description.get(i).setBounds(120, y, 300, 40);;
            y += 50;
        }
    }

    private void actions() {
        exit.addActionListener((ActionEvent ae) -> {
            dispose();
        });
    }

    public void start() {
        position();
        ViewAPI.addItems(screen, nameL, username, exit, jp, details);
        ViewAPI.paint(username, nameL, details);
        for(int i = 0; i < titles.size(); i++) {
            ViewAPI.paint(titles.get(i), description.get(i));
        }
        screen.setBackground(UTILS.backgroundColorSecondWindows);
        exit.setFocusPainted(false);
        detailsPanel.setBackground(UTILS.backgroundColorSecondWindows);
        jp.setBorder(null);
        ViewAPI.configScreen(this, 500, titles.size() > 0 ? 150 + (titles.size() < 4 ? titles.size() * 50 : 200) : 150);
        JButtonUtils.configButton(exit);
        actions();
    }
}
