package com.pluralsight;

import java.util.Scanner;

public abstract class UserInput {
    protected Scanner scanner = new Scanner(System.in);

    abstract void displayOptions();

    protected void welcomeMessage() {
        System.out.print(Colors.GOLD.printWithColor("""
                ██╗     ███████╗██████╗  ██████╗ ███████╗██████╗ ███████╗██╗      ██████╗ ██╗    ██╗
                ██║     ██╔════╝██╔══██╗██╔════╝ ██╔════╝██╔══██╗██╔════╝██║     ██╔═══██╗██║    ██║
                ██║     █████╗  ██║  ██║██║  ███╗█████╗  ██████╔╝█████╗  ██║     ██║   ██║██║ █╗ ██║
                ██║     ██╔══╝  ██║  ██║██║   ██║██╔══╝  ██╔══██╗██╔══╝  ██║     ██║   ██║██║███╗██║
                ███████╗███████╗██████╔╝╚██████╔╝███████╗██║  ██║██║     ███████╗╚██████╔╝╚███╔███╔╝
                ╚══════╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝\s
            
                """));
        String[] systemBoot = {
                "> Booting LedgerFlow...",
                "> Loading transaction engine...",
                "> Establishing secure environment..."
        };

        for (String line : systemBoot) {
            System.out.print("\r" + line);
            pauseProgram(700);
        }
        System.out.println("\r ");

        String progressBar = "████████████████████████████";
        for(int i = 0; i < progressBar.length(); i++) {
            System.out.print(Colors.GREEN.printWithColor("\rLoading: " + progressBar.substring(0, i + 1) + " " + (i + 1) * 100 / progressBar.length() + "%"));
            pauseProgram(100);
        }
        System.out.println("\r ");
    }


    // Add more to the Goodbye message
    protected void stop() {
        String[] shutdown = {
                "|  > Logging out user...                           |",
                "|  > Archiving session data...                     |",
                "|  > Powering down...                              |"
        };

        for (String line : shutdown) {
            System.out.print(line);
            pauseProgram(1500);
            System.out.print("\r ✅");
            pauseProgram(1000);
            System.out.print("\r ");
        }
        System.out.println(Colors.GOLD.printWithColor("""
                  ██████╗  ██████╗  ██████╗ ██████╗ ██████╗ ██╗   ██╗███████╗
                 ██╔════╝ ██╔═══██╗██╔═══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝██╔════╝
                 ██║  ███╗██║   ██║██║   ██║██████╔╝██████╔╝ ╚████╔╝ █████╗ \s
                 ██║   ██║██║   ██║██║   ██║██╔══██╗██╔══██╗  ╚██╔╝  ██╔══╝ \s
                 ╚██████╔╝╚██████╔╝╚██████╔╝██████╔╝██████╔╝   ██║   ███████╗
                  ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝    ╚═╝   ╚══════╝
                """));
        System.out.println("""
                           \n
                💻 Thanks for using LedgerFlow 🛒
                           See you next transaction 👋
                """);
    }

    private void pauseProgram(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}
