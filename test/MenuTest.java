import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.util.Scanner;

import static org.junit.Assert.*;

public class MenuTest {

    @Before
    public void resetScanner() {
        Main.scanner = new Scanner(System.in);
    }

    @Rule
    public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();


    @Test
    public void checkInput() {
        Menu menu = new Menu();
        // Case 1: Check if the input is number
        // Case 2: Check if the the number between the range of 1 to 4
        systemInMock.provideLines("a","-1","2");
        int input = menu.checkInput(1,4);
        assertEquals(input,2);
    }

    @Test
    public void updateMenu() {
        // Assume that we want to choose the adding option
        Menu menu = new Menu();
        systemInMock.provideLines("a","-1","1");
        int input = menu.updateMenu();
        assertEquals(1, input);
    }

    @Test
    public void displayMenu() {
        // Assume that we want to choose the return to the main menu option
        Menu menu = new Menu();
        systemInMock.provideLines("a","-1","3");
        int input = menu.updateMenu();
        assertEquals(3, input);
    }

    @Test
    public void mainMenu() {
        // Assume that we want to choose the return to the main menu option
        Menu menu = new Menu();
        systemInMock.provideLines("a","-1","6");
        int input = menu.mainMenu();
        assertEquals(6, input);
    }
}