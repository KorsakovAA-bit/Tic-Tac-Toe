import java.util.Random;
import java.util.Scanner;
//import com.sun.glass.ui.Size;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс описывающий логику программы (ход ии, проверка победы/ничьей, проверка корректности ввода, инициализация поля).
 */
public class Model {


        public static char field[][];
        public static final int SIZE = 3;
        public  static final char EMPTY_CELL = '*';
        public static final char PLAYER_1_DOT = 'X';
        public static final char PLAYER_2_DOT = 'O';
        public static final int DOT_TO_WIN = 3;
        public static Scanner scan = new Scanner(System.in);
        public static Random random = new Random();
        public static char playerDot;
        public static char aiDot;
        public static int gameMode = 1;

        public void insteadMaine() {
            char mod;

            String gameMod;

            do {
                System.out.println("Как вы хотите сыграть y-против компьютера, n - два игрока, exit - для выхода");//
                gameMod = scan.next();
                mod = gameMod.charAt(0);
                if (mod == 'y' || mod == 'Y') {
                    do {
                        System.out.println("Каким символом вы хотите играть, Х или О?");
                        String side = scan.next();
                        playerDot = side.charAt(0);
                        if (playerDot == 'X' || playerDot == 'O' || playerDot == 'x' || playerDot == 'o') break;
                    } while (true);
                    game(playerDot);
                } else if (mod == 'n' || mod == 'N') twoPlayersGame();
            } while (gameMod == "exit");

        }
        //Процесс игры в 2 игрока
        public static void twoPlayersGame(){
            fieldInit();
            fieldRendering();
            while (true) {
                System.out.println("Ход первого игрока");
              //  playerStep(PLAYER_1_DOT);
                fieldRendering();
                if (win(PLAYER_1_DOT)) {
                    System.out.println("Победил первый игрок!");
                    break;
                }
                if (draw()) {
                    System.out.println("Ничья, в тяжелой битве!");
                    break;
                }
                System.out.println("Ход второго игрока");
              //  playerStep(PLAYER_2_DOT);
                fieldRendering();
                if (win(PLAYER_2_DOT)) {
                    System.out.println("Победил второй игрок!");
                    break;
                }
                if (draw()) {
                    System.out.println("Ничья, в тяжелой битве!");
                    break;
                }
            }


        }
        // Процесс игры
        public static void game(char playerDot){
            fieldInit();
            fieldRendering();
           // char aiDot;
            if (playerDot == 'X' || playerDot == 'x' ) {
                if (playerDot == 'x') playerDot = 'X';
                aiDot = 'O';
                while (true) {

                   // playerStep(playerDot);
                    fieldRendering();
                    if (win(playerDot)) {
                        System.out.println("Вы победили!");
                        break;
                    }
                    if (draw()) break;
                    AiStep(aiDot, playerDot);
                    System.out.println("ИИ сходил");
                    fieldRendering();
                    if (win(aiDot)) {
                        System.out.println("ИИ победил!");
                        break;
                    }
                    if (draw()) break;
                }
            } else {
                if (playerDot == 'o') playerDot = 'O';
                aiDot = 'X';
                while (true) {
                    AiStep(aiDot, playerDot);
                    System.out.println("ИИ сходил");
                    fieldRendering();
                    if (win(aiDot)) {
                        System.out.println("ИИ победил!");
                        break;
                    }
                    if (draw()) break;
                    //playerStep(playerDot);
                    fieldRendering();
                    if (win(playerDot)) {
                        System.out.println("Вы победили!");
                        break;
                    }
                    if (draw()) break;

                }
            }


        }
        //Проверка победы
        public static boolean win(char dot){
            int countX = 0;
            int countY = 0;
            int countZ = 0;
            for(int i = 0; i<SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if(field[i][j] == dot) countX++;
                    if (i==j && field[i][j] == dot) countZ++;
                    if (countX == 3 || countZ == DOT_TO_WIN) return true;
                }
                countX = 0;
            }
            countZ = 0;
            for(int j = 0; j<SIZE; j++) {
                for (int i = 0; i < SIZE; i++) {
                    if(field[i][j] == dot) countY++;
                    if(field[i][j] == dot && i+j == SIZE-1) countZ++;
                    if (countY == 3 || countZ == DOT_TO_WIN) return true;
                }
                countY = 0;
            }
            return false;
        }
        //Проверка ничьей
        public static boolean draw(){
            for(int i = 0; i<SIZE; i++){
                for(int j = 0; j<SIZE; j++){
                    if (field[i][j] == EMPTY_CELL) return false;
                }
            }
            System.out.println("Ничья в тяжелой битве!");
            return true;
        }
        // ход искуственного интелекта
        public static void AiStep(char aiDot, char playerDot) {
            int dotX;
            int dotY;
            do {
                if (field[1][1] == EMPTY_CELL){
                    dotX = dotY = 2;
                }else if(winningMove(aiDot)){
                    int result[] = dotsInWinningMove(aiDot);
                    dotX = result[0];
                    dotY = result[1];
                }else if (winningMove(playerDot)){
                    int result[] = dotsInWinningMove(playerDot);
                    dotX = result[0];
                    dotY = result[1];
                }else
                {
                    dotX = (int) (Math.random() * SIZE + 1);
                    dotY = (int) (Math.random() * SIZE + 1);
                }
            } while(!checkInput(dotY, dotX));
            field[dotX-1][dotY-1] = aiDot;

        }
        //dotX и dotY на выйгрышном ходу
        public static int[] dotsInWinningMove(char dot){
            int countX= 0;
            int countY = 0;
            int countZ = 0;
            int Xi = -1, Xj = -1, Zi = -1, Zj = -1, Yi = -1, Yj = -1;
            for(int i=0; i<SIZE; i++){
                for (int j=0; j<SIZE; j++){
                    if(field[i][j] == dot) countX++;
                    else if (field[i][j] == EMPTY_CELL) {
                        Xi = i; Xj = j;
                    }
                    if (i==j && field[i][j] == dot) countZ++;
                    else if (i==j && field[i][j] == EMPTY_CELL){
                        Zi = i;
                        Zj = j;
                    }
                    if (countX == DOT_TO_WIN-1 && Xi!=-1) return new int[] {Xi+1, Xj+1};
                    else if (countZ == DOT_TO_WIN-1 && Zi!=-1 ) return new int[] {Zi+1, Zj+1};
                }
                countX = 0;
                Xi = -1;
            }
            countZ = 0;
            Zi = -1;
            for(int j = 0; j<SIZE; j++) {
                for (int i = 0; i < SIZE; i++) {
                    if(field[i][j] == dot) countY++;
                    else if (field[i][j] == EMPTY_CELL) {
                        Yi = i; Yj = j;
                    }
                    if(field[i][j] == dot && i+j == SIZE-1) countZ++;
                    else if (i+j == SIZE-1 && field[i][j] == EMPTY_CELL){
                        Zi = i;
                        Zj = j;
                    }
                    if (countY == DOT_TO_WIN-1 && Yi!=-1) return new int[] {Yi+1, Yj+1};
                    else if (countZ == DOT_TO_WIN-1 && Zi!=-1) return new int[] {Zi+1, Zj+1};
                }
                countY = 0;
                Yi = -1;
            }
            return new int[] {1, 1};
        }
        // Проверяем есть ли выйгрышный ход
        public static boolean winningMove(char dot){
            int countX= 0;
            int countY = 0;
            int countZ = 0;
            int Xi = -1, Xj = -1, Zi = -1, Zj = -1, Yi = -1, Yj = -1;
            for(int i=0; i<SIZE; i++){
                for (int j=0; j<SIZE; j++){
                    if(field[i][j] == dot) countX++;
                    else if (field[i][j] == EMPTY_CELL) {
                        Xi = i; Xj = j;
                    }
                    if (i==j && field[i][j] == dot) countZ++;
                    else if (i==j && field[i][j] == EMPTY_CELL){
                        Zi = i;
                        Zj = j;
                    }
                    if ((countX == DOT_TO_WIN-1 && Xi!=-1) || (countZ == DOT_TO_WIN-1 && Zi!=-1 )) return true;
                }
                countX = 0;
                Xi = -1;
            }
            countZ = 0;
            Zi = -1;
            for(int j = 0; j<SIZE; j++) {
                for (int i = 0; i < SIZE; i++) {
                    if(field[i][j] == dot) countY++;
                    else if (field[i][j] == EMPTY_CELL) {
                        Yi = i; Yj = j;
                    }
                    if(field[i][j] == dot && i+j == SIZE-1) countZ++;
                    else if (i+j == SIZE-1 && field[i][j] == EMPTY_CELL){
                        Zi = i;
                        Zj = j;
                    }
                    if ((countY == DOT_TO_WIN-1 && Yi!=-1) || (countZ == DOT_TO_WIN-1 && Zi!=-1)) return true;
                }
                countY = 0;
                Yi = -1;
            }
            return false;
        }

        //провека число ли в строке
        private static boolean isNumeric(String s) throws NumberFormatException {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        // Проверка корректного ввода
        public static boolean checkInput(int dotX, int dotY){
            if((dotX < 1 || dotX > SIZE) || (dotY < 1 || dotY > SIZE)) return false;
            if(field[dotY-1][dotX-1] != EMPTY_CELL) return false;
            else return true;
        }
        // Первоначальная инициализация поля
        public static void fieldInit(){
            field = new char[SIZE][SIZE];
            for (int i = 0; i<SIZE; i++){
                for (int j = 0; j<SIZE; j++){
                    field[i][j] = EMPTY_CELL;
                }
            }
        }
        // Отрисовка поля
        public static void fieldRendering(){
            for(int i = 0; i<=SIZE; i++){
                System.out.print(i+ " ");
            }
            System.out.println();
            for (int i = 0; i<SIZE; i++) {
                for (int j = 0; j <=SIZE; j++) {
                    if(j==0) System.out.print(i+1+ " ");
                    else {
                        System.out.print(field[i][j-1]+ " ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }



}
