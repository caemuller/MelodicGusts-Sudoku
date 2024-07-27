package com.trabf.melodicgusts.Models.entities;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    public void incrementScore() {
        //this.score++;
        score.set(score.get() + 1);
    }

    //save score in file
    public void saveScore() {
        String filePath = "Scores.txt";
        String content = "User: " + nameProperty().get() + " Score: " + scoreProperty().get() + "\n";

        // Use FileWriter and BufferedWriter to write to the file
        try (FileWriter fw = new FileWriter(filePath);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(content);
            System.out.println("File written successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
