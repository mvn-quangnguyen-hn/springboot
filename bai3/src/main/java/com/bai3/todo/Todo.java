package com.bai3.todo;

import javax.persistence.*;

@Entity
@Table(name = "todo_tbl")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Todo() {
    }

    public Todo(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}
