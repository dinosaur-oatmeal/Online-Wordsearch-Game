package uta.cse3310;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserAuthentication {
    private static final String DB_FILE = "users.txt";
    private ArrayList<String> users;

    public UserAuthentication() {
        users = readUsersFromFile();
    }

    public void login(String nickname) {
        if (checkName(nickname)) {
            System.out.println("Welcome " + nickname);
        } else {
            System.out.println("Invalid nickname");
        }
    }

    public void newUser(String nickname) {
        if (checkName(nickname)) {
            System.out.println("User already exists");
        } else {
            users.add(nickname);
            saveUsersToFile(users);
            System.out.println("Welcome " + nickname);
        }
    }

    public Boolean checkName(String nickname) {
        return users.contains(nickname);
    }

    private ArrayList<String> readUsersFromFile() {
        ArrayList<String> userList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DB_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                userList.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return userList;
    }

    private void saveUsersToFile(ArrayList<String> userList) {
        try (FileWriter writer = new FileWriter(DB_FILE)) {
            for (String user : userList) {
                writer.write(user + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}