import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Guest implements Serializable{
    int userID;
    private String login;

    public int getUserID() {return userID;}

    private String password;
    private String name;
    private String surname;
    private ArrayList<Integer> bookings = new ArrayList<>();

    private static FileManager dataBase = new FileManager();

    public String getName() {return name;}

    public String getSurname() {return surname;}

    public ArrayList<Integer> getBookings() {return bookings;}

    public static FileManager getDataBase() {return dataBase;}

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GuestID=" + userID +
                ", " + name +
                " " + surname +
                ", bookings=" + bookings+
        ", login= " + login;
    }

    /*public static Guest logIn() {
        Guest storingValuables = new Guest();
        ArrayList<Guest> users = dataBase.seeUsers();
        System.out.print("Enter username");
        Scanner usernameScan = new Scanner(System.in);
        String usernameIn = usernameScan.nextLine();
        boolean repeats = false;
        int index;
        for (int i = 0; i < users.size(); i++) {
            if (usernameIn.equals(users.get(i).getLogin())) {
                index = i;
                repeats = true;
                System.out.print("Enter password");
                Scanner passwordScan = new Scanner(System.in);
                String passwordIn = passwordScan.nextLine();
                if (passwordIn.equals(users.get(index).getPassword())) {
                //    storingValuables.setMenuState(true);---------------------------------------------------------------
                    storingValuables.setUserID(index);
                    return storingValuables;
                } else {
                    System.out.print("Password is wrong");
                //    storingValuables.setMenuState(false);---------------------------------------------------------
                    return storingValuables;
                }
            }
            if (repeats = false) {
                System.out.print("This username doesn't exist - Try again or create new account");
            //    storingValuables.setMenuState(false);-----------------------------------------------------------------
                return storingValuables;
            }
        }
        System.out.print("No users are created");
        //storingValuables.setMenuState(false);-------------------------------------------------------------
        return storingValuables;
    }

    public static Guest creatingUser() {
        Guest storingValuables = new Guest();
        ArrayList<Guest> users = dataBase.seeUsers();
        Guest user = new Guest();
        boolean creatingUsername = true;
        while (creatingUsername == true) {
            System.out.print("Create username");
            Scanner usernameScan = new Scanner(System.in);
            String usernameIn = usernameScan.nextLine();
            boolean repeats = false;
            for (int i = 0; i < users.size(); i++) {
                if (usernameIn.equals(users.get(i).getLogin())) {
                    repeats = true;
                    System.out.print("This username is already taken, try another one");
                }
            }
            if (repeats == false) {
                user.setLogin(usernameIn);
                System.out.print("Username created");
                creatingUsername = false;
            }
        }
        System.out.print("Now please choose password");
        Scanner passwordScan = new Scanner(System.in);
        String password = passwordScan.nextLine();
        user.setPassword(password);
        dataBase.addUser(user);
        storingValuables.setUserID(users.size());
        //storingValuables.setMenuState(true);-----------------------------------------------------------
        return storingValuables;
    }*/



}
