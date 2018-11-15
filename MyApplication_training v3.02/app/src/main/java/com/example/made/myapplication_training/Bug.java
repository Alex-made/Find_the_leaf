package com.example.made.myapplication_training;

import android.util.Log;

import java.util.Random;

import static java.lang.Math.*;

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



    //если не содержит barrier или closed, то добавляем в список открытых ячеек
    void addOpen(int x, int y, Cell gameField){
        if (gameField.cells[x][y] == null){
            gameField.cells[x][y] = "open";
        }
    }
    //проверка ячейки на пустоту
    boolean cellIsOpen(int x, int y, Cell gameField){
        if (gameField.cells[x][y] == null){
            return true;
        } else {
            return false;
        }
    }
    //проверка расстояния до цели
    int calcDistanse(int x, int y, int least_x, int least_y){
        return abs(x-least_x)+abs(y-least_y);
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

        //пока не найдем листок, повторять
        //while (least_x != getBug()[0][0] && least_y != getBug()[0][1]) {

        while (least_x != getBug()[0][0] || least_y != getBug()[0][1]) {
            //пробежаться по ячейкам вокруг положения жука
            exitlabel:
            for (int i = getBug()[0][0] - 1; i <= getBug()[0][0] + 1; i++) {
                for (int j = getBug()[0][1] - 1; j <= getBug()[0][1] + 1; j++) {
                    if (i > 0 && i < 8 && j > 0 && j < 8) {
                        //прописать все открытые ячейки в cells
                        //addOpen(i, j, gameField);//сработает, если ячейка открыта, а не занята препятствием или листиком
                        //если ячейка пуста, то посчитать расстояние от нее до листика
                        if (cellIsOpen(i, j, gameField)) {
                            int d = calcDistanse(i, j, least_x, least_y);
                            int f = calcDistanse(getBug()[0][0], getBug()[0][1], least_x, least_y);
                            //если расстояние меньше, то переместиться в эту ячейку
                            if (d < f) {
                                moveTo(i, j);
                                break exitlabel;
                            }
                        }
                        //если ячейка имеет лист, то в лог запись, что нашли лист
                        if (gameField.hasLeast(i, j)) {
                            moveTo(i, j);
                            Log.w("игра", "нашли!");
                            break exitlabel;
                        }
                    }

                }
            }
        }
        //посчитать расстояния от всех открытых ячеек до листика

        //отладка
        /*for (int c = 0; c<gameField.cells.length; c++) {
            for (int o = 0; o<gameField.cells.length; o++) {
                if (gameField.cells[c][o] != null) {
                    Log.w("игра", "Проверка заполнения массива cells: " + c+ "  " + o +"  " + gameField.cells[c][o]);
                }
            }
        }*/

         /*   if (Math.abs(((getBug()[0][1] - 1) - least_y)) > Math.abs((getBug()[0][1] - least_y))   ||
                    gameField.hasBarrier(getBug()[0][0], getBug()[0][1]-1)
               ) {
                 moveDown();
            }

        }*/




    }

    void moveTo(int x, int y){
        //удалить рисунок жука с координат getBug()[0][0], getBug()[0][1]
        setBug(x, y);
         /*while (getBug()[0][0] != x || /*&& ??getBug()[0][1] != y) {
            //движение по координате x
            if ( Math.abs(((getBug()[0][0] + 1) - x)) > Math.abs((getBug()[0][0] - x))) {
                   moveLeft();
            }
            if (Math.abs(((getBug()[0][0] - 1) - x)) > Math.abs((getBug()[0][0] - x))) {
                moveRight();
            }

            //движение по координате y (в нашей коорд. сетке чем больше разница коррдинат y, тем ближе к цели, т.к. y инвертирована)
            if (Math.abs(((getBug()[0][1] + 1) - y)) > Math.abs((getBug()[0][1] - y))) {
                 moveUp();
            }
            if (Math.abs(((getBug()[0][1] - 1) - y)) > Math.abs((getBug()[0][1] - y))) {
                 moveDown();
            }

        }*/

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
