

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;

public class Run {

    public static void main(String[] args) throws IOException {
        Room[] rm = new Room[500];
        FileManager sm = new FileManager();
        sm.saveChange(rm);
        int cnt=0;
        for (int i = 0; i < 50; i++) {
            Random rand = new Random();

            Room.createRoom(rand.nextInt(4)+1,rand.nextInt(3)+1,1+cnt );
            System.out.println("room created "+(1+cnt));
            cnt++;
        }

        Room.takeRoom(2021,2021,11,12,12,12,1,30);
        Room.allRoomsStatus();
        Room.availableBySize(4,2021,2022,11,3,25,1);
        Room.availableByDate(2021,2021,1,12,17,1);
        Room.availableByPrice(100,200,2021,2021,1,12,17,1);
        Room.availableByTier(1,2021,2021,1,12,17,1);
        Room.allRoomsBookings();
        //Room.deleteGuestRooms(1);
        Room.allRoomsBookings();

        Room.allRooms();
        GUI.login();


        //Used to create new rooms for testing

        //AdManageStaff.manageStaffe();




        //Room[] rooms = new Room[100];

        /*
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
        //ManageRooms.menuRooms();
        //This in case we lose the Staff.txt

        /*ArrayList<Staff> toWrite = new ArrayList<>();
        Staff person = new Staff("Jonathan", "Bomber", "12345678", "100.000", "cacca");
        for (int i = 0; i < 10; i++) {
            toWrite.add(i, person);
        }
        FileManager.editStaff(toWrite);*/

    }

}
