import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class DoctorsTableModel extends AbstractTableModel{

    private ArrayList<Doctor> TableList= new ArrayList<>();
    private String[] columnNames={"Doctor's Name", "Surname", "License Number","Specialisation" };

    public DoctorsTableModel(ArrayList<Doctor> list){
        this.TableList=list;
    }
    @Override
    public int getRowCount() {
        return TableList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object temp=null;
        if(columnIndex==0){
            temp=TableList.get(rowIndex).getName();
        }
        else if(columnIndex==1){
            temp=TableList.get(rowIndex).getSurname();
        }
        else if(columnIndex==2){
            temp=TableList.get(rowIndex).getLicenseNum();
        }
        else{
            temp= TableList.get(rowIndex).getSpecialisation();
        }
        return temp;
    }
    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }
}
