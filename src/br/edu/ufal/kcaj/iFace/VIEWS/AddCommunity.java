package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.Community;
import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.JButtonUtils;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddCommunity extends JFrame {
    private User me;
    private JLabel usernameMaster, nameL, descriptionL;
    private JTextField nameTF;
    private JTextArea descriptionTA;
    private JButton confirm, cancel;
    private Container screen;
    private List<Community> communityList;
    private List<User> users;

    public AddCommunity(User me, List<Community> communityList, List<User> users) {
        this.me = me;
        this.communityList = communityList;
        this.users = users;

        screen = getContentPane();
        usernameMaster = new JLabel(UTILS.toHtmlH2("Dono: " + me.getUsername()));
        nameL = new JLabel("Nome");
        descriptionL = new JLabel("Descrição");
        nameTF = new JTextField();
        descriptionTA = new JTextArea();
        confirm = new JButton("Criar");
        cancel = new JButton("Cancelar");
    }

    private void position() {
        usernameMaster.setBounds(20, 20, 150, 30);
        nameL.setBounds(20, 60, 100, 20);
        nameTF.setBounds(130, 60, 200, 20);
        descriptionL.setBounds(20, 90, 100, 20);
        descriptionTA.setBounds(130, 90, 200, 50);
        confirm.setBounds(30, 150, 100, 40);
        cancel.setBounds(150, 150, 100, 40);
    }

    private boolean isValidData() {
        if(nameTF.getText().length() == 0) return false;
        if(descriptionTA.getText().length() == 0) return false;
        for(Community c : me.getCommunities()) {
            if(c.getCommunityName().equals(nameTF.getText())) return false;
        }
        for(User u : users) {
            if(u.getUsername().equals(nameTF.getText())) return false;
        }
        return true;
    }

    private void actions() {
        cancel.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(this, "Você cancelou está ação, com isso os dados fornecidos não vão ser salvos!");
            dispose();
        });
        confirm.addActionListener((ActionEvent ae) -> {
            if(isValidData()) {
                Community c;
                c = new Community(me.getUsername());
                c.setCommunityName(nameTF.getText());
                c.setCommunityDescription(descriptionTA.getText());
                c.getMembers().add(me);
                me.getCommunities().add(c);
                communityList.add(c);
                JOptionPane.showMessageDialog(this, "Comunidade criada com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Verifique se preencheu todos os campos, se sim, o nome de usuário já está em usos");
            }
        });
    }

    public void start() {
        ViewAPI.addItems(screen, usernameMaster, nameL, nameTF, descriptionL, descriptionTA, confirm, cancel);
        ViewAPI.paint(nameL, descriptionL, usernameMaster);
        JButtonUtils.configButton(confirm, cancel);
        position();
        actions();
        ViewAPI.configScreen(this, 350, 230);
        screen.setBackground(UTILS.backgroundColorSecondWindows);
    }
}
