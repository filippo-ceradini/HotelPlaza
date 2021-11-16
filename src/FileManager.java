import java.io.*;
import java.util.ArrayList;

public class FileManager {
    //Creating files
    File rooms = new File("Rooms.txt");
    File usernames = new File("Accounts.txt");
    ArrayList<Integer> roomsNo = new ArrayList<>();

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
            FileOutputStream addUsers = new FileOutputStream(rooms, false);
            ObjectOutputStream usernamesIN = new ObjectOutputStream(addUsers);
            usernamesIN.writeObject(room);
            addUsers.close();
            usernamesIN.close();
        } catch (Exception e) {
            System.out.println("Failed file saving updates");
        }
    }

    public Room[] seeRooms()
    {
        Room[] seeRooms = new Room[1000];
        try {
            FileInputStream userList = new FileInputStream(rooms);
            ObjectInputStream usernamesOut = new ObjectInputStream(userList);
            seeRooms = (Room[]) usernamesOut.readObject();
            userList.close();
            usernamesOut.close();
        }catch(Exception e)
        {
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

    public ArrayList<Guest> seeUsers()
    {
        ArrayList<Guest> users = new ArrayList<>();
        try {
            FileInputStream userList = new FileInputStream(usernames);
            ObjectInputStream usernamesOut = new ObjectInputStream(userList);
            users = (ArrayList<Guest>) usernamesOut.readObject();
            userList.close();
            usernamesOut.close();
        }catch(Exception e)
        {
            System.out.println("Failed file reading");
        }
        return users;
    }
}
