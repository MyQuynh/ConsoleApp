import java.util.InputMismatchException;

public interface Valid {

    default int checkInput(int min, int max){
        int option = 0;

        while (true) {
            try {
                System.out.print("Please enter your option: ");
                option = Main.scanner.nextInt();
                Main.scanner.nextLine();
                if (option < min || option > max) {
                    System.out.println("The option has to be between "+ min+" and "+max);
                    System.out.println("Please try again");
                } else if (String.valueOf(option).equalsIgnoreCase("")){
                    System.out.println("The option cannot be empty");
                    System.out.println("Please try again");
                } else {
                    break;
                }

            } catch (InputMismatchException e) {
                Main.scanner.nextLine();
                System.out.println("Please enter only number");
                System.out.println("Please try again");
            }
        }
        return option;
    };

}
