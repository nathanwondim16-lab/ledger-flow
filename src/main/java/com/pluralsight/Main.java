package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.displayOptions();
    }
}

/*


// Think about using a Stack to order Ledger entries by most recently added transactions
// Ask gregor should the transactions be ordered by most recently added or by the date the transactions took place
// Ask Gregor if all final variable's names need to be capitalized -> TRANSACTION_DATE
// Ask Gregor if the user is required to enter a start date for the custom search to workl



date|time|description|vendor|amount
04/27/26|03:15 PM|Streaming TV|Netflix|1256.73
04/28/26|12:12 PM|Food|McDonald's|123.48
04/27/26|11:11 AM|Streaming TV|Netflix|-19.99
04/27/26|12:45 AM|Work|Amazon LLC|3489.24
04/23/26|11:11 PM|Streaming TV|Netflix|-34.99
03/04/25|05:19 AM|Water|Smart Water|12.89
10/12/25|01:17 PM|Healthcare|Molina|-234.89
08/08/23|06:17 AM|Invoice|Microsoft|5679.23
08/09/26|08:12 PM|Apple Music|Apple|-11.99
03/03/26|12:12 AM|Car|Mercedes|1233.23
03/18/26|03:45 PM|Phone|Apple|-256.78
08/09/19|08:18 PM|Invoice|Year Up United|100.0
04/01/26|11:19 PM|Health|CVS|-78.99
05/07/21|07:17 AM|Food|Taco Bell|-123.12
 */

