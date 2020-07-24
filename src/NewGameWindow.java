import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;


/**
 * Данный класс описывает окно выбора режима игры и символа, которым будет играть пользователь в одиночном режиме.
 */
public class NewGameWindow extends JFrame {

    public NewGameWindow(){
        // задаем название окна, действие при закрытии, размер.
        setTitle(" Tic Tac Toe");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(250,250);

        // Создаем объект класса container, являющегося панелью.
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setAlignmentY(Component.CENTER_ALIGNMENT);
        container.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));

        // Создаем кнопки выбор режима (один игрок, 2 игрока, выход), а так же Label в который выведем комментарий
        JButton btOnePlayer = new JButton("One player");
        JButton btTwoPlayer = new JButton("Two players");
        JButton btExit = new JButton("Exit");
        JLabel lbChS = new JLabel("Choose your symbol:");
        // Задаем размещение кнопок на панели
        btOnePlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        btExit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btTwoPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbChS.setAlignmentX(Component.CENTER_ALIGNMENT);
        btOnePlayer.setBorder(BorderFactory.createEmptyBorder(7,20,7,20));
        btTwoPlayer.setBorder(BorderFactory.createEmptyBorder(7,15,7,15));
        btExit.setBorder(BorderFactory.createEmptyBorder(7,40,7,40));
        lbChS.setBorder(BorderFactory.createEmptyBorder(7,40,7,40));
        //Добавляем кнопки на панель
        container.add(btOnePlayer);
        container.add(btTwoPlayer);
        container.add(btExit);
        add(container, BorderLayout.CENTER);
        // Создаем объект класса  ChooseSymbol, являющегося панелью. На данной панели будет реализован выбор символа игры для одиночного режима
        ChooseSymbol choosepanel = new ChooseSymbol();
        // Задаем алгоритм срабатывающий при нажатии на кнопку выбора одиночного режима
        btOnePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dispose(); // закрывает это окно
                // удаляем все кнопки из окна, добавляем панель выбора символа и Label, указывающий пользователю, что необходимо сделать выбор символа
                container.removeAll();
                container.add(choosepanel);
                add(lbChS, BorderLayout.NORTH);
                revalidate();
                repaint();
                // закрепляем выбор пользовательского символа, закрываем окно выбора режима и открываем окно с игровым полем.
                choosepanel.addMouseListener(new MouseAdapter() {
                    @Override
                        public void mouseReleased(java.awt.event.MouseEvent e) {
                           if(Model.playerDot == 'O' || Model.playerDot == 'X') {
                               dispose();
                               Window ticTacToeWindow = new Window();

                           }
                        }
                });

            }
        });
        // Задаем алгоритм срабатывающий при нажатии на кнопку выбора совместной игры
        btTwoPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // закрываем окно выбора режима, фиксируем, что выбран режим совместной игры (режим номер 2) и открываем окно с игровым полем
                dispose();
                Model.gameMode = 2;
                Window ticTacToeWindow = new Window();

            }
        });
        //Задаем алгоритм срабатывающий при нажатии на кнопку выхода из приложения
        btExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // заканчивает выполнение программы
            }
        });
        // Отмечаем, что бы окно нельзя было растянуть и что бы его было видно
        setResizable(false);

        setVisible(true);
    }


}
