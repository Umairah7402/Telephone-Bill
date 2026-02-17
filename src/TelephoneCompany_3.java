import java.util.Scanner;

public class TelephoneCompany_3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int accNum, callIndex = 0, smsIndex = 0;
        String name, phone;
        double monthlyFees = 10;

        int[] callMinutesArray = new int[100];
        double[] callChargeArray = new double[100];
        double[] smsChargeArray = new double[100];
        String[] callPhoneDestinationArray = new String[100];
        String[] smsPhoneDestinationArray = new String[100];

        System.out.print("Enter Account Number = ");
        accNum = input.nextInt();
        input.nextLine();
        System.out.print("Enter Name = ");
        name = input.nextLine();
        System.out.print("Enter Phone Number = ");
        phone = input.nextLine();

        int choice;
        do {
            System.out.println("\n\n1. Phone Call");
            System.out.println("2. SMS");
            System.out.println("3. Exit");
            System.out.print("\nEnter your choice = ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    double callCharge = gatherCallInformation(callPhoneDestinationArray,callMinutesArray, callChargeArray , callIndex);
                    System.out.printf("Current Call Charge = RM %.2f", callCharge);
                    callIndex++;
                    break;
                case 2:
                    gatherSMSInformation(smsPhoneDestinationArray, smsChargeArray, smsIndex);
                    double smsCharge = smsChargeArray[smsIndex];
                    System.out.printf("Current Call Charge = RM %.2f", smsCharge);
                    smsIndex++;

                    break;
                case 3:
                    displayBillInformation(accNum, name, phone, callMinutesArray, callChargeArray, smsChargeArray, monthlyFees, callPhoneDestinationArray, smsPhoneDestinationArray);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    // Method to display the menu and gather user choice
    public static int displayMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("");
        System.out.println("Menu");
        System.out.println("====");
        System.out.println("1-Call");
        System.out.println("2-SMS");
        System.out.println("3-Exit");
        System.out.print("Your Choice = ");
        int choice = input.nextInt();
        return choice;
    }

    // Method to gather call information and calculate call charge
    public static double gatherCallInformation(String[] callPhoneDestinationArray, int[] callMinutesArray, double[] callChargeArray, int index) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the destination phone = ");
        String phoneDestination = input.next()+input.nextLine();
        callPhoneDestinationArray[index] = phoneDestination;
        System.out.print("Enter the duration of call (in minutes) = ");
        int callDuration = input.nextInt();
        callMinutesArray[index] = callDuration;
        double callCharge = 0.0;
        if (phoneDestination.charAt(2)== '9'){
            callCharge = callDuration * 0.05;
        } else {
            callCharge = callDuration * 0.10;
        }
        callChargeArray[index] = callCharge;
        return callCharge;
    }

    // Method to gather SMS information and calculate SMS charge
    public static double gatherSMSInformation(String[] smsPhoneDestinationArray, double[] smsChargeArray, int index) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the destination phone = ");
        String phoneDestination = input.next()+input.nextLine();
        smsPhoneDestinationArray[index] = phoneDestination;
        double smsCharge = 0.0;
        if (phoneDestination.charAt(2)== '9'){
            smsCharge = 0.05;
        } else {
            smsCharge = 0.10;
        }
        smsChargeArray[index] = smsCharge;
        return smsCharge;
    }

    // Method to display bill information
    public static void displayBillInformation(int accNum, String name, String phone, int[] callMinutesArray, double[] callChargeArray,
                                              double[] smsChargeArray, double monthlyFees, String[] callPhoneDestinationArray, String[] smsPhoneDestinationArray) {

        double totalCallCharge = 0, totalSMSCharge = 0;
        int totalMinutes = 0, callIndex = 0, smsIndex=0;
        for (int i = 0; i < callChargeArray.length; i++) {
            totalCallCharge += callChargeArray[i];
            if (callChargeArray[i] != 0.0) {
                callIndex++;
            }
        }
        for (int i = 0; i < smsChargeArray.length; i++) {
            totalSMSCharge += smsChargeArray[i];
            if (smsChargeArray[i] != 0.0) {
                smsIndex++;
            }
        }
        for (int i = 0; i < callMinutesArray.length; i++) {
            totalMinutes += callMinutesArray[i];
        }
        System.out.println("\nTelephone Bill");
        System.out.println("");
        System.out.println("Account Number: " + accNum);
        System.out.println("Customer Name: " + name);
        System.out.println("Phone Number: " + phone);
        System.out.println("");

        System.out.println("A list of phone call");
        System.out.println("-----------------------------------------");
        System.out.println("Num\t PhoneNumber\t Minutes \t Charge");
        System.out.println("-----------------------------------------");

        for (int i = 0; i < callIndex; i++) {
            System.out.printf("%d\t %-15s %-3d\t\t %-6.2f\n", (i + 1), callPhoneDestinationArray[i], callMinutesArray[i], callChargeArray[i]);
        }

        System.out.println("");
        System.out.printf("The total of call minutes   : %2d minutes%n",totalMinutes);
        System.out.printf("The cost of total call      : RM%5.2f%n",totalCallCharge);
        System.out.println("");

        System.out.println("A list of phone SMS");
        System.out.println("-----------------------------------------");
        System.out.println("Num\t PhoneNumber \t Charge");
        System.out.println("-----------------------------------------");

        for (int i = 0; i < smsIndex; i++) {
            System.out.printf("%d\t %-13s\t %-10.2f\n", (i + 1), smsPhoneDestinationArray[i], smsChargeArray[i]);
        }


        System.out.printf("\n\nThe cost of SMS             : RM%5.2f%n",totalSMSCharge);
        System.out.printf("The monthly rent fees       : RM%5.2f%n",monthlyFees);
        double totalCharge = monthlyFees + totalCallCharge + totalSMSCharge;
        System.out.printf("The total                   : RM%5.2f%n",totalCharge);

        double serviceTax = 0.05 * totalCharge;
        System.out.printf("The service tax (5)         : RM%5.2f%n",serviceTax);

        double chargedBill = totalCharge + serviceTax;
        System.out.printf("The total charged bill      : RM%5.2f%n",chargedBill);
    }
}