// Talon James Easterling
// SPC ID: 2295124
package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    // Creates the True/False buttons.
    private Button mTrueButton;
    private Button mFalseButton;

    // Creates the Next/Previous buttons
    private ImageButton mNextButton;
    private ImageButton mPrevButton;

    // Sets up a TextView for the question that will be displayed.
    private TextView mQuestionTextView;

    // Initiates the index variable.
    private int mCurrentIndex = 0;

    // Create a TAG for debugging and indexing.
    public static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    // Create an array that will store the questions and answers.
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_ocean, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    // A private void that will allow the app to display a question.
    private void upDateQuestion() {
        Log.d(TAG,"Updating question text", new Exception());
        // Gets the id of the selected question.
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        // Sets the text.
        mQuestionTextView.setText(question);
    }

    // A private void that will check the answer of the user.
    private void checkAnswer(boolean userPressedTrue) {
        // A boolean variable that stores the answer of the selected question.
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        // Initiates the ID of a message that will be broadcasted.
        int messageResId = 0;

        // If what the user has answered if true, inform them that they are correct.
        if (userPressedTrue == answerIsTrue) {
            // Assigns messageResId with the "Correct!" string.
            messageResId = R.string.correct_toast;
        }
        // Else, inform them they are incorrect.
        else {
            // Assigns messageResId with the "Incorrect!" string.
            messageResId = R.string.incorrect_toast;
        }

        // Creates a notification text that will inform the user of the result.
        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        // Set the Toast to the top-center of the screen.
        toast.setGravity(Gravity.TOP | Gravity.CENTER,0,0);
        // Show the toast.
        toast.show();
    }

    // A void method that is invoked when something needs to be created.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Super class.
        super.onCreate(savedInstanceState);
        // Will create a log whenever this method is called.
        Log.d(TAG,"onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        // Get the index of an index if savedInstanceState is not empty.
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        // Find the id for question_text_view.
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        // Find the ID of the True button.
        mTrueButton = (Button) findViewById(R.id.true_button);
        // Create a listener node for the True button.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            // Use whenever the user presses the true button
            @Override
            public void onClick(View v) {
                // Call method.
                checkAnswer(true);
                // Disable the buttons
                mFalseButton.setEnabled(false);
                mTrueButton.setEnabled(false);
            }
        });

        // Find the ID of the False button.
        mFalseButton = (Button) findViewById(R.id.false_button);
        // Create a listener node for the False button.
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            // Use whenever the user presses the false button
            @Override
            public void onClick(View v) {
                // Call method.
                checkAnswer(false);
                // Disables the buttons
                mFalseButton.setEnabled(false);
                mTrueButton.setEnabled(false);
            }
        });

        // Find the ID of the Next button.
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        // Create a listener node for the Next button.
        mNextButton.setOnClickListener(new View.OnClickListener() {
            // Use whenever the user presses the next button
            @Override
            public void onClick(View v) {
                // Moves to the next question, or loops back to the beginning.
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                // Call method.
                upDateQuestion();

                // Re-enables the True/False buttons.
                mFalseButton.setEnabled(true);
                mTrueButton.setEnabled(true);
            }
        });

        // Find the ID of the Previous button.
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        // Create a listener node for the previous button.
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Moves back to the previous question.
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                // Call method
                upDateQuestion();

                // Re-enables the True/False buttons.
                mFalseButton.setEnabled(true);
                mTrueButton.setEnabled(true);

            }
        });

        // Create a listener node for the TextView.
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Moves to the next question, or loops back to the beginning.
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    // Call method
                    upDateQuestion();

                    // Re-enables the True/False buttons.
                    mFalseButton.setEnabled(true);
                    mTrueButton.setEnabled(true);
                }
            });
        }

    // A method that will record any instance that onStart() is called.
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    // A method that will record any instance that onResume() is called.
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
        // Updates the question so the question pops back up on the screen.
        upDateQuestion();
    }

    // A method that will record any instance that onPause() is called.
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    // A method that will record any instance that onSaveInstanceState() is called.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    // A method that will record any instance that onStop() is called.
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }

    // A method that will record any instance that onDestroy() is called.
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }
}