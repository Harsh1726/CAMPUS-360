package com.example.mitujjainofficial.ui.faculty;

public class TeacherData {

    private String name, email, post, image, subjects, cabinlocation, telegramid, key;

    public TeacherData() {

    }

    public TeacherData(String name, String email, String post, String image, String subjects, String cabinlocation, String telegramid, String key) {
        this.name = name;
        this.email = email;
        this.post = post;
        this.image = image;
        this.subjects = subjects;
        this.cabinlocation = cabinlocation;
        this.telegramid = telegramid;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getCabinlocation() {
        return cabinlocation;
    }

    public void setCabinlocation(String cabinlocation) {
        this.cabinlocation = cabinlocation;
    }

    public String getTelegramid() {
        return telegramid;
    }

    public void setTelegramid(String telegramid) {
        this.telegramid = telegramid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



}
