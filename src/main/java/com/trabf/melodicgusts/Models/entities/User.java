package com.trabf.melodicgusts.Models.entities;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    private String name;
    private int score;

    public User(String name) {
        this.name = name;
        this.score = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore() {
        this.score++;
    }

    //save score in file
    public void saveScore() {
        String filePath = "Scores.txt";
        String content = "User: " + this.name + " Score: " + this.score + "\n";

        // Use FileWriter and BufferedWriter to write to the file
        try (FileWriter fw = new FileWriter(filePath);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(content);
            System.out.println("File written successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
