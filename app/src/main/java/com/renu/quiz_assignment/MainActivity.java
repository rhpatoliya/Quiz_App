package com.renu.quiz_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements FragmentDialog.DialogClickListener
{
    String TAG = "MainActivity";


    private ProgressBar progess_bar;
    Button true_btu, false_btu;


    ArrayList<Questions> answerList = new ArrayList<>();
    QuestionsBank questionsBank = new QuestionsBank();


    int correctAnswer = 0;
    private int currentQuestionsIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        true_btu = (Button) findViewById(R.id.true_btu);
        false_btu = (Button) findViewById(R.id.false_btu);

        progess_bar = (ProgressBar) findViewById(R.id.progess_bar);
        progess_bar.setMax(10);
        progess_bar.setProgress(0);

        questionsBank.createArrayList();

        updateFragment();

    }

    //Menu part
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Menu Part
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.get_the_average:
                Toast.makeText(this, "Clicked Menu 1", Toast.LENGTH_SHORT).show();
                getAverage();

                break;
            case R.id.select_the_numberofquestions:
                Toast.makeText(this, "Clicked Menu 2", Toast.LENGTH_SHORT).show();


                break;
            case R.id.reset_the_saved_result:
                Toast.makeText(this, "Clicked Menu 3", Toast.LENGTH_SHORT).show();
                SaveResult.resetAllTask(MainActivity.this);
                break;
            case R.id.add_questions:
                Toast.makeText(this, "Clicked Menu 4", Toast.LENGTH_SHORT).show();
                open_dialog();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAverage() {
        ArrayList<Integer> data = SaveResult.getAllTasks(MainActivity.this);
int totalCorrectAnswers = 0;
if(data != null && data.size()>0){
    for(int i = 0; i < data.size();i++){
        totalCorrectAnswers = totalCorrectAnswers + data.get(i);

    }

    AlertDialog.Builder adb = new AlertDialog.Builder(this);

    adb.setTitle("Result");
    adb.setMessage("Your score is " + totalCorrectAnswers/data.size() + " out of " + data.size() + "attempts");

    adb.setPositiveButton("Save", new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {



            correctAnswer = 0;
            answerList = new ArrayList<>();
        }
    });

    adb.setNegativeButton("Ok", new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {
            correctAnswer = 0;
            answerList = new ArrayList<>();
        }
    });
    adb.show();

}
else{
    Toast.makeText(MainActivity.this,"plz select questions", Toast.LENGTH_SHORT).show();

}

    }

    //getting data from Fragment
    private void updateFragment()
    {
        FragmentManager manager = getSupportFragmentManager();
        manager.findFragmentById(R.id.framelayout);
        QuestionsFragment fragmentDemo = QuestionsFragment.newInstance(questionsBank.questionsList.get(currentQuestionsIndex).questionsid, questionsBank.questionsList.get(currentQuestionsIndex).colorid);
        manager.beginTransaction().add(R.id.framelayout, fragmentDemo, "tag").commit();
    }


    // On click on True and False Buttons
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.true_btu:
                Toast.makeText(MainActivity.this, "True", Toast.LENGTH_SHORT).show();
                checkAnswer(true);
                updateIndex();
                updateFragment();
                //For Progress Bar
                progess_bar.setProgress(currentQuestionsIndex);
                break;


            case R.id.false_btu:
                Toast.makeText(MainActivity.this, "False", Toast.LENGTH_SHORT).show();
                checkAnswer(false);
                updateIndex();
                updateFragment();
                //for progress bar
                progess_bar.setProgress(currentQuestionsIndex);
                break;
        }

    }

    private void showDialoag()
    {
        Log.d("Current", "Onclick" + currentQuestionsIndex);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        adb.setTitle("Result");
        adb.setMessage("Your score is " + correctAnswer + " out of " + questionsBank.questionsList.size());

        adb.setPositiveButton("Save", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {

                SaveResult.saveTask(MainActivity.this,correctAnswer);
                correctAnswer = 0;
                answerList = new ArrayList<>();
            }
        });

        adb.setNegativeButton("Ignore", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                correctAnswer = 0;
                answerList = new ArrayList<>();
            }
        });
        adb.show();

        }
        public void updateIndex()
        {
            if(currentQuestionsIndex == (questionsBank.questionsList.size()-1))
            {
                showDialoag();
                currentQuestionsIndex=0;
            }
            else
                {
                currentQuestionsIndex++;
                }
        }

        private void checkAnswer ( boolean userChoosenCorrect)
        {
        Questions questions = questionsBank.questionsList.get(currentQuestionsIndex);
            boolean answeIsTrue = questions.answers;

            int toastMessageId = 0;
            if (userChoosenCorrect == answeIsTrue)
            {
                toastMessageId = R.string.correct_answer;
                correctAnswer++;
                answerList.add(questions);
            } else
                {
                toastMessageId = R.string.wrong_answer;
                }
            Toast.makeText(MainActivity.this, toastMessageId, Toast.LENGTH_SHORT).show();
        }


    public void open_dialog() {
        //  numOfClicked++;
        FragmentManager fm=getSupportFragmentManager();
        FragmentDialog msg_fragment = new FragmentDialog();
        msg_fragment.listener = this;
        //  FragmentDialog msg_fragment = new FragmentDialog();
        msg_fragment.show(fm.beginTransaction(),"1");
    }

    @Override
    public void dialogAddQuestions(String questions_txt,boolean answer) {
        Questions questions=new Questions();
        questions.setText(questions_txt);
        questions.setAnswers(answer);
        questions.setColor("#"+getRandomColor());
        Toast.makeText(MainActivity.this, "Questions added successfully", Toast.LENGTH_SHORT).show();
       // questionsBank.questionsList.add(questions);
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

}
