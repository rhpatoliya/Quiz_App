package com.renu.quiz_assignment;

import android.graphics.Color;

public class Questions
{
    int questionsid;
    Boolean answers;
    String colorid;


    public Questions(int question1, boolean answers, String color)
    {
        this.questionsid = question1;
        this.answers = answers;
        this.colorid = color;

    }

    public Questions() {

    }


    public int getText()
    {
        return questionsid;
    }

    public void setText(String text)
    {
        this.questionsid = questionsid;
    }

    public Boolean getAnswers()
    {
        return answers;
    }

    public void setAnswers(Boolean answers)
    {
        this.answers = answers;
    }

    public String getColor()
    {
        return colorid;
    }

    public void setColor(String color)
    {
        this.colorid = colorid;
    }
}
