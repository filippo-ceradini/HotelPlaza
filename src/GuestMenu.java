import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class GuestMenu extends JFrame {
    FileManager fm = new FileManager();
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
    private JTextField newBookDayFrom;
    private JTextField newBookMonthFrom;
    private JTextField newBookYearFrom;
    private JTextField newBookDayTo;
    private JTextField newBookMonthTo;
    private JTextField newBookYearTo;
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
    private JButton editBookingButton;
    private JButton deleteBookingButton;
    private JTextField newBookingRoom;
    private JTextField editBookingRoom;
    private JTextField delBookingRoom;
    private JComboBox comboTier;
    private JComboBox comboSize;
    private JButton availableByTierButton;
    private JButton availableBySizeButton;
    private JTextField addDays;
    private JTextField removeDays;
    private JTextArea textArea1;

    public GuestMenu(int GuestID) {
        AvlbRooms.addActionListener(e -> {
            Room.availableByDate(Integer.parseInt(yearFrom.getText()),Integer.parseInt(yearTo.getText()),Integer.parseInt(monthFrom.getText()),Integer.parseInt(monthTo.getText()),Integer.parseInt(dayFrom.getText()),Integer.parseInt(dayTo.getText()));
        });
        availableByTierButton.addActionListener(e -> {
            Room.availableByTier(comboTier.getSelectedIndex() + 1, Integer.parseInt(yearFrom.getText()), Integer.parseInt(yearTo.getText()), Integer.parseInt(monthFrom.getText()), Integer.parseInt(monthTo.getText()), Integer.parseInt(dayFrom.getText()), Integer.parseInt(dayTo.getText()));
        });
        availableBySizeButton.addActionListener(e -> {
            Room.availableBySize(comboSize.getSelectedIndex() + 1, Integer.parseInt(yearFrom.getText()), Integer.parseInt(yearTo.getText()), Integer.parseInt(monthFrom.getText()), Integer.parseInt(monthTo.getText()), Integer.parseInt(dayFrom.getText()), Integer.parseInt(dayTo.getText()));
        });

        addNew.addActionListener(e -> {
            Room.takeRoom(Integer.parseInt(newBookYearFrom.getText()), Integer.parseInt(newBookYearTo.getText()), Integer.parseInt(newBookMonthFrom.getText()), Integer.parseInt(newBookMonthTo.getText()), Integer.parseInt(newBookDayFrom.getText()), Integer.parseInt(newBookDayTo.getText()), GuestID, Integer.parseInt(newBookingRoom.getText()));
            GUI.Diag("booking added \n" + " in Room No " + newBookingRoom.getText()
                    + "\n from: " + newBookDayFrom.getText() + "/" + newBookMonthFrom.getText() + "/" + newBookYearFrom.getText()
                    + "\n to: " + newBookDayTo.getText() + "/" + newBookMonthTo.getText() + "/" + newBookYearTo.getText());
            Room.availableByDate(2021,2022,12,12,1,1);
            Room.guestsRooms(GuestID);
        });

        editBookingButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "You want to edit: \n" + "Booking in Room No " + editBookingRoom.getText()
                    + "\n from: " + editBookDayFrom.getText() + "/" + editBookMonthFrom.getText() + "/" + editBookYearFrom.getText()
                    + "\n to: " + editBookDayTo.getText() + "/" + editBookMonthTo.getText() + "/" + editBookYearTo.getText());
            switch (result) {
                case JOptionPane.YES_OPTION:
                    int days = Integer.parseInt(editBookDayTo.getText()) + Integer.parseInt(addDays.getText()) - Integer.parseInt(removeDays.getText());
                    Room.cancelBooking(Integer.parseInt(editBookYearFrom.getText()), Integer.parseInt(editBookYearTo.getText()), Integer.parseInt(editBookMonthFrom.getText()), Integer.parseInt(editBookMonthTo.getText()), Integer.parseInt(editBookDayFrom.getText()), Integer.parseInt(editBookDayTo.getText()), Integer.parseInt(editBookingRoom.getText()));
                    Room.takeRoom(Integer.parseInt(editBookYearFrom.getText()), Integer.parseInt(editBookYearTo.getText()), Integer.parseInt(editBookMonthFrom.getText()), Integer.parseInt(editBookMonthTo.getText()), Integer.parseInt(editBookDayFrom.getText()), days, GuestID, Integer.parseInt(editBookingRoom.getText()));
                    Room.availableByDate(2021,2022,12,12,1,1);
                    Room.guestsRooms(GuestID);
                    editBookingRoom.setText("");
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
        });
        editBookingRoom.addActionListener(e -> {
            int[] date = Room.getBookingDate(GuestID, Integer.parseInt(editBookingRoom.getText()));
            editBookYearFrom.setText(String.valueOf(date[0] + 2020));
            editBookMonthFrom.setText(String.valueOf(date[1]));
            editBookDayFrom.setText(String.valueOf(date[2]));
            editBookYearTo.setText(String.valueOf(date[3] + 2020));
            editBookMonthTo.setText(String.valueOf(date[4]));
            editBookDayTo.setText(String.valueOf(date[5]));
        });

        deleteBookingButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Do you want to delete: \n" + " Booking in Room No " + delBookingRoom.getText()
                    + "\n from: " + delBookDayFrom.getText() + "/" + delBookMonthFrom.getText() + "/" + delBookYearFrom.getText()
                    + "\n to: " + delBookDayTo.getText() + "/" + delBookMonthTo.getText() + "/" + delBookYearTo.getText() + " ?");
            switch (result) {
                case JOptionPane.YES_OPTION:
                    Room.cancelBooking(Integer.parseInt(delBookYearFrom.getText()), Integer.parseInt(delBookYearTo.getText()), Integer.parseInt(delBookMonthFrom.getText()), Integer.parseInt(delBookMonthTo.getText()), Integer.parseInt(delBookDayFrom.getText()), Integer.parseInt(delBookDayTo.getText()), Integer.parseInt(delBookingRoom.getText()));
                    Room.availableByDate(2021,2022,12,12,1,1);
                    Room.guestsRooms(GuestID);
                    delBookingRoom.setText("");
                    break;
                case JOptionPane.NO_OPTION:
                    break;
                case JOptionPane.CANCEL_OPTION:
                    break;
                case JOptionPane.CLOSED_OPTION:
                    break;
            }
        });
        delBookingRoom.addActionListener(e -> {
            int[] date = Room.getBookingDate(GuestID, Integer.parseInt(delBookingRoom.getText()));
            delBookYearFrom.setText(String.valueOf(date[0] + 2020));
            delBookMonthFrom.setText(String.valueOf(date[1]));
            delBookDayFrom.setText(String.valueOf(date[2]));
            delBookYearTo.setText(String.valueOf(date[3] + 2020));
            delBookMonthTo.setText(String.valueOf(date[4]));
            delBookDayTo.setText(String.valueOf(date[5]));
        });

        goBack.addActionListener(e -> {
            try {
                GUI.login();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.dispose();});

        if(backgrdPanel==null){
            //System.out.println("vaffanculo");
        }
        this.setContentPane(backgrdPanel);
        this.setSize(1280, 800);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        comboSize = new JComboBox();
        comboSize.addItem("1");
        comboSize.addItem("2");
        comboSize.addItem("3");

        comboTier = new JComboBox();
        comboTier.addItem("Economy");
        comboTier.addItem("Normal");
        comboTier.addItem("Luxury");


    }
    public static void GuestMenue(int GuestID) {
        FileManager fm = new FileManager();
        JFrame m = new GuestMenu(GuestID);
        m.setVisible(true);
        for (Guest g:fm.seeUsers()
        ) {
            if (g.getUserID()==GuestID){
                GUI.Diag("Hello "+g.getName());
            }
        }
        Room.availableByDate(2021,2022,12,12,1,1);
        Room.guestsRooms(GuestID);
    }

    public static void main(String[] args) {
        GuestMenue(2);
    }


}
