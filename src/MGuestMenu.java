import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class MGuestMenu extends JFrame {
    private JTextArea printOutArea;
    private JPanel panelImg;
    private JTextField editSize;
    private JTextField editTier;
    private JButton AddGuest;
    private JButton goBack;
    private JScrollPane contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton editGuestButton;
    private JButton deleteGuestButton;
    private JTextField delGuestID;
    private JTextField delGuestName;
    private JTextField delGuestSurname;
    private JTextField textField12;
    private JTextField textField13;
    private JButton button1;

    public MGuestMenu() {
        goBack.addActionListener(e -> {UserMenu.MainMenu();this.dispose(); });
        AddGuest.addActionListener(e -> {


        });

        this.setContentPane(panelImg);
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AddGuest.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Guest Added");
        });
        editGuestButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Guest Deleted");
        });
        deleteGuestButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Are you sure you want to delete?");
        });

        delGuestID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delGuestName.setText("billy");
                delGuestSurname.setText("Ballo");
            }
        });
    }

    public String printRooms() {
        String data = "";
        for (int i = 0; i < FileManager.getStaff().size(); i++) {
            data = data + FileManager.getStaff().get(i).toString() + "\n";
        }
        return data;
    }

    public static void menuGuest() {

        JFrame m = new MGuestMenu();
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
