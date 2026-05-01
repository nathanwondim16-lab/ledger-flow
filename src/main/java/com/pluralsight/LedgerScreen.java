package com.pluralsight;

/**
 * Displays teh ledger menu and handles user navigation for ledger-related actions.
 *
 * This screen allows the user to:
 * - View all ledger entries
 * - View deposits only
 * - View payments only
 * - Navigate to the reports screen
 * - Return to the home screen
 */
public class LedgerScreen extends ScreenManager {

    /**
     * Displays the ledger meny, processes user input, and routes the user to the
     * selected ledger feature.
     */
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

        // Keep showing ledger options until the user chooses to return home.
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

                default -> System.out.println(Colors.CRIMSON.colorize("\n===== INVALID OPTION. PLEASE CHOOSE A VALID OPTION ====="));
            }

            // Re-display menu after each action
            System.out.print(ledgerOptions);
            userChoice = scanner.nextLine().strip();
            System.out.println("\n");
        }
    }
}