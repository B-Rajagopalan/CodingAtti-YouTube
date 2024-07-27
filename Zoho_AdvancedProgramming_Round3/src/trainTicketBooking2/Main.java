package trainTicketBooking2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        TicketBooking t1 = new TicketBooking('A','D',8);
//
//        //TicketBooking t2 = new TicketBooking('A','D',3);
//
//        TicketBooking t3 = new TicketBooking('D','E',1);
//
//        TicketCanceling t4 = new TicketCanceling(1,5);
//
//        TicketBooking t5 = new TicketBooking('A','C',5);
//
//        TicketBooking t6 = new TicketBooking('C','E',5);
//
//        t1.execute();
//        t3.execute();
//        t4.execute();
//        t5.execute();
//        t6.execute();
//        //t1.execute();
//
//
//        TicketSystem.printChart();

        while(true) {
            System.out.println(" 1. Book Ticket\n 2. Cancel Ticket\n 3. Print chart\n Choose any one");
            Scanner sc =  new Scanner(System.in);
            int option = sc.nextInt();

            switch(option) {
                case 1 -> {
                    System.out.println("Enter the source : ");
                    char source = sc.next().charAt(0);
                    System.out.println("Enter the destination : ");
                    char destination = sc.next().charAt(0);
                    System.out.println("Enter no of seats to book : ");
                    int seats = sc.nextInt();

                    if(source != destination) {
                        TicketBooking booking = new TicketBooking(source, destination, seats);
                        booking.execute();
                    }
                    else {
                        System.out.println("I handled this scenario. Try again");
                    }
                    break;
                }
                case 2 -> {
                    System.out.println("Enter the pnr number : ");
                    int pnr = sc.nextInt();
                    System.out.println("Enter no of seats to cancel : ");
                    int seats = sc.nextInt();

                    TicketCanceling canceling = new TicketCanceling(pnr, seats);
                    canceling.execute();
                    break;
                }
                case 3 -> {
                    TicketSystem.getInstance().printChart();
                    break;
                }
                default -> {
                    System.out.println("Unfortunately Stopped!!");
                    break;
                }
            }
        }
    }
}