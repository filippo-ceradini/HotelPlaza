import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


public class GuestGUI extends JFrame {
    private JTextArea printOutArea;
    private JPanel panelImg;
    private static FileManager dataBase = new FileManager();

    private JTextField custName;
    private JTextField custSurname;
    private JTextField custDoc;
    private JPasswordField custPassword;
    private JButton exitButton;
    private JTextField custLogin;
    private JButton addNew;
    private JTextArea textArea2;

    public GuestGUI() {

        addNew.addActionListener(e -> {
            Guest newG = new Guest();
            newG.setLogin(custLogin.getText());
            newG.setPassword(custPassword.getText());
            GUI.Diag(custPassword.getText());
            newG.setUserID(dataBase.seeUsers().size()+1);
            newG.setName(custName.getText());
            newG.setSurname(custSurname.getText());
            dataBase.addUser(newG);
            GUI.Diag("New gest Created: \n"+"name: "+custName.getText()+" surname: "+custSurname.getText()+"\n"+"login: "+custLogin.getText()+"\n"+"password: "+custPassword.getText());
            this.dispose();});
        custPassword.addActionListener(e -> {
            custLogin.setText(custName.getText()+"."+custSurname.getText());
        });



        this.setContentPane(panelImg);
        //this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        custPassword.addActionListener(e -> {

        });
    }

    public String printRooms() {
        String data = "";
        for (int i = 0; i < FileManager.getStaff().size(); i++) {
            data = data + FileManager.getStaff().get(i).toString() + "\n";
        }
        return data;
    }

    public static void GuestGUI() {

        JFrame m = new GuestGUI();
        m.setVisible(true);
        Print.test();
    }


    private void createUIComponents() throws IOException, InterruptedException {

        /*final BufferedImage image = ImageIO.read(new File("background.jpg"));
        panelImg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };*/
        panelImg = new JPanel();
        printOutArea = new JTextArea();
        textArea2= new JTextArea();

        printOutArea.setEditable(false);
        JTextAreaOutputStream out = new JTextAreaOutputStream(printOutArea);
        System.setOut(new PrintStream(out));

        //printOutArea.setText(Print.testPrintRooms());

    }


}
