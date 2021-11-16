import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Room implements Serializable {

    private int ID;
    private int size;
    private boolean[][][] empty = new boolean[2023][12][31];
    private int[][][] takenBy = new int[2023][12][31];
    private int price;
    private int bookings = 0;

    private static FileManager dataBase = new FileManager();

    public int getID() {
        return ID;
    }

    public static void createRoom(int size, int ID, int price) {
        Room storing = new Room();
        storing.size = size;
        storing.ID = ID;
        storing.price = price;
        for (int l = 2020; l <= 2023; l++) {
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 31; j++) {
                    storing.empty[l][i][j] = true;
                    storing.takenBy[l][i][j]=0;
                }
            }
        }
        dataBase.addRoom(storing);
    }

    public static int takeRoom(int inYear, int outYear, int inMonth, int inDay, int outMonth, int outDay, int guest, int ID) {
        Room[] storing = dataBase.seeRooms();
        int ticket = 0;
        for (int l = inYear; l < outYear; l++) {
            for (int i = inMonth; i < outMonth; i++) {
                for (int j = inDay; j < outDay; j++) {
                    storing[ID].empty[l][i][j] = false;
                    storing[ID].takenBy[l][i][j] = guest;
                    ticket += storing[ID].price;
                }
            }
        }
        dataBase.saveChange(storing);
        return ticket;
    }

    public static void allRoomsStatus()
    {
        for(int i=0;i<dataBase.roomsNo.size();i++)
        {
            seeRoomStatus(dataBase.roomsNo.get(i));
        }
    }
    public static void seeRoomStatus(int ID) {
        Room[] storing = dataBase.seeRooms();
        boolean free = false;
        for (int j = 2021; j < 2024; j++) {
            for (int k = 1; k < 13; k++) {
                for (int l = 1; l < 31; l++) {
                    if (storing[ID].empty[j][k][l] == true) {
                        free = true;
                    }
                }
            }
        }
        if (free == true) {
            System.out.println("Room No." + ID + " has no bookings.");
        } else {
            System.out.println("Room No." + ID + " has bookings:");
        }
        boolean inBooking = false;
        int IDUsing = 0;
        for (int j = 2021; j < 2024; j++) {
            for (int k = 1; k < 13; k++) {
                for (int l = 1; l < 31; l++) {
                    if (storing[ID].empty[j][k][l] == false && inBooking == false) {
                        System.out.print("By: " + dataBase.seeUsers().get(storing[ID].takenBy[j][k][l]).getLogin() + " From: " + j + "-" + k + "-" + l);
                        IDUsing = storing[ID].takenBy[j][k][l];
                        inBooking = true;
                    }
                    if (storing[ID].empty[j][k][l] == true && inBooking == true) {

                        if ((l - 1) == 0) {
                            if (k == 2 || k == 4 || k == 6 || k == 8 || k == 9 || k == 11 || k == 1) {
                                System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 31);
                            } else if (k == 5 || k == 7 || k == 10 || k == 12) {
                                System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 30);
                            } else if (k == 3) {
                                System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 28);
                            }
                            if ((k - 1) == 0) {
                                System.out.println(" Until: " + (j - 1) + "-" + 12 + "-" + 31);
                            }
                        } else {
                            System.out.println(" Until: " + j + "-" + k + "-" + (l - 1));
                        }
                        inBooking = false;
                    }
                    if (inBooking == true && storing[ID].takenBy[j][k][l] != IDUsing) {
                        if ((l - 1) == 0) {
                            if (k == 2 || k == 4 || k == 6 || k == 8 || k == 9 || k == 11 || k == 1) {
                                System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 31);
                            } else if (k == 5 || k == 7 || k == 10 || k == 12) {
                                System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 30);
                            } else if (k == 3) {
                                System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 28);
                            }
                            if ((k - 1) == 0) {
                                System.out.println(" Until: " + (j - 1) + "-" + 12 + "-" + 31);
                            }
                        } else {
                            System.out.println(" Until: " + j + "-" + k + "-" + (l - 1));
                        }
                        System.out.print("By: " + dataBase.seeUsers().get(storing[ID].takenBy[j][k][l]).getLogin() + " From: " + j + "-" + k + "-" + l);
                        IDUsing=storing[ID].takenBy[j][k][l];
                    }
                }
            }

        }

    }

    public static void guestsRooms(int guestID)
    {
        for(int i=0;i<dataBase.roomsNo.size();i++) {
            int ID = (dataBase.roomsNo.get(i));
            Room[] storing = dataBase.seeRooms();
            boolean inBooking = false;
            int IDUsing = 0;
            for (int j = 2021; j < 2024; j++) {
                for (int k = 1; k < 13; k++) {
                    for (int l = 1; l < 31; l++) {
                        if (storing[ID].takenBy[j][k][l] == guestID && inBooking == false) {
                            System.out.print("By: " + dataBase.seeUsers().get(storing[ID].takenBy[j][k][l]).getLogin() + " From: " + j + "-" + k + "-" + l);
                            IDUsing = storing[ID].takenBy[j][k][l];
                            inBooking = true;
                        }
                        if (inBooking == true && storing[ID].takenBy[j][k][l] != IDUsing) {
                            if ((l - 1) == 0) {
                                if (k == 2 || k == 4 || k == 6 || k == 8 || k == 9 || k == 11 || k == 1) {
                                    System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 31);
                                } else if (k == 5 || k == 7 || k == 10 || k == 12) {
                                    System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 30);
                                } else if (k == 3) {
                                    System.out.println(" Until: " + j + "-" + (k - 1) + "-" + 28);
                                }
                                if ((k - 1) == 0) {
                                    System.out.println(" Until: " + (j - 1) + "-" + 12 + "-" + 31);
                                }
                            } else {
                                System.out.println(" Until: " + j + "-" + k + "-" + (l - 1));
                            }
                            inBooking=false;
                        }
                    }
                }

            }
        }
    }
}