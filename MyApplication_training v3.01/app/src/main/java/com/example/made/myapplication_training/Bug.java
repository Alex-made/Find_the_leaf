package com.example.made.myapplication_training;

import android.util.Log;

import java.util.Random;

/*
axe_x, axe_y - координаты поля, то есть 6 на 6 в данном случае
x и y - реальные координаты экрана
 */
public class Bug {

    //координаты жука
    Integer[][] bugAxes;

    //создать жука в координатах игрового поля - 8 на 8
    Bug(int axe_x, int axe_y){

        setBug(axe_x,axe_y);
    }

    public void setBug(int axe_x, int axe_y){
        //нарисовать жука в координатах сетки axe_x,axe_y
        bugAxes = new Integer[1][2];
        bugAxes[0][0]=axe_x;
        bugAxes[0][1]=axe_y;
        Log.w("игра", "нарисовали таракана в (axes): "+ bugAxes[0][0] + " " + bugAxes[0][1]);
    }
    //получить координаты жука
    public Integer[][] getBug(){
        return bugAxes;
    }


    //логика поиска и проверки на препятствия
    void findLeast(Cell gameField) {
        //координаты листочка
        int least_x=0;
        int least_y=0;
        for (int k=0; k<8; k++) {
            for (int p = 0; p < 8; p++) {
                //узнаем координаты листочка
                if (gameField.cells[k][p] == "least"){
                    least_x = k;
                    least_y = p;
                }

            }
        }

        while (least_x != getBug()[0][0] || least_y != getBug()[0][1]) {
            //движение по координате x
            if ( Math.abs(((getBug()[0][0] + 1) - least_x)) > Math.abs((getBug()[0][0] - least_x))  ||
                 gameField.hasBarrier(getBug()[0][0] + 1, getBug()[0][1])
               ) {
                   moveLeft();
            }
            if (Math.abs(((getBug()[0][0] - 1) - least_x)) > Math.abs((getBug()[0][0] - least_x))   ||
                    gameField.hasBarrier(getBug()[0][0] - 1, getBug()[0][1])
               ) {
                moveRight();
            }

            //движение по координате y (в нашей коорд. сетке чем больше разница коррдинат y, тем ближе к цели, т.к. y инвертирована)
            if (Math.abs(((getBug()[0][1] + 1) - least_y)) > Math.abs((getBug()[0][1] - least_y))   ||
                    gameField.hasBarrier(getBug()[0][0], getBug()[0][1]+1)
               ) {
                 moveUp();
            }
            if (Math.abs(((getBug()[0][1] - 1) - least_y)) > Math.abs((getBug()[0][1] - least_y))   ||
                    gameField.hasBarrier(getBug()[0][0], getBug()[0][1]-1)
               ) {
                 moveDown();
            }

        }


        //если ячейка имеет листок, то вывести в лог "игра закончена"
        if (least_x == getBug()[0][0] && least_y == getBug()[0][1]) {
            // вывести в лог сообщение "игра закончена!!!"
            Log.w("игра", "игра закончена в координатах: " + getBug()[0][0] + " " + getBug()[0][1]);
        }

    }

    void moveLeft(){
        //удалить рисунок жука с координат getBug()[0][0], getBug()[0][1]
        setBug(getBug()[0][0]-1, getBug()[0][1]);
    }

    void moveRight(){
        //удалить рисунок жука с координат getBug()[0][0], getBug()[0][1]
        setBug(getBug()[0][0]+1, getBug()[0][1]);
    }

    void moveUp(){
        //удалить рисунок жука с координат getBug()[0][0], getBug()[0][1]
        setBug(getBug()[0][0], getBug()[0][1]-1);
    }

    void moveDown(){
        //удалить рисунок жука с координат getBug()[0][0], getBug()[0][1]
        setBug(getBug()[0][0], getBug()[0][1]+1);
    }

}
