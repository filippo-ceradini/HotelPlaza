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


public class BookingsMenu extends JFrame {

    private JTextArea printOutArea;
    private JPanel panelImg;
    FileManager fm = new FileManager();
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
    private JTextField newBookName;
    private JTextField newBookRoom;
    private JTextField newBookDayFrom;
    private JTextField newBookMonthFrom;
    private JTextField newBookYearFrom;
    private JTextField newBookDayTo;
    private JTextField newBookMonthTo;
    private JTextField newBookYearTo;
    private JTextField newBookSurname;
    private JTextField editBookGuestName;
    private JTextField delBookGuestName;
    private JTextField editBookGuestSurname;

    private JTextField editBookMonthFrom;
    private JTextField editBookMonthTo;
    private JTextField editBookYearTo;
    private JTextField editBookYearFrom;
    private JTextField editBookDayTo;
    private JTextField editBookDayFrom;
    private JTextField delBookYearTo;
    private JTextField delBookDayTo;
    private JTextField delBookYearFrom;
    private JTextField delBookMonthTo;
    private JTextField delBookMonthFrom;
    private JTextField delBookDayFrom;
    private JTextField editBookRoomNo;
    private JTextField delBookRoomNo;
    private JButton editBookingButton;
    private JButton deleteBookingButton;
    private JButton printReceiptButton;
    private JTextField roomNumberReceipt;
    private JButton viewAvailableRooms;
    private JTextField newBookGuestID;
    private JTextField editBookGuestID;
    private JTextField delBookGuestID;
    private JTextField guestIDReceipt;
    private JComboBox comboSize;
    private JComboBox comboTier;
    private JButton availableBySizeButton;
    private JButton availableByTierButton;
    private JTextField delBookGuestSurname;

    public BookingsMenu() {

        goBack.addActionListener(e -> {
            UserMenu.MainMenu();
            this.dispose();});


        addNew.addActionListener(e -> {
            Room.takeRoom(Integer.parseInt(newBookYearFrom.getText()),Integer.parseInt(newBookYearTo.getText()),Integer.parseInt(newBookMonthFrom.getText()),Integer.parseInt(newBookMonthTo.getText()),Integer.parseInt(newBookDayFrom.getText()),Integer.parseInt(newBookDayTo.getText()),Integer.parseInt(newBookGuestID.getText()),Integer.parseInt(newBookRoom.getText()));
            GUI.Diag("booking added \n" + newBookName.getText()+" "+ newBookSurname.getText() +" in Room No "+newBookRoom.getText()
            +"\n from: "+newBookDayFrom.getText()+"/"+newBookMonthFrom.getText()+"/"+newBookYearFrom.getText()
            +"\n to: "+newBookDayTo.getText()+"/"+newBookMonthTo.getText()+"/"+newBookYearTo.getText());
            newBookName.setText("");
            newBookSurname.setText("");
            newBookGuestID.setText("");
            newBookRoom.setText("");
            Room.allRoomsStatus();
        });

        this.setContentPane(panelImg);
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        viewAvailableRooms.addActionListener(e -> {
            Room.availableByDate(Integer.parseInt(yearFrom.getText()),Integer.parseInt(yearTo.getText()),Integer.parseInt(monthFrom.getText()),Integer.parseInt(monthTo.getText()),Integer.parseInt(dayFrom.getText()),Integer.parseInt(dayTo.getText()));
            System.out.println("Rooms Booked");
            Room.allRoomsBookings();
        });
        editBookingButton.addActionListener(e -> {
            //todo edit a booking
        });
        deleteBookingButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "booking added \n" + delBookGuestName.getText()+" "+ delBookGuestSurname.getText() +" in Room No "+delBookRoomNo.getText()
                    +"\n from: "+delBookDayFrom.getText()+"/"+delBookMonthFrom.getText()+"/"+delBookYearFrom.getText()
                    +"\n to: "+delBookDayTo.getText()+"/"+delBookMonthTo.getText()+"/"+delBookYearTo.getText());
            switch (result) {
                case JOptionPane.YES_OPTION:
                    Room.cancelBooking(Integer.parseInt(delBookYearFrom.getText()),Integer.parseInt(delBookYearTo.getText()),Integer.parseInt(delBookMonthFrom.getText()),Integer.parseInt(delBookMonthTo.getText()),Integer.parseInt(delBookDayFrom.getText()),Integer.parseInt(delBookDayTo.getText()),Integer.parseInt(delBookRoomNo.getText()));
                    GUI.Diag("Booking deleted");
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;

            }
            Room.allRoomsStatus();
        });
        newBookSurname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        editBookGuestSurname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        printReceiptButton.addActionListener(e -> {
            Room.getTicket(Integer.parseInt(guestIDReceipt.getText()),Integer.parseInt(roomNumberReceipt.getText()));
        });
        availableBySizeButton.addActionListener(e -> {

            Room.availableBySize(comboSize.getSelectedIndex()+1,Integer.parseInt(yearFrom.getText()),Integer.parseInt(yearTo.getText()),Integer.parseInt(monthFrom.getText()),Integer.parseInt(monthTo.getText()),Integer.parseInt(dayFrom.getText()),Integer.parseInt(dayTo.getText()));
        });
        availableByTierButton.addActionListener(e -> {
            Room.availableByTier(comboTier.getSelectedIndex()+1,Integer.parseInt(yearFrom.getText()),Integer.parseInt(yearTo.getText()),Integer.parseInt(monthFrom.getText()),Integer.parseInt(monthTo.getText()),Integer.parseInt(dayFrom.getText()),Integer.parseInt(dayTo.getText()));
        });
        newBookGuestID.addActionListener(e -> {
            for (Guest g: fm.seeUsers()
                 ) {
                if (g.getUserID()==Integer.parseInt(newBookGuestID.getText())) {
                    newBookName.setText(g.getName());
                    newBookSurname.setText(g.getSurname());
                }
            }
        });
        newBookGuestID.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (Guest g: fm.seeUsers()
                ) {
                    if (g.getName().equals(newBookName.getText())&&g.getSurname().equals(newBookSurname.getText())) {
                        newBookGuestID.setText(String.valueOf(g.getUserID()));
                    }
                }
            }
        });
    }

    /*public String printRooms() {
        String data = "";
        for (int i = 0; i < FileManager.getStaff().size(); i++) {
            data = data + FileManager.getStaff().get(i).toString() + "\n";
        }
        return data;
    }*/

    public static void ManageBookings() {

        JFrame m = new BookingsMenu();
        m.setVisible(true);
        Room.allRoomsStatus();

    }


    private void createUIComponents() throws IOException, InterruptedException {

        final BufferedImage image = ImageIO.read(new File("background.jpg"));
        panelImg = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };
        printOutArea = new JTextArea();
        comboSize = new JComboBox();
        comboSize.addItem("1");
        comboSize.addItem("2");
        comboSize.addItem("3");

        comboTier=new JComboBox();
        comboTier.addItem("Economy");
        comboTier.addItem("Normal");
        comboTier.addItem("Luxury");

        printOutArea.setEditable(false);
        JTextAreaOutputStream out = new JTextAreaOutputStream(printOutArea);
        System.setOut(new PrintStream(out));

        //printOutArea.setText(Print.testPrintRooms());

    }

    public static void main(String[] args) {
        Room.deleteGuestRooms(9);
        ManageBookings();
    }

}
