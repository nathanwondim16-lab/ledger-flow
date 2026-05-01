package com.pluralsight;

import java.util.Scanner;

/**
 * Base class for all console screens in LedgerFlow.
 *
 * This class provides shared screen behavior, including:
 * - Access to Scanner input
 * - A required displayOptions() method for each screen
 * - Startup/welcome animation
 * - Shutdown message
 * - Shared screen title formatting
 */
public abstract class ScreenManager {

    // Shared input reader available to all screen subclasses.
    protected Scanner scanner = new Scanner(System.in);

    /**
     * Displays the menu options for a specific screen.
     *
     * Each subclass must provide its own implementation.
     */
    abstract void displayOptions();

    /**
     * Displays the LedgerFlow startup animation.
     *
     * This includes boot messages and a loading progress bar.
     */
    protected void welcomeMessage() {
        String[] systemBoot = {
                "> Booting LedgerFlow...",
                "> Loading transaction engine...",
                "> Establishing secure environment..."
        };

        // Display boot messages one line at a time with a short delay.
        for (String line : systemBoot) {
            System.out.print("\r" + Colors.AMBER.colorize(line));
            pauseProgram(1000);
        }
        System.out.println("\r ");

        String progressBar = "████████████████████████████";

        // Animate progress bar by printing one block at a time.
        for(int i = 0; i < progressBar.length(); i++) {
            System.out.print(Colors.GREEN.colorize("\rLoading: " + progressBar.substring(0, i + 1) + " " + (i + 1) * 100 / progressBar.length() + "%"));
            pauseProgram(100);
        }

        System.out.println("\r ");
    }

    // Displays the LedgerFlow shutdown animation and goodbye message.
    protected void stop() {
        String[] shutdown = {
                "> Logging out user...                           |",
                "> Archiving session data...                     |",
                "> Powering down...                              |"
        };

        // Display shutdown messages with a short delay between each step.
        for (String line : shutdown) {
            System.out.print(Colors.GREEN.colorize(line));
            pauseProgram(1000);
            System.out.print("\r ");
        }

        System.out.println("\n\n"); // Creating space for Goodbye message to print

        System.out.println(Colors.GOLD.colorize("""
                  ██████╗  ██████╗  ██████╗ ██████╗ ██████╗ ██╗   ██╗███████╗
                 ██╔════╝ ██╔═══██╗██╔═══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝██╔════╝
                 ██║  ███╗██║   ██║██║   ██║██████╔╝██████╔╝ ╚████╔╝ █████╗ \s
                 ██║   ██║██║   ██║██║   ██║██╔══██╗██╔══██╗  ╚██╔╝  ██╔══╝ \s
                 ╚██████╔╝╚██████╔╝╚██████╔╝██████╔╝██████╔╝   ██║   ███████╗
                  ╚═════╝  ╚═════╝  ╚═════╝ ╚═════╝ ╚═════╝    ╚═╝   ╚══════╝
                """));

        System.out.println("""
                           \n
                💻THANKS FOR USING LEDGERFLOW 📒
                
                SEE YOU NEXT TRANSACTION 👋
                """);
    }

    /**
     * Prints a screen title using the shared title color.
     *
     * @param screenTitle the title text or ASCII art to display
     */
    protected void printScreenTitle(String screenTitle) {
        System.out.println(Colors.GOLD.colorize("\n" + screenTitle));
    }

    /**
     * Pauses the program for the specified number of milliseconds.
     *
     * Used to create startup and shutdown animation effects.
     *
     * @param milliseconds the number of milliseconds to pause
     */
    private void pauseProgram(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
}