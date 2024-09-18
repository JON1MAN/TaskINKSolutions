package com.task.TaskINKSolutions.DTOs;

import java.time.LocalDate;

public class NewsResponse {
    private String author;
    private String title;
    private String description;
    private String url;
    private LocalDate date;

    public NewsResponse(String author, String title, String description, String url, LocalDate date) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
