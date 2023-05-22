import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager{

    //Required Data Structures
    private static ArrayList<Doctor> DoctorsList= new ArrayList<Doctor>(); //For maintaining the list of 10 doctors (instances)
    private HashMap<String, Doctor> hashMap= new HashMap<String, Doctor>(); //key value matching to find the doctor using license number

    //Getter
    public ArrayList<Doctor> getDoctorsList(){
        return DoctorsList;
    }

    @Override
    public void addDoctor() {
        System.out.println("This Skin Consultation Centre can allocate a maximum of 10 doctors.\nHow many doctors " +
                "do you wish to add now?");
        Scanner obj= new Scanner(System.in);
        int num= obj.nextInt();

        //Creating arrays to hold data about the Doctor instances
        String[] name = new String[num];
        String[] surname = new String[num];
        String[] dob = new String[num];
        String[] mobileNum = new String[num];
        String[] licenseNum = new String[num];
        String[] specialisation = new String[num];

        if(num<=10){ // The centre can allocate 10 doctors at most.
            for(int i=0;i<num;i++){
                System.out.println("Enter the name of doctor "+i+" : ");
                name[i]= obj.next();
                System.out.println("Enter the surname of doctor "+i+" : ");
                surname[i]= obj.next();
                System.out.println("Enter the date of birth of doctor "+i+" in the format yyyy-mm-dd : ");
                dob[i]= obj.next();
                System.out.println("Enter the mobile number of doctor "+i+" : ");
                mobileNum[i]= obj.next();
                System.out.println("Enter the license number of doctor "+i+" : ");
                licenseNum[i]= obj.next();
                System.out.println("Enter the specialisation of doctor "+i+" : ");
                specialisation[i]=obj.next();

                Doctor doctor = new Doctor(name[i], surname[i], dob[i], mobileNum[i], licenseNum[i],specialisation[i]);
                DoctorsList.add(doctor);
                hashMap.put(doctor.getLicenseNum(), doctor);
            }
        }
        else{
            System.out.println("The centre can allocate a maximum of 10 doctors.");
        }
        for(int i=0; i<DoctorsList.size();i++){
            System.out.println(DoctorsList.get(i));
        }
    }

    @Override
    public void deleteDoctor() {
        System.out.println("Enter the license number of the doctor you want to delete : ");
        Scanner obj1= new Scanner(System.in);
        String licenseNum= obj1.next();
        Doctor returned_value = (Doctor)hashMap.remove(licenseNum);
        if(DoctorsList.contains(returned_value)){
            DoctorsList.remove(returned_value);
            System.out.println("Information of the doctor who was deleted from the system : ");
            System.out.println(returned_value);
            System.out.println("Total number of doctors in the centre are : "+DoctorsList.size());
        }
        else{
            System.out.println("License number does not match with any of the doctors!");
        }
    }

    @Override
    public void printListOfDoctors() {
        Collections.sort(DoctorsList);
        System.out.println("List has been ordered alphabetically according to the doctor's surname. Printing...");
        for(Doctor doctor: DoctorsList){
            System.out.println(doctor);
        }
    }

    @Override
    public void saveInAFile()  {
        System.out.println("Press w to write to a file or r to read from an existing file : ");
        Scanner obj= new Scanner(System.in);
        String input= obj.next();

        if(input.equalsIgnoreCase("w")){
            try { //writing to the file
                FileWriter myWriter = new FileWriter("DoctorsList.txt");
                for (Doctor doctor:DoctorsList){
                    myWriter.write(doctor.getName()+" "+doctor.getSurname()+" "+doctor.getDob()+" "+
                            doctor.getMobileNUmber()+" "+doctor.getLicenseNum()+" "+doctor.getSpecialisation()+
                            "\n");
                }
                myWriter.close();
                System.out.println("Successfully written to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        else if(input.equalsIgnoreCase("r")){
            try{ //Reading from the file
                File myObj= new File("DoctorsList.txt");
                Scanner myReader= new Scanner(myObj);
                while(myReader.hasNextLine()){
                    String data= myReader.nextLine();
                    String elements[] = data.split(" ");
                    Doctor doctor= new Doctor(elements[0],elements[1],elements[2],elements[3],elements[4],elements[5]);
                    DoctorsList.add(doctor);
                }
                myReader.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("An error occurred!");
                e.printStackTrace();
            }
            System.out.println("Displaying the DoctorsList ArrayList that was created using data from the file. ");
            for (Doctor doctor: DoctorsList){
                System.out.println(doctor);
            }
        }
        else{
            System.out.println("Enter a valid character.");
        }
    }

    public void openGui() throws IOException {
        WestminsterSkinConsultationFrame frame= new WestminsterSkinConsultationFrame();
        frame.setTitle("Westminster Skin Consultation Centre");
        frame.setLayout(new GridLayout(2,1,5,5));
        frame.setVisible(true);
        frame.setSize(1150,600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.encryptImage();
    }

    public void decryptImage() throws IOException { //ENCRYPTION will be performed right after the image is uploaded while SWING GUI is running.
        //DECRYPTING the IMAGE uploaded by the user
        System.out.println("Do you want to decrypt the image now? yes- y or no- n");
        Scanner obj = new Scanner(System.in);
        String input= obj.next();
        if(input.equalsIgnoreCase("y")){
            System.out.println("Enter Encryption key to decrypt : ");
            int key = obj.nextInt();

            // Selecting image for Decryption.
            FileInputStream fis = new FileInputStream(
                    "C:\\Users\\ACER\\Desktop\\Coursework- Code\\src\\Skin Images\\Skin image.jpg");

            // Converting Image to byte array and creating a byte array of size same as the image size
            byte data[] = new byte[fis.available()];

            // Reading the array
            fis.read(data);
            int i = 0;

            // Performing an XOR operation
            // on each value of
            // byte array to Decrypt it.
            for (byte b : data) {
                data[i] = (byte)(b ^ key);
                i++;
            }
            // Opening a file for writing
            FileOutputStream fos = new FileOutputStream(
                    "C:\\Users\\ACER\\Desktop\\Coursework- Code\\src\\Skin Images\\Skin image.jpg");
            // Writing the decrypted data on the Image
            fos.write(data);
            fos.close();
            fis.close();
            System.out.println("Image decrypted. Program will exit once Swing GUI is closed..");
        }
        else{
            System.out.println("Image not decrypted now. Program will exit shortly. ");
        }
    }

    public static void main(String[]args) throws IOException {
        WestminsterSkinConsultationManager manager1= new WestminsterSkinConsultationManager();
        
        System.out.println("--Skin Consultation Centre--");
        String option= "";
        while(!(option.equals("EXT"))){
            System.out.println("\nEnter option: \nAdd- Add a Doctor to the system. ");
            System.out.println("Del- Delete a doctor from the system. ");
            System.out.println("Print- Print the list of doctors in the system. ");
            System.out.println("Save- Save in a file or read from a file. ");
            System.out.println("GUI- Open the Graphical User Interface for visualization. ");
            System.out.println("Decrypt- Decrypt the added notes. ");
            Scanner obj= new Scanner(System.in);
            option= obj.next();

            switch (option){
                case "Add":
                    manager1.addDoctor();
                    break;
                case "Del":
                    manager1.deleteDoctor();
                    break;
                case "Print":
                    manager1.printListOfDoctors();
                    break;
                case "Save":
                    manager1.saveInAFile();
                    break;
                case "GUI":
                    manager1.openGui();
                    break;
                case "Decrypt":
                    manager1.decryptImage();
                    break;
                case "EXT":
                    System.out.println("Program Terminated...");
                    return;
            }
        }
    }
}
