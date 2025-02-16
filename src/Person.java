public class Person {
    private String First_name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email) {
        this.First_name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return First_name;
    }
    public void setName(String name) {
        this.First_name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void person_info(){
        System.out.println("Name: " + First_name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
        System.out.println("Your information recorded!");
    }
}
