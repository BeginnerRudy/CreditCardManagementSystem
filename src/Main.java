import java.util.Scanner;

public class Main {
    public static final String Welcome_Message  = "Welcome to our credit card management system!";
    public static final String Ask_Whether_Customer_Has_Account = "Do you already have an account?";
    public static final String User_Input_Command_Panel = "Please enter 1 for yes, 0 for no, 2 for cancel: ";
    public static final String Warning_For_Incorret_Input = "Sorry, you enter invalid input.";
    public static final String Say_Goodbye = "Thanks for using!";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userinput = welcomingPhase(scanner);
        if (is_input_yes(userinput)){
            System.out.println("Please insert your card or type in your card number: ");
        }else{
            System.out.println("Would you like your create a account? ");
            System.out.print(User_Input_Command_Panel);
        }

    }

    private static String welcomingPhase(Scanner scanner){
        System.out.println(Welcome_Message);
        System.out.print(Ask_Whether_Customer_Has_Account);
        System.out.println(User_Input_Command_Panel);

        String userinput = scanner.nextLine();
        while (!input_is_valid(userinput)){
            if (!userinput.equals("1") || !userinput.equals("0")){
                System.out.println("Warning! Please enter 1 for yes, 0 for no : ");
            }

            userinput = scanner.next();
        }

        if (userinput.equals("2")){
            System.out.println(Say_Goodbye);
            System.exit(0);
        }
        return  userinput;
    }

    private static boolean input_is_valid(String input){
        return (input.equals("0") || input.equals("1") || input.equals("2"));
    }

    private static boolean is_input_yes(String in){
        return in.equals("1");
    }
}

