public class Date {
    private int day;
    private int month;
    private int year;

    //constructor
    public Date(int day, int month, int year){
        if((day>0) && (day<32) && (month>0) && (month<13) && (year>1900) && (year<=2023)){
            this.day=day;
            this.month= month;
            this.year=year;
        }
        else{
            System.out.println("Date is not correct!");
        }
    }
    public void setDay(int day){
        if((day>0)&&(day<32)){
            this.day= day;
        }
        else{
            System.out.println("Out of range. Enter a day between 1 and 31. ");
        }
    }
    public void setMonth(int month){
        if((month>0)&&(month<13)){
            this.month= month;
        }
        else{
            System.out.println("Out of range. Enter a month between 1 and 12. ");
        }
    }
    public void setYear(int year){
        if((year>0)&&(year<13)){
            this.year= month;
        }
        else{
            System.out.println("Out of range. Enter a valid year");
        }
    }
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }

    public String toString(){
        String date= String.format("%04d/%02d/%02d", year, month, day);
        return date;
    }
}
