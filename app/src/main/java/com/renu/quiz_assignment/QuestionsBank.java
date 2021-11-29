package com.renu.quiz_assignment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class QuestionsBank
{
    ArrayList<Questions> questionsList = new ArrayList<>();
    public QuestionsBank()
    {

    }

    public void createArrayList()
    {
        questionsList.add(new Questions(R.string.question1, true, "#ffe4e1" ));
        questionsList.add(new Questions(R.string.questioin2, true, "#838b83" ));
        questionsList.add(new Questions(R.string.question3, false, "#00ffff" ));
        questionsList.add(new Questions(R.string.question4, true, "#7fffd4" ));
        questionsList.add(new Questions(R.string.question5, false, "#ffff00" ));
        questionsList.add(new Questions(R.string.question6, true, "#cd5c5c" ));
        questionsList.add(new Questions(R.string.question7, true, "#ff69b4" ));
        questionsList.add(new Questions(R.string.question8, false, "#ee82ee" ));
        questionsList.add(new Questions(R.string.question9, true, "#ff4500" ));

        Collections.shuffle(questionsList);
    }
}
