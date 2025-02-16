import java.io.FileWriter;
import java.io.IOException;
public class Ticket {
    private String row_letter;
    private  String Seat;
    private int ticket_price;
    private Person passenger;

    public Ticket(String row, String seat, int price,Person passenger){
        this.row_letter=row;
        this.Seat=seat;
        this.ticket_price=price;
        this.passenger=passenger;
    }
    public String getRow(){
        return row_letter;
    }
    public void setRow(String row){
        this.row_letter=row;
    }
    public String seat(){
        return Seat;
    }
    public void setPrice(int price){
        this.ticket_price=price;
    }
    public Person getPassenger(){
        return passenger;
    }
    public void setPassenger(Person passenger){
        this.passenger=passenger;
    }
    public void print_ticket(){
        System.out.println("Ticket Information:");
        System.out.println("Row: " + row_letter);
        System.out.println("Seat: " + Seat);
        System.out.println("Price: $" + ticket_price);

    }
    public void Save (String Save_path){
        String File_name=Seat+".txt";
        try {
            FileWriter file_writer = new FileWriter(File_name);
            file_writer.write("Ticket information:\n");
            file_writer.write("Seat: " + Seat + "\n");
            file_writer.write("Price: £" + ticket_price + "\n");
            file_writer.write("Passenger Information:\n");
            file_writer.write("Name: " + passenger.getName() + "\n");
            file_writer.write("Surname: " + passenger.getSurname() + "\n");
            file_writer.write("Email: " + passenger.getEmail() + "\n");
            file_writer.close();
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("An error occurred while saving.");
        }
    }
}