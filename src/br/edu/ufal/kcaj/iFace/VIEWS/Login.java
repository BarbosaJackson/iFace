package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.Utils;
import jdk.jshell.execution.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private final JButton confirm, softName, createAccount;
    private final JLabel userName, password;
    private final JTextField user;
    private final JPasswordField pass;
    private final Container screen;


    public Login() {
        screen = getContentPane();
        String urlImg = "../iFace/src/br/edu/ufal/kcaj/iFace/assets/";

        confirm = new JButton("   login");
        softName = new JButton("    IFace", new ImageIcon(urlImg + "logo.png"));
        createAccount = new JButton("   Criar conta", new ImageIcon(urlImg + "addAccount.png"));

        userName = new JLabel("Usuário");
        password = new JLabel("Senha");

        user = new JTextField();
        pass = new JPasswordField();
    }

    private void position() {
        userName.setBounds(20, 80, 100, 20);
        password.setBounds(20, 110, 100, 20);

        user.setBounds(130, 80, 150, 20);
        pass.setBounds(130, 110, 150, 20);

        confirm.setBounds(75, 140, 150, 50);
        createAccount.setBounds(75, 200, 150, 50);
        softName.setBounds(50 ,20, 200, 50);

    }

    private void addItems() {
        screen.add(softName);
        screen.add(userName);
        screen.add(password);
        screen.add(user);
        screen.add(pass);
        screen.add(confirm);
        screen.add(createAccount);
    }

    private void configScreen() {
        setUndecorated(true);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 280);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void paint() {
        screen.setBackground(new Color(0, 139, 139));

        userName.setForeground(new Color(176, 224, 230));
        password.setForeground(new Color(176, 224, 230));
    }

    private void actions() {
        confirm.addActionListener((ActionEvent ae) -> {

        });
        Utils.configButton(createAccount, confirm);
        Utils.allignButtons(softName);
        Utils.paintButtons(softName);
        softName.setEnabled(false);
    }

    public void start() {
        position();
        addItems();
        configScreen();
        actions();
        paint();
    }

    public static void main(String[] args) {
        new Login().start();
    }

}
