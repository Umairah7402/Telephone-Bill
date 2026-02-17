import java.util.Scanner;
public class TelephoneBill_2
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        // Gather account information
        System.out.print("Enter the Account number = ");
        int accNum = input.nextInt();
        System.out.print("Enter Customer name = ");
        String name = input.next()+input.nextLine();
        System.out.print("Enter the phone number = ");
        String phone = input.next()+input.nextLine();

        // Create arrays to store call and SMS information
        int[] minutesArray = new int[100];
        String[] phoneDestinationArray = new String[100];
        double[] callChargeArray = new double[100];
        double[] smsChargeArray = new double[100];

        boolean proceed = true;
        int choice, minutesIndex = 0, smsIndex = 0;
        double monthlyFees = 10.00;

        while (proceed)
        {
            // Display menu and gather user choice
            choice = displayMenu();
            switch(choice) {
                case 1:
                    // Gather call information and calculate call charge
                    double callCharge = gatherCallInformation(input, minutesArray, phoneDestinationArray, callChargeArray, minutesIndex);
                    minutesIndex++;
                    break;
                case 2:
                    // Gather SMS information and calculate SMS charge
                    double smsCharge = gatherSMSInformation(input, phoneDestinationArray, smsChargeArray, smsIndex);
                    smsIndex++;
                    break;
                case 3:
                    // Exit the while loop
                    proceed = false;
                    break;
            }
        }
        // Display bill information
        displayBillInformation(accNum, name, phone, minutesArray, callChargeArray, smsChargeArray, monthlyFees);
    }

    // Method to display the menu and gather user choice
    public static int displayMenu() {
        Scanner input = new Scanner(System.in);
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
    public static double gatherCallInformation(Scanner input, int[] minutesArray, String[] phoneDestinationArray, double[] callChargeArray, int index) {
        System.out.print("Enter the minutes for call = ");
        int minutes = input.nextInt();
        minutesArray[index] = minutes;
        System.out.print("Enter the destination phone = ");
        String phoneDestination = input.next()+input.nextLine();
        phoneDestinationArray[index] = phoneDestination;
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
    public static double gatherSMSInformation(Scanner input, String[] phoneDestinationArray, double[] smsChargeArray, int index) {
        System.out.print("Enter the destination phone = ");
        String phoneDestination = input.next()+input.nextLine();
        phoneDestinationArray[index] = phoneDestination;
        double smsCharge = 0.0;
        if (phoneDestination.charAt(2)== '9') {
            smsCharge = 0.05;
        } else {
            smsCharge = 0.10;
        }
        smsChargeArray[index] = smsCharge;
        return smsCharge;
    }

    // Method to display the bill information
    public static void displayBillInformation(int accNum, String name, String phone, int[] minutesArray, double[] callChargeArray, double[] smsChargeArray, double monthlyFees) {
        int totalMinutes = 0;
        double totalCallCharge = 0.0, totalSmsCharge = 0.0;
        for (int i = 0; i < minutesArray.length; i++) {
            totalMinutes += minutesArray[i];
            totalCallCharge += callChargeArray[i];
            totalSmsCharge += smsChargeArray[i];
        }
        System.out.println("\nTelephone Bill");
        System.out.println("\nAccount number : "+accNum);
        System.out.println("Name : "+name);
        System.out.println("Phone number : "+phone);
        System.out.printf("The total of call minutes : %2d minutes%n", totalMinutes);
        System.out.printf("The cost of total call : RM%5.2f%n", totalCallCharge);
        System.out.printf("The cost of SMS : RM%5.2f%n", totalSmsCharge);
        System.out.printf("The monthly rent fees : RM%5.2f%n", monthlyFees);
        double totalCharge = monthlyFees + totalCallCharge + totalSmsCharge;
        System.out.printf("The total                   : RM%5.2f%n", totalCharge);

        double serviceTax = 0.05 * totalCharge;
        System.out.printf("The service tax             : RM%5.2f%n", serviceTax);

        double grandTotal = totalCharge + serviceTax;
        System.out.printf("The grand total             : RM%5.2f%n", grandTotal);
    }
}