package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.JButtonUtils;
import br.edu.ufal.kcaj.iFace.utils.Pair;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class Account extends JFrame {
    private final JPanel leftMenu, configMenu;
    private final JButton  softName, notifications, configProfile, addFriend, sendMessage, createCommunity, showProfile,
            deleteAccout, logout, addDetail, editProfile;
    private final JLabel userName;
    private final Container screen;
    private List<User> users;
    private User me;
    private boolean visibleConfigMenu;

    public Account(List<User> users, User me) {
        screen = getContentPane();

        this.me = me;
        this.users = users;
        this.visibleConfigMenu = false;

        leftMenu = new JPanel(null);
        configMenu = new JPanel(null);

        softName = new JButton("<html><h1>    IFace</h1></html>", new ImageIcon(UTILS.urlImg + "logo.png"));
        JButtonUtils.allignButtons(softName);
        JButtonUtils.paintButtons(softName);
        softName.setEnabled(false);

        screen.setBackground(new Color(32, 99, 155));
        leftMenu.setBackground(new Color(32, 99, 155));
        configMenu.setBackground(new Color(32, 99, 155));

        userName = new JLabel("<html><h1>Olá, " + me.getUsername() + " seja bem vindo</h1></html>");
        configProfile = new JButton(UTILS.toHtmlH3("Config. Perfil"), new ImageIcon(UTILS.urlImg
         + "edit.png"));
        addFriend = new JButton(UTILS.toHtmlH3("adicionar amigo"), new ImageIcon(UTILS.urlImg + "add_friend.png"));
        sendMessage = new JButton(UTILS.toHtmlH3("Enviar mensagem"), new ImageIcon(UTILS.urlImg + "send_message.png"));
        createCommunity = new JButton(UTILS.toHtmlH3("Criar comunidade"), new ImageIcon(UTILS.urlImg + "add_community.png"));
        showProfile = new JButton(UTILS.toHtmlH3("Ver perfil"), new ImageIcon(UTILS.urlImg + "look_profile.png"));
        deleteAccout = new JButton(UTILS.toHtmlH3("Deletar conta"), new ImageIcon(UTILS.urlImg + "delete-user.png"));
        logout = new JButton(UTILS.toHtmlH3("Sair"));
        addDetail = new JButton(UTILS.toHtmlH3("Adicionar caracteristica"));
        editProfile = new JButton(UTILS.toHtmlH3("Editar perfil"));
        if(this.me.getNotifications().size() == 0) {
            notifications = new JButton(new ImageIcon(UTILS.urlImg + "no_notification.png"));
        } else {
            notifications = new JButton(new ImageIcon(UTILS.urlImg + "has_notification.png"));
        }
        configMenu.setVisible(visibleConfigMenu);
    }

    private void position() {
        userName.setBounds(300, 20, 300, 100);
        softName.setBounds(50 ,20, 200, 50);
        notifications.setBounds(620, 20, 100, 100);
        leftMenu.setBounds(0, 100, 200, 500);
        configProfile.setBounds(10, 20, 150, 50);
        configMenu.setBounds(200, 100, 200, 130);
        addDetail.setBounds(20, 20, 150, 50);
        editProfile.setBounds(20, 80, 150, 50);;
        addFriend.setBounds(10, 90, 150, 50);
        sendMessage.setBounds(10, 160, 150, 50);
        createCommunity.setBounds(10, 230, 150 , 50);
        showProfile.setBounds(10, 300, 150, 50);
        deleteAccout.setBounds(10, 370, 150, 50);
        logout.setBounds(10, 430, 150, 50);
    }


    private void actions() {
        notifications.addActionListener((ActionEvent ae) -> {
            if(visibleConfigMenu) {
                visibleConfigMenu = !visibleConfigMenu;
                configMenu.setVisible(visibleConfigMenu);
            }
            if(me.getNotifications().size() == 0) {
                JOptionPane.showMessageDialog(this, "Não há novas solicitações de amizade");
            } else {

            }
        });
        editProfile.addActionListener((ActionEvent ae) -> {
            new EditProfile(me).start();
        });
        configProfile.addActionListener((ActionEvent ae) -> {
            visibleConfigMenu = !visibleConfigMenu;
            configMenu.setVisible(visibleConfigMenu);
        });

        showProfile.addActionListener((ActionEvent ae) -> {
            if(visibleConfigMenu) {
                visibleConfigMenu = !visibleConfigMenu;
                configMenu.setVisible(visibleConfigMenu);
            }
            String usernameS = JOptionPane.showInputDialog("Digite o nome de usuário que você deseja ver o perfil");
            boolean flag = false;
            for(User u : users) {
                if(u.getUsername().equals(usernameS)) {
                    new DetailUser(u, this).start();
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
            }
        });
        sendMessage.addActionListener((ActionEvent ae) -> {
            if(visibleConfigMenu) {
                visibleConfigMenu = !visibleConfigMenu;
                configMenu.setVisible(visibleConfigMenu);
            }
            if(me.getFriends().size() > 0) {
                new SendMessage(me, users, this).start();
            } else {
                JOptionPane.showMessageDialog(this, "Você não tem nenhum amigo para enviar mensagem!");
            }
        });
        logout.addActionListener((ActionEvent ae) -> {
            new Login(users).start();
            dispose();
        });
        addDetail.addActionListener((ActionEvent ae) -> {
            String titulo = JOptionPane.showInputDialog("Digite o titulo para a caracteristica que você deseja adicionar");
            while(titulo.length() == 0) {
                titulo = JOptionPane.showInputDialog("Digite o titulo para a caracteristica que você deseja adicionar");
            }
            String detail = JOptionPane.showInputDialog("Olá, " + me.getUsername() + ". Qual o seu " + titulo + "?");
            Pair<String, String> p = new Pair<String, String>(titulo, detail);
            me.getDetails().add(p);
        });
    }

    public void start() {
        position();
        ViewAPI.addItems(screen, softName, userName, notifications, leftMenu, configMenu);
        ViewAPI.addItems(leftMenu, configProfile, addFriend, sendMessage, createCommunity, showProfile, deleteAccout, logout);
        ViewAPI.addItems(configMenu, addDetail, editProfile);
        ViewAPI.configScreen(this, 800, 600);
        actions();
        ViewAPI.paint(softName, userName, notifications);
        JButtonUtils.paintButtons(notifications);
        JButtonUtils.configButton(configProfile, addFriend, sendMessage, createCommunity, showProfile, deleteAccout, logout, editProfile, addDetail);
    }
}
