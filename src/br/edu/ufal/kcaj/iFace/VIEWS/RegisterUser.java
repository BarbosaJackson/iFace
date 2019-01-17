package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.JButtonUtils;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RegisterUser extends JFrame {
    private final JButton confirm, cancel, softName;
    private final JLabel userNameL, passwordL, nameL;
    private final JTextField username, name;
    private final JPasswordField pass;
    private final Container screen;
    private List<User> users;

    public RegisterUser(List<User> users) {
        screen = getContentPane();
        screen.setBackground(new Color(32, 99, 155));
        confirm = new JButton("Confirmar");
        cancel = new JButton("Cancelar");
        softName = new JButton("    IFace", new ImageIcon(UTILS.urlImg + "logo.png"));

        userNameL = new JLabel("Nome de usuário");
        passwordL = new JLabel("Senha");
        nameL = new JLabel("Nome");

        username = new JTextField();
        name = new JTextField();

        pass = new JPasswordField();

        this.users = users;
    }

    private boolean validateData() {
        return (username.getText().length() > 0) && (pass.getPassword().length > 0) && (name.getText().length() > 0);
    }

    private void position() {
        nameL.setBounds(20, 80, 100, 20);
        userNameL.setBounds(20, 110, 100, 20);
        passwordL.setBounds(20, 140, 100, 20);
        name.setBounds(130, 80, 150, 20);
        username.setBounds(130, 110, 150, 20);
        pass.setBounds(130, 140, 150, 20);


        confirm.setBounds(80, 180, 120, 50);
        cancel.setBounds(200, 180, 100, 50);
        softName.setBounds(50 ,20, 200, 50);

    }

    private void actions() {
        confirm.addActionListener((ActionEvent ae) -> {
            if(validateData()) {
                User u = new User();
                u.setName(name.getText());
                u.setPassword(new String(pass.getPassword()));
                if (u.setUsername(username.getText(), users)) {
                    users.add(u);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "O nome de usuario digitado já existe, digite outro, por favor");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Você deve preencher todos os campos");
            }
        });
        cancel.addActionListener((ActionEvent ae) -> {
            dispose();
        } );
        JButtonUtils.configButton(confirm, cancel);
        JButtonUtils.allignButtons(softName);
        JButtonUtils.paintButtons(softName);
        softName.setEnabled(false);
    }

    public void start() {
        position();
        ViewAPI.addItems(screen, softName, userNameL, passwordL, nameL, username, name, pass, confirm, cancel);
        ViewAPI.configScreen(this, 350,300);
        actions();
        ViewAPI.paint(userNameL, passwordL, nameL);

    }
}
