package com.webingate.paysmartbusinessapp.object;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Panacea-Soft on 7/23/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


public class QuizGeneral {

    @SerializedName("question")
    public String question;

    @SerializedName("answer1")
    public String answer1;

    @SerializedName("answer2")
    public String answer2;

    @SerializedName("answer3")
    public String answer3;

    @SerializedName("answer4")
    public String answer4;

    @SerializedName("answer5")
    public String answer5;

    @SerializedName("correct_answer")
    public String correctAnswer;

    @SerializedName("selected_answer")
    public int selectedAnswer;

    @SerializedName("image")
    public String image;

    public QuizGeneral(String question, String answer1, String answer2, String answer3, String answer4, String answer5, String correctAnswer, String image) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answer5 = answer5;
        this.correctAnswer = correctAnswer;
        this.image = image;
    }
}
