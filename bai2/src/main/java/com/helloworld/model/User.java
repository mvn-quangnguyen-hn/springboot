package com.helloworld.model;

public class User {
    private int id;
    private String content;

    public User(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }
}
