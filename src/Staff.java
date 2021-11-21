import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Staff implements Serializable{
    private String name;
    private String surname;
    private String phone;
    private String salary;
    private String login;
    private String password;
    private static FileManager staffDB = new FileManager();

    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getPhone() {return phone;}
    public String getSalary() {return salary;}
    public String getPassword() {return password;}

    public Staff(String name, String surname, String phone, String salary, String password) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.salary = salary;
        this.login=name+"."+surname;
        this.password=password;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}
