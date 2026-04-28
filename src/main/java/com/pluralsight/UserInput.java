package com.pluralsight;

import java.util.Scanner;

public abstract class UserInput {
    protected Scanner scanner = new Scanner(System.in);

    abstract void displayOptions();

    protected void welcomeMessage() {
        System.out.print(Colors.TRON.printWithColor("""
                ██╗     ███████╗██████╗  ██████╗ ███████╗██████╗ ███████╗██╗      ██████╗ ██╗    ██╗
                ██║     ██╔════╝██╔══██╗██╔════╝ ██╔════╝██╔══██╗██╔════╝██║     ██╔═══██╗██║    ██║
                ██║     █████╗  ██║  ██║██║  ███╗█████╗  ██████╔╝█████╗  ██║     ██║   ██║██║ █╗ ██║
                ██║     ██╔══╝  ██║  ██║██║   ██║██╔══╝  ██╔══██╗██╔══╝  ██║     ██║   ██║██║███╗██║
                ███████╗███████╗██████╔╝╚██████╔╝███████╗██║  ██║██║     ███████╗╚██████╔╝╚███╔███╔╝
                ╚══════╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚═╝  ╚═╝╚═╝     ╚══════╝ ╚═════╝  ╚══╝╚══╝\s
            
                """));
        String[] systemBoot = {
                "|  > Booting LedgerFlow...                         |",
                "|  > Initializing modules...                       |",
                "|  > Loading transaction engine...                 |",
                "|  > Establishing secure environment...            |",
                "|  > System ready.                                 |"
        };

        for (String line : systemBoot) {
            System.out.print(line);
            try {
                Thread.sleep(1500);
                System.out.print("\r ✅");
            } catch (InterruptedException e) {
                System.out.println("Something went wrong " + e.getMessage()); // Be more specific about what went wrong
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\r ");
        }

    }

    protected void stop() {
        String[] shutdown = {
                "|  > Logging out user...                           |",
                "|  > Archiving session data...                     |",
                "|  > Powering down...                              |",
                "|  > Session Closed                                |",
                "|  > LedgerFlow signing off                        |"
        };

        for (String line : shutdown) {
            System.out.print(line);
            try {
                Thread.sleep(1500);
                System.out.print("\r ✅");
            } catch (InterruptedException e) {
                System.out.println("Something went wrong " + e.getMessage()); // Be more specific about what went wrong
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.print("\r ");
        }
    }
}
