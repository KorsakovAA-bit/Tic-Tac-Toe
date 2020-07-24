//import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.concurrent.TimeUnit;

/**
 * Класс реализует панель с игровым полем для обоих режимов.
 */


public class GameField extends JPanel{
    public int pointer = 0;
    int width;
    int heigth;
    private int cellWidth;
    private int cellHeight;
    boolean flagPlayerStep = false;
    boolean flagOnePlayerO = false;
    int clX;
    int clY;
    int p = 0;

    public GameField(){
        this.setFocusable(true);
        //addKeyListener(kLis);
        Model.fieldInit();
        Model.fieldRendering();
    }

    public void addkl(){
        addKeyListener(kLis);
    }

    public void game(){
            this.setFocusable(true);
            if (Model.playerDot == 'X') {
                Model.aiDot = 'O';
                repaint();
            } else if(Model.gameMode != 2){
                Model.aiDot = 'X';

                Model.AiStep(Model.aiDot, Model.playerDot);
                repaint();
                System.out.println("ИИ сходил");
                Model.fieldRendering();
                flagOnePlayerO = true;
            }

    }


    protected  void paintComponent(Graphics g){
        super.paintComponent(g);
        width = getWidth();
        heigth = getHeight();
        cellHeight = heigth / Model.SIZE;
        cellWidth = width / Model.SIZE;

        if(this.pointer == 1){

            for(int i=0; i<Model.SIZE; i++) {
            for (int j = 0; j < Model.SIZE; j++) {
                Model.field[i][j] = '*';
            }
            }
            g.setColor(Color.WHITE);
            g.fillRect(0,0, width,heigth);
            g.setColor(Color.black);
            for (int i = 0; i <= Model.SIZE; i++) {
                g.drawLine(0, i * cellHeight, width, i * cellHeight);
                g.drawLine(i * cellWidth, 0, i * cellWidth, heigth);
            }

            if(Model.aiDot == 'X'){
                Model.AiStep(Model.aiDot, Model.playerDot);
            }
            drawSymbol(g);
            this.pointer = 0;


        } else if(pointer == 2){
            g.setColor(Color.ORANGE);
            // рисуем первую Н
            g.drawLine(cellWidth, cellHeight, cellWidth, cellHeight+50);
            g.drawLine(cellWidth, cellHeight+25, cellWidth+10, cellHeight+25);
            g.drawLine(cellWidth+10, cellHeight, cellWidth+10, cellHeight+50);
            // рисуем первую И
            g.drawLine(cellWidth+20, cellHeight, cellWidth+20, cellHeight+50);
            g.drawLine(cellWidth+20, cellHeight+50, cellWidth+30, cellHeight);
            g.drawLine(cellWidth+30, cellHeight, cellWidth+30, cellHeight+50);
            // рисуем первую Ч
            g.drawLine(cellWidth+40, cellHeight, cellWidth+40, cellHeight+25);
            g.drawLine(cellWidth+50, cellHeight, cellWidth+50, cellHeight+50);
            g.drawLine(cellWidth+40, cellHeight+25, cellWidth+50, cellHeight+25);
            // рисуем первую Ь
            g.drawLine(cellWidth+60, cellHeight, cellWidth+60, cellHeight+50);
            g.drawLine(cellWidth+60, cellHeight+50, cellWidth+70, cellHeight+50);
            g.drawLine(cellWidth+70, cellHeight+25, cellWidth+70, cellHeight+50);
            g.drawLine(cellWidth+60, cellHeight+25, cellWidth+70, cellHeight+25);
            // рисуем первую Я
            g.drawLine(cellWidth+90, cellHeight, cellWidth+90, cellHeight+50);
            g.drawLine(cellWidth+80, cellHeight, cellWidth+90, cellHeight);
            g.drawLine(cellWidth+80, cellHeight, cellWidth+80, cellHeight+25);
            g.drawLine(cellWidth+80, cellHeight+25, cellWidth+90, cellHeight+25);
            g.drawLine(cellWidth+90, cellHeight+25, cellWidth+80, cellHeight+50);

            } else if(pointer == 3){
            g.setColor(Color.red);
            // рисуем первую И
            g.drawLine(cellWidth-50, cellHeight, cellWidth-50, cellHeight+50);
            g.drawLine(cellWidth-50, cellHeight+50, cellWidth-40, cellHeight);
            g.drawLine(cellWidth-40, cellHeight, cellWidth-40, cellHeight+50);
            // рисуем вторую И
            g.drawLine(cellWidth-30, cellHeight, cellWidth-30, cellHeight+50);
            g.drawLine(cellWidth-30, cellHeight+50, cellWidth-20, cellHeight);
            g.drawLine(cellWidth-20, cellHeight, cellWidth-20, cellHeight+50);
            // рисуем П
            g.drawLine(cellWidth, cellHeight, cellWidth, cellHeight+50);
            g.drawLine(cellWidth, cellHeight, cellWidth+10, cellHeight);
            g.drawLine(cellWidth+10, cellHeight, cellWidth+10, cellHeight+50);
            // рисуем О
            g.drawOval(cellWidth+20, cellHeight, 20,50);
            // рисуем Б
            g.drawLine(cellWidth+50, cellHeight, cellWidth+60, cellHeight);
            g.drawLine(cellWidth+50, cellHeight, cellWidth+50, cellHeight+50);
            g.drawLine(cellWidth+50, cellHeight+50, cellWidth+60, cellHeight+50);
            g.drawLine(cellWidth+60, cellHeight+50, cellWidth+60, cellHeight+25);
            g.drawLine(cellWidth+50, cellHeight+25, cellWidth+60, cellHeight+25);
            // рисуем E
            g.drawLine(cellWidth+70, cellHeight, cellWidth+70, cellHeight+50);
            g.drawLine(cellWidth+70, cellHeight, cellWidth+80, cellHeight);
            g.drawLine(cellWidth+70, cellHeight+25, cellWidth+80, cellHeight+25);
            g.drawLine(cellWidth+70, cellHeight+50, cellWidth+80, cellHeight+50);
            // рисуем Д
            g.drawLine(cellWidth+90, cellHeight+35, cellWidth+110, cellHeight+35);
            g.drawLine(cellWidth+90, cellHeight+35, cellWidth+90, cellHeight+50);
            g.drawLine(cellWidth+110, cellHeight+35, cellWidth+110, cellHeight+50);
            g.drawLine(cellWidth+95, cellHeight+35, cellWidth+95, cellHeight);
            g.drawLine(cellWidth+105, cellHeight+35, cellWidth+105, cellHeight);
            g.drawLine(cellWidth+95, cellHeight, cellWidth+105, cellHeight);
            // рисуем И
            g.drawLine(cellWidth+120, cellHeight, cellWidth+120, cellHeight+50);
            g.drawLine(cellWidth+120, cellHeight+50, cellWidth+130, cellHeight);
            g.drawLine(cellWidth+130, cellHeight, cellWidth+130, cellHeight+50);
            // рисуем Л
            g.drawLine(cellWidth+145, cellHeight, cellWidth+140, cellHeight+50);
            g.drawLine(cellWidth+145, cellHeight, cellWidth+150, cellHeight+50);

        } else if (pointer == 4) {
            g.setColor(Color.GREEN);
            if (Model.gameMode == 2) {

                    //рисуем И
                    g.drawLine(cellWidth - 50, cellHeight - 75, cellWidth - 50, cellHeight - 25);
                    g.drawLine(cellWidth - 50, cellHeight - 25, cellWidth - 40, cellHeight - 75);
                    g.drawLine(cellWidth - 40, cellHeight - 75, cellWidth - 40, cellHeight - 25);
                    //рисуем г
                    g.drawLine(cellWidth - 30, cellHeight - 75, cellWidth - 30, cellHeight - 25);
                    g.drawLine(cellWidth - 30, cellHeight - 75, cellWidth - 20, cellHeight - 75);
                    g.drawLine(cellWidth - 20, cellHeight - 75, cellWidth - 20, cellHeight - 65);
                    // рисуем Р
                    g.drawLine(cellWidth - 10, cellHeight - 75, cellWidth - 10, cellHeight - 25);
                    g.drawLine(cellWidth - 10, cellHeight - 75, cellWidth, cellHeight - 75);
                    g.drawLine(cellWidth, cellHeight - 75, cellWidth, cellHeight - 50);
                    g.drawLine(cellWidth - 10, cellHeight - 50, cellWidth, cellHeight - 50);
                    // рисуем O
                    g.drawOval(cellWidth + 10, cellHeight - 75, 20, 50);
                    // рисум К
                    g.drawLine(cellWidth + 40, cellHeight - 75, cellWidth + 40, cellHeight - 25);
                    g.drawLine(cellWidth + 40, cellHeight - 50, cellWidth + 50, cellHeight - 75);
                    g.drawLine(cellWidth + 40, cellHeight - 50, cellWidth + 50, cellHeight - 25);
                if (Model.playerDot == 'O') {
                    //рисуем 1
                    g.drawLine(cellWidth + 70, cellHeight - 75, cellWidth + 70, cellHeight - 25);
                    g.drawLine(cellWidth + 70, cellHeight - 75, cellWidth + 60, cellHeight - 50);
                } else {
                    //рисуем 2
                    g.drawLine(cellWidth + 70, cellHeight - 50, cellWidth + 70, cellHeight - 75);
                    g.drawLine(cellWidth + 70, cellHeight - 75, cellWidth + 80, cellHeight - 75);
                    g.drawLine(cellWidth + 80, cellHeight - 75, cellWidth + 70, cellHeight - 25);
                    g.drawLine(cellWidth + 70, cellHeight - 25, cellWidth + 80, cellHeight - 25);

                }
                    // рисуем П
                    g.drawLine(cellWidth, cellHeight, cellWidth, cellHeight + 50);
                    g.drawLine(cellWidth, cellHeight, cellWidth + 10, cellHeight);
                    g.drawLine(cellWidth + 10, cellHeight, cellWidth + 10, cellHeight + 50);
                    // рисуем О
                    g.drawOval(cellWidth + 20, cellHeight, 20, 50);
                    // рисуем Б
                    g.drawLine(cellWidth + 50, cellHeight, cellWidth + 60, cellHeight);
                    g.drawLine(cellWidth + 50, cellHeight, cellWidth + 50, cellHeight + 50);
                    g.drawLine(cellWidth + 50, cellHeight + 50, cellWidth + 60, cellHeight + 50);
                    g.drawLine(cellWidth + 60, cellHeight + 50, cellWidth + 60, cellHeight + 25);
                    g.drawLine(cellWidth + 50, cellHeight + 25, cellWidth + 60, cellHeight + 25);
                    // рисуем E
                    g.drawLine(cellWidth + 70, cellHeight, cellWidth + 70, cellHeight + 50);
                    g.drawLine(cellWidth + 70, cellHeight, cellWidth + 80, cellHeight);
                    g.drawLine(cellWidth + 70, cellHeight + 25, cellWidth + 80, cellHeight + 25);
                    g.drawLine(cellWidth + 70, cellHeight + 50, cellWidth + 80, cellHeight + 50);
                    // рисуем Д
                    g.drawLine(cellWidth + 90, cellHeight + 35, cellWidth + 110, cellHeight + 35);
                    g.drawLine(cellWidth + 90, cellHeight + 35, cellWidth + 90, cellHeight + 50);
                    g.drawLine(cellWidth + 110, cellHeight + 35, cellWidth + 110, cellHeight + 50);
                    g.drawLine(cellWidth + 95, cellHeight + 35, cellWidth + 95, cellHeight);
                    g.drawLine(cellWidth + 105, cellHeight + 35, cellWidth + 105, cellHeight);
                    g.drawLine(cellWidth + 95, cellHeight, cellWidth + 105, cellHeight);
                    // рисуем И
                    g.drawLine(cellWidth + 120, cellHeight, cellWidth + 120, cellHeight + 50);
                    g.drawLine(cellWidth + 120, cellHeight + 50, cellWidth + 130, cellHeight);
                    g.drawLine(cellWidth + 130, cellHeight, cellWidth + 130, cellHeight + 50);
                    // рисуем Л
                    g.drawLine(cellWidth + 145, cellHeight, cellWidth + 140, cellHeight + 50);
                    g.drawLine(cellWidth + 145, cellHeight, cellWidth + 150, cellHeight + 50);
                    Model.playerDot = 'X';

            } else {
                // рисуем В
                g.drawLine(cellWidth - 50, cellHeight, cellWidth - 50, cellHeight + 50);
                g.drawLine(cellWidth - 40, cellHeight + 5, cellWidth - 40, cellHeight + 45);
                g.drawLine(cellWidth - 50, cellHeight, cellWidth - 40, cellHeight + 5);
                g.drawLine(cellWidth - 50, cellHeight + 50, cellWidth - 40, cellHeight + 45);
                g.drawLine(cellWidth - 50, cellHeight + 25, cellWidth - 40, cellHeight + 25);
                // рисуем Ы
                g.drawLine(cellWidth - 30, cellHeight, cellWidth - 30, cellHeight + 50);
                g.drawLine(cellWidth - 30, cellHeight + 50, cellWidth - 25, cellHeight + 50);
                g.drawLine(cellWidth - 30, cellHeight + 25, cellWidth - 25, cellHeight + 25);
                g.drawLine(cellWidth - 25, cellHeight + 50, cellWidth - 25, cellHeight + 25);
                g.drawLine(cellWidth - 20, cellHeight, cellWidth - 20, cellHeight + 50);
                // рисуем П
                g.drawLine(cellWidth, cellHeight, cellWidth, cellHeight + 50);
                g.drawLine(cellWidth, cellHeight, cellWidth + 10, cellHeight);
                g.drawLine(cellWidth + 10, cellHeight, cellWidth + 10, cellHeight + 50);
                // рисуем О
                g.drawOval(cellWidth + 20, cellHeight, 20, 50);
                // рисуем Б
                g.drawLine(cellWidth + 50, cellHeight, cellWidth + 60, cellHeight);
                g.drawLine(cellWidth + 50, cellHeight, cellWidth + 50, cellHeight + 50);
                g.drawLine(cellWidth + 50, cellHeight + 50, cellWidth + 60, cellHeight + 50);
                g.drawLine(cellWidth + 60, cellHeight + 50, cellWidth + 60, cellHeight + 25);
                g.drawLine(cellWidth + 50, cellHeight + 25, cellWidth + 60, cellHeight + 25);
                // рисуем E
                g.drawLine(cellWidth + 70, cellHeight, cellWidth + 70, cellHeight + 50);
                g.drawLine(cellWidth + 70, cellHeight, cellWidth + 80, cellHeight);
                g.drawLine(cellWidth + 70, cellHeight + 25, cellWidth + 80, cellHeight + 25);
                g.drawLine(cellWidth + 70, cellHeight + 50, cellWidth + 80, cellHeight + 50);
                // рисуем Д
                g.drawLine(cellWidth + 90, cellHeight + 35, cellWidth + 110, cellHeight + 35);
                g.drawLine(cellWidth + 90, cellHeight + 35, cellWidth + 90, cellHeight + 50);
                g.drawLine(cellWidth + 110, cellHeight + 35, cellWidth + 110, cellHeight + 50);
                g.drawLine(cellWidth + 95, cellHeight + 35, cellWidth + 95, cellHeight);
                g.drawLine(cellWidth + 105, cellHeight + 35, cellWidth + 105, cellHeight);
                g.drawLine(cellWidth + 95, cellHeight, cellWidth + 105, cellHeight);
                // рисуем И
                g.drawLine(cellWidth + 120, cellHeight, cellWidth + 120, cellHeight + 50);
                g.drawLine(cellWidth + 120, cellHeight + 50, cellWidth + 130, cellHeight);
                g.drawLine(cellWidth + 130, cellHeight, cellWidth + 130, cellHeight + 50);
                // рисуем Л
                g.drawLine(cellWidth + 145, cellHeight, cellWidth + 140, cellHeight + 50);
                g.drawLine(cellWidth + 145, cellHeight, cellWidth + 150, cellHeight + 50);
                // рисуем И
                g.drawLine(cellWidth + 160, cellHeight, cellWidth + 160, cellHeight + 50);
                g.drawLine(cellWidth + 160, cellHeight + 50, cellWidth + 170, cellHeight);
                g.drawLine(cellWidth + 170, cellHeight, cellWidth + 170, cellHeight + 50);
            }
        }
        else {

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, heigth);
            g.setColor(Color.black);
            for (int i = 0; i <= Model.SIZE; i++) {
                g.drawLine(0, i * cellHeight, width, i * cellHeight);
                g.drawLine(i * cellWidth, 0, i * cellWidth, heigth);
            }
            drawSymbol(g);
        }
    }


    protected void drawSymbol(Graphics g){
        for(int i=0; i<Model.SIZE; i++) {
            for (int j = 0; j < Model.SIZE; j++) {
                if(Model.field[i][j] != '*' && Model.field[i][j] == 'O'){
                    g.setColor(Color.GREEN);
                    g.fillOval(j*cellWidth+15, i*cellHeight+15, cellWidth-30, cellHeight-30);
                    g.setColor(Color.WHITE);
                    g.fillOval(j*cellWidth+25, i*cellHeight+25, cellWidth-50, cellHeight-50);
                }
                if(Model.field[i][j] != '*' && Model.field[i][j] == 'X'){
                    g.setColor(Color.BLUE);
                    int k = 0;
                    while(k<4) {
                        g.drawLine(j * cellWidth + (20+k), i * cellHeight + 20, (j + 1) * cellWidth - (20-k), (i + 1) * cellHeight - 20);
                        g.drawLine(j * cellWidth + (20+k), (i + 1) * cellHeight - 20, (j + 1) * cellWidth - (20-k), i * cellHeight + 20);
                        k++;
                    }
                }
            }
        }
    }

    KeyListener kLis = new KeyListener() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent e) {

        }

        @Override
        public void keyPressed(java.awt.event.KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1) {
                if (pointer == 0) {
                    if (Model.field[0][0] == '*') {
                        Model.field[0][0] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2) {
                if (pointer == 0) {
                    if (Model.field[0][1] == '*') {
                        Model.field[0][1] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3) {
                if (pointer == 0) {
                    if (Model.field[0][2] == '*') {
                        Model.field[0][2] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4) {
                if (pointer == 0) {
                    if (Model.field[1][0] == '*') {
                        Model.field[1][0] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_NUMPAD5) {
                if (pointer == 0) {
                    if (Model.field[1][1] == '*') {
                        Model.field[1][1] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_6 || e.getKeyCode() == KeyEvent.VK_NUMPAD6) {
                if (pointer == 0) {
                    if (Model.field[1][2] == '*') {
                        Model.field[1][2] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_NUMPAD7) {
                if (pointer == 0) {
                    if (Model.field[2][0] == '*') {
                        Model.field[2][0] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_8 || e.getKeyCode() == KeyEvent.VK_NUMPAD8) {
                if (pointer == 0) {
                    if (Model.field[2][1] == '*') {
                        Model.field[2][1] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }

            if (e.getKeyCode() == KeyEvent.VK_9 || e.getKeyCode() == KeyEvent.VK_NUMPAD9) {
                if (pointer == 0) {
                    if (Model.field[2][2] == '*') {
                        Model.field[2][2] = Model.playerDot;
                        afterPlayerStep();
                    }
                } else repaint();
            }

        }

        @Override
        public void keyReleased(java.awt.event.KeyEvent e) {

        }
    };

    MouseListener ml = new MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
           if(pointer == 0) {

               clX = e.getX() / cellWidth;
               int parametr = e.getY() - 28;
               clY = parametr / cellHeight;
               if (Model.field[clY][clX] == '*') {
                   Model.field[clY][clX] = Model.playerDot;
                   System.out.println(" e.getY() = "+parametr+" CellHeight =  "+ cellHeight +"Height = "+ heigth);
                   afterPlayerStep();
               }
           } else repaint();
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {

        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {

        }
    };
    private void afterPlayerStep(){
        repaint();
        Model.fieldRendering();
        p = 1;
        if (Model.win(Model.playerDot)) {
            System.out.println("Вы победили!");
            pointer = 4;
            repaint();
        }
        if (Model.draw()) {
            System.out.println("Ничья в тяжелой битве");
            pointer = 2;
            repaint();
        }
        if(Model.gameMode == 2){
            if(Model.playerDot == 'X'){
                Model.playerDot = 'O';
            } else Model.playerDot = 'X';
        }
        if (!Model.draw() && Model.gameMode!=2) {
            Model.AiStep(Model.aiDot, Model.playerDot);
            repaint();
            System.out.println("ИИ сходил");
            Model.fieldRendering();
            if (Model.win(Model.aiDot)) {
                System.out.println("ИИ победил!");
                pointer = 3;
                repaint();
            }
            if (Model.draw()) {
                System.out.println("Ничья в тяжелой битве");
                pointer = 2;
                repaint();
            }
        }
    }

}


