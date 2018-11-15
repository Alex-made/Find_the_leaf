package com.example.made.myapplication_training;

import android.util.Log;

class Least {
    //координаты листочка
    Integer[][] leastAxes;

    //конструктор класса
    Least(int axe_x, int axe_y){
        leastAxes = new Integer[1][2];
        leastAxes[0][0]=axe_x;
        leastAxes[0][1]=axe_y;
        setLeast(axe_x,axe_y);
    }

    void setLeast(int axe_x, int axe_y){
        //нарисовать листок в координатах axe_х и axe_у - координатах сетки
        Log.w("игра", "листок нарисован в (axes):"+ axe_x + " " + axe_y);
    }
    public Integer[][] getLeast(){
        return leastAxes;
    }



}
