import java.util.Scanner;
public class TelephoneCompany {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Gather account information
        System.out.print("Enter the Account number = ");
        int accNum = input.nextInt();
        System.out.print("Enter Customer name = ");
        String name = input.next() + input.nextLine();
        System.out.print("Enter the phone number = ");
        String phone = input.next() + input.nextLine();

        // Create arrays to store call and SMS information
        int[] callMinutesArray = new int[100];
        String[] callPhoneDestinationArray = new String[100];
        double[] callChargeArray = new double[100];
        String[] smsPhoneDestinationArray = new String[100];
        double[] smsChargeArray = new double[100];

        boolean proceed = true;
        int choice, callIndex = 0, smsIndex = 0;
        double monthlyFees = 10.00;

        while (proceed) {
            // Display menu and gather user choice
            choice = displayMenu();
            switch (choice) {
                case 1:
                    // Gather call information and calculate call charge
                    double callCharge = gatherCallInformation(input, callMinutesArray, callPhoneDestinationArray, callChargeArray, callIndex);
                    System.out.println("Current Call Charge = " + callCharge);
                    callIndex++;
                    break;

                case 2:
                    // Gather SMS information and calculate SMS charge
                    double smsCharge = gatherSMSInformation(input, smsPhoneDestinationArray, smsChargeArray, smsIndex);
                    smsIndex++;
                    break;
                case 3:
                    System.out.println("Num\t PhoneNumber\t Minutes \t Charge\t\t Type");
                    System.out.println("============================================================");
                    for (int i = 0; i < callIndex; i++) {
                        System.out.printf("%d\t %-15s %-3d\t\t %-6.2f\t\t %s\n", (i + 1), callPhoneDestinationArray[i], callMinutesArray[i], callChargeArray[i], "Call");
                    }
                    for (int i = 0; i < smsIndex; i++) {
                        System.out.printf("%d\t %-15s\t\t %-3.2f\t\t %s\n", (i + callIndex + 1), smsPhoneDestinationArray[i], smsChargeArray[i], "SMS");
                    }
                    break;
                case 4:
                    // Exit the while loop
                    proceed = false;
                    break;
            }
        }
        // Display bill information
        displayBillInformation(accNum, name, phone, callMinutesArray, callChargeArray, smsChargeArray, monthlyFees);
    }

    // Method to display the menu and gather user choice
    public static int displayMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("====");
        System.out.println("1-Call");
        System.out.println("2-SMS");
        System.out.println("3-Bill List");
        System.out.println("4-Exit");
        System.out.print("Your Choice = ");
        int choice = input.nextInt();
        return choice;
    }

    // Method to gather call information and calculate call charge
    public static double gatherCallInformation(Scanner input, int[] callMinutesArray, String[] callPhoneDestinationArray, double[] callChargeArray, int index) {
        System.out.print("Enter the minutes for call = ");
        int minutes = input.nextInt();
        callMinutesArray[index] = minutes;
        System.out.print("Enter the destination phone = ");
        String phoneDestination = input.next()+input.nextLine();
        callPhoneDestinationArray[index] = phoneDestination;
        double callCharge = 0.0;
        if (phoneDestination.charAt(2)== '9') {
            callCharge = minutes * 0.1;
        } else {
            callCharge = minutes * 0.2;
        }
        callChargeArray[index] = callCharge;
        return callCharge;
    }

    // Method to gather SMS information and calculate SMS charge
    public static double gatherSMSInformation(Scanner input, String[] smsPhoneDestinationArray, double[] smsChargeArray, int index) {
        System.out.print("Enter the destination phone = ");
        String phoneDestination = input.next()+input.nextLine();
        smsPhoneDestinationArray[index] = phoneDestination;
        double smsCharge = 0.05;
        smsChargeArray[index] = smsCharge;
        return smsCharge;
    }

    // Method to display bill information
    public static void displayBillInformation(int accNum, String name, String phone, int[] callMinutesArray, double[] callChargeArray, double[] smsChargeArray, double monthlyFees) {
        double totalCallCharge = 0, totalSMSCharge = 0;
        for (int i = 0; i < callChargeArray.length; i++) {
            totalCallCharge += callChargeArray[i];
        }
        for (int i = 0; i < smsChargeArray.length; i++) {
            totalSMSCharge += smsChargeArray[i];
        }
        double totalBill = totalCallCharge + totalSMSCharge + monthlyFees;
        System.out.println("\nBill Information");
        System.out.println("===============");
        System.out.println("Account Number: " + accNum);
        System.out.println("Customer Name: " + name);
        System.out.println("Phone Number: " + phone);
        System.out.println("Monthly Fees: " + monthlyFees);
        System.out.println("Total Call Charge: " + totalCallCharge);
        System.out.println("Total SMS Charge: " + totalSMSCharge);
        System.out.println("Total Bill: " + totalBill);
    }
}