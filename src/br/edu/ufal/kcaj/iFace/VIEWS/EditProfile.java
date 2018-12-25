package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.Pair;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EditProfile extends JFrame {
    private JLabel username, name;
    private JTextField nameT;
    private JScrollPane jp;
    private Container screen;
    private List<JLabel> titles;
    private List<JTextField> detail;

    public EditProfile(User u) {
        screen = getContentPane();
        username = new JLabel(UTILS.toHtmlH3(u.getUsername()));
        name = new JLabel(UTILS.toHtmlH3("Nome: "));
        nameT = new JTextField();
        jp = new JScrollPane(screen);
        titles = new ArrayList<>();
        detail = new ArrayList<>();
        for(Pair<String, String> p : u.getDetails()) {
            titles.add(new JLabel(p.first));
            detail.add(new JTextField(p.second));
        }
    }

    private void position() {
        username.setBounds(20, 20, 200, 20);
        name.setBounds(20, 50, 100, 20);
        nameT.setBounds(120, 50, 200, 20);

    }

    private void addItems() {
        screen.add(username);
        screen.add(name);
        screen.add(nameT);
        for(int i = 0; i < titles.size(); i++) {
            screen.add(titles.get(i));
            screen.add(detail.get(i));
        }
    }

    public void start() {
        ViewAPI.configScreen(this, 300, 400);
        addItems();
    }
}
