import java.util.Scanner;

public class Main {
    public static final String Welcome_Message  = "Welcome to our credit card management system!";
    public static final String Ask_Whether_Customer_Has_Account = "Do you already have an account? ";
    public static final String User_Input_Command_Panel = "Please enter 1 for yes, 0 for no, 2 for cancel: ";
    public static final String User_Input_Command_Panel_2 = "0 for exit, 1 for report card stolen, 2 for consuming $400" +
            " with card,\n3 for activate card, 4 for checking credit, 5 for pay $400 for the bill, " +
            "\n6 for account state checking, 7 for bill checking";
    public static final String Warning_For_Incorret_Input = "Sorry, you enter invalid input.";
    public static final String Say_Goodbye = "Thanks for using!";

    public static void main(String[] args) {
        Customer customer;

        Scanner scanner = new Scanner(System.in);
        customer = handleCustomerCreation(scanner);
        handleAccountCreation(scanner, customer);

        // Now we have a customer object
        System.out.println("======================Command Window================================");
        System.out.println("What would you like to do now?");
        System.out.println(User_Input_Command_Panel_2);
        System.out.println("====================================================================");
        System.out.print("Please enter: ");
        String userinput2 = getUserInput2(scanner);
        while (!isInput2Exit(userinput2)){
            // check if the account has been inactive for more than certain time, if so close the account

            // check if the account is (has no outstanding bill && has not been used for more than certain time), if so make the account into pending state

            // check if there is any overdue bill if it is make account into default state
            if (customer.getAccount().getState() != Account.State.Default){
                if (customer.hasOverDueBill(System.currentTimeMillis())){
                    customer.getAccount().toDefault();
                }
            }

            // go into restrict state, when the customer has overdue bill and the account is in default
            if (customer.getAccount().getState() == Account.State.Default && customer.isHasOverDueBill()) {
                // do something
                System.out.println("Now should go into the restrict state, CAREFUL. if you do not behave appropriately, your account would be closed");
                // if the user has no overdue history
                if (!customer.isHasOverDueHistory()) {
                    // enter the Grace period
                    customer.getAccount().GracePeriod();
                    customer.setHasOverDueHistory(true);
                } else {
                    // enter the plan offered state
                    customer.getAccount().PlanOffered();
                    // Charge a $20 fee, offer a plan
                }
            }

            // handle the grace period state

            // handle the plan offered state

            if (isInput2ReportCardStolen(userinput2)){
                customer.reportLostCard();
            }else if (isInput2Active(userinput2)){
                customer.activeCreditCard();
            }else if (isInput2Consuming(userinput2)){
                customer.useCard();
            }else if (isInput2CreditChecking(userinput2)){
                customer.getAccount().creditChecking();
            }else if (isInput2PayBill(userinput2)) {
                if (customer.getCreditCard().isActive() == false && customer.getAccount().getState() == Account.State.Pending) {
                    System.out.println("Please active credit card first!!");
                } else {
                    System.out.println("Each time you are going to pay 400 dollar for a bill.");
                    customer.payBill(Double.parseDouble("400"));
                }
            }else if (isInput2CheckState(userinput2)){
                System.out.println(String.format("Current state of the account is %s", customer.getAccount().getState()));
            }else if (isInput2BillChecking(userinput2)){
                customer.getAccount().checkingBill(System.currentTimeMillis());
            }

            System.out.println("======================Command Window================================");
            System.out.println("What would you like to do now?");
            System.out.println(User_Input_Command_Panel_2);
            System.out.println("====================================================================");
            System.out.print("Please enter: ");


            userinput2 = getUserInput2(scanner);
        }
    }


    private static boolean input_is_valid(String input){
        return (input.equals("0") || input.equals("1") || input.equals("2"));
    }

    private static boolean input_is_valid2(String input){
        return (input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")|| input.equals("6")|| input.equals("7"));
    }

    private static boolean is_input_yes(String in){
        return in.equals("1");
    }



    private static boolean isInput2Exit(String in){
        return in.equals("0");
    }

    private static boolean isInput2CheckState(String in){
        return in.equals("6");
    }

    private static boolean isInput2Active(String in){
        return in.equals("3");
    }

    private static boolean isInput2ReportCardStolen(String in){
        return in.equals("1");
    }

    private static boolean isInput2Consuming(String in){
        return in.equals("2");
    }

    private static boolean isInput2CreditChecking(String in){
        return in.equals("4");
    }
    private static boolean isInput2BillChecking(String in){
        return in.equals("7");
    }

    private static boolean isInput2PayBill(String in){
        return in.equals("5");
    }

    private static String getUserInput(Scanner scanner){
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
        return userinput;
    }

    private static String getUserInput2(Scanner scanner){
        String userinput = scanner.nextLine();
        while (!input_is_valid2(userinput)){
            if (!userinput.equals("0") || !userinput.equals("1") ||
                    !userinput.equals("2") || !userinput.equals("3")){
                System.out.println("Warning!" + User_Input_Command_Panel_2);
            }

            userinput = scanner.nextLine();
        }

        if (userinput.equals("0")){
            System.out.println(Say_Goodbye);
            System.exit(0);
        }
        return userinput;
    }


    private static Customer handleCustomerCreation(Scanner scanner){
        System.out.println("Before creating an account, you first have to register as a customer.");
        System.out.println("Please enter your name: ");
        String usernmae = scanner.nextLine();

        Customer customer = new Customer(usernmae);
        return customer;
    }

    private static void handleAccountCreation(Scanner scanner, Customer customer){
        System.out.println("Would you like to apply for your account now? ");
        System.out.println(User_Input_Command_Panel);
        String userinput = getUserInput(scanner);
        if (is_input_yes(userinput)){
            customer.applyForAccount();
        }else{
            System.out.println(Say_Goodbye);
            System.exit(0);
        }
    }
}

