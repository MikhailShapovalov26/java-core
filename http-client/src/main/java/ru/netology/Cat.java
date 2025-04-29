package ru.netology;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Cat {
    private String id;
    @JsonAlias({ "text" })
    private String description;
    private String type;
    @JsonAlias({ "user" })
    private String fio;
    private String upvotes;

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setUpvotes(String upvotes) {
        this.upvotes = upvotes;
    }



    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getFio() {
        return fio;
    }

    public String getUpvotes() {
        return upvotes;
    }

    @Override
    public String toString() {
        return "id='" + id + '\'' + "\n"+
                "description='" + description + '\'' + "\n"+
                "type='" + type + '\'' + "\n"+
                "fio='" + fio + '\'' + "\n"+
                "upvotes='" + upvotes + '\'';
    }
}
