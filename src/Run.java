import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

public class Run {

    public static void main(String[] args) throws IOException {


        // GUI.login();


        //Used to create new rooms for testing
        ManageRooms.menuRooms();

        /*
        Room[] rooms = new Room[100];
        for (int i = 0; i < 100; i++) {
            Random rand = new Random();
            Room barnaba = new Room(i, rand.nextInt(3) + 1, (rand.nextInt(3) + 1));
            rooms[i] = barnaba;
        }
        try {
            FileOutputStream addRoom = new FileOutputStream("Rooms.txt");
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addRoom);
            usernamesIN.writeObject(rooms);
            addRoom.close();
            usernamesIN.close();
        } catch (Exception e) {
            System.out.println("Failed file writing");
        }
        Print.testPrintRooms();
        //This in case we lose the Staff.txt
        /*ArrayList<Staff> toWrite = new ArrayList<>();
        Staff person = new Staff("Jonathan", "Bomber", "12345678", "100.000");

        for (int i = 0; i < 10; i++) {
            toWrite.add(i, person);
        }
        FileManager.editStaff(toWrite);*/
    }

}
