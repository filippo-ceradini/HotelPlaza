import java.io.*;
import java.sql.Array;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Print {
    public static void print(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("print.txt"));
        writer.write(data);
        writer.close();
    }

    public static void test(){
        for (Room r: FileManager.seeRoomsStatic()
    ) {
        //System.out.println(r.toString()+"");
    }}


    public static String testPrintRooms(){
        String returnData = "";
        for (Room r: FileManager.seeRoomsStatic()
             ) {
            //returnData += r.toString()+"\n";
        }
        return  returnData;
    }

    public static int checkInt(String text) {
        int check = 0;
        Scanner userInput = new Scanner(System.in);
        boolean watchint = false;
        while (!watchint) {
            try {
                check = userInput.nextInt();
                watchint = true;
            } catch (InputMismatchException e) {

                userInput.nextLine();
            }
        }
        return check;
    }
}
