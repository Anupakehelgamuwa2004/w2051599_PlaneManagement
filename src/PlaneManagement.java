import java.util.*;
import java.io.File;

public class PlaneManagement {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // array define
        int[][] Seats = new int[4][];
        Seats[0] = new int[14];
        Seats[1] = new int[12];                 // seats array
        Seats[2] = new int[12];                 // hold numbers of seat rows and columns
        Seats[3] = new int[14];

        int[][] tickets_booking = new int[4][];
        tickets_booking[0] = new int[14];
        tickets_booking[1] = new int[12];       // ticket array
        tickets_booking[2] = new int[12];
        tickets_booking[3] = new int[14];

        // person information array
        Person[][] Information_of_passenger = new Person[Seats.length][Seats[0].length];
        // task1
        System.out.println("Welcome to Plane Management application");
        // task2
        // loop define
        boolean user_will = true;
        while (user_will) {
            System.out.println();

            System.out.println("*******************************************************");
            System.out.println("*                  MENU OPTION                        *");
            System.out.println("*******************************************************");
            System.out.println();

            System.out.println("      Buy a seat");
            System.out.println("     2) Cancel a seat");
            System.out.println("     31)) Find first available seat");
            System.out.println("     4) Show seating plan");
            System.out.println("     5) Print tickets information and total sales");
            System.out.println("     6) Search ticket");
            System.out.println("     0) Quit");
            System.out.println("*******************************************************");
            System.out.println();
            // Print menu options
            try {
                System.out.print("Please select an option: ");
                int User_choice = input.nextInt();
                //
                switch (User_choice) {
                    case 0:
                        user_will = false;
                        break;
                    case 1:
                        buy_seat(input, Seats, tickets_booking, Information_of_passenger);
                        break;
                    case 2:
                        cancel_seat(input, Seats, tickets_booking);
                        break;
                    case 3:
                        find_first_available(Seats);
                        break;
                    case 4:
                        Show_seating_plan(Seats);
                        break;
                    case 5:
                        print_ticket_info(tickets_booking);
                        break;
                    case 6:
                        search_ticket(input, Seats, tickets_booking, Information_of_passenger);
                        break;
                    default:
                        System.out.println("Invalid option. Please enter between 0 to 6 to access menu .");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number between 0 to 6");    // error handel with invalid input
                input.next();
            }
        }
    }
    /* to determine the price of each seat
       Seat_number is a variable which used to determine ticket price
       return the price of seat
     */
    public static int Ticket_prices(int seat_number) {
        int ticket_price = -1;
        if (seat_number <= 5) {
            ticket_price = 200;
        } else if (seat_number <= 9) {
            ticket_price = 150;
        } else {
            ticket_price = 180;
        }
        return ticket_price;
    }

    //task 3
    /*
    perform all the logics and allow user to buy a seat
    prompt user to "Enter row letter (A-D) : " and seat number
    check if the seat available if it available then book the seat
    collect passenger information and display ticket price and save
    (parameters)
    input : getting input form user
    Seats : 2D array show the availability of plane seats
    tickets_booking: 2D array show the ticket prices
    Information_of_passenger: 2D array show passenger information
     */
    public static void buy_seat(Scanner input, int[][] Seats, int[][] tickets_booking, Person[][] passenger_information) {
        boolean row_validate = true;
        String row_letter = "";
        int row_number = 100;
        while (row_validate) {  // loop to get user input for row_letter
            System.out.print("Enter row letter (A-D) : ");
            row_letter = input.next().toUpperCase();
            String regex = "[A-D]";
            if (row_letter.matches(regex)) {
                row_validate = false;
            } else {
                System.out.println("Please enter only  'A' 'B' 'C' 'D' as Row letter ");
            }
        }
        // determine row index  based on the row letter
        switch (row_letter) {
            case "A":
                row_number = 0;
                break;
            case "B":
                row_number = 1;
                break;
            case "C":
                row_number = 2;
                break;
            case "D":
                row_number = 3;
                break;
        }

        boolean column_validate = true;
        int seat_number = 100;
        while (column_validate) {  // loop to get user input for seat_number
            try {
                if (row_number == 0 || row_number == 3) {
                    System.out.print("Please enter seat number between 1 to 14 :");
                    seat_number = input.nextInt();
                    if (seat_number >= 1 && seat_number <= 14) {
                        column_validate = false;
                    } else {
                        System.out.println("Please enter a number between 1 to 14 only");
                        continue;
                    }
                }
                if (row_number == 1 || row_number == 2) {
                    System.out.print("please enter seat number only between 1 to 12 :");
                    seat_number = input.nextInt();
                    if (seat_number >= 1 && seat_number <= 12) {
                        column_validate = false;
                    } else {
                        System.out.println("Please enter a number between 1 to 12 only ");
                        continue;
                    }
                }
                //booking seat if it's available
                if (Seats[row_number][seat_number - 1] == 0) {
                    Seats[row_number][seat_number - 1] = 1;
                    // getting inputs First_name, surname, email from user
                    String First_name = "";
                    String surname = "";
                    String email = "";
                    System.out.print("Please Enter your First name : ");
                    First_name = input.next();
                    System.out.print("Please Enter your surname : ");
                    surname = input.next();
                    System.out.print("Please Enter your Email : ");
                    email = input.next();
                    System.out.println("You successfully booked your seat ");


                    String Seat = row_letter + seat_number;
                    int ticket_price = Ticket_prices(seat_number);
                    tickets_booking[row_number][seat_number - 1] = ticket_price;

                    Person passenger = new Person(First_name, surname, email);
                    passenger_information[row_number][seat_number - 1] = passenger;

                    Ticket ticket = new Ticket(row_letter, Seat, ticket_price, passenger);
                    ticket.print_ticket();

                    String Save_path = Seat+".txt";
                    ticket.Save(Save_path);
                } else {
                    System.out.println("Sorry seat is already booked ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter only number");
                input.next();
            }
        }
    }

    //task 4
    /*
        allow user to cancel a seat which he already booked
        prompt user to "Enter row letter (A-D) : " and seat number
        check the seat is booked if the seat is booked,cancel the booking
        and update the seat array and ticket_booking array
        (parameters)
        input: getting inputs from user
        Seats: 2D array show the availability of plane seats
        tickets_booking :2D array show the ticket prices according to seat booking

     */
    public static void cancel_seat(Scanner input, int[][] Seats, int[][] tickets_booking) {
        boolean row_validate = true;
        String row_letter = "";
        int row_number = 100;
        while (row_validate) {          // loop to get user input for row_letter
            System.out.print("Enter row letter  (A-D) :");
            row_letter = input.next().toUpperCase();
            String regex = "[A-D]";
            if (row_letter.matches(regex)) {
                row_validate = false;
            } else {
                System.out.println("Please enter only  'A' 'B' 'C' 'D' as Row letter");
            }
        }
        // determine row index  based on the row letter
        switch (row_letter) {
            case "A":
                row_number = 0;
                break;
            case "B":
                row_number = 1;
                break;
            case "C":
                row_number = 2;
                break;
            case "D":
                row_number = 3;
                break;
        }

        boolean column_validate = true;
        int seat_number = 100;
        String delete_file=" ";
        while (column_validate) {           // loop to get user input for seat_number
            try {
                if (row_number == 0 || row_number == 3) {
                    System.out.print("Please Enter seat number between 1 to 14 :");
                    seat_number = input.nextInt();
                    if (seat_number >= 1 && seat_number <= 14) {
                        column_validate = false;
                    } else {
                        System.out.println("Please enter a number between 1 to 14 only");
                        continue;
                    }
                }
                if (row_number == 1 || row_number == 2) {
                    System.out.print("lease Enter seat number only between 1 to 12 :");
                    seat_number = input.nextInt();
                    if (seat_number >= 1 && seat_number <= 12) {
                        column_validate = false;
                    } else {
                        System.out.println("Please enter a number between 1 to 12 only ");
                        continue;
                    }
                }
                // cancelling seat if its booked
                if (Seats[row_number][seat_number - 1] == 1) {
                    Seats[row_number][seat_number - 1] = 0;
                    tickets_booking[row_number][seat_number - 1] = 0;
                    System.out.println("You successfully cancel your seat ");

                } else {
                    System.out.println("Sorry, the seat is not booked ");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter only number");
                input.next();
            }
        }
        String file_identity=row_letter+seat_number+".txt";
        File my_file=new File(file_identity);
        if(my_file.exists()){
            my_file.delete();
        }
    }

    //task5
    /*
        Find and prints the first available seat
        this method loops on Seat 2D array
        search for first available seat which is not booked and prints the seat row letter and seat number
        (parameters)
        Seat: 2D array represent te availability of seat
     */

    public static void find_first_available(int[][]Seat) {
        boolean First_find = true;
        for (int i = 0; i < Seat.length; i++) {
            for (int j = 0; j < Seat[i].length; j++) {
                if (Seat[i][j] == 0) {
                    System.out.println("First available seat is  " + (char) ('A' + i)  + (j + 1)); // print row letter and seat number of the first available seat
                    First_find = true; //inner loop break when the first available seat found
                    break;
                }
            }
            //outer loop break when the first available seat found
            if (First_find) {
                break;
            }
        }
        //if no  available seat print this statement
        if (!First_find) {
            System.out.println("Sorry! there are No any  available seats.");
        }

    }


    //task 6
    /*
        prints the seating plan of the plane
        this method print the visual representation of seating plan
        "X" is used to represent as booked "O" is used to represent as available
        (parameters)
        Seat:2D array representing the seating plan


     */
    public static void Show_seating_plan(int[][] Seat) {
        System.out.println("Seating plan ");
        for (int i = 0; i < Seat.length; i++) {
            for (int j = 0; j < Seat[i].length; j++) {
                if (Seat[i][j] == 1) {
                    System.out.print("X ");//sign of booked seat
                } else {
                    System.out.print("O ");//sign of available seat
                }
            }
            System.out.println();
        }
    }

    //task 10
    /*
    print information about booked seat and ticket prices
    this method loops over 2D array ticket_booking
    getting seat and ticket information , and print them
    also print the total sales generated from ticket booking
    (parameter)
    ticket_booking: 2D array hold the prices of each ticket
     */

    public static void print_ticket_info(int[][] ticket_booking) {
        int total_seat_booking = 0;  // Initialize total sales counter
        for (int row = 0; row < ticket_booking.length; row++) {
            // determine row letter based on the row index
            String row_letter = "";
            // determine row letter based on the row index
            switch (row) {
                case 0:
                    row_letter = "A";
                    break;
                case 1:
                    row_letter = "B";
                    break;
                case 2:
                    row_letter = "C";
                    break;
                case 3:
                    row_letter = "D";
                    break;
            }
            // Iterate over each column of the current row in the ticket_booking array
            for (int col = 0; col < ticket_booking[row].length; col++) {
                if (ticket_booking[row][col] != 0) {
                    System.out.println("Seat information : " + " " + row_letter + (col + 1));
                    System.out.println("Ticket information : " + " " + " £" + ticket_booking[row][col]);
                    total_seat_booking = total_seat_booking + ticket_booking[row][col];
                }
            }

        }
        System.out.println("The Total sales is  = " + " £" + total_seat_booking);
    }

    //task11
    /*
    allows to search information for specific seat and also ticket and passenger information
    (parameter)
    input: getting inputs from user
    Seats: 2D array show the availability of seat
    ticket_booking: 2D array show the ticket prices for booked seats
    Information_of_passenger: 2D array show passenger information for booked seats
     */
    public static void search_ticket(Scanner input, int[][] Seats, int[][] tickets_booking, Person[][] Information_of_passenger) {
        boolean row_validate = true;
        String row_letter = "";
        int row_number = 100;
        while (row_validate) {              // loop to get user input for row_letter
            System.out.print("Enter row letter (A-D) : ");
            row_letter = input.next().toUpperCase();
            String regex = "[A-D]";
            if (row_letter.matches(regex)) {
                row_validate = false;
            } else {
                System.out.println("Please enter only  'A' 'B' 'C' 'D' as Row letter");
            }
        }
        // determine row index  based on the row letter
        switch (row_letter) {
            case "A":
                row_number = 0;
                break;
            case "B":
                row_number = 1;
                break;
            case "C":
                row_number = 2;
                break;
            case "D":
                row_number = 3;
                break;
        }

        int seat_number = 1;
        boolean column_validate = true;
        while (column_validate) {           // loop to get user input for seat_number
            try {
                System.out.print("Please Enter seat number: ");
                seat_number = input.nextInt();
                if (seat_number >= 1 && seat_number <= Seats[row_number].length) {
                    column_validate = false;
                } else {
                    System.out.println("Please enter a valid seat number for this row");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter only a number");
                input.next();
            }
        }
        // Check if the seat is booked
        if (Seats[row_number][seat_number - 1] == 1) {
            // getting  ticket price
            int ticketPrice = tickets_booking[row_number][seat_number - 1];
            System.out.println("Seat information: " + row_letter + seat_number);
            System.out.println("Ticket information: Price £" + ticketPrice);
            // getting  passenger information and print
            Person passenger = Information_of_passenger[row_number][seat_number - 1];
            if (passenger != null) {
                System.out.println("Passenger information:");
                System.out.println("Passenger Name: " + passenger.getName());
                System.out.println("Passenger Surname: " + passenger.getSurname());
                System.out.println("Passenger Email: " + passenger.getEmail());
            } else {
                System.out.println("No passenger information available for this seat.");
            }
        } else {
            System.out.println("This seat is available");
        }
    }

}