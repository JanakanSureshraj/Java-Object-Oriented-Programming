public class Person{
    private String name;
    private String surname;
    private String dob;
    private String mobileNUmber;

    //constructor
    public Person(String name, String surname, String dob, String mobileNUmber){
        this.name= name;
        this.surname= surname;
        this.dob= dob;
        this.mobileNUmber= mobileNUmber;
    }

    //get methods to obtain encapsulated data
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }
    public String getDob(){
        return dob;
    }
    public String getMobileNUmber(){
        return mobileNUmber;
    }

    //relevant set methods
    public void setName(String nameInput){
        this.name= nameInput;
    }
    public void setSurname(String surnameInput){
        this.name=surnameInput;
    }
    public void setDob(String dobInput){
        this.dob= dobInput;
    }
    public void setMobileNUmber(String mobileNUmberInput){
        this.mobileNUmber=mobileNUmberInput;
    }

}
