package com.trabf.melodicgusts.Models.entities;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
// import hashmap
import java.util.HashMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    private StringProperty name;
    private IntegerProperty score;

    public User(String name, Integer score) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.score = new SimpleIntegerProperty(this, "score", score);
    }

    public StringProperty nameProperty() {return name;}

    public IntegerProperty scoreProperty() {return score;}

    //save score in file
    public void saveScore() {
        String filePath = "Scores.txt";
        // create hash map to store user scores
        HashMap<String, Integer> userScores = new HashMap<>();
        //read file if exists adn complete hashmap
        try {
            // Use FileReader and BufferedReader to read the file
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", Score: ");
                String name = parts[0].substring(6);
                int score = Integer.parseInt(parts[1].substring(0, parts[1].length() - 1));
                userScores.put(name, score);
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // check if name is already in hashmap
        if (userScores.containsKey(nameProperty().get())) {
            // if it is, update the score adding to the previous score
            userScores.put(nameProperty().get(), scoreProperty().get() + userScores.get(nameProperty().get()));
        } else {
            // if it isn't, add the name and score
            userScores.put(nameProperty().get(), scoreProperty().get());
        }
        //overwrite file with new scores
        try {
            // Use FileWriter and BufferedWriter to write the file
            FileWriter fw = new FileWriter(filePath);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String key : userScores.keySet()) {
                bw.write("Name: " + key + ", Score: " + userScores.get(key) + "\n");
            }
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
