import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class GuestMenu extends JFrame {
    private JPanel backgrdPanel;
    private JTextArea printOutArea;


    private JButton goBack;
    private JScrollPane contentPane;
    private JButton AvlbRooms;
    private JTextField dayFrom;
    private JTextField monthFrom;
    private JTextField yearFrom;
    private JTextField dayTo;
    private JTextField monthTo;
    private JTextField yearTo;
    private JButton addNew;
    private JTextField roomTextField;
    private JTextField a12TextField1;
    private JTextField a11TextField;
    private JTextField a2021TextField;
    private JTextField a12TextField;
    private JTextField a1TextField;
    private JTextField a2022TextField;
    private JTextField a11TextField1;
    private JTextField a1TextField1;
    private JTextField a2022TextField1;
    private JTextField a2021TextField1;
    private JTextField a12TextField2;
    private JTextField a12TextField3;
    private JTextField a2022TextField2;
    private JTextField a12TextField4;
    private JTextField a2021TextField2;
    private JTextField a1TextField2;
    private JTextField a11TextField2;
    private JTextField a12TextField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton editBookingButton;
    private JButton deleteBookingButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextArea textArea1;

    public GuestMenu() {

       goBack.addActionListener(e -> {
            UserMenu.MainMenu();
            this.dispose();});
        AvlbRooms.addActionListener(e -> {
            Room.availableByDate(Integer.parseInt(yearFrom.getText()),Integer.parseInt(yearTo.getText()),Integer.parseInt(monthFrom.getText()),Integer.parseInt(monthTo.getText()),Integer.parseInt(dayFrom.getText()),Integer.parseInt(dayTo.getText()));
        });

        addNew.addActionListener(e -> {

        });
        if(backgrdPanel==null){System.out.println("vaffanculo");}
        this.setContentPane(backgrdPanel);
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);}




    public String printRooms() {
        String data = "";
        for (int i = 0; i < FileManager.getStaff().size(); i++) {
            data = data + FileManager.getStaff().get(i).toString() + "\n";
        }
        return data;
    }




    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
      final BufferedImage image = ImageIO.read(new File("background.jpg"));
        backgrdPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        textArea1 = new JTextArea();
        printOutArea = new JTextArea();
        printOutArea.setEditable(false);
        JTextAreaOutputStream out = new JTextAreaOutputStream(printOutArea);
        System.setOut(new PrintStream(out));


    }
    public static void GuestMenue(int GuestID) {

        JFrame m = new GuestMenu();
        m.setVisible(true);

    }

    public static void main(String[] args) {
        JFrame m = new GuestMenu();
        m.setVisible(true);
    }


}
