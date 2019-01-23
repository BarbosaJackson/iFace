package br.edu.ufal.kcaj.iFace.VIEWS;

import br.edu.ufal.kcaj.iFace.BEANS.Community;
import br.edu.ufal.kcaj.iFace.BEANS.Message;
import br.edu.ufal.kcaj.iFace.BEANS.User;
import br.edu.ufal.kcaj.iFace.utils.JButtonUtils;
import br.edu.ufal.kcaj.iFace.utils.Pair;
import br.edu.ufal.kcaj.iFace.utils.UTILS;
import br.edu.ufal.kcaj.iFace.utils.ViewAPI;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.border.Border;
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
    private List<Community> communities;
    private boolean visibleConfigMenu, visibleFriends, visibleMessages;
    private ArrayList<JLabel> friendsList, messagesList;
    private JButton showFriend, showMessage;

    public Account(List<User> users, User me, List<Community> communities) {
        screen = getContentPane();

        this.me = me;
        this.users = users;
        this.communities = communities;

        this.visibleConfigMenu = false;

        leftMenu = new JPanel(null);
        configMenu = new JPanel(null);

        friendsPanel = new JPanel();
        friendsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));
        friendsPanel.setLayout(new GridLayout(0, 1, 10, 15));

        messagesPanel = new JPanel();
        messagesPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 10));
        messagesPanel.setLayout(new GridLayout(0, 1));

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

        messagesList = new ArrayList<>();
        showMessage = new JButton("Mensagens");

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
        notifications = new JButton("Notificações");
        if(this.me.getNotifications().size() == 0) {
            notifications.setIcon(new ImageIcon(UTILS.urlImg + "no_notification.png"));
        } else {
            notifications.setIcon(new ImageIcon(UTILS.urlImg + "has_notification.png"));
        }

        this.visibleMessages = changeButton(showMessage, visibleMessages, visibleFriends, showFriend, messages, friends);
        this.visibleFriends = changeButton(showFriend, visibleFriends, visibleMessages, showMessage, friends, messages);
        visibleMessages = !visibleFriends;
        configMenu.setVisible(visibleConfigMenu);
    }

    private void updateFriendList() {
        friendsPanel.removeAll();
        friendsList.add(new JLabel(UTILS.toHtmlH2("Amigos<hr>")));
        friendsPanel.add(friendsList.get(0));
        friendsList.get(0).setForeground(UTILS.foregroundFontColor);
        for(User u : me.getFriends()) {
            String friend = "Nome de usuário: " + u.getUsername() + "<br>Nome: " + u.getName();
            friendsList.add(new JLabel(UTILS.toHtmlParagraph(friend)));
            friendsPanel.add(friendsList.get(friendsList.size() - 1));
            friendsList.get(friendsList.size() - 1).setForeground(UTILS.foregroundFontColor);
        }
        friendsList.add(new JLabel(UTILS.toHtmlH2("Comunidades<hr>")));
        friendsPanel.add(friendsList.get(friendsList.size() - 1));
        friendsList.get(friendsList.size() - 1).setForeground(UTILS.foregroundFontColor);
        for(Community c : me.getCommunities()) {
            String field = "Dono: " + c.getLoginMaster() + "<br>Nome: " + c.getCommunityName();
            friendsList.add(new JLabel(UTILS.toHtmlParagraph(field)));
            friendsPanel.add(friendsList.get(friendsList.size() - 1));
            friendsList.get(friendsList.size() - 1).setForeground(UTILS.foregroundFontColor);
        }
        friends.setViewportView(friendsPanel);
    }

    private void addMessageToList(JLabel jl) {
        messagesList.add(jl);
        messagesPanel.add(messagesList.get(messagesList.size() - 1));
        messagesList.get(messagesList.size() - 1).setForeground(UTILS.foregroundFontColor);
    }

    private void updateMessageList() {
        messagesPanel.removeAll();
        messagesList.add(new JLabel(UTILS.toHtmlH2("Enviadas<hr>")));
        messagesPanel.add(messagesList.get(0));
        messagesList.get(0).setForeground(UTILS.foregroundFontColor);
        for(Message ms : me.getSentMessages()) {
            String field = UTILS.toHtmlParagraph("Para: " + ms.getFrom() + "<br>" + ms.getMessage());
            addMessageToList(new JLabel(field));
        }
        messagesList.add(new JLabel(UTILS.toHtmlH2("Recebidas<hr>")));
        messagesPanel.add(messagesList.get(messagesList.size() - 1));
        messagesList.get(messagesList.size() - 1).setForeground(UTILS.foregroundFontColor);
        for(Message ms : me.getReceivedMessages()) {
            String field = UTILS.toHtmlParagraph("De: " + ms.getTo() + "<br>" + ms.getMessage());
            addMessageToList(new JLabel(field));
        }
        for(Community c : me.getCommunities()) {
            for (Message ms : c.getMessages()) {
                String field = UTILS.toHtmlParagraph("De: " + ms.getFrom() + "@" + ms.getTo() + "<br>" + ms.getMessage());
                addMessageToList(new JLabel(field));
            }
        }
    }

    private void position() {
        userName.setBounds(300, 20, 300, 100);
        softName.setBounds(50 ,20, 200, 50);
        notifications.setBounds(620, 20, 200, 100);
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
        messages.setBounds(200, 260, 500, 300);
        showFriend.setBounds(400, 210, 100, 50);
        showMessage.setBounds(520, 210, 150, 50);
    }

    private boolean changeButton(JButton jb, boolean visibleButton, boolean visibleItemMenu2, JButton itemMenu2, JScrollPane jp1, JScrollPane jp2) {
        visibleButton = !visibleButton;
        if(visibleButton && visibleItemMenu2) {
            jp2.setVisible(false);
        }
        if(visibleButton) {
            JButtonUtils.paintButtons(jb, UTILS.selectedItemColor);
            JButtonUtils.paintButtons(itemMenu2);
        } else {
            JButtonUtils.paintButtons(jb);
        }
        jp1.setVisible(visibleButton);
        return visibleButton;
    }


    private void changeVisibleConfigMenu() {
        if(visibleConfigMenu) {
            visibleConfigMenu = false;
            configMenu.setVisible(false);
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
            return searchComunity(userNameSearch);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(this, "Você cancelou esta ação!");
            return -1;
        }
    }
    private int searchComunity(String nameUser) {
        int i = 0;
        for (Community c : communities) {
            if (c.getCommunityName().equals(nameUser)) {
                for (Community c2 : me.getCommunities()) {
                    if (c2.getCommunityName().equals(c.getCommunityName())) {
                        return -3;
                    }
                }
                me.getCommunities().add(c);
                communities.get(i).getMembers().add(me);
                return -4;
            }
            i++;
        }
        return -2;
    }

    private void updateNotifications() {
        if(this.me.getNotifications().size() == 0) {
            notifications.setIcon(new ImageIcon(UTILS.urlImg + "no_notification.png"));
        } else {
            notifications.setIcon(new ImageIcon(UTILS.urlImg + "has_notification.png"));
        }
    }

    private void actions() {
        addFriend.addActionListener((ActionEvent ae) -> {
            changeVisibleConfigMenu();
            int posUser = searchUser("Digite o nome de usuario do amigo ou da comunidade que você gostaria de adicionar");
            if(posUser == -2) {
                JOptionPane.showMessageDialog(this, "O nome de usuário digitado não foi encontrado");
            } else if(posUser == -3) {
                JOptionPane.showMessageDialog(this, "Você já faz parte dessa comunidade");
            } else if(posUser == -4) {
                JOptionPane.showMessageDialog(this, "Comunidade adicionada com sucesso!");
            } else if(posUser >= 0){
                users.get(posUser).addNotification(me);;
            }
        });
        showFriend.addActionListener((ActionEvent ae) -> {
            visibleFriends = changeButton(showFriend, visibleFriends, visibleMessages, showMessage, friends, messages);
            if(visibleMessages) visibleMessages = false;
            updateFriendList();
        });

        showMessage.addActionListener((ActionEvent ae) -> {
            visibleMessages = changeButton(showMessage, visibleMessages, visibleFriends, showFriend, messages, friends);
            if(visibleFriends) visibleFriends = false;
            updateMessageList();

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
            updateNotifications();
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
            if(me.getFriends().size() > 0 || me.getCommunities().size() > 0) {
                SendMessage sm = new SendMessage(me, users, communities,this);
                sm.start();
                if(visibleMessages) {
                    this.visibleMessages = changeButton(showMessage, visibleMessages, visibleFriends, showFriend, messages, friends);
                    this.visibleFriends = changeButton(showFriend, visibleFriends, visibleMessages, showMessage, friends, messages);
                    visibleMessages = false;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Você não tem nenhum amigo para enviar mensagem!");
            }
        });
        logout.addActionListener((ActionEvent ae) -> {
            new Login(users, communities).start();
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
        createCommunity.addActionListener((ActionEvent ae) -> {
            new AddCommunity(me, communities, users).start();
            if(visibleFriends) {
                this.visibleFriends = changeButton(showFriend, visibleFriends, visibleMessages, showMessage, friends, messages);
                this.visibleMessages = changeButton(showMessage, visibleMessages, visibleFriends, showFriend, messages, friends);
                visibleFriends = false;
            }
            updateMessageList();
        });
        deleteAccout.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(this, "Apagando as comunidades");
            for(Community c : communities) {
                if(c.getLoginMaster().equals(me.getUsername())) {
                    if(c.getMembers().size() > 1) {
                        c.setLoginMaster(c.getMembers().get(1).getUsername());
                        c.getMembers().remove(0);
                    } else {
                        communities.remove(c);
                    }
                }
            }
            for(Community c : communities) {
                for(int i = 0; i < c.getMessages().size(); i++) {
                    if(c.getMessages().get(i).getFrom().equals(me.getUsername())){
                        c.getMessages().remove(i);
                        i--;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Apagando as mensagens e os relacionamentos");
            for(User u : me.getFriends()) {
                for(User u2 : users) {
                    if(u.getUsername().equals(u2.getUsername())) {
                        for(int i = 0; i < u2.getReceivedMessages().size(); i++) {
                            // remove todas as mensagens que foram enviadas aos amigos do perfil deles
                            if(u2.getReceivedMessages().get(i).getFrom().equals(me.getUsername())){
                                u2.getReceivedMessages().remove(i);
                                i--;
                            }
                        }
                        // remove todas as mensagens enviadas dos amigos pra você
                        for(int i = 0; i < u2.getSentMessages().size(); i++) {
                            if(u2.getSentMessages().get(i).getTo().equals(me.getUsername())) {
                                u2.getSentMessages().remove(i);
                                i--;
                            }
                        }
                        // remove seu perfil da conta dos seus amigos
                        for(int i = 0; i < u2.getFriends().size(); i++) {
                            if(u2.getFriends().get(i).getUsername().equals(me.getUsername())) {
                                u2.getFriends().remove(i);
                                break;
                            }
                        }
                    }
                }
            }
            users.remove(me);
            new Login(users, communities).start();
            dispose();
        });
    }

    public void start() {
        position();
        ViewAPI.addItems(screen, softName, userName, notifications, leftMenu, configMenu, friends, messages, showFriend, showMessage);
        ViewAPI.addItems(leftMenu, configProfile, addFriend, sendMessage, createCommunity, showProfile, deleteAccout, logout);
        ViewAPI.addItems(configMenu, addDetail, editProfile);
        ViewAPI.configScreen(this, 800, 600);
        actions();

        ViewAPI.paint(softName, userName, notifications);
        friendsPanel.setBackground(UTILS.backgroundColorSecondWindows);
        messagesPanel.setBackground(UTILS.backgroundColorSecondWindows);
        friends.setBorder(null);
        messages.setBorder(null);

        JButtonUtils.paintButtons(notifications);
        JButtonUtils.configButton(configProfile, addFriend, sendMessage, createCommunity, showProfile, deleteAccout, logout, editProfile, addDetail);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
