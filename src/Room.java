import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Room implements Serializable {

    private int ID;
    private int size;
    private boolean[][][] empty = new boolean[4][13][32];
    private int[][][] takenBy = new int[4][13][32];
    private int tier;
    private int price;
    private int[][][] ticket = new int[4][13][32];

    public Room(int ID, int size, int tier) {
        this.ID = ID;
        this.size = size;
        this.tier = tier;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSize() {return size;}

    public void setSize(int size) {
        this.size = size;
    }

    public boolean[][][] getEmpty() {return empty;}

    public void setEmpty(boolean[][][] empty) {
        this.empty = empty;
    }

    public int[][][] getTakenBy() {return takenBy;}

    public void setTakenBy(int[][][] takenBy) {
        this.takenBy = takenBy;
    }

    public int getTier() {return tier;}

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getPrice() {return price;}

    public void setPrice(int price) {
        this.price = price;
    }

    public int[][][] getTicket() {return ticket;}

    public void setTicket(int[][][] ticket) {
        this.ticket = ticket;
    }

    public static FileManager getDataBase() {return dataBase;}

    public static void setDataBase(FileManager dataBase) {
        Room.dataBase = dataBase;
    }

    private static FileManager dataBase = new FileManager();

    public Room() {

    }

    public int getID() {
        return ID;
    }

    public String toString() {
        return "       Room Number " + ID +
                ", size=" + size +
                ", tier=" + tier +
                ", price=" + price;
    }

    public static void createRoom(int size, int tier, int ID) {
        Room storing = new Room();
        storing.size = size;
        storing.ID = ID;
        storing.tier = tier;
        storing.price = (size * (tier + 1) * 20);
        for (int l = 1; l < 4; l++) {
            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 32; j++) {
                    storing.empty[l][i][j] = true;
                    storing.takenBy[l][i][j] = 0;
                    storing.ticket[l][i][j] = 0;
                }
            }
        }
        dataBase.addRoom(storing);
    }

    public static void seeRoom(int ID) {
        Room[] storing = dataBase.seeRooms();
        System.out.println(ID+" "+storing[ID].size+" "+storing[ID].tier);
    }

    public static void editTierSize(int ID, int tier, int size)
    {
        Room[] storing = dataBase.seeRooms();
        storing[ID].size = size;
        storing[ID].tier = tier;
        storing[ID].price = (size * (tier + 1) * 20);
        System.out.println(ID+" "+storing[ID].size+" "+storing[ID].tier);
        dataBase.saveChange(storing);

    }
    public static void takeRoom(int inYear1, int outYear1, int inMonth, int outMonth, int inDay, int outDay, int guest, int ID) {
        Room[] storing = dataBase.seeRooms();
        int inYear = (inYear1 - 2020);
        int outYear = (outYear1 - 2020);
        int days = 0;
        if ((outYear - inYear) > 0) {
            for (int l = inYear; l <= inYear; l++) {
                for (int i = inMonth; i <= inMonth; i++) {
                    for (int j = inDay; j <= 31; j++) {
                        storing[ID].empty[l][i][j] = false;
                        storing[ID].takenBy[l][i][j] = guest;
                        days++;
                    }
                }
                for (int i = (inMonth + 1); i <= 12; i++) {
                    for (int j = inDay; j <= outDay; j++) {
                        storing[ID].empty[l][i][j] = false;
                        storing[ID].takenBy[l][i][j] = guest;
                        days++;
                    }
                }
            }
            for (int l = (inYear + 1); l <= (outYear - 1); l++) {
                for (int i = 1; i <= 12; i++) {
                    for (int j = 1; j <= 31; j++) {
                        storing[ID].empty[l][i][j] = false;
                        storing[ID].takenBy[l][i][j] = guest;
                        days++;
                    }
                }
            }
            for (int l = outYear; l <= outYear; l++) {
                for (int i = 1; i <= (outMonth - 1); i++) {
                    for (int j = 1; j <= 31; j++) {
                        storing[ID].empty[l][i][j] = false;
                        storing[ID].takenBy[l][i][j] = guest;
                        days++;
                    }
                }
                for (int i = outMonth; i <= outMonth; i++) {
                    for (int j = 1; j <= outDay; j++) {
                        storing[ID].empty[l][i][j] = false;
                        storing[ID].takenBy[l][i][j] = guest;
                        days++;
                    }
                }
            }
        } else {
            for (int l = inYear; l <= outYear; l++) {
                if ((outMonth - inMonth) > 0) {
                    for (int i = inMonth; i <= inMonth; i++) {
                        for (int j = inDay; j <= 31; j++) {
                            storing[ID].empty[l][i][j] = false;
                            storing[ID].takenBy[l][i][j] = guest;
                            days++;
                        }
                    }
                    for (int i = (inMonth + 1); i <= (outMonth - 1); i++) {
                        for (int j = 1; j <= 31; j++) {
                            storing[ID].empty[l][i][j] = false;
                            storing[ID].takenBy[l][i][j] = guest;
                            days++;
                        }
                    }
                    for (int i = outMonth; i <= outMonth; i++) {
                        for (int j = 1; j <= outDay; j++) {
                            storing[ID].empty[l][i][j] = false;
                            storing[ID].takenBy[l][i][j] = guest;
                            days++;
                        }
                    }
                } else {
                    for (int i = inMonth; i <= outMonth; i++) {
                        for (int j = inDay; j <= outDay; j++) {
                            storing[ID].empty[l][i][j] = false;
                            storing[ID].takenBy[l][i][j] = guest;
                            days++;
                        }
                    }
                }
            }
        }
        //---------------------------------------------------------------------------
        if ((outYear - inYear) > 0) {
            for (int l = inYear; l <= inYear; l++) {
                for (int i = inMonth; i <= inMonth; i++) {
                    for (int j = inDay; j <= 31; j++) {
                        storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                    }
                }
                for (int i = (inMonth + 1); i <= 12; i++) {
                    for (int j = inDay; j <= outDay; j++) {
                        storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                    }
                }
            }
            for (int l = (inYear + 1); l <= (outYear - 1); l++) {
                for (int i = 1; i <= 12; i++) {
                    for (int j = 1; j <= 31; j++) {
                        storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                    }
                }
            }
            for (int l = outYear; l <= outYear; l++) {
                for (int i = 1; i <= (outMonth - 1); i++) {
                    for (int j = 1; j <= 31; j++) {
                        storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                    }
                }
                for (int i = outMonth; i <= outMonth; i++) {
                    for (int j = 1; j <= outDay; j++) {
                        storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                    }
                }
            }
        } else {
            for (int l = inYear; l <= outYear; l++) {
                if ((outMonth - inMonth) > 0) {
                    for (int i = inMonth; i <= inMonth; i++) {
                        for (int j = inDay; j <= 31; j++) {
                            storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                        }
                    }
                    for (int i = (inMonth + 1); i <= (outMonth - 1); i++) {
                        for (int j = 1; j <= 31; j++) {
                            storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                        }
                    }
                    for (int i = outMonth; i <= outMonth; i++) {
                        for (int j = 1; j <= outDay; j++) {
                            storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                        }
                    }
                } else {
                    for (int i = inMonth; i <= outMonth; i++) {
                        for (int j = inDay; j <= outDay; j++) {
                            storing[ID].ticket[l][i][j] = (days * storing[ID].size * storing[ID].tier);
                        }
                    }
                }
            }
        }
        dataBase.saveChange(storing);
    }

    public static void allRoomsStatus() {
        for (int i = 0; i < dataBase.seeRoomsNo().size(); i++) {
            seeRoomStatus(dataBase.seeRoomsNo().get(i));
        }
    }

    public static void allRooms() {
        Room[] storing = dataBase.seeRooms();
        try {
            for (int i = 1; i < storing.length; i++) {
                System.out.println(storing[i].toString());
            }
        } catch (NullPointerException e){}

    }

    public static void seeRoomStatus(int ID) {
        Room[] storing = dataBase.seeRooms();
        boolean free = true;
        for (int j = 1; j < 4; j++) {
            for (int k = 1; k < 13; k++) {
                for (int l = 1; l < 32; l++) {
                    if (storing[ID].empty[j][k][l] == false) {
                        free = false;
                    }
                }
            }
        }
        if (free == true) {
            System.out.println("Room No." + ID + " has no bookings.");
        } else {
            System.out.print("Room No." + ID + " is taken: ");
        }
        boolean inBooking = false;
        int IDUsing = 0;
        for (int j = 1; j < 4; j++) {
            for (int k = 1; k < 13; k++) {
                for (int l = 1; l < 32; l++) {
                    if (storing[ID].empty[j][k][l] == false && inBooking == false) {
                        System.out.print("By: " + storing[ID].takenBy[j][k][l] + " From: " + (2020 + j) + "-" + k + "-" + l);
                        IDUsing = storing[ID].takenBy[j][k][l];
                        inBooking = true;
                    }
                    if (storing[ID].empty[j][k][l] == true && inBooking == true) {

                        if ((l - 1) == 0) {
                            if (k == 2 || k == 4 || k == 6 || k == 8 || k == 9 || k == 11 || k == 1) {
                                System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 31);
                            } else if (k == 5 || k == 7 || k == 10 || k == 12) {
                                System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 30);
                            } else if (k == 3) {
                                System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 28);
                            }
                            if ((k - 1) == 0) {
                                System.out.println(" Until: " + ((2020 + j) - 1) + "-" + 12 + "-" + 31);
                            }
                        } else {
                            System.out.println(" Until: " + (2020 + j) + "-" + k + "-" + (l - 1));
                        }
                        inBooking = false;
                    }
                    if (inBooking == true && storing[ID].takenBy[j][k][l] != IDUsing) {
                        if ((l - 1) == 0) {
                            if (k == 2 || k == 4 || k == 6 || k == 8 || k == 9 || k == 11 || k == 1) {
                                System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 31);
                            } else if (k == 5 || k == 7 || k == 10 || k == 12) {
                                System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 30);
                            } else if (k == 3) {
                                System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 28);
                            }
                            if ((k - 1) == 0) {
                                System.out.println(" Until: " + ((2020 + j) - 1) + "-" + 12 + "-" + 31);
                            }
                        } else {
                            System.out.println(" Until: " + (2020 + j) + "-" + k + "-" + (l - 1));
                        }
                        System.out.print("By: " + dataBase.seeUsers().get(storing[ID].takenBy[j][k][l]).getLogin() + " From: " + (2020 + j) + "-" + k + "-" + l);
                        IDUsing = storing[ID].takenBy[j][k][l];
                    }
                }
            }

        }

    }

    public static void guestsRooms(int guestID) {
        for (int i = 0; i < dataBase.seeRoomsNo().size(); i++) {
            int ID = (dataBase.seeRoomsNo().get(i));
            Room[] storing = dataBase.seeRooms();
            boolean inBooking = false;
            int IDUsing = 0;
            for (int j = 1; j < 4; j++) {
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
                            inBooking = false;
                        }
                    }
                }

            }
        }
    }

    public static void deleteGuestRooms(int guestID) {
        Room[] storing = dataBase.seeRooms();
        for (int i = 0; i < dataBase.seeRoomsNo().size(); i++) {
            int ID = (dataBase.seeRoomsNo().get(i));
            boolean inBooking = false;
            for (int j = 1; j < 4; j++) {
                for (int k = 1; k < 13; k++) {
                    for (int l = 1; l < 32; l++) {
                        if (storing[ID].takenBy[j][k][l] == guestID) {
                            storing[ID].empty[j][k][l] = true;
                            storing[ID].takenBy[j][k][l] = 0;
                            storing[ID].ticket[j][k][l] = 0;
                        }
                    }
                }

            }
        }
        dataBase.saveChange(storing);
    }

    public static void availableBySize(int size, int fromYear1, int untilYear1, int inMonth, int outMonth, int inDay, int outDay) {
        int inYear = (fromYear1 - 2020);
        int outYear = (untilYear1 - 2020);
        Room[] storing = dataBase.seeRooms();
        ArrayList<Room> storing1 = new ArrayList<>();
        for (int i = 0; i < dataBase.seeRoomsNo().size(); i++) {
            if (storing[dataBase.seeRoomsNo().get(i)].size == size) {
                storing1.add(storing[dataBase.seeRoomsNo().get(i)]);
            }
        }
        for (int q = 0; q < storing1.size(); q++) {
            boolean free = true;
            int ID = storing1.get(q).ID;
            if ((outYear - inYear) > 0) {
                for (int l = inYear; l <= inYear; l++) {
                    for (int i = inMonth; i <= inMonth; i++) {
                        for (int j = inDay; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                    for (int i = (inMonth + 1); i <= 12; i++) {
                        for (int j = inDay; j <= outDay; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
                for (int l = (inYear + 1); l <= (outYear - 1); l++) {
                    for (int i = 1; i <= 12; i++) {
                        for (int j = 1; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
                for (int l = outYear; l <= outYear; l++) {
                    for (int i = 1; i <= (outMonth - 1); i++) {
                        for (int j = 1; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                    for (int i = outMonth; i <= outMonth; i++) {
                        for (int j = 1; j <= outDay; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
            } else {
                for (int l = inYear; l <= outYear; l++) {
                    if ((outMonth - inMonth) > 0) {
                        for (int i = inMonth; i <= inMonth; i++) {
                            for (int j = inDay; j <= 31; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                        for (int i = (inMonth + 1); i <= (outMonth - 1); i++) {
                            for (int j = 1; j <= 31; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                        for (int i = outMonth; i <= outMonth; i++) {
                            for (int j = 1; j <= outDay; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                    } else {
                        for (int i = inMonth; i <= outMonth; i++) {
                            for (int j = inDay; j <= outDay; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                    }
                }
            }
            if (free == true) {
                System.out.println("Room No." + ID + " Tier: " + tierName(storing[ID].tier) + " Price: " + storing[ID].price);
            }
        }
    }

    public static void availableByTier(int tier, int fromYear1, int untilYear1, int inMonth, int outMonth, int inDay, int outDay) {
        int inYear = (fromYear1 - 2020);
        int outYear = (untilYear1 - 2020);
        Room[] storing = dataBase.seeRooms();
        ArrayList<Room> storing1 = new ArrayList<>();
        for (int i = 0; i < dataBase.seeRoomsNo().size(); i++) {
            if (storing[dataBase.seeRoomsNo().get(i)].tier == tier) {
                storing1.add(storing[dataBase.seeRoomsNo().get(i)]);
            }
        }
        for (int q = 0; q < storing1.size(); q++) {
            boolean free = true;
            int ID = storing1.get(q).ID;
            if ((outYear - inYear) > 0) {
                for (int l = inYear; l <= inYear; l++) {
                    for (int i = inMonth; i <= inMonth; i++) {
                        for (int j = inDay; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == true) {
                                free = true;
                            }
                        }
                    }
                    for (int i = (inMonth + 1); i <= 12; i++) {
                        for (int j = inDay; j <= outDay; j++) {
                            if (storing[ID].empty[l][i][j] == true) {
                                free = true;
                            }
                        }
                    }
                }
                for (int l = (inYear + 1); l <= (outYear - 1); l++) {
                    for (int i = 1; i <= 12; i++) {
                        for (int j = 1; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
                for (int l = outYear; l <= outYear; l++) {
                    for (int i = 1; i <= (outMonth - 1); i++) {
                        for (int j = 1; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                    for (int i = outMonth; i <= outMonth; i++) {
                        for (int j = 1; j <= outDay; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
            } else {
                for (int l = inYear; l <= outYear; l++) {
                    if ((outMonth - inMonth) > 0) {
                        for (int i = inMonth; i <= inMonth; i++) {
                            for (int j = inDay; j <= 31; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                        for (int i = (inMonth + 1); i <= (outMonth - 1); i++) {
                            for (int j = 1; j <= 31; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                        for (int i = outMonth; i <= outMonth; i++) {
                            for (int j = 1; j <= outDay; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                    } else {
                        for (int i = inMonth; i <= outMonth; i++) {
                            for (int j = inDay; j <= outDay; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                    }
                }
            }
            if (free == true) {
                System.out.println("Room No." + ID + " Size: " + storing[ID].size + " Price: " + storing[ID].price);
            }
        }
    }

    public static void availableByPrice(int fromPrice, int untilPrice, int fromYear1, int untilYear1, int inMonth, int outMonth, int inDay, int outDay) {
        int inYear = (fromYear1 - 2020);
        int outYear = (untilYear1 - 2020);
        Room[] storing = dataBase.seeRooms();
        ArrayList<Room> storing1 = new ArrayList<>();
        for (int i = 0; i < dataBase.seeRoomsNo().size(); i++) {
            if (storing[dataBase.seeRoomsNo().get(i)].price >= fromPrice && storing[dataBase.seeRoomsNo().get(i)].price <= untilPrice) {
                storing1.add(storing[dataBase.seeRoomsNo().get(i)]);
            }
        }
        for (int q = 0; q < storing1.size(); q++) {
            boolean free = true;
            int ID = storing1.get(q).ID;
            if ((outYear - inYear) > 0) {
                for (int l = inYear; l <= inYear; l++) {
                    for (int i = inMonth; i <= inMonth; i++) {
                        for (int j = inDay; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                    for (int i = (inMonth + 1); i <= 12; i++) {
                        for (int j = inDay; j <= outDay; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
                for (int l = (inYear + 1); l <= (outYear - 1); l++) {
                    for (int i = 1; i <= 12; i++) {
                        for (int j = 1; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
                for (int l = outYear; l <= outYear; l++) {
                    for (int i = 1; i <= (outMonth - 1); i++) {
                        for (int j = 1; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                    for (int i = outMonth; i <= outMonth; i++) {
                        for (int j = 1; j <= outDay; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
            } else {
                for (int l = inYear; l <= outYear; l++) {
                    if ((outMonth - inMonth) > 0) {
                        for (int i = inMonth; i <= inMonth; i++) {
                            for (int j = inDay; j <= 31; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                        for (int i = (inMonth + 1); i <= (outMonth - 1); i++) {
                            for (int j = 1; j <= 31; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                        for (int i = outMonth; i <= outMonth; i++) {
                            for (int j = 1; j <= outDay; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                    } else {
                        for (int i = inMonth; i <= outMonth; i++) {
                            for (int j = inDay; j <= outDay; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                    }
                }
            }
            if (free == true) {
                System.out.println("Room No." + ID + " Tier: " + tierName(storing[ID].tier) + " Price: " + storing[ID].price);
            }
        }
    }

    public static void availableByDate(int fromYear1, int untilYear1, int inMonth, int outMonth, int inDay, int outDay) {
        int inYear = (fromYear1 - 2020);
        int outYear = (untilYear1 - 2020);
        Room[] storing = dataBase.seeRooms();
        for (int q = 0; q < dataBase.seeRoomsNo().size(); q++) {
            boolean free = true;
            int ID = storing[dataBase.seeRoomsNo().get(q)].ID;
            if ((outYear - inYear) > 0) {
                for (int l = inYear; l <= inYear; l++) {
                    for (int i = inMonth; i <= inMonth; i++) {
                        for (int j = inDay; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                    for (int i = (inMonth + 1); i <= 12; i++) {
                        for (int j = inDay; j <= outDay; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
                for (int l = (inYear + 1); l <= (outYear - 1); l++) {
                    for (int i = 1; i <= 12; i++) {
                        for (int j = 1; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
                for (int l = outYear; l <= outYear; l++) {
                    for (int i = 1; i <= (outMonth - 1); i++) {
                        for (int j = 1; j <= 31; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                    for (int i = outMonth; i <= outMonth; i++) {
                        for (int j = 1; j <= outDay; j++) {
                            if (storing[ID].empty[l][i][j] == false) {
                                free = false;
                            }
                        }
                    }
                }
            } else {
                for (int l = inYear; l <= outYear; l++) {
                    if ((outMonth - inMonth) > 0) {
                        for (int i = inMonth; i <= inMonth; i++) {
                            for (int j = inDay; j <= 31; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                        for (int i = (inMonth + 1); i <= (outMonth - 1); i++) {
                            for (int j = 1; j <= 31; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                        for (int i = outMonth; i <= outMonth; i++) {
                            for (int j = 1; j <= outDay; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                    } else {
                        for (int i = inMonth; i <= outMonth; i++) {
                            for (int j = inDay; j <= outDay; j++) {
                                if (storing[ID].empty[l][i][j] == false) {
                                    free = false;
                                }
                            }
                        }
                    }
                }
            }
            if (free == true) {
                System.out.println("Room No." + ID + " Tier: " + tierName(storing[ID].tier) + " Size: " + storing[ID].size + " Price: " + storing[ID].price);
            }
        }
    }

    public static void cancelBooking(int inYear1, int outYear1, int inMonth, int outMonth, int inDay, int outDay, int ID) {
        int inYear = (inYear1 - 2020);
        int outYear = (outYear1 - 2020);
        Room[] storing = dataBase.seeRooms();
        for (int l = inYear; l < outYear; l++) {
            for (int i = inMonth; i < outMonth; i++) {
                for (int j = inDay; j < outDay; j++) {
                    storing[ID].empty[l][i][j] = true;
                    storing[ID].takenBy[l][i][j] = 0;
                    storing[ID].ticket[l][i][j] = 0;
                }
            }
        }
        if ((outYear - inYear) > 0) {
            for (int l = inYear; l <= inYear; l++) {
                for (int i = inMonth; i <= inMonth; i++) {
                    for (int j = inDay; j <= 31; j++) {
                        storing[ID].empty[l][i][j] = true;
                        storing[ID].takenBy[l][i][j] = 0;
                        storing[ID].ticket[l][i][j] = 0;
                    }
                }
                for (int i = (inMonth + 1); i <= 12; i++) {
                    for (int j = inDay; j <= outDay; j++) {
                        storing[ID].empty[l][i][j] = true;
                        storing[ID].takenBy[l][i][j] = 0;
                        storing[ID].ticket[l][i][j] = 0;
                    }
                }
            }
            for (int l = (inYear + 1); l <= (outYear - 1); l++) {
                for (int i = 1; i <= 12; i++) {
                    for (int j = 1; j <= 31; j++) {
                        storing[ID].empty[l][i][j] = true;
                        storing[ID].takenBy[l][i][j] = 0;
                        storing[ID].ticket[l][i][j] = 0;
                    }
                }
            }
            for (int l = outYear; l <= outYear; l++) {
                for (int i = 1; i <= (outMonth - 1); i++) {
                    for (int j = 1; j <= 31; j++) {
                        storing[ID].empty[l][i][j] = true;
                        storing[ID].takenBy[l][i][j] = 0;
                        storing[ID].ticket[l][i][j] = 0;
                    }
                }
                for (int i = outMonth; i <= outMonth; i++) {
                    for (int j = 1; j <= outDay; j++) {
                        storing[ID].empty[l][i][j] = true;
                        storing[ID].takenBy[l][i][j] = 0;
                        storing[ID].ticket[l][i][j] = 0;
                    }
                }
            }
        } else {
            for (int l = inYear; l <= outYear; l++) {
                if ((outMonth - inMonth) > 0) {
                    for (int i = inMonth; i <= inMonth; i++) {
                        for (int j = inDay; j <= 31; j++) {
                            storing[ID].empty[l][i][j] = true;
                            storing[ID].takenBy[l][i][j] = 0;
                            storing[ID].ticket[l][i][j] = 0;
                        }
                    }
                    for (int i = (inMonth + 1); i <= (outMonth - 1); i++) {
                        for (int j = 1; j <= 31; j++) {
                            storing[ID].empty[l][i][j] = true;
                            storing[ID].takenBy[l][i][j] = 0;
                            storing[ID].ticket[l][i][j] = 0;
                        }
                    }
                    for (int i = outMonth; i <= outMonth; i++) {
                        for (int j = 1; j <= outDay; j++) {
                            storing[ID].empty[l][i][j] = true;
                            storing[ID].takenBy[l][i][j] = 0;
                            storing[ID].ticket[l][i][j] = 0;
                        }
                    }
                } else {
                    for (int i = inMonth; i <= outMonth; i++) {
                        for (int j = inDay; j <= outDay; j++) {
                            storing[ID].empty[l][i][j] = true;
                            storing[ID].takenBy[l][i][j] = 0;
                            storing[ID].ticket[l][i][j] = 0;
                        }
                    }
                }
            }
        }

        dataBase.saveChange(storing);
    }

    public static String tierName(int tier) {
        if (tier == 1) {
            return "economy";
        } else if (tier == 2) {
            return "normal";
        } else if (tier == 3) ;
        {
            return "luxury";
        }
    }

    public static void allRoomsBookings() {
        for (int i = 0; i < dataBase.seeRoomsNo().size(); i++) {
            int ID = (dataBase.seeRoomsNo().get(i));
            Room[] storing = dataBase.seeRooms();
            boolean free = true;
            for (int j = 1; j < 4; j++) {
                for (int k = 1; k < 13; k++) {
                    for (int l = 1; l < 32; l++) {
                        if (storing[ID].empty[j][k][l] == false) {
                            free = false;
                        }
                    }
                }
            }
            if (free == true) {
            } else {
                System.out.print("Room No." + ID + " is taken: ");
            }
            boolean inBooking = false;
            int IDUsing = 0;
            for (int j = 1; j < 4; j++) {
                for (int k = 1; k < 13; k++) {
                    for (int l = 1; l < 32; l++) {
                        if (storing[ID].empty[j][k][l] == false && inBooking == false) {
                            System.out.print("By: " + storing[ID].takenBy[j][k][l] + " From: " + (2020 + j) + "-" + k + "-" + l);
                            IDUsing = storing[ID].takenBy[j][k][l];
                            inBooking = true;
                        }
                        if (storing[ID].empty[j][k][l] == true && inBooking == true) {

                            if ((l - 1) == 0) {
                                if (k == 2 || k == 4 || k == 6 || k == 8 || k == 9 || k == 11 || k == 1) {
                                    System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 31);
                                } else if (k == 5 || k == 7 || k == 10 || k == 12) {
                                    System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 30);
                                } else if (k == 3) {
                                    System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 28);
                                }
                                if ((k - 1) == 0) {
                                    System.out.println(" Until: " + ((2020 + j) - 1) + "-" + 12 + "-" + 31);
                                }
                            } else {
                                System.out.println(" Until: " + (2020 + j) + "-" + k + "-" + (l - 1));
                            }
                            inBooking = false;
                        }
                        if (inBooking == true && storing[ID].takenBy[j][k][l] != IDUsing) {
                            if ((l - 1) == 0) {
                                if (k == 2 || k == 4 || k == 6 || k == 8 || k == 9 || k == 11 || k == 1) {
                                    System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 31);
                                } else if (k == 5 || k == 7 || k == 10 || k == 12) {
                                    System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 30);
                                } else if (k == 3) {
                                    System.out.println(" Until: " + (2020 + j) + "-" + (k - 1) + "-" + 28);
                                }
                                if ((k - 1) == 0) {
                                    System.out.println(" Until: " + ((2020 + j) - 1) + "-" + 12 + "-" + 31);
                                }
                            } else {
                                System.out.println(" Until: " + (2020 + j) + "-" + k + "-" + (l - 1));
                            }
                            System.out.print("By: " + dataBase.seeUsers().get(storing[ID].takenBy[j][k][l]).getLogin() + " From: " + (2020 + j) + "-" + k + "-" + l);
                            IDUsing = storing[ID].takenBy[j][k][l];
                        }
                    }
                }

            }

        }
    }

    public static void getTicket(int user, int room)
    {
        Room[] storing = dataBase.seeRooms();
        boolean free = true;

        String print = "Could not find a booking";
        try {
            String convert;
            for (int j = 1; j < 4; j++) {
                for (int k = 1; k < 13; k++) {
                    for (int l = 1; l < 32; l++) {
                        if (storing[room].takenBy[j][k][l] == user) {
                            convert = String.valueOf(storing[room].ticket[j][k][l]);
                            print = "You owe us ";
                            print += convert;
                            print += "\nWe accept Mastercard, Visa, Dollars\nGolden coins, Euros, Złoty, Firstborn child";
                            //GUI.Diag(print);
                            break;
                        }

                    }
                }
            }
        } catch (NullPointerException e){
        }
        GUI.Diag(print);
    }


}