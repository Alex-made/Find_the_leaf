package com.example.made.myapplication_training;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Игровое поле
    public Cell gameField;

    //листик
    Least least;

    //жук
    Bug bug;

    //массив препятствий
    Barrier[] b = new Barrier[64];

    //интерфейс пользователя
    Button button, button2;
    View view;
    TextView textView;

    //счетчик количества созданных препятствий
    //почему только здесь нужно размещать переменную???
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        view = (View) findViewById(R.id.view);
        textView = (TextView) findViewById(R.id.textView);

        //кнопка "новая игра"
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //сброс счетчика препятствий i
                i=0;
                //сброс массива препятствий
                b=new Barrier[64];

                //обновляем игровое поле
                //создать поле 8 на 8
                gameField = new Cell(8,8);

                //создается листок в координатах 5,5
                //TODO сделать рандомные потом
                least = new Least(5,4);
                //в координаты поля cells помещаем листочек
                gameField.cells[least.getLeast()[0][0]][least.getLeast()[0][1]] = "least";
                Log.w("игра", "листок помещен в координаты поля: " + least.getLeast()[0][0] + "  " + least.getLeast()[0][1]);

                //создаем жука в рандомных координатах
                final Random random = new Random();
                bug = new Bug(1, 1);
                //в cells помещаем жука -> bug
                gameField.cells[bug.getBug()[0][0]][bug.getBug()[0][1]] = "bug";
                Log.w("игра", "жук помещен в координаты cells: " + bug.getBug()[0][0] + "  " + bug.getBug()[0][1]);

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Новая игра начата!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //отслеживаем нажатие на view - это установка препятствия
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                    int x = (int) event.getX();
                    int y = (int) event.getY();

                    if (gameField!=null) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            //TODO нельзя препятствия ставить друг на друга, их список должен обновляться при обновлении игрового поля
                            //создать новый i-ый экземпляр (имя+идентификатор)
                            b[i] = new Barrier(x, y);
                            //barrierAxes - координаты, пересчитанные в клетках игрового поля
                            int barrierAxe_x = b[i].getBarrier()[0][0] / (view.getWidth() / gameField.cells.length);
                            int barrierAxe_y = b[i].getBarrier()[0][1] / (view.getHeight() / gameField.cells[0].length);
                            if (gameField.cells[barrierAxe_x][barrierAxe_y] != "bug"
                                    && gameField.cells[barrierAxe_x][barrierAxe_y] != "least"
                                    ) {
                                //если созданный экземпляр можно поставить в клеточное поле gameField, то
                                //ставим препятствие на реальные координаты экрана, где нажали, а запоминаем в gameField!!!
                                b[i].setBarrier(x, y);
                                //запомнить расположение в gameField.cells и записать туда "barrier"
                                gameField.cells[barrierAxe_x][barrierAxe_y] = "barrier";
                                Log.w("игра", "препятствие помещено в cells: " + barrierAxe_x + "  " + barrierAxe_y);

                                /*for (int c = 0; c<=i; c++) {
                                    Log.w("игра", "Проверка заполнения массива b: " + b[c].getBarrier()[0][0] + " " +  b[c].getBarrier()[0][1]);
                                }*/

                                Log.w("игра", "количество препятствий на данный момент: " + (i + 1));
                                i++;
                            }

                            //отладка
                            /*for (int k=0; k<8; k++) {
                                for(int p=0; p<8;p++) {
                                Log.w("cells", "в ячейке:" + gameField.cells[k][p]);
                                }
                            }*/


                        }
                        textView.setText(x + "; " + y);
                        textView.append("\n" + x / (view.getWidth() / gameField.cells.length));
                        textView.append(", " + y / (view.getHeight() / gameField.cells[0].length));
                    }

                    return true;

            }

        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ищем листок, задав цель "координаты листка"
                try{
                    bug.findLeast(gameField);
                } catch (NullPointerException n) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Начните новую игру!", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }

}


