import java.util.Scanner;

import static java.lang.Math.floor;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);
    static String[][] Cinema;
    static int row;
    static int seat;

    static int counterOfSeat = 0;

    static long Bank = 0;

    static long totalIncome = 0;

    public static void main(String[] args) {
        // Write your code here
        // First Step
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seat = scanner.nextInt();
        if (row * seat <= 60) {
            totalIncome = (long) row * seat;
        } else {
            int seats = row * seat;
            totalIncome = (seats / 2) * 10L;
            totalIncome += floor(seats / 2.0) * 8;
        }
        Cinema = new String[row + 1][seat + 1];
        setCinema(row, seat);
        while (true) {
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. Statistics
                    0. Exit""");
            int cases = scanner.nextInt();
            if (cases == 0) {
                break;
            }
            switchMode(cases);
        }
    }

    public static void SmallCinema() {
        System.out.println("Ticket price: $" + 10);
        Bank += 10;
    }

    public static void LargeCinema(int row, int seatRow) {
        int mid = row / 2;
        int result = (seatRow <= mid) ? 10 : 8;
        System.out.println("Ticket price: $" + result);
        Bank += result;
    }

    public static void setCinema(int row, int column) {
        for (int i = 0; i <= row; ++i) {
            if (i != 0) {
                Cinema[i][0] = Integer.toString(i);
            }
            for (int j = 0; j <= column; ++j) {
                if (i == 0 && j == 0) {
                    Cinema[i][j] = " ";
                } else if (i == 0 && j > 0) {
                    Cinema[i][j] = Integer.toString(j);
                } else if (j > 0) {
                    Cinema[i][j] = "S";
                }
            }
        }
    }

    public static void displayCinema() {
        System.out.println("Cinema:");
        for (int i = 0; i <= row; ++i) {
            for (int j = 0; j <= seat; ++j) {
                System.out.print(Cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void switchMode(int cases) {
        switch (cases) {
            case 1 -> displayCinema();
            case 2 -> buyTickets();
            case 3 -> statistics();
            default -> {
            }
        }
    }

    public static void buyTickets() {
        while (true) {
            System.out.println("Enter a row number:");
            int seatRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();
            if (seatRow > row || seatNumber > seat) {
                System.out.println("Wrong input!");
            } else if (Cinema[seatRow][seatNumber].contains("B")) {
                System.out.println("That ticket has already been purchased!");
            } else {
                if (row * seat >= 60) {
                    LargeCinema(row, seatRow);
                } else {
                    SmallCinema();
                }
                Cinema[seatRow][seatNumber] = "B";
                ++counterOfSeat;
                break;
            }
        }
    }

    public static void statistics() {
        System.out.println("Number of purchased tickets: " + counterOfSeat);
        double percent = ((counterOfSeat / (double) (row * seat)) * 100);
        System.out.printf("Percentage: %.2f%%%n", percent);
        System.out.println("Current income: $" + Bank);
        System.out.println("Total income: $" + totalIncome);
    }
}




