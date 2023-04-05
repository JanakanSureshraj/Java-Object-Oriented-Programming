
public class Consultation {
    private Date date;
    private String time;
    private int cost;
    private String doctorName;
    private String patientID;
    private String bookingID;
    private String notes;

    //constructor
    public Consultation(Date date, String time, int cost, String doctorName,
                        String patientID, String bookingID, String notes) {
        this.date = date;
        this.time = time;
        this.cost = cost;
        this.doctorName = doctorName;
        this.patientID = patientID;
        this.bookingID = bookingID;
        this.notes= notes;
    }

    //getters
    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getCost() {
        return cost;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getPatientID() {
        return patientID;
    }

    public String getBookingID() {
        return bookingID;
    }
    public String getNotes(){
        return notes;
    }

    //setters
    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
    public void setBookingID(String BookingId){
        this.bookingID=bookingID;
    }
    public void setNotes(String notes){
        this.notes=notes;
    }
}
