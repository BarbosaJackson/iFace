package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.JButtonUtils;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class SendMessage extends JFrame {
    Container screen;
    private List<User> users;
    private User me;
    private JLabel from, to, messageL;
    private JComboBox toCB;
    private JTextArea message;
    private JButton confirm, cancel;

    public SendMessage(User me, List<User> users, JFrame parent) {

        this.me = me;
        this.users = users;
        screen = getContentPane();
        screen.setBackground(UTILS.backgroundColorSecondWindows);
        from = new JLabel("DE: " + me.getUsername());
        to = new JLabel("Para: ");
        toCB = new JComboBox();
        for(User u : me.getFriends()) {
            toCB.addItem(u.getUsername());
        }
        messageL = new JLabel("Digite sua mensagem: ");
        message = new JTextArea();
        confirm = new JButton("Enviar");
        cancel = new JButton("Cancelar");
        from.setForeground(UTILS.foregroundFontColor);
        to.setForeground(UTILS.foregroundFontColor);
        messageL.setForeground(UTILS.foregroundFontColor);
        JButtonUtils.configButton(confirm, cancel);
    }

    private void position() {
        from.setBounds(20, 20, 300, 30);
        to.setBounds(20, 60, 100, 30);
        toCB.setBounds(130, 60, 200, 30);
        messageL.setBounds(20, 100, 100, 30);
        message.setBounds(130, 100, 200, 40);
        confirm.setBounds(20, 150, 150, 40);
        cancel.setBounds(180, 150, 150, 40);
    }

    private void actions() {
        cancel.addActionListener((ActionEvent ae) -> {
            dispose();
        });
    }

    public void start() {
        position();
        ViewAPI.addItems(screen, from, to, toCB, message, messageL, confirm, cancel);
        ViewAPI.configScreen(this, 350, 280);
        actions();
//        paint();
    }

}
