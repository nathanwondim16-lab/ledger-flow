package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.displayOptions();
    }
}

//

/*
04/28/26|12:12 PM|Food|McDonald's|123.48
04/27/26|11:11 AM|Streaming TV|Netflix|-19.99
04/27/26|12:12:12|Work|Amazon LLC|3489.24
04/23/26|11:11:11|Streaming TV|Netflix|-34.99
03/04/25|19:19:19|Water|Smart Water|12.89
10/12/25|17:17:17|Healthcare|Molina|-234.89
08/08/23|16:17:18|Invoice|Microsoft|5679.23
08/09/26|18:12:13|Apple Music|Apple|-11.99
03/03/26|12:12:12|Car|Mercedes|1233.23
03/18/26|18:11:19|Phone|Apple|-256.78
08/09/19|18:18:18|Invoice|Year Up United|100.0
04/01/26|11:19:21|Health|CVS|-78.99
05/07/21|17:17:18|Food|Taco Bell|-123.12


// Think about using a Stack to order Ledger entries by most recently added transactions
// Ask gregor should the transactions be ordered by most recently added or by the date the transactions took place
// Ask Gregor if all final variable's names need to be capitalized -> TRANSACTION_DATE
 */