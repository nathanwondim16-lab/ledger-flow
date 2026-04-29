package com.pluralsight;

public class ReportScreen extends ScreenManager {


    public void displayOptions() {
        System.out.print(Colors.CHAMPAGNE_SILVER.printWithColor("""
                Select one of the following options below
                
                1) Month to Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                
                Select option:\s"""));
        int userChoice = Integer.parseInt(scanner.nextLine());
        System.out.println(); // New Line
        Reports reports = new Reports();
        reports.reportsScreen(userChoice);
    }
}
