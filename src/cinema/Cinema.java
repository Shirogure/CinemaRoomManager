package cinema;

import java.util.Scanner;

public class Cinema{
    private static String[][] cinemaScheme;
    private static final Scanner sc = new Scanner(System.in);
    private static int rows;
    private static int seats;
    private static int totalTicketsSold = 0;

    private static int curentIncome = 0;

    public static void main(String[] args) {
        // Solicită utilizatorului numărul de rânduri și locuri pe rând
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();

        // Inițializează schema de cinema cu rânduri și locuri
        cinemaScheme = new String[rows + 1][seats + 1];
        initializeSeats();

        // Meniu principal
        while (true) {
            System.out.println("""
                    1. Show the seats
                    2. Buy a ticket
                    3. statistics
                    0. Exit
                    """);

            int option = sc.nextInt();

            switch (option) {
                case 1 -> showTheSeats();
                case 2 -> buyATicket();
                case 3 -> statistic();
                case 0 -> {
                    System.out.println("Exiting the program...");
                    return; // Oprește programul
                }

            }
        }
    }

    private static void initializeSeats() {
        // Inițializează prima linie cu numerele coloanelor
        for (int j = 1; j <= seats; j++) {
            cinemaScheme[0][j] = String.valueOf(j);
        }

        // Inițializează rândurile cu numere și locuri libere "S"
        for (int i = 1; i <= rows; i++) {
            cinemaScheme[i][0] = String.valueOf(i); // Numerotare rânduri
            for (int j = 1; j <= seats; j++) {
                cinemaScheme[i][j] = "S";
            }
        }
    }

    public static void showTheSeats() {
        System.out.println("Cinema:");

        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                if (cinemaScheme[i][j] == null) {
                    System.out.print("  ");
                } else {
                    System.out.print(cinemaScheme[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void buyATicket() {
        System.out.println("Enter a row number:");
        int rowNumber = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = sc.nextInt();

        // Verifică dacă locul este deja ocupat
        if (rowNumber > rows || seatNumber > seats || rowNumber < 1 || seatNumber < 1) {
            System.out.println("Wrong input!");

        } else if (cinemaScheme[rowNumber][seatNumber].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            buyATicket();

        } else {
            int totalSeats = rows * seats;
            int ticketPrice = (totalSeats <= 60 || rowNumber <= rows / 2) ? 10 : 8;

            cinemaScheme[rowNumber][seatNumber] = "B";
            totalTicketsSold++;
            System.out.println("Ticket price: $" + ticketPrice);
            curentIncome += ticketPrice;
        }
    }

    public static void statistic() {
        double percentage = ((double) totalTicketsSold / (rows * seats)) * 100;
        int totalIncome = ((rows / 2) * seats) * 10 + ((rows - (rows / 2)) * seats) * 8;

        System.out.println("Number of purchased tickets: " + totalTicketsSold);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("current income: $" + curentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}
