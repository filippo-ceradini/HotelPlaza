import java.io.*;
import java.sql.Array;

public class Print {
    public static void print(String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("print.txt"));
        writer.write(data);
        writer.close();
    }

    public static void test(){
        for (Room r: FileManager.readRooms()
    ) {
        System.out.println(r.toString()+"\n");
    }}


    public static String testPrintRooms(){
        String returnData = "";
        for (Room r: FileManager.readRooms()
             ) {
            returnData += r.toString()+"\n";
        }
        return  returnData;
    }
}
