package uta.cse3310;

import java.util.ArrayList;

public class UserAuthentication {
    ArrayList<String> users = new ArrayList<String>();
    public void login(String nickname) {
        // Implementation goes here
        if (checkName(nickname)) {
            System.out.println("Welcome " + nickname);
        } else {
            System.out.println("Invalid nickname");
        }

    }

    public void newUser(String nickname) {
        // Implementation goes here
        if (checkName(nickname)) {
            System.out.println("User already exists");
        } else {
            users.add(nickname);
            System.out.println("Welcome " + nickname);
        }
    }

    public Boolean checkName(String nickname) {
        // Implementation goes here
        return users.contains(nickname);
    }
}