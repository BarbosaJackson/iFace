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
import java.util.List;

public class EditProfile extends JFrame implements ViewInterface {
    private JLabel username, name;
    private JPanel detailPanel;
    private JTextField nameT;
    private JScrollPane jp;
    private Container screen;
    private List<JLabel> titles;
    private List<JTextField> detail;
    private JButton confirm, cancel;
    private User me;

    public EditProfile(User u) {
        me = u;
        screen = getContentPane();
        username = new JLabel(UTILS.toHtmlH3(u.getUsername()));
        name = new JLabel(UTILS.toHtmlH3("Nome: "));
        nameT = new JTextField(u.getName());
        titles = new ArrayList<>();
        detail = new ArrayList<>();
        detailPanel = new JPanel(new GridLayout(0, 2, 10, 10));
        confirm = new JButton("Confirmar");
        cancel = new JButton("Cancelar");
        int i = 0;
        for(Pair<String, String> p : u.getDetails()) {
            titles.add(new JLabel(p.first));
            detail.add(new JTextField(p.second));
            ViewAPI.addItems(detailPanel, titles.get(i), detail.get(i));
            i++;
        }
        jp = new JScrollPane();
        jp.setViewportView(detailPanel);
    }

    public void position() {
        username.setBounds(20, 20, 200, 20);
        name.setBounds(20, 50, 100, 20);
        nameT.setBounds(120, 50, 300, 20);
        jp.setBounds(20, 110, 400, titles.size() > 0 ? titles.size() <= 4 ? titles.size() * 30 : 200 : 0);
        int y = 10;
        for(int i = 0; i < titles.size(); i++) {
            titles.get(i).setBounds(20, y, 100, 20);
            detail.get(i).setBounds(120, y, 300, 20);;
            y += 30;
        }
        confirm.setBounds(20, y == 10 ? 100 : 110 + (titles.size() < 4 ? y : 200), 150, 50);
        cancel.setBounds(200, y == 10 ? 100 : 110 + (titles.size() < 4 ? y : 200), 150, 50);
    }

    public void actions() {
        cancel.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(this, "Você cancelou a edição do perfil, com isso, as alterações não foram feitas");
            dispose();
        });
        confirm.addActionListener((ActionEvent ae) -> {
            me.setName(nameT.getText());
            for(int i = 0; i < me.getDetails().size(); i++) {
                if(titles.get(i).getText().equals(me.getDetails().get(i).first)) {
                    me.getDetails().get(i).second = detail.get(i).getText();
                }
            }
            dispose();
        });
    }

    public void start() {
        ViewAPI.addItems(screen, username, name, nameT, jp, confirm, cancel);
        position();
        ViewAPI.configScreen(this, 500, titles.size() > 0 ? 250 + (titles.size() < 4 ? titles.size() * 30 : 200) : 150);
        screen.setBackground(UTILS.backgroundColorSecondWindows);
        detailPanel.setBackground(UTILS.backgroundColorSecondWindows);
        ViewAPI.paint(username, name);
        JButtonUtils.configButton(confirm, cancel);
        for(int i = 0; i < titles.size(); i++) {
            ViewAPI.paint(titles.get(i));
        }
        jp.setBorder(null);
        actions();
    }
}
