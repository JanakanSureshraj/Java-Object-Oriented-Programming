public class Patient extends Person{
    private String patientID;

    public Patient(String name, String surname, String dob, String mobileNUmber,String patientID) {
        super(name, surname, dob, mobileNUmber);
        this.patientID=patientID;
    }


    //getter method
    public String getPatientID(){
        return patientID;
    }

    //setter method
    public void setPatientID(String patientIDInput){
        this.patientID=patientIDInput;
    }
}
