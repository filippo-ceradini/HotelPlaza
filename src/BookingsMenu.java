import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class BookingsMenu extends JFrame {
    private JTextArea printOutArea;
    private JPanel panelImg;

    private JButton goBack;
    private JScrollPane contentPane;
    private JButton changeDate;
    private JTextField dayFrom;
    private JTextField monthFrom;
    private JTextField yearFrom;
    private JTextField dayTo;
    private JTextField monthTo;
    private JTextField yearTo;
    private JButton addNew;
    private JTextField guestTextField;
    private JTextField roomTextField;
    private JTextField a12TextField1;
    private JTextField a11TextField;
    private JTextField a2021TextField;
    private JTextField a12TextField;
    private JTextField a1TextField;
    private JTextField a2022TextField;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
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
    private JTextField textField8;
    private JTextField textField9;
    private JButton editBookingButton;
    private JButton deleteBookingButton;
    private JButton printReceiptButton;
    private JTextField textField10;

    public BookingsMenu() {

        goBack.addActionListener(e -> {
            UserMenu.UserMenu();
            this.dispose();});
        changeDate.addActionListener(e -> {

        });

        addNew.addActionListener(e -> {

        });

        this.setContentPane(panelImg);
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public String printRooms() {
        String data = "";
        for (int i = 0; i < FileManager.getStaff().size(); i++) {
            data = data + FileManager.getStaff().get(i).toString() + "\n";
        }
        return data;
    }

    public static void ManageBookings() {

        JFrame m = new BookingsMenu();
        m.setVisible(true);
        Print.test();
    }


    private void createUIComponents() throws IOException, InterruptedException {
        // TODO: place custom component creation code here
        final BufferedImage image = ImageIO.read(new File("background.jpg"));
        panelImg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        printOutArea = new JTextArea();


        printOutArea.setEditable(false);
        JTextAreaOutputStream out = new JTextAreaOutputStream(printOutArea);
        System.setOut(new PrintStream(out));

        //printOutArea.setText(Print.testPrintRooms());

    }


}
