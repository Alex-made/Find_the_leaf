package com.example.made.myapplication_training;

import android.util.Log;
import android.view.View;

public class Barrier {
    //координаты препятствия (реальные)
    Integer[][] barrierAxes;

    //конструктор класса
    Barrier(int x, int y){

        //записать в barrierAxes рассчитанные координаты препятствия
        barrierAxes = new Integer[1][2];
        barrierAxes[0][0] = x;
        barrierAxes[0][1] = y;

    }

    void setBarrier(int x, int y){

        //нарисовать барьер в реальных координатах касания х и у
        Log.w("игра", "препятствие установлено в: "+ x + " " + y);



    }

   //вернуть координаты препятствия в gameField.cellMap
    public Integer[][] getBarrier(){    //View view на вход

        /*int x = barrierAxes[0][0];
        int y = barrierAxes[0][1];

        Integer [][] barrierAxesInCells = new Integer[1][2];
        barrierAxesInCells[0][0] = barrierAxes[0][0]/(view.getWidth()/gameField.getCells()[0][0].length);
        barrierAxesInCells[0][1] = barrierAxes[0][1]/(view.getWidth()/gameField.getCells()[0][1].length);
        */
        return barrierAxes;
    }
}
