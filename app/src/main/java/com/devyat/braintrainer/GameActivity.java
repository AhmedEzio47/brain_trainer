package com.devyat.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devyat.braintrainer.models.Question;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private final int QUIZ_SECONDS = 15;
    private boolean active = true;
    private CountDownTimer mTimer;

    TextView mTimerTV;
    TextView mQuizTV;
    TextView mScoreTV;
    TextView mResultTV;
    Question mQuestion;
    Button mAnswer1;
    Button mAnswer2;
    Button mAnswer3;
    Button mAnswer4;
    Button mPlayAgain;
    int mQuestionsCount = 0;
    int mCorrectAnswersCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();

    }

    private void init() {
        mQuestionsCount = 0;
        mCorrectAnswersCount = 0;
        mTimerTV = findViewById(R.id.timerTV);
        mScoreTV = findViewById(R.id.scoreTV);
        mQuizTV = findViewById(R.id.quizTV);
        mResultTV = findViewById(R.id.resultTV);
        mAnswer1 = findViewById(R.id.answer1);
        mAnswer2 = findViewById(R.id.answer2);
        mAnswer3 = findViewById(R.id.answer3);
        mAnswer4 = findViewById(R.id.answer4);
        mPlayAgain = findViewById(R.id.playAgainBtn);
        mResultTV.setVisibility(View.INVISIBLE);
        mPlayAgain.setVisibility(View.INVISIBLE);
        mScoreTV.setText("0/0");
        mPlayAgain.setOnClickListener(v -> init());
        mAnswer1.setOnClickListener(v -> checkAnswer(Integer.parseInt(((Button) v).getText().toString())));
        mAnswer2.setOnClickListener(v -> checkAnswer(Integer.parseInt(((Button) v).getText().toString())));
        mAnswer3.setOnClickListener(v -> checkAnswer(Integer.parseInt(((Button) v).getText().toString())));
        mAnswer4.setOnClickListener(v -> checkAnswer(Integer.parseInt(((Button) v).getText().toString())));

        mTimer = new CountDownTimer(QUIZ_SECONDS * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimerTV.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                active = false;
                mResultTV.setText(mCorrectAnswersCount + "/" + mQuestionsCount);
                mResultTV.setVisibility(View.VISIBLE);
                mPlayAgain.setVisibility(View.VISIBLE);
            }
        }.start();

        generateQuestion();
    }

    private void checkAnswer(int answer) {
        if(!active){
            return;
        }
        if (answer == mQuestion.getCorrectAnswer()) {
            mCorrectAnswersCount++;
        }
        mQuestionsCount++;
        mScoreTV.setText(mCorrectAnswersCount + "/" + mQuestionsCount);
        generateQuestion();
    }

    private void generateQuestion() {
        Random random = new Random();
        int[] answers = {random.nextInt(20), random.nextInt(20), random.nextInt(20), random.nextInt(20)};
        int first = random.nextInt(10);
        int second = random.nextInt(10);
        mQuestion = new Question(first, second, answers);
        answers[random.nextInt(4)] = mQuestion.getCorrectAnswer();
        mQuizTV.setText(mQuestion.getFirst() + " + " + mQuestion.getSecond());
        mAnswer1.setText(String.valueOf(answers[0]));
        mAnswer2.setText(String.valueOf(answers[1]));
        mAnswer3.setText(String.valueOf(answers[2]));
        mAnswer4.setText(String.valueOf(answers[3]));
    }
}