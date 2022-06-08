package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();

        char[][] cinemaSeats = new char[rows][seatsInRow];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(cinemaSeats[i], 'S');
        }

        int runCode = 0;
        do {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            runCode = scanner.nextInt();
            run(rows, seatsInRow, cinemaSeats, runCode);
        }
        while (runCode != 0);
    }

    private static void run(int rows, int seatsInRow, char[][] cinemaSeats, int runCode) {
        switch (runCode) {
            case 1:
                printCinemaSeats(cinemaSeats);
                break;
            case 2:
                buyTicket(cinemaSeats);
                break;
            case 3:
                showStatistics(cinemaSeats);
                break;
        }
    }

    private static void showStatistics(char[][] cinemaSeats) {
        int purchasedTickets = getPurchasedTickets(cinemaSeats);
        double purchasedTicketsPercentage = getPurchasedTicketsPercentage(cinemaSeats);
        int currentIncome = getCurrentIncome(cinemaSeats);
        int totalIncome = getTotalIncome(cinemaSeats);

        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.println("Percentage: " + String.format("%.2f", purchasedTicketsPercentage) + "%");
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    private static int getTotalSeats(char[][] cinemaSeats) {
        return cinemaSeats.length * cinemaSeats[0].length;
    }

    private static double getPurchasedTicketsPercentage(char[][] cinemaSeats) {
        double percentage = ((double) getPurchasedTickets(cinemaSeats) / (double) getTotalSeats(cinemaSeats));
        return percentage * 100;
    }

    private static int getCurrentIncome(char[][] cinemaSeats) {
        int currentIncome = 0;
        for (int i = 0; i < cinemaSeats.length; i++) {
            for (int j = 0; j < cinemaSeats[i].length; j++) {
                if (cinemaSeats[i][j] == 'B') {
                    currentIncome += getTicketPrice(cinemaSeats, new int[]{i, j});
                }
            }
        }
        return currentIncome;
    }

    private static int getTotalIncome(char[][] cinemaSeats) {
        int totalIncome = 0;
        for (int i = 0; i < cinemaSeats.length; i++) {
            for (int j = 0; j < cinemaSeats[i].length; j++) {
                totalIncome += getTicketPrice(cinemaSeats, new int[]{i, j});
            }
        }
        return totalIncome;
    }

    private static int getPurchasedTickets(char[][] cinemaSeats) {
        int purchasedTickets = 0;
        for (int i = 0; i < cinemaSeats.length; i++) {
            for (int j = 0; j < cinemaSeats[i].length; j++) {
                if (cinemaSeats[i][j] == 'B') {
                    purchasedTickets++;
                }
            }
        }
        return purchasedTickets;
    }

    private static void buyTicket(char[][] cinemaSeats) {
        Scanner scanner = new Scanner(System.in);

        int[] pickedSeat = new int[2];
        boolean isSeatPicked = false;
        int rows = cinemaSeats.length;
        int columns = cinemaSeats[0].length;

        do {
            System.out.println("Enter a row number:");
            pickedSeat[0] = scanner.nextInt() - 1;

            System.out.println("Enter a seat number in that row:");
            pickedSeat[1] = scanner.nextInt() - 1;

            if ((pickedSeat[1] < 0 || pickedSeat[1] >= columns) ||
                    (pickedSeat[0] < 0 || pickedSeat[0] >= rows)
            ) {
                System.out.println("Wrong input!");
                isSeatPicked = true;
                continue;
            }

            isSeatPicked = 'B' == cinemaSeats[pickedSeat[0]][pickedSeat[1]];

            if (isSeatPicked) {
                System.out.println("That ticket has already been purchased!");
            }
        } while (isSeatPicked);

        System.out.printf("Ticket price: $%d%n", getTicketPrice(cinemaSeats, pickedSeat));

        cinemaSeats[pickedSeat[0]][pickedSeat[1]] = 'B';
    }

    private static int getTicketPrice(char[][] cinemaSeats, int[] pickedSeat) {
        int rows = cinemaSeats.length;
        int seatsInRow = cinemaSeats[0].length;
        int totalSeats = rows * seatsInRow;

        if (totalSeats <= 60) {
            return 10;
        } else {
            if (pickedSeat[0] < rows / 2) {
                return 10;
            } else {
                return 8;
            }
        }
    }


    private static void printCinemaSeats(char[][] cinemaSeats) {
        System.out.println();
        System.out.println("Cinema:");

        System.out.print("  ");
        for (int i = 1; i <= cinemaSeats[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");

        for (int i = 0; i < cinemaSeats.length; i++) {
            System.out.println((i + 1) + " " + Arrays.toString(cinemaSeats[i])
                    .replace("[", "")
                    .replace("]", "")
                    .replace(",", "")
            );
        }

        System.out.println();
    }
}