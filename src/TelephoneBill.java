//NUR UMAIRAH BINTI SHAMSUL
//292355

import java.util.Scanner;
public class TelephoneBill 
{
    public static void main(String[] args)
    {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the Account number = ");
    int accNum = input.nextInt();
    System.out.print("Enter Customer name = ");
    String name = input.next()+input.nextLine();
    System.out.print("Enter the phone number = ");
    String phone = input.next()+input.nextLine();
    
    boolean proceed = true;
    int choice,minutes,totalMinutes=0;
    String phoneDestination;
    double callCharge,smsCharge,totalCallCharge=0,totalSmsCharge=0;
    double monthlyFees = 10.00;
    while (proceed)
    {
         System.out.println("Menu");
         System.out.println("====");
         System.out.println("1-Call");
         System.out.println("2-SMS");
         System.out.println("3-Exit");
         System.out.print("Your Choice = ");
         choice = input.nextInt();
         
         if (choice == 1)
         {
             System.out.print("Enter the minute call = ");
             minutes = input.nextInt();
             System.out.print("Enter the destination phone = ");
             phoneDestination = input.next()+input.nextLine();
             if (phoneDestination.charAt(2)== '9')
             {
                 callCharge = minutes *0.1;
                 System.out.print("Current call charge = "+callCharge);
             }
             else
             {
                 callCharge = minutes * 0.2;
                 System.out.println("Current call charge = "+callCharge);
             }
             totalMinutes = totalMinutes + minutes;
             totalCallCharge = totalCallCharge + callCharge;
         }
         else if (choice == 2)
         {
             System.out.print("Enter the destination phone = ");
             phoneDestination = input.next()+input.nextLine();
             if (phoneDestination.charAt(2)== '9')
                 smsCharge = 0.05;
             else
                 smsCharge = 0.10;
             totalSmsCharge = totalSmsCharge + smsCharge;
         }
         else
             proceed = false;
         System.out.println("");
    }//while
    System.out.println("\nTelephone Bill");
    System.out.println("\nAccount number   : "+accNum);
    System.out.println("Name               : "+name);
    System.out.println("Phone number       : "+phone);
    System.out.printf("The total of call minutes   : %2d minutes%n",totalMinutes);
    System.out.printf("The cost of total call      : RM%5.2f%n",totalCallCharge);
    System.out.printf("The cost of SMS             : RM%5.2f%n",totalSmsCharge);
    System.out.printf("The monthly rent fees       : RM%5.2f%n",monthlyFees);
    
    double totalCharge = monthlyFees + totalCallCharge + totalSmsCharge;
    
    System.out.printf("The total                   : RM%5.2f%n",totalCharge);
    
    double serviceTax = 0.05 * totalCharge;
    System.out.printf("The service tax (5)         : RM%5.2f%n",serviceTax);
    
    double chargedBill = totalCharge + serviceTax;
    System.out.printf("The total charged bill      : RM%5.2f%n",chargedBill);
    
    }
}
