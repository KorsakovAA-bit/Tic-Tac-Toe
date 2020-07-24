import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Класс, описывающий главное меню (одиночная игра, два игрока, выход)
 */
public class Window extends JFrame{


    public Window(){
        setTitle(" Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400,460);

        JButton btNG = new JButton("Start new game");
        JButton btExit = new JButton("Exit");
        JButton btMM = new JButton("Main menu");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(btMM);
        buttonPanel.add(btNG);
        buttonPanel.add(btExit);
        add(buttonPanel, BorderLayout.SOUTH);
        GameField gF = new GameField();
        add(gF, BorderLayout.CENTER);

        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btMM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Model.gameMode = 1;
                NewGameWindow startNewTicTacToe = new NewGameWindow();

            }
        });

        btNG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeMouseListener(gF.ml);
                addMouseListener(gF.ml);
                gF.requestFocus(true);
                Model.field[1][1] = '*';
                gF.pointer = 1;
                gF.repaint();
                gF.game();
            }
        });




        setResizable(false);
        setVisible(true);
        gF.repaint();
        gF.game();
        gF.addkl();
        addMouseListener(gF.ml);


    }
}
