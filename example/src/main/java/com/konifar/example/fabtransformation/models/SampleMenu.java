package com.konifar.example.fabtransformation.models;

public class SampleMenu {
    private String title;
    private int drawableResId;

    public SampleMenu(String title, int drawableResId) {
        this.title = title;
        this.drawableResId = drawableResId;
    }

    public String getTitle() {
        return title;
    }

    public int getDrawableResId() {
        return drawableResId;
    }

}
