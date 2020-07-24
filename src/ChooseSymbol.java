import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

//import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

/**
 * Данный класс описывает панель, на которой наримованы крестик и нолик, нажатие на которые регистрируется панелью
 * Таким образом данная панель позволяет игроку выбрать каким символом играть (В одиночном режиме).
 */
public class ChooseSymbol extends JPanel {
    // создаем локальные переменные, которые будут отыечать за то, в какую часть панели нажал пользователь, ширину и высоту панели
    private int clX;
    private int Width;
    private int Height;
    // Конструктор объекта. При создании объекта запускается режим ожидания щелчка мыши по панели
    public ChooseSymbol(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                clX = e.getX(); // в какую часть поля нажал игрок. т.е чему равна координата х, считая от левого края
                // Если игрок попал в левую часть панели, то задать выбранный символ как Х
                if(clX<=Width/2){
                    Model.playerDot = 'X';
                // Если игрок попал в равую часть панели, задать выбранный символ как О
                } else Model.playerDot = 'O';
                repaint();
            }
        });
    }
    
    protected  void paintComponent(Graphics g){
        Width = getWidth();
        Height = getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, Width,Height);
        g.setColor(Color.black);
        g.drawLine(Width/2, 0, Width/2, Height);
        g.setColor(Color.GREEN);
        g.fillOval(Width/2+20, 10, Width/2-30,Height-20);
        g.setColor(Color.WHITE);
        g.fillOval(Width/2+25, 15, Width/2-40,Height-30);
        g.setColor(Color.blue);
        int k=0;
        while(k<4) {
            g.drawLine(20+k, 20, Width/2 - 20+k, Height - 10);
            g.drawLine(Width/2 - 20+k, 20, 20+k, Height - 10);
            k++;
        }
    }


}
