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

    public BookingsMenu() {

        goBack.addActionListener(e -> this.dispose());
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
