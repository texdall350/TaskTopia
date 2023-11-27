public class Table {
    // Defines the width of the table in characters.
    int space = 73;
    TextColors TEXT = new TextColors(); // Instance of TextColors for color formatting.

    // Returns the width of the table.
    public int getSpace() {
        return this.space;
    }

    // Prints a blank line within the table boundaries.
    public void blankLine() {
        System.out.print("|");
        for (int i = 0; i < space; i++) {
            System.out.print(" "); // Fills the line with spaces.
        }
        System.out.print("|\n"); // Closes the line with a border and moves to the next line.
    }

    // Prints the last line of the table with an underscore border.
    public void lastLine() {
        System.out.print("|");
        for (int i = 0; i < space; i++) {
            System.out.print("_"); // Fills the line with underscores.
        }
        System.out.print("|\n"); // Closes the line with a border and moves to the next line.
    }

    // Clears the console screen.
    public void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape codes to clear the screen.
        System.out.flush(); // Ensures the buffer is cleared and the screen is updated.
    }

    // Prints the title menu of the application.
    public void titleMenu() {
        // Prints the title with color formatting.
        System.out.println(" _________________________________________________________________________");
        System.out.println("|" + TEXT.BLUE + "  _____     _      ____    _  __  _____    ___    ____    ___      _  "
                + TEXT.RESET + "   |");
        System.out.println("|" + TEXT.BLUE + " |_   _|   / \\    / ___|  | |/ / |_   _|  / _ \\  |  _ \\  |_ _|    / \\"
                + TEXT.RESET + "    |");
        System.out.println("|" + TEXT.BLUE
                + "   | |    / _ \\   \\___ \\  | ' /    | |   | | | | | |_) |  | |    / _ \\" + TEXT.RESET + "   |");
        System.out.println("|" + TEXT.BLUE
                + "   | |   / ___ \\   ___) | | . \\    | |   | |_| | |  __/   | |   / ___ \\" + TEXT.RESET + "  |");
        System.out.println(
                "|" + TEXT.BLUE + "   |_|  /_/   \\_\\ |____/  |_|\\_\\   |_|    \\___/  |_|     |___| /_/   \\_\\"
                        + TEXT.RESET + " |");
    }

    // Prints the help menu with options.
    public void helpMenu() {
        titleMenu(); // Prints the title menu at the top.
        // Prints the help menu options using a text block for readability.
        System.out.println("""
                |                                                                         |
                |                     Type any of the following options:                  |
                |                                                                         |
                |                        1 - To view or make new tasks                    |
                |                                                                         |
                |                            2 - To add new task                          |
                |                                                                         |
                |                             3 - Exits program                           |
                |                                                                         |
                |                            0 - Help (this menu)                         |
                |                                                                         |
                |_________________________________________________________________________|
                """);
    }

    // Prints the main menu with options.
    public void mainMenu() {
        titleMenu(); // Prints the title menu at the top.
        // Prints the main menu options using a text block for readability.
        System.out.println("""
                |                                                                         |
                |                                                                         |
                |                                                                         |
                |                               1. View Tasks                             |
                |                                                                         |
                |                               2. Make Tasks                             |
                |                                                                         |
                |                               3. Edit Tasks                             |
                |                                                                         |
                |                               4. EXIT                                   |
                |                                                                         |
                |_________________________________________________________________________|
                """);
    }

    // Prints the add task screen with options.
    public void addTaskScreen() {
        titleMenu(); // Prints the title menu at the top.
        // Prints the add task options using a text block for readability.
        System.out.println("""
                |                                                                         |
                |                               1. Basic Task                             |
                |                                                                         |
                |                               2. Work Task                              |
                |                                                                         |
                |                               3. School Task                            |
                |                                                                         |
                |                               4. Personal Task                          |
                |                                                                         |
                |                               5. Household Chore                        |
                |                                                                         |
                |                               0. Back to Main                           |
                |                                                                         |
                |_________________________________________________________________________|
                """);
    }

    // Prints a screen when no tasks are available.
    public void noTasksScreen() {
        titleMenu(); // Prints the title menu at the top.
        // Prints a message indicating no tasks are available and an option to add a
        // task.
        System.out.println("""
                |                                                                         |
                |                            No tasks available.                          |
                |                                                                         |
                |                   Would you like to add a task? (yes/no)                |
                |                                                                         |
                |_________________________________________________________________________|
                """);
    }

    // Prints the sorting options screen.
    public void sortingScreen() {
        titleMenu(); // Prints the title menu at the top.
        // Prints sorting options using a text block for readability.
        System.out.println("""
                |                                                                         |
                |                                  SORT BY:                               |
                |                               1. Due Date                               |
                |                                                                         |
                |                               2. Priority                               |
                |                                                                         |
                |                               3. Task Type                              |
                |                                                                         |
                |                               0. Back to Main                           |
                |                                                                         |
                |_________________________________________________________________________|
                """);
    }

    // Prints the edit task options screen.
    public void editScreen() {
        titleMenu(); // Prints the title menu at the top.
        // Prints edit task options using a text block for readability.
        System.out.println("""
                |                                                                         |
                |                               1. Complete Task                          |
                |                                                                         |
                |                               2. Delete Task                            |
                |                                                                         |
                |                               3. Edit Task                              |
                |                                                                         |
                |                               0. Back to Main                           |
                |                                                                         |
                |_________________________________________________________________________|
                """);
    }
}
