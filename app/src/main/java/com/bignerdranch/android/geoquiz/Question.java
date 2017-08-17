package com.bignerdranch.android.geoquiz;

/**
 * Created by Talon on 8/16/2017.
 */

// A Class that will store a string and boolean value.
public class Question {
    // An int variable that will store the ID.
    private int mTextResId;
    // Determines if a value is true.
    private boolean mAnswerTrue;


    // A custom method that will find the id of a
    // question and the boolean value of the answer.
    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    // Another method returns the strings ID.
    public int getTextResId() {
        return mTextResId;
    }

    // A void method that sets the textResId to mTextResId
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    // Returns a boolean value
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    // Assigns answerTrue to mAnswerTrue
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
