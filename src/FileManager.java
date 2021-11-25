import java.io.*;
import java.util.ArrayList;

public class FileManager {
    //Creating files
    File rooms = new File("Rooms.ser");
    File usernames = new File("Accounts.txt");
    static File staffFile = new File("Staff.ser");
    ArrayList<Integer> roomsNo = new ArrayList<>();
    ArrayList<Staff> staffDB = getStaff();

    public static void editStaff(ArrayList<Staff> toWrite) {

        try {
            FileOutputStream fileOut = new FileOutputStream(staffFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(toWrite);
            objectOut.close();
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Failed file writing");
        }
    }

    public static ArrayList<Staff> getStaff() {
            ArrayList<Staff> getStaff = new ArrayList<>();
        try {
            FileInputStream StaffList = new FileInputStream(staffFile);
            ObjectInputStream StaffOutPut = new ObjectInputStream(StaffList);
            getStaff = (ArrayList<Staff>) StaffOutPut.readObject();
            StaffOutPut.close();
            StaffList.close();
        } catch (Exception e) {
            System.out.println("Failed file reading");
        }
        return getStaff;
    }

    public void addRoom(Room newRoom) {
        Room[] writeRooms = seeRooms();
        writeRooms[newRoom.getID()] = newRoom;
        roomsNo.add(newRoom.getID());
        try {
            FileOutputStream addRoom = new FileOutputStream(rooms);
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addRoom);
            usernamesIN.writeObject(writeRooms);
            addRoom.close();
            usernamesIN.close();
        } catch (Exception e) {
            System.out.println("Failed file writing");
        }
    }

    public void saveChange(Room[] room) {

        try {
            FileOutputStream addRoom = new FileOutputStream(rooms, false);
            ObjectOutputStream roomNamesIN = new ObjectOutputStream(addRoom);
            roomNamesIN.writeObject(room);
            addRoom.close();
            roomNamesIN.close();
        } catch (Exception e) {
            System.out.println("Failed file saving updates");
        }
    }

    public Room[] seeRooms() {
        Room[] seeRooms = new Room[1000];
        try {
            FileInputStream roomList = new FileInputStream(rooms);
            ObjectInputStream roomNamesOut = new ObjectInputStream(roomList);
            seeRooms = (Room[]) roomNamesOut.readObject();
            roomList.close();
            roomNamesOut.close();
        } catch (Exception e) {
            System.out.println("Failed file reading");
        }
        return seeRooms;
    }

    public static Room[] seeRoomsStatic(){
        Room[] seeRooms = new Room[100];
        try {
            FileInputStream userList = new FileInputStream("Rooms.txt");
            ObjectInputStream usernamesOut = new ObjectInputStream(userList);
            seeRooms = (Room[]) usernamesOut.readObject();
            userList.close();
            usernamesOut.close();
        } catch (Exception e) {
            System.out.println("Failed file reading");
        }
        return seeRooms;
    }

    public void addUser(Guest user) {

        ArrayList<Guest> users = seeUsers();
        users.add(user);
        try {
            FileOutputStream addUsers = new FileOutputStream(usernames);
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addUsers);
            usernamesIN.writeObject(users);
            addUsers.close();
            usernamesIN.close();
        } catch (Exception e) {
            System.out.println("Failed file writing");
        }
    }

    /*public void saveChange(ArrayList<Guest> users) {

        try {
            FileOutputStream addUsers = new FileOutputStream(usernames, false);
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addUsers);
            usernamesIN.writeObject(users);
            addUsers.close();
            usernamesIN.close();
        } catch (Exception e) {
            System.out.println("Failed file saving updates");
        }
    }*/

    public ArrayList<Guest> seeUsers() {
        ArrayList<Guest> users = new ArrayList<>();//
        try {
            FileInputStream userList = new FileInputStream(usernames);
            ObjectInputStream usernamesOut = new ObjectInputStream(userList);
            users = (ArrayList<Guest>) usernamesOut.readObject();
            userList.close();
            usernamesOut.close();
        } catch (Exception e) {
            System.out.println("Failed file reading");
        }
        return users;
    }
}
