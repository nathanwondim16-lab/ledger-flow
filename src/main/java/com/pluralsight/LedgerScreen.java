package com.pluralsight;

public class LedgerScreen extends ScreenManager {


    @Override
    protected void displayOptions() {

        // Display stylized screen title
        printScreenTitle("""
                
                
                
                
                
                
                ██╗     ███████╗██████╗  ██████╗ ███████╗██████╗     ███████╗ ██████╗██████╗ ███████╗███████╗███╗   ██╗
                ██║     ██╔════╝██╔══██╗██╔════╝ ██╔════╝██╔══██╗    ██╔════╝██╔════╝██╔══██╗██╔════╝██╔════╝████╗  ██║
                ██║     █████╗  ██║  ██║██║  ███╗█████╗  ██████╔╝    ███████╗██║     ██████╔╝█████╗  █████╗  ██╔██╗ ██║
                ██║     ██╔══╝  ██║  ██║██║   ██║██╔══╝  ██╔══██╗    ╚════██║██║     ██╔══██╗██╔══╝  ██╔══╝  ██║╚██╗██║
                ███████╗███████╗██████╔╝╚██████╔╝███████╗██║  ██║    ███████║╚██████╗██║  ██║███████╗███████╗██║ ╚████║
                ╚══════╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚═╝  ╚═╝    ╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝  ╚═══╝
                """);
        String ledgerOptions = Colors.CHAMPAGNE_SILVER.colorize("""
               
               Select one of the following options below
              
               A) Display All Entries
               D) Deposits
               P) Payments
               R) Reports
               H) Home - Go back to the Home Screen
               
               Select option:\s""");
        System.out.print(ledgerOptions);
        String userChoice = scanner.nextLine().strip();
        while(!userChoice.equalsIgnoreCase("H")) {
            switch(userChoice.toUpperCase()) {
                case "A" -> {
                    Ledger.displayLedger();
                }

                case "D" -> {
                    Ledger.displayDeposits();
                }

                case "P" -> {
                    Ledger.displayPayments();
                }

                case "R" -> {
                    ReportScreen reportScreen = new ReportScreen();
                    reportScreen.displayOptions();
                }

                default -> System.out.println(Colors.CRIMSON.colorize("===== INVALID OPTION. PLEASE CHOOSE A VALID OPTION ====="));
            }
            System.out.print(ledgerOptions);
            userChoice = scanner.nextLine().strip();
            System.out.println("\n");
        }
    }
}
