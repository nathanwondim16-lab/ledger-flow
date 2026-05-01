package com.pluralsight;

/**
 * Displays the Reports menu and handles user selection.
 *
 * This screen allows the user to choose from predefined report options:
 * - Month to Date
 * - Previous month
 * - Year to date
 * - Previous year
 * - Vendor search
 * - Custom search
 *
 * After a valid option is selected, control is passed to the Reports class to
 * process and display the corresponding data.
 */
public class ReportScreen extends ScreenManager {

    /**
     * Displays the reports menu, validates user input, and routes the selection
     * to the appropriate report logic.
     */
    public void displayOptions() {

        // Display stylized screen title
        printScreenTitle("""
                ██████╗ ███████╗██████╗  ██████╗ ██████╗ ████████╗███████╗    ███████╗ ██████╗██████╗ ███████╗███████╗███╗   ██╗
                ██╔══██╗██╔════╝██╔══██╗██╔═══██╗██╔══██╗╚══██╔══╝██╔════╝    ██╔════╝██╔════╝██╔══██╗██╔════╝██╔════╝████╗  ██║
                ██████╔╝█████╗  ██████╔╝██║   ██║██████╔╝   ██║   ███████╗    ███████╗██║     ██████╔╝█████╗  █████╗  ██╔██╗ ██║
                ██╔══██╗██╔══╝  ██╔═══╝ ██║   ██║██╔══██╗   ██║   ╚════██║    ╚════██║██║     ██╔══██╗██╔══╝  ██╔══╝  ██║╚██╗██║
                ██║  ██║███████╗██║     ╚██████╔╝██║  ██║   ██║   ███████║    ███████║╚██████╗██║  ██║███████╗███████╗██║ ╚████║
                ╚═╝  ╚═╝╚══════╝╚═╝      ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚══════╝    ╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝  ╚═══╝
                
       
                """);

        String reportOptions = Colors.CHAMPAGNE_SILVER.colorize("""
                
                
                Select one of the following options below:
                
                1) Month to Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                0) Go back to Ledger Screen
                
                6) Custom Search
                
                Select option:\s""");

        // Keep prompting until a valid numeric option is entered
        while(true) {
            System.out.print(reportOptions);

            try {
                int userChoice = Integer.parseInt(scanner.nextLine().strip());
                System.out.println("\n"); // New Line

                // Delegate selected option to Reports class for processing.
                Reports.reportsOptions(userChoice);

                if(userChoice == 0) {
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println(Colors.CRIMSON.colorize("\n===== OPTIONS REQUIRE A NUMERICAL VALUE TO BE ENTERED ====="));
            }
        }
    }
}