package com.pluralsight;

public class ReportScreen extends UserInput {


    public void displayOptions() {
        System.out.print("""
                Select one of the following options below
                
                1) Month to Date
                2) Previous Month
                3) Year To Date
                4) Previous Year
                5) Search by Vendor
                
                Select option:\s""");
        int userChoice = Integer.parseInt(scanner.nextLine());
        Reports reports = new Reports();
        reports.reportsScreen(userChoice);
    }
}
