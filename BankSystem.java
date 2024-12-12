import java.util.Scanner;

// to do list
    // variable renaming
    // forgotten password
    // phone number format verification
    // loan max, account.
public class BankSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        long[] accountNumbers = new long[100];
        long[] balance = new long[100];
        String[] passwords = new String[100];
        String[] names = new String[100];
        String[] lastNames = new String[100];
        String[] genders = new String[100];
        long[] phoneNumbers = new long[100];

        int choice;
        int stay = 1;
        int numberOfAccounts = 0;
        int numOfTransaction=0;

        while (stay != 0) {
            System.out.println();
            System.out.println("Welcome to Abu Dhabi Islamic Bank");
            System.out.println("Enter 1 to login");
            System.out.println("Enter 2 to create new account: ");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice == 2) {
                System.out.print("Enter your name: ");
                names[numberOfAccounts] = input.next();
                System.out.print("Enter your last name: ");
                lastNames[numberOfAccounts] = input.next();
                boolean isCorrect = false;
                while (!isCorrect) {
                    System.out.print("Enter your gender: ");
                    String gender= input.next();
                    if ( gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
                        genders[numberOfAccounts] = gender.toLowerCase();
                        isCorrect =true;
                    }
                    else
                        System.out.println("Please enter either male or female!");
                }

                System.out.print("Enter your telephone number: ");
                phoneNumbers[numberOfAccounts] = input.nextLong();

                boolean isRejected = true;
                while (isRejected) {
                    System.out.print("Enter at least 8 digit password(containing capital, small letters and digits): ");
                    String password = input.next();

                    boolean containsUpperCase = false, containsLowerCase = false, containsDigit = false;
                    int m = 0;
                    while (m < password.length()) {
                        if (Character.isLowerCase(password.charAt(m))) {
                            containsLowerCase = true;
                        } else if (Character.isUpperCase(password.charAt(m))) {
                            containsUpperCase = true;
                        } else if (Character.isDigit(password.charAt(m))) {
                            containsDigit = true;
                        } else {
                            System.out.println("Rejected as It contains special characters");
                        }
                        m++;

                    }
                    if (containsDigit && containsUpperCase && containsLowerCase && password.length() >= 8) {
                        passwords[numberOfAccounts] = password;
                        isRejected = false;
                    } else
                        System.out.println("Rejected as it doesn't full fill the requirement");

                }
                long accountNumber = (long) (Math.random() * 1000000 + 100000);
                accountNumbers[numberOfAccounts] = accountNumber;
                System.out.println("Save your password! Here is your account number is: " + accountNumber);

                System.out.print("Please enter the opening amount to be deposited: ");
                balance[numberOfAccounts] = input.nextLong();
                System.out.println("Congrats! your account has been created successfully!!!");
                System.out.println(balance[numberOfAccounts] + " AED was credited to your account.Now your balance is "
                        + balance[numberOfAccounts] + " AED");
                numOfTransaction++;
                numberOfAccounts++;

                boolean isNotValid = true;
                while (isNotValid) {
                    System.out.print("Do you want to continue banking?(yes or no): ");
                    String selection = input.next();
                    if (selection.equals("no")) {
                        stay = 0;
                        isNotValid = false;

                    } else if (selection.equals("yes"))
                        isNotValid = false;
                    else
                        System.out.println("please enter the correct option!");

                }

            } else if (choice == 1) {
                boolean isallowed = false;
                while (!isallowed) {
                    System.out.print("Enter your username: ");
                    String userName = input.next();
                    System.out.print("Enter you password: ");
                    String userPassword = input.next();

                    int accountLocation = 0, r, l;
                    boolean isNameExist = false;
                    boolean isPasswordExist = false;

                    for (r = 0; r < names.length; r++) {
                        if (String.valueOf(names[r]).equals(userName)) {
                            accountLocation = r;
                            isNameExist = true;
                            break;
                        }
                    }
                    for (l = 0; l < names.length; l++) {
                        if (String.valueOf(passwords[l]).equals(userPassword)) {
                            isPasswordExist = true;
                            break;
                        }
                    }

                    if (isNameExist && isPasswordExist && r == l) {
                        int action = 1;
                        while (action != 0) {
                            System.out.println("Enter 1 to view balance: ");
                            System.out.println("Enter 2 to deposit money: ");
                            System.out.println("Enter 3 to withdraw money: ");
                            System.out.println("Enter 4 to transfer money: ");
                            System.out.println("Enter 5 to delete account: ");
                            System.out.println("Enter 6 to apply for loan: ");
                            System.out.print("Enter your choice: ");
                            action = input.nextInt();

                            if (action == 1) {
                                System.out.println("Your current balance is " + balance[accountLocation] + " AED");
                                numOfTransaction++;
                            } else if (action == 2) {
                                System.out.print("Enter the amount you want to deposit: ");
                                long cashIn = input.nextLong();
                                balance[accountLocation] += cashIn;
                                System.out.println(cashIn + " was credited to your account. Now your balance is "
                                        + balance[accountLocation] + " AED");
                                numOfTransaction++;
                            } else if (action == 3) {
                                System.out.print("Enter the amount you want to withdraw: ");
                                long cashOut = input.nextLong();
                                if (cashOut <= balance[0]) {
                                    balance[accountLocation] -= cashOut;
                                    System.out.println(cashOut + " was debited to your account.Now your balance is "
                                            + balance[accountLocation] + " AED");
                                    numOfTransaction++;

                                } else {
                                    System.out.println("Opps! Your balance is insufficient to finish the transaction");
                                }
                            } else if (action == 4) {
                                System.out.print("Enter the account number that you want to transfer: ");
                                long receiverAccount = input.nextLong();

                                int accountIndex = 0;
                                boolean isThereAccount = false;
                                for (int k = 0; k < accountNumbers.length; k++) {
                                    if (accountNumbers[k] == receiverAccount) {
                                        accountIndex = k;
                                        isThereAccount = true;
                                    }

                                }
                                if (isThereAccount) {
                                    System.out.println("Enter the amount that you want to transfer: ");
                                    long transferAmount = input.nextLong();
                                    if (balance[accountLocation] >= transferAmount) {
                                        balance[accountIndex] += transferAmount;
                                        balance[accountLocation] -= transferAmount;
                                        System.out.println(
                                                transferAmount + " was transferred to " + names[accountLocation]
                                                        + ".Now your available balance is " + balance[accountLocation]);
                                        numOfTransaction++;
                                    } else {
                                        System.out.println(
                                                "Opps! Your balance is insufficient to finish the transaction");
                                    }

                                } else {
                                    System.out.println("please enter a correct account number");
                                }
                            } else if (action == 5) {
                                accountNumbers[accountLocation] = 0;
                                names[accountLocation] = "";
                                passwords[accountLocation] = "";
                                genders[accountLocation] = "";
                                phoneNumbers[accountLocation] = 0;
                                balance[accountLocation] = 0;
                                System.out.println("Your account is deleted successfully!!");
                                action = 0;// logs out the user as the account is already deleted
                            }

                            else if(action==6){
                                if(numOfTransaction < 3 || balance[accountLocation] < 1000){
                                    System.out.println("Sorry you are not eligible for these service. Check your transaction history it has to be at least 3");
                                }
                                else{
                                    System.out.println("Thank you for applying for loan.Enter the amount that you want for a loan? ");
                                    long loan = input.nextLong();
                                    balance[accountLocation] += loan;
                                    System.out.println(loan + " of a loan was credited to your account. Now your balance is "
                                            + balance[accountLocation] + " AED");
                                }
                            }
                            else {
                                System.out.println("Please Enter a correct choice!!");
                            }

                            boolean isNotValid = true;
                            while (isNotValid) {
                                System.out.print("Do you want to logout?(yes or no): ");
                                String selection = input.next();
                                if (selection.equals("no")) {
                                    isNotValid = false;
                                } else if (selection.equals("yes")) {
                                    isNotValid = false;
                                    action = 0;
                                } else
                                    System.out.println("please enter the correct option!");
                            }

                        }
                        isallowed = true;

                    } else {
                        System.out.println("Invalid user!,either your name or password is incorrect, try again!");
                        System.out.print("Enter 1 to see the menu and enter other number to try again: ");
                        int alt = input.nextInt();
                        if (alt == 1)
                            isallowed = true;
                    }
                }

            } else {
                System.out.println("Please Enter a correct choice!!");
            }
        }
    }
}
