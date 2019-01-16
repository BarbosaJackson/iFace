package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.JButtonUtils;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
public class Login extends JFrame {

    private final JButton confirm, softName, createAccount, exit;
    private final JLabel userName, password;
    private final JTextField user;
    private final JPasswordField pass;
    private final Container screen;
    private List<User> users;

    public Login(List<User> users) {
        screen = getContentPane();

        confirm = new JButton("   login");
        softName = new JButton("    IFace", new ImageIcon(UTILS.urlImg + "logo.png"));
//        System.out.println(UTILS.urlImg + "logo.png");
        createAccount = new JButton("   Criar conta", new ImageIcon(UTILS.urlImg + "addAccount.png"));
        exit = new JButton(UTILS.toHtmlH3("FECHAR"));

        this.users = users;
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
        createAccount.setBounds(75, 200, 200, 50);
        softName.setBounds(50 ,20, 200, 50);
        exit.setBounds(250, 0, 100, 20);
    }

    private void addItems() {
        screen.add(softName);
        screen.add(userName);
        screen.add(password);
        screen.add(user);
        screen.add(pass);
        screen.add(confirm);
        screen.add(createAccount);
        screen.add(exit);
    }

    private void paint() {
        screen.setBackground(new Color(32, 99, 155));
        userName.setForeground(new Color(176, 224, 230));
        softName.setForeground(new Color(176, 223, 230));
        password.setForeground(new Color(176, 224, 230));
        exit.setForeground(new Color(176, 224, 230));
    }

    private void actions() {
        confirm.addActionListener((ActionEvent ae) -> {
            for(User u : users) {
                if(u.getUsername().equals(user.getText()) && u.getPassword().equals(new String(pass.getPassword()))){
                    new Account(users, u).start();
                    dispose();
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Nome de usuário ou senha não encontrados.");
        });

        createAccount.addActionListener((ActionEvent ae) -> {
            new RegisterUser(this.users).start();
        });

        exit.addActionListener((ActionEvent ae) -> {
            dispose();
        });

        JButtonUtils.configButton(createAccount, confirm);
        JButtonUtils.allignButtons(exit);
        exit.setContentAreaFilled(false);
        exit.setBorderPainted(false);
        JButtonUtils.allignButtons(softName);
        JButtonUtils.paintButtons(softName);
        softName.setEnabled(false);
    }

    public void start() {
        position();
        addItems();
        ViewAPI.configScreen(this, 330, 280);
        actions();
        paint();
        user.requestFocus();
    }

    public static void main(String[] args) {
        List<User> u = new ArrayList<>();
        u.add(new User("Jackson", "kcaj", "1234"));
        u.add(new User("Hiago", "hrns", "1234"));
        u.add(new User("Bruno", "bcn", "1234"));
        u.add(new User("Adilson", "adilsullen", "1234"));
        u.add(new User("a", "a", "a"));
        u.add(new User("b", "b", "b"));
        u.add(new User("c", "c", "c"));
        u.add(new User("d", "d", "d"));
        u.add(new User("e", "e", "e"));
        u.add(new User("f", "f", "f"));
        u.add(new User("g", "g", "g"));
        u.add(new User("h", "h", "h"));
        for(int i = 1; i < u.size(); i++) {
            u.get(0).getNotifications().add(u.get(i));
        }


        new Login(u).start();
    }

}
