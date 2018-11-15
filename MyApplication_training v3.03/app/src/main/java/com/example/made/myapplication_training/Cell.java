package com.example.made.myapplication_training;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Cell {

    //размеры решетки
    String cells[][];

     /*
     axe_x, axe_y - координаты поля, то есть 6 на 6 в данном случае
     x и y - реальные координаты экрана
     */

    //конструктор класса
    Cell(int axe_x,int axe_y){
        //создать массив cells размерами axe_x Х axe_y
        cells = new String[axe_x][axe_y];

        Log.w("игра", "создали поле " + cells.length + " на " + cells[0].length);
    }

    //в ячейке что-то есть
    String h;
    //ключ - ячейка размерами а на b axes, значение - строка least или barrier. Значение можно сделать списком потом
    Map<Integer[][], String> cellMap = new HashMap<Integer[][], String>();

    void deleteCellMapKey(Integer[][] key){
        //удалить рисунок с вьюхи (это потом дописать) - координаты получить из key
        //удалить из cellMap запись key-value по значению key
       if (cellMap.containsKey(key)) {
           cellMap.remove(key);
       }
    }



    String[][] getCells(){
        return cells;
    }

    //ячейка имеет листок
    public boolean hasLeast(int axe_x,int axe_y){
        //если в координатах axes есть листок, то вернуть истину
        //если в cellMap x,y   h==least, то вернуть истину

            if (cells[axe_x][axe_y]=="least"){
                return true;
            } else {return false;}

    }

    //ячейка имеет барьер
    public boolean hasBarrier(int axe_x,int axe_y){
        //если в каких-то координатах есть барьер, то вернуть истину

            if (cells[axe_x][axe_y] == "barrier") {
                return true;
            }

        return false;
    }

}