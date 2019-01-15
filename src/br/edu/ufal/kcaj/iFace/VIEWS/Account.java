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

public class Account extends JFrame {
    private final JPanel leftMenu, configMenu;
    private JPanel friendsPanel, messagesPanel;
    private JScrollPane friends, messages;
    private final JButton  softName, notifications, configProfile, addFriend, sendMessage, createCommunity, showProfile,
            deleteAccout, logout, addDetail, editProfile;
    private final JLabel userName;
    private final Container screen;
    private List<User> users;
    private User me;
    private boolean visibleConfigMenu, visibleFriends, visibleMessages;
    private ArrayList<JLabel> friendsList, messagesList;
    private JButton showFriend, showMessage;

    public Account(List<User> users, User me) {
        screen = getContentPane();

        this.me = me;
        this.users = users;
        this.visibleConfigMenu = false;
        this.visibleFriends = true;
        this.visibleMessages = false;

        leftMenu = new JPanel(null);
        configMenu = new JPanel(null);

        friendsPanel = new JPanel(new GridLayout(0, 1));
        messagesPanel = new JPanel(null);

        friends = new JScrollPane();
        friends.setViewportView(friendsPanel);
        messages = new JScrollPane();
        messages.setViewportView(messagesPanel);

        softName = new JButton("<html><h1>    IFace</h1></html>", new ImageIcon(UTILS.urlImg + "logo.png"));
        JButtonUtils.allignButtons(softName);
        JButtonUtils.paintButtons(softName);
        softName.setEnabled(false);
        friendsList = new ArrayList<>();
        showFriend = new JButton("Amigos");

        updateFriendList();

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

    private void updateFriendList() {
        friendsPanel.removeAll();
        friendsList.add(new JLabel(UTILS.toHtmlH2("Amigos")));
        ViewAPI.addItems(friendsPanel, friendsList.get(0));
        friendsList.get(0).setBounds(20, 20, 100, 20);
        friendsList.get(0).setForeground(UTILS.foregroundFontColor);
        int y = 80;

        for(User u : me.getFriends()) {
            String friend = "Nome de usuário: " + u.getUsername() + "<br>Nome: " + u.getName();
            friendsList.add(new JLabel(UTILS.toHtmlParagraph(friend)));
            friendsPanel.add(friendsList.get(friendsList.size() - 1));
            friendsList.get(friendsList.size() - 1).setBounds(20, y, 100, 40);
            friendsList.get(friendsList.size() - 1).setForeground(UTILS.foregroundFontColor);
            y += 50;
        }
        friends.setViewportView(friendsPanel);
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
        friends.setBounds(200, 260, 500, 300);
        showFriend.setBounds(400, 210, 100, 50);
    }

    private void changeVisibleMessages() {
        if(visibleMessages){
            visibleMessages = !visibleMessages;
            // TODO: change the component visible
        }
    }

    private void changeVisibleConfigMenu() {
        if(visibleConfigMenu) {
            visibleConfigMenu = !visibleConfigMenu;
            configMenu.setVisible(visibleConfigMenu);
        }
    }

    private int searchUser(String searchText) {
        try {
            String userNameSearch = JOptionPane.showInputDialog(searchText);
            while (userNameSearch.length() == 0) {
                userNameSearch = JOptionPane.showInputDialog(searchText);
            }
            int i = 0;
            for(User u : users) {
                if(u.getUsername().equals(userNameSearch)) {
                    return i;
                }
                i++;
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Você cancelou esta ação!");
            return -1;
        }
        return -2;
    }
    private void actions() {
        addFriend.addActionListener((ActionEvent ae) -> {
            changeVisibleConfigMenu();
            int posUser = searchUser("Digite o nome de usuario do amigo que você gostaria de adicionar");
            if(posUser == -2) {
                JOptionPane.showMessageDialog(this, "O nome de usuário digitado não foi encontrado");
            } else if(posUser >= 0){
                users.get(posUser).addNotification(me);;
            }
        });
        showFriend.addActionListener((ActionEvent ae) -> {
            visibleFriends = !visibleFriends;
            if(visibleFriends && visibleMessages) {
                visibleMessages = !visibleMessages;
                messages.setVisible(visibleMessages);
            }
            friends.setVisible(visibleFriends);
        });
        notifications.addActionListener((ActionEvent ae) -> {
            changeVisibleConfigMenu();
            if(me.getNotifications().size() == 0) {
                JOptionPane.showMessageDialog(this, "Não há novas solicitações de amizade");
            } else {
                JOptionPane.showMessageDialog(this, "Você tem " + me.getNotifications().size() + " solicitações de amizade!");
                for(int i = 0; i < me.getNotifications().size(); i++) {
                    String friend = "Nome de usuário: " + me.getNotifications().get(i).getUsername() + "\nNome: " + me.getNotifications().get(i).getName();
                    switch (JOptionPane.showConfirmDialog(this, friend)) {
                        case 0:
                            me.addFriend(me.getNotifications().get(i));
                            me.getNotifications().get(i).addFriend(me);
                            me.getNotifications().remove(i);
                            i--;
                            break;
                        case 1:
                            me.getNotifications().remove(i);
                            i--;
                            break;
                        case 2:
                            i = me.getNotifications().size() + 10;
                            break;
                    }
                }
                updateFriendList();
            }
        });
        editProfile.addActionListener((ActionEvent ae) -> {
            changeVisibleConfigMenu();
            new EditProfile(me).start();
        });
        configProfile.addActionListener((ActionEvent ae) -> {
            visibleConfigMenu = !visibleConfigMenu;
            configMenu.setVisible(visibleConfigMenu);
        });

        showProfile.addActionListener((ActionEvent ae) -> {
            changeVisibleConfigMenu();
            int u = searchUser("Digite o nome de usuário de quem você deseja ver o perfil");
            if(u == -1) {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
            } else {
                new DetailUser(users.get(u), this).start();
            }
        });
        sendMessage.addActionListener((ActionEvent ae) -> {
            changeVisibleConfigMenu();
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
            changeVisibleConfigMenu();
            try {
                String titulo = JOptionPane.showInputDialog("Digite o titulo para a caracteristica que você deseja adicionar");
                while (titulo.length() == 0) {
                    titulo = JOptionPane.showInputDialog("Digite o titulo para a caracteristica que você deseja adicionar");
                }
                String detail = JOptionPane.showInputDialog("Olá, " + me.getUsername() + ". Qual o seu " + titulo + "?");
                while (detail.length() == 0) {
                    detail = JOptionPane.showInputDialog("Digite o titulo para a caracteristica que você deseja adicionar");
                }
                Pair<String, String> p = new Pair<String, String>(titulo, detail);
                me.getDetails().add(p);
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this,"Você cancelou a ação");
            }
        });
    }

    public void start() {
        position();
        ViewAPI.addItems(screen, softName, userName, notifications, leftMenu, configMenu, friends, showFriend);
        ViewAPI.addItems(leftMenu, configProfile, addFriend, sendMessage, createCommunity, showProfile, deleteAccout, logout);
        ViewAPI.addItems(configMenu, addDetail, editProfile);
        ViewAPI.configScreen(this, 800, 600);
        actions();
        ViewAPI.paint(softName, userName, notifications);
        friendsPanel.setBackground(UTILS.backgroundColorSecondWindows);
        friends.setBorder(null);
        JButtonUtils.paintButtons(notifications);
        JButtonUtils.configButton(configProfile, addFriend, sendMessage, createCommunity, showProfile, deleteAccout, logout, editProfile, addDetail, showFriend);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
