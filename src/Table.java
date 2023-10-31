import java.util.Scanner;

public class Table 
{
    //each line in the box has 30 spaces (not including the sides of the box)
    int space = 73;

    public void blankLine()
    {
        System.out.print("|");
        for (int i = 0; i < space; i++)
        {
            System.out.print(" ");
        }
        System.out.print("|\n");
    }

    public static void clearScreen() 
    {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void mainMenu()
    {
       

        System.out.println("__________________________________________________________________________");
        System.out.println("|  _____     _      ____    _  __  _____    ___    ____    ___      _     |");
        System.out.println("| |_   _|   / \\    / ___|  | |/ / |_   _|  / _ \\  |  _ \\  |_ _|    / \\    |");
        System.out.println("|   | |    / _ \\   \\___ \\  | ' /    | |   | | | | | | ) |  | |    / _ \\   |");
        System.out.println("|   | |   / ___ \\   ___) | | . \\    | |   | |_| | |  __/   | |   / ___ \\  |");
        System.out.println("|   |_|  /_/   \\_\\ |____/  |_|\\_\\   |_|    \\___/  |_|     |___| /_/   \\_\\ |");
        blankLine();
        blankLine();
        blankLine();
        System.out.println("|                               * VIEW TASKS                              |");
        blankLine();
        blankLine();
        System.out.println("|                               * MAKE TASKS                              |");
        blankLine();
        blankLine();
        System.out.println("|                                 * EXIT                                  |");
        blankLine();
        System.out.println("|_________________________________________________________________________|");
    }
   
    public void addTaskScreen()
    {
        System.out.println("__________________________________________________________________________");
        System.out.println("|  _____     _      ____    _  __  _____    ___    ____    ___      _     |");
        System.out.println("| |_   _|   / \\    / ___|  | |/ / |_   _|  / _ \\  |  _ \\  |_ _|    / \\    |");
        System.out.println("|   | |    / _ \\   \\___ \\  | ' /    | |   | | | | | | ) |  | |    / _ \\   |");
        System.out.println("|   | |   / ___ \\   ___) | | . \\    | |   | |_| | |  __/   | |   / ___ \\  |");
        System.out.println("|   |_|  /_/   \\_\\ |____/  |_|\\_\\   |_|    \\___/  |_|     |___| /_/   \\_\\ |");

        System.out.println("|                               * VIEW TASKS                              |");
        blankLine();
        blankLine();
        System.out.println("|                               * MAKE TASKS                              |");
        blankLine();
        blankLine();
        System.out.println("|                                 * EXIT                                  |");
        blankLine();
        System.out.println("|_________________________________________________________________________|");
    }
}
