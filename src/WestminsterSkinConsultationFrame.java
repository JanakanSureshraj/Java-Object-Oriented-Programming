import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;
public class WestminsterSkinConsultationFrame extends JFrame {

    //Swing components with global scope. Required for event handling in the listener inner-class.
    private JComboBox DropDownName;
    private JComboBox DropDownDate;
    private JComboBox DropDownTime;
    private JTextField txt1;
    private JCheckBox FirstConsultationCheckBox;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea NotesText;
    private JTextArea NotesImage;
    private JLabel imageViewingLabel;
    private JTextField nameTxt;
    private JTextField surNameTxt;
    private JTextField dobTxt;
    private JTextField mobileNumTxt;
    private JTextField patientIDTxt;
    private JTextField bookingIDTxt;
    private JTextField submitConfirmation;
    private ImageIcon skinImage;

    //Instantiating WestminsterSkinConsultationManager class to access the DoctorsList Data Structure
    WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

    //The doctor allocated to the patient according to availability
    private Doctor doctorAllocated;
    private String AllocatedDoctorsName;
    //To alter the consultation cost to 15 if it is the first consultation and pass it as an argument to instantiate Consultation class
    private int consultationCost;

    //Instantiating Patient and Consultation classes for setting and getting data during event handling
    Patient patient1;
    Consultation consultation1;

    public WestminsterSkinConsultationFrame(){

        Font myFont1 = new Font("SansSerif ", Font.BOLD, 15);

        //panel 1 with the Heading and the JTable
        JPanel panel1 = new JPanel(new BorderLayout());
        JLabel label1 = new JLabel("BOOK AN APPOINTMENT WITH A DERMATOLOGIST");
        label1.setFont(myFont1);
        label1.setHorizontalAlignment(JLabel.CENTER);
        panel1.add(label1, BorderLayout.NORTH);
        DoctorsTableModel tableModel = new DoctorsTableModel(manager.getDoctorsList()); //DoctorsList is being passed
        JTable table = new JTable(tableModel);
        JScrollPane panel = new JScrollPane(table);
        table.setAutoCreateRowSorter(true); //For row sorting
        panel1.add(panel); //Adding table to the panel
        String msg = "<html><font size='3'>**Click on the table's "
                + "<font color=blue>column headings</font> to sort accordingly.</html>";
        JLabel msgLabel = new JLabel(msg);
        panel1.setPreferredSize(new java.awt.Dimension(panel.getWidth(), 100));
        msgLabel.setHorizontalAlignment(JLabel.LEFT);
        msgLabel.setPreferredSize(new java.awt.Dimension(panel.getWidth(), 40));
        panel1.add(msgLabel, BorderLayout.SOUTH); //Adding the label about sorting to the panel

        add(panel1, BorderLayout.NORTH); //Adding panel 1 to the center of the frame


        String[] doctorNames = new String[manager.getDoctorsList().size()]; //Storing doctors' names for the combo box
        for (int i = 0; i < manager.getDoctorsList().size(); i++) {
            doctorNames[i] = manager.getDoctorsList().get(i).getName();
        }
        JLabel label2 = new JLabel("CHECK DOCTOR'S AVAILABILITY           Select a doctor: ");
        DropDownName = new JComboBox(doctorNames);
        JLabel label3 = new JLabel("Pick a specific date :");
        DropDownDate = new JComboBox(new String[]{"2023-01-10", "2023-01-11", "2023-01-12", "2023-01-13",
                "2023-01-14", "2023-01-15", "2023-01-16"}); //Dates for the combo box
        JLabel label4 = new JLabel("Preferred time : ");
        DropDownTime = new JComboBox(new String[]{"10-00", "11-00", "12-00", "14-00", "15-00", "16-00"});//Times for the combo box
        JButton checkButton = new JButton("CHECK");
        checkButton.setForeground(Color.blue);
        txt1 = new JTextField("Confirmation message from the system");//Displaying doctor's availability
        txt1.setBackground(Color.white);
        txt1.setEditable(false);
        txt1.setColumns(78);
        //Adding the availability related components to panel 2
        JPanel panel2 = new JPanel(new FlowLayout(5));
        panel2.add(label2);
        panel2.add(DropDownName);
        panel2.add(label3);
        panel2.add(DropDownDate);
        panel2.add(label4);
        panel2.add(DropDownTime);
        panel2.add(checkButton);
        panel2.add(txt1);
        panel2.setBackground(SystemColor.ORANGE);

        add(panel2, BorderLayout.CENTER);//Adding panel2 to the Center of the frame

        Listener listener = new Listener();

        checkButton.addActionListener(listener); //Event handling-> Checking doctor's availability

        //Obtaining patient's details
        JLabel nameLbl = new JLabel("ADD PATIENT INFORMATION                   Enter patient's name : ");
        nameTxt = new JTextField("                    ");
        JLabel surnameLbl = new JLabel("Enter patient's surname : ");
        surNameTxt = new JTextField("                       ");
        JLabel dobLbl = new JLabel("Enter patient's date of birth : ");
        dobTxt = new JTextField(" yyyy-mm-dd ");
        JLabel mobileNumLbl = new JLabel("Enter patient's mobile number : ");
        mobileNumTxt = new JTextField("E.g.- +44 7727 256265");
        JLabel patientIDLbl = new JLabel("Enter patient ID : ");
        patientIDTxt = new JTextField("  6 digits  ");
        FirstConsultationCheckBox = new JCheckBox("First Consultation");
        JLabel bookingIDlbl = new JLabel("Enter booking ID : ");
        bookingIDTxt = new JTextField("  6 digits  ");

        //For adding notes
        //Text notes
        JPanel panel4= new JPanel(new GridLayout(2,2));
        JLabel addTextNoteLabel= new JLabel("Add text notes here");
        NotesText= new JTextArea("");
        panel4.add(addTextNoteLabel);
        panel4.add(NotesText);

        //Image notes
        JPanel panel5= new JPanel(new GridLayout(2,2));
        JLabel addImageNoteLabel= new JLabel("Add image file name with path here");
        NotesImage= new JTextArea("");
        panel5.add(addImageNoteLabel);
        panel5.add(NotesImage);

        imageViewingLabel=new JLabel(skinImage); //JLabel to display the skin image
        imageViewingLabel.setSize(1,1);

        //Submitting inputs
        JButton submitButton = new JButton("SUBMIT");
        submitButton.setForeground(Color.blue);
        submitButton.addActionListener(listener);
        submitConfirmation= new JTextField("confirmation");
        submitConfirmation.setColumns(15);
        JPanel panel3= new JPanel(new GridLayout(2,2));
        panel3.add(submitButton);
        panel3.add(submitConfirmation);

        //For visualising the stored information
        //Button
        JButton visualiseButton = new JButton("View Details of Your Booking");
        visualiseButton.setForeground(Color.RED);
        visualiseButton.addActionListener(listener); //Registering the button with the listener
        //Displaying information
        textArea1 = new JTextArea("BOOKING DETAILS- CONSULTATION",5,5);
        textArea2= new JTextArea("BOOKING DETAILS- PATIENT",5,5);
        textArea3= new JTextArea("TEXT OR IMAGE NOTES",5,5);

        //Adding patient and booking details related components to panel2
        panel2.add(nameLbl);
        panel2.add(nameTxt);
        panel2.add(surnameLbl);
        panel2.add(surNameTxt);
        panel2.add(dobLbl);
        panel2.add(dobTxt);
        panel2.add(mobileNumLbl);
        panel2.add(mobileNumTxt);
        panel2.add(patientIDLbl);
        panel2.add(patientIDTxt);
        panel2.add(FirstConsultationCheckBox);
        panel2.add(bookingIDlbl);
        panel2.add(bookingIDTxt);
        panel2.add(panel4);
        panel2.add(panel5);
        panel2.add(panel3);
        panel2.add(visualiseButton);
        panel2.add(textArea1);
        panel2.add(textArea2);
        panel2.add(textArea3);
        panel2.add(imageViewingLabel);
    }
    public void encryptImage() throws IOException {
        //ENCRYPTING the IMAGE uploaded by the user
        System.out.print("Enter key for Encryption : ");
        Scanner obj= new Scanner(System.in);
        int key = obj.nextInt();
        // Selecting image for encryption
        FileInputStream fis = new FileInputStream(
                "C:\\Users\\ACER\\Desktop\\Coursework- Code\\src\\Skin Images\\Skin image.jpg");
        // Converting Image to byte array and creating a byte array of size same as the image size
        byte data[] = new byte[fis.available()];
        // Reading the array
        fis.read(data);
        int i = 0;
        // Performing an XOR operation on each value of byte array to change every value of the image
        for (byte b : data) {
            data[i] = (byte)(b ^ key);
            i++;
        }
        // Opening a file for writing
        FileOutputStream fos = new FileOutputStream(
                "C:\\Users\\ACER\\Desktop\\Coursework- Code\\src\\Skin Images\\Skin image.jpg");
        // Writing new byte array value to image which will encrypt it.
        fos.write(data);
        // Closing the opened file
        fos.close();
        fis.close();
    }
    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String btnName = e.getActionCommand(); //Identifying which button has fired the event

            //Converting combo box values to string representations
            String selectedName = DropDownName.getSelectedItem().toString();
            String selectedDate = DropDownDate.getSelectedItem().toString();
            String selectedTime = DropDownTime.getSelectedItem().toString();

            //Converting the String date from the GUI to an object of class Date
            String[] dateElements = selectedDate.split("-");//regex separates String values based on the given character
            int year = Integer.parseInt(dateElements[0]); //I have written the Swing GUI dates in the format yyyy-mm-dd
            int month = Integer.parseInt(dateElements[1]);
            int day = Integer.parseInt(dateElements[2]);
            Date consultationDate = new Date(day, month, year);

            patient1= new Patient(nameTxt.getText(),surNameTxt.getText(),dobTxt.getText(),
                    mobileNumTxt.getText(),patientIDTxt.getText());

            consultation1 = new Consultation(consultationDate, selectedTime, consultationCost,
                    selectedName, patient1.getPatientID(),
                    bookingIDTxt.getText(),NotesText.getText());

            if (FirstConsultationCheckBox.isSelected()) {
                consultationCost =15;
            }
            else{
                consultationCost=25;
            }

            if (btnName.equals("CHECK")) {

                //Testing a doctor's availability
                if (selectedName.equalsIgnoreCase("Jane") && selectedDate.equals("2023-01-14")) {
                    Random random = new Random();
                    int index = random.nextInt(0, manager.getDoctorsList().size()); //Allocating a doctor randomly
                    if (index != 1) {
                        doctorAllocated = manager.getDoctorsList().get(index);
                        AllocatedDoctorsName=doctorAllocated.getName();
                        txt1.setText("Doctor " + selectedName + " is not available on " + selectedDate + " and so you have been " +
                                "allocated to Doctor " +AllocatedDoctorsName + " on " + selectedDate + " at " + selectedTime +
                                ". Please enter further details below.");
                    }
                } else {
                    AllocatedDoctorsName=selectedName;
                    txt1.setText("Doctor " + AllocatedDoctorsName + " is available for consultation on " + selectedDate +
                            " at " + selectedTime + ". Please enter further details below.");
                }
            }

            if(btnName.equals("SUBMIT")) {
                //Setting the text notes to no text note specified if none has been entered.
                if(NotesText.getText().equalsIgnoreCase("")){
                    NotesText.setText("No text note specified.");
                }
                submitConfirmation.setText("Details have been saved!");
            }

            if(btnName.equals("View Details of Your Booking")){
                 textArea1.setText(" BOOKING DETAILS- CONSULTATION \n\n Booking ID : "+consultation1.getBookingID()+"\n Doctor: "+
                                  AllocatedDoctorsName+" "+"\n Appointment Date : "+ consultation1.getDate()+" \n Appointment Time : "+
                                   consultation1.getTime()+"\n Cost for the consultation : "+consultation1.getCost()+" Pounds");
                 textArea2.setText("BOOKING DETAILS- PATIENT \n\n Patient ID : "+ patient1.getPatientID()+"" +
                                    "\n Name : "+patient1.getName()+" "+
                                   patient1.getSurname()+"\n Date of birth : "+ patient1.getDob()+"\n Mobile Number : "+
                                   patient1.getMobileNUmber()+
                                   "\n\n Thank you for your booking at Westminster Skin Consultation Centre!");
                 textArea3.setText("TEXT NOTES\n"+consultation1.getNotes()+"\n\nIMAGE NOTES \nDisplayed here if any-->");
                 skinImage=new ImageIcon(getClass().getResource(NotesImage.getText()));
                 imageViewingLabel.setIcon(skinImage);
                 imageViewingLabel.setVisible(true);
             }
        }
    }
}

