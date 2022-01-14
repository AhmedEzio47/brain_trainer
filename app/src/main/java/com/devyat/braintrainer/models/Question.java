package com.devyat.braintrainer.models;

public class Question {
    public Question(int first, int second, int[] answers) {
        this.first = first;
        this.second = second;
        this.answers = answers;
    }

    public int getCorrectAnswer(){
        return  first + second;
    }

    public int getFirst(){
        return this.first;
    }

    public int getSecond(){
        return this.second;
    }

    int first;
    int second;
    int[] answers;
}
