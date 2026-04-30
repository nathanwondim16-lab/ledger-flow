package com.pluralsight;

public class ReportScreen extends ScreenManager {


    public void displayOptions() {
        printScreenTitle("""
                ██████╗ ███████╗██████╗  ██████╗ ██████╗ ████████╗███████╗    ███████╗ ██████╗██████╗ ███████╗███████╗███╗   ██╗
                ██╔══██╗██╔════╝██╔══██╗██╔═══██╗██╔══██╗╚══██╔══╝██╔════╝    ██╔════╝██╔════╝██╔══██╗██╔════╝██╔════╝████╗  ██║
                ██████╔╝█████╗  ██████╔╝██║   ██║██████╔╝   ██║   ███████╗    ███████╗██║     ██████╔╝█████╗  █████╗  ██╔██╗ ██║
                ██╔══██╗██╔══╝  ██╔═══╝ ██║   ██║██╔══██╗   ██║   ╚════██║    ╚════██║██║     ██╔══██╗██╔══╝  ██╔══╝  ██║╚██╗██║
                ██║  ██║███████╗██║     ╚██████╔╝██║  ██║   ██║   ███████║    ███████║╚██████╗██║  ██║███████╗███████╗██║ ╚████║
                ╚═╝  ╚═╝╚══════╝╚═╝      ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚══════╝    ╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚══════╝╚═╝  ╚═══╝
                
       
                """);

        int userChoice;
        while(true) {
            System.out.print(Colors.CHAMPAGNE_SILVER.printWithColor("""
                
                
                Select one of the following options below
                
                1) Month to Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                0) Go back to Ledger Screen
                
                6) Custom Search
             
                Select option:\s"""));
            try {
                userChoice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.CRIMSON.printWithColor("\n===== OPTIONS REQUIRE A NUMERICAL VALUE TO BE ENTERED ====="));
            }
        }
        Reports reports = new Reports();
        reports.reportsScreen(userChoice);
    }
}
