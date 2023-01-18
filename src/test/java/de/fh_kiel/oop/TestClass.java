package de.fh_kiel.oop;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestClass {

    @JsonProperty("title")
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
