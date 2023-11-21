public class Table {
    // each line in the box has 30 spaces (not including the sides of the box)
    int space = 73;

    public int getSpace() {
        return this.space;
    }

    public void blankLine() {
        System.out.print("|");
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print("|\n");
    }

    public void lastLine() {
        System.out.print("|");
        for (int i = 0; i < space; i++) {
            System.out.print("_");
        }
        System.out.print("|\n");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void titleMenu() {
        System.out.print("""
                 _________________________________________________________________________
                |  _____     _      ____    _  __  _____    ___    ____    ___      _     |
                | |_   _|   / \\    / ___|  | |/ / |_   _|  / _ \\  |  _ \\  |_ _|    / \\    |
                |   | |    / _ \\   \\___ \\  | ' /    | |   | | | | | | ) |  | |    / _ \\   |
                |   | |   / ___ \\   ___) | | . \\    | |   | |_| | |  __/   | |   / ___ \\  |
                |   |_|  /_/   \\_\\ |____/  |_|\\_\\   |_|    \\___/  |_|     |___| /_/   \\_\\ |
                """);
    }

    public void helpMenu() {
        titleMenu();
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

    public void mainMenu() {
        titleMenu();
        System.out.println("""
                |                                                                         |
                |                                                                         |
                |                                                                         |
                |                               1. VIEW TASKS                             |
                |                                                                         |
                |                                                                         |
                |                               2. MAKE TASKS                             |
                |                                                                         |
                |                                                                         |
                |                                 3. EXIT                                 |
                |                                                                         |
                |_________________________________________________________________________|
                """);
    }

    public void addTaskScreen() {
        titleMenu();
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

    public void noTasksScreen() {
        titleMenu();
        System.out.println("""
                |                                                                         |
                |                            No tasks available.                          |
                |                                                                         |
                |                   Would you like to add a task? (yes/no)                |
                |                                                                         |
                |_________________________________________________________________________|
                """);
    }

    public void sortingScreen() {
        titleMenu();
        System.out.println(
                """
                        |                                                                         |
                        |                                  SORT BY:                               |
                        |                                1. Due Date                              |
                        |                                                                         |
                        |                                2. Priority                              |
                        |                                                                         |                                                                         |
                        |                                3. Task Type                             |                                                                         |
                        |                                                                         |                                                                         |
                        |                                 0. None                                 |
                        |                                                                         |
                        |_________________________________________________________________________|
                        """);
    }
}
