package br.edu.ufal.kcaj.iFace.BEANS;

import br.edu.ufal.kcaj.iFace.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name, username, password;
    private List < Pair < String, String > > details; // first = title, second = detail
    private List <Message> sentMessages, receivedMessages;
    private List < User > notifications, friends;

    public User() {
        details = new ArrayList<>();
        notifications = new ArrayList<>();
        sentMessages = new ArrayList<>();
        receivedMessages = new ArrayList<>();
        friends = new ArrayList<>();
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        notifications = new ArrayList<>();
        details = new ArrayList<>();
        sentMessages = new ArrayList<>();
        receivedMessages = new ArrayList<>();
        friends = new ArrayList<>();
    }


    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User u) {
        friends.add(u);
    }
    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String username, List<User> users) {
        for(User u : users) {
            if(u.getUsername().equals(username)) {
                return false;
            }
        }
        this.username = username;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addDetail(Pair<String, String> detail) {
        details.add(detail);
    }

    public List<Pair<String, String>> getDetails() {
        return details;
    }

    public void setDetails(List<Pair<String, String>> details) {
        this.details = details;
    }

    public List<User> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<User> notifications) {
        this.notifications = notifications;
    }
}
