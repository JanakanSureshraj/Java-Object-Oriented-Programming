public class Doctor extends Person implements Comparable<Doctor>{
    private String licenseNum;
    private String specialisation;

    //constructor
    public Doctor(String name, String surname, String dob, String mobileNUmber,
                  String licenseNum, String specialisation) {
        super(name, surname, dob, mobileNUmber);
        this.licenseNum = licenseNum;
        this.specialisation = specialisation;
    }


    //get methods
    public String getLicenseNum() {
        return licenseNum;
    }

    public String getSpecialisation() {
        return specialisation;
    }
    public String toString(){
        return "Name : "+getName()+" Surname: "+getSurname()+" Date of Birth: "+getDob()+" Mobile Number : "+
                getMobileNUmber()+" License Number : "+getLicenseNum()+" Specialisation: "+getSpecialisation();
    }

    @Override
    public int compareTo(Doctor o) { //To display the list of doctors sorted according to surname
        if(getSurname().equalsIgnoreCase(o.getSurname())){
            return 0;
        }
        else if(getSurname().compareToIgnoreCase(o.getSurname())>0){
            return 1;
        }
        else{
            return -1;
        }
    }

}

