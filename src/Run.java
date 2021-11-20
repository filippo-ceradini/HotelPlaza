import java.io.IOException;
import java.util.ArrayList;

public class Run {
    public static void main(String[] args) throws IOException {

        easy2.easy();

        ManageStaff.manageStaffe();
        //ManageStaff.ManageStaffe();
        //ManageRooms.MenuRooms();
        //GUI.login();
//ManageStaff.ManageStaff();
        //This in case we lose the Staff.txt
        /*ArrayList<Staff> toWrite = new ArrayList<>();
        Staff person = new Staff("Jonathan", "Bomber", "12345678", "100.000");

        for (int i = 0; i < 10; i++) {
            toWrite.add(i, person);
        }
        FileManager.editStaff(toWrite);*/
    }

}
