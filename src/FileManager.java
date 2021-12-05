import java.io.*;
import java.util.ArrayList;

public class FileManager {
    //Creating files
    File rooms = new File("Rooms.txt");
    File usernames = new File("Accounts.txt");
    static File staffFile = new File("Staff.ser");
    File roomsNumbers = new File("RoomsNo.txt");
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
        addRoomNo(newRoom.getID());
        try {
            FileOutputStream addRoom = new FileOutputStream(rooms);
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addRoom);
            usernamesIN.writeObject(writeRooms);
            usernamesIN.close();
            addRoom.close();
        } catch (Exception e) {
            System.out.println("Failed file writing");
        }
    }

    public void addRoomNo(int number) {
        roomsNo.add(number);
        try {
            FileOutputStream addRoom = new FileOutputStream(roomsNumbers);
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addRoom);
            usernamesIN.writeObject(roomsNo);
            usernamesIN.close();
            addRoom.close();
        } catch (Exception e) {
            System.out.println("Failed file writing of roomsNO");
        }
    }

    public ArrayList<Integer> seeRoomsNo() {

        try {
            FileInputStream roomList = new FileInputStream(roomsNumbers);
            ObjectInputStream roomNamesOut = new ObjectInputStream(roomList);
            roomsNo = (ArrayList<Integer>) roomNamesOut.readObject();
            roomNamesOut.close();
            roomList.close();
        } catch (Exception e) {
            System.out.println("Failed file reading of roomsNO");
        }
        return roomsNo;
    }

    public void saveChange(Room[] room) {

        try {
            FileOutputStream addRoom = new FileOutputStream(rooms, false);
            ObjectOutputStream roomNamesIN = new ObjectOutputStream(addRoom);
            roomNamesIN.writeObject(room);
            roomNamesIN.close();
            addRoom.close();
        } catch (Exception e) {
            System.out.println("Failed file saving updates");
        }
    }

    public Room[] seeRooms() {
        Room[] seeRooms = new Room[500];
        try {
            FileInputStream roomList = new FileInputStream(rooms);
            ObjectInputStream roomNamesOut = new ObjectInputStream(roomList);
            seeRooms = (Room[]) roomNamesOut.readObject();
            roomNamesOut.close();
            roomList.close();
        } catch (Exception e) {
            System.out.println("Failed file reading");
        }
        return seeRooms;
    }

    public static Room[] seeRoomsStatic(){
        Room[] seeRooms = new Room[100];
        try {
            FileInputStream userList = new FileInputStream("Rooms1.ser");
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

    public void deleteUser(Guest user) {

        ArrayList<Guest> users = seeUsers();
        int index=-1;
        for (int i = 0; i < seeUsers().size(); i++) {
            if (user.getUserID()==seeUsers().get(i).getUserID()){
                index=i;
            }
        }
        if(index>=0){users.remove(index);
        try {
            FileOutputStream addUsers = new FileOutputStream(usernames);
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addUsers);
            usernamesIN.writeObject(users);
            addUsers.close();
            usernamesIN.close();
        } catch (Exception e) {
            System.out.println("Failed file writing");
        }}
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
