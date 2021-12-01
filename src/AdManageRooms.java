import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class AdManageRooms extends JFrame {
    private static FileManager l = new FileManager();
    private JTextArea printOutArea;
    private JPanel panelImg;
    private JTextField editSize;
    private JTextField editTier;
    private JButton editButton;
    private JButton goBack;
    private JScrollPane contentPane;
    private JTextField ediRoomNr;
    private JTextField editPrice;
    private JButton button1;

    public AdManageRooms() {
        goBack.addActionListener(e -> {
            try {
                GUI.login();
                this.dispose();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        });
        editButton.addActionListener(e -> {
            Room.createRoom(Integer.parseInt(editSize.getText()),Integer.parseInt(editTier.getText()),Integer.parseInt(ediRoomNr.getText()));
            GUI.Diag("Room Successfuly Edited");
            Room.allRooms();
        });

        this.setContentPane(panelImg);
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ediRoomNr.addActionListener(e -> {
            //editSize.setText(l.seeRooms()[Integer.parseInt(ediRoomNr.getText())]);
        });
        editTier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        editSize.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Room newRoom = new Room();
                Room[] g = l.seeRooms();
                try {
                    for (int i = 1; i < l.seeRooms().length; i++) {
                        if (g[i].getID() == Integer.parseInt(ediRoomNr.getText())) {
                            newRoom = g[i];
                        }
                    }
                } catch (NullPointerException ne) {

                }

                editSize.setText(String.valueOf(newRoom.getSize()));
                editTier.setText(String.valueOf(newRoom.getTier()));
                editPrice.setText(String.valueOf(newRoom.getPrice()));


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

    public static void menuRooms() {

        JFrame m = new AdManageRooms();
        m.setVisible(true);
Room.allRooms();

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

    public static void main(String[] args) {
        menuRooms();
    }

}
