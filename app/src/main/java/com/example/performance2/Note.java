package com.example.performance2;

public class Note  {
    private String title;
    private String subTitle;
    private String content;

    public Note(){ }
    public Note(String title,String subTitle,String content){
        this.title=title;
        this.subTitle = subTitle;
        this.content=content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
