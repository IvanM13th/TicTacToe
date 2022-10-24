package main;

import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в игру крестики - нолики!");

        String[] players = createPlayer();
        printPlayers(players);
        String[][] field = generate();
        printField(field);
        String smbl = " ";
        for (int i = 1; i < 9; i++) {
            //опрелелили игрока
            String player = getPlayer(players, i);
            System.out.println("Сейчас ход игрока " + player);

            //спрашиваем, куда ходит мгрок
            System.out.println("Введите номер ячейки");
            printField(field);
            int move = move();
            if (!validNum(move)) {
                System.out.println("Некорректное число");
                i--;
                continue;
            }

            //находим ячейку в массиве
            String cell = findCell(field, move);

            //нужно проверить ход на валидность
            if (!validMove(field, cell)) {
                System.out.println("В этой ячейке уже есть символ, выберите другую");
                i--;
                continue;
            }

            // если все проверки прошли, определяем, ставить крестик или нолик
            if (player.equals(players[0])) {
                smbl = " X ";
            } else {
                smbl = " O ";
            }
            replace(field, move, smbl);
            printField(field);

            //осталось проверить на победные комбинации или ничью
            if (winCheck(field, smbl)) {
                System.out.println("Игрок " + player + " победил!");
                System.out.println("Поздравляем! Спасибо за игру!");
                break;
            }
            if (!winCheck(field, smbl) && i == 8) {
                System.out.println("Ничья!");
                break;
            }
        }
    }


    public static String[] createPlayer() {
        String[] players = new String[2];
        Scanner in = new Scanner(System.in);
        String tmp = "первого";
        for (int i = 0; i <= 1; i++) {
            System.out.println("Введите имя " + tmp + " игрока: ");
            String name = in.nextLine();
            players[i] = name;
            tmp = "второго";
        }
        return players;
    }

    public static void printPlayers(String[] players) {
        System.out.println("Начинаем игру между " + players[0] + " и " + players[1]);
    }

    public static String getPlayer(String[] players, int id) {
        String player = " ";
        if (id % 2 != 0) {
            player = players[0];
        } else {
            player = players[1];
        }
        return player;
    }

    public static String findCell(String[][] field, int num) {
        String cell = field[4][4];
        switch (num) {
            case 1 -> {
                return cell = field[0][0];
            }
            case 2 -> {
                return cell = field[0][2];
            }
            case 3 -> {
                return cell = field[0][4];
            }
            case 4 -> {
                return cell = field[2][0];
            }
            case 5 -> {
                return cell = field[2][2];
            }
            case 6 -> {
                return cell = field[2][4];
            }
            case 7 -> {
                return cell = field[4][0];
            }
            case 8 -> {
                return cell = field[4][2];
            }

        }
        return cell;
    }

    public static int move() {
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    public static boolean validNum(int cell) {
        return cell >= 1 && cell <= 9;
    }

    public static boolean validMove(String[][] field, String cell) {
        boolean valid = true;

        if (" X ".equals(cell) || " O ".equals(cell)) {
            valid = false;
            return valid;
        }
        return valid;
    }

    public static String[][] replace(String[][] field, int num, String smbl) {
        String[][] newField = field;
        switch (num) {
            case 1 -> field[0][0] = smbl;
            case 2 -> field[0][2] = smbl;
            case 3 -> field[0][4] = smbl;
            case 4 -> field[2][0] = smbl;
            case 5 -> field[2][2] = smbl;
            case 6 -> field[2][4] = smbl;
            case 7 -> field[4][0] = smbl;
            case 8 -> field[4][2] = smbl;
            case 9 -> field[4][4] = smbl;
        }
        return newField;
    }

    public static boolean winCheck(String[][] field, String smbl) {
        boolean rsl = false;
        for (int i = 0; i < field.length; i++) {
            if (smbl.equals(field[i][i])) {
                if (horWin(field, smbl, i) || verWin(field, smbl, i)) {
                    rsl = true;
                }
            }
        }
        return rsl;
    }

    public static boolean horWin(String[][] field, String smbl, int row) {
        boolean rsl = true;
        for (int i = 0; i < field[row].length; i = i + 2) {
            if (!smbl.equals(field[row][i])) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    public static boolean verWin(String[][] field, String smbl, int column) {
        boolean rsl = true;
        for (int i = 0; i < field.length; i = i + 2) {
            if (!smbl.equals(field[i][column])) {
                rsl = false;
                break;
            }
        }
        return rsl;
    }

    public static String[][] generate() {
        String[][] field = new String[][]{{" 1 ", "|", " 2 ", "|", " 3 "},
                {"---", "+", "---", "+", "---"},
                {" 4 ", "|", " 5 ", "|", " 6 "},
                {"---", "+", "---", "+", "---"},
                {" 7 ", "|", " 8 ", "|", " 9 "}};
        return field;
    }

    public static String[][] printField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
        return field;
    }
}


