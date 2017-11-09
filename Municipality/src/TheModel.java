/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author sareek
 */
public class TheModel extends AbstractTableModel {
    
private String[] columns;
private Object[][] row;
public TheModel(){}
public TheModel(Object[][] data, String[] columnName){
    this.row=data;
    this.columns=columnName;
}
public Class getColumnClass(int column){
// 4 is the index of the column image
        if(column == 3){
            return Icon.class;
        }
        else{
            return getValueAt(0,column).getClass();
        }
    }
    
    
    public int getRowCount() {
     return this.row.length;
    }

    public int getColumnCount() {
     return this.columns.length;
    }

    
    public Object getValueAt(int rowIndex, int columnIndex) {
    
    return this.row[rowIndex][columnIndex];
    }
    public String getColumnName(int col){
        return this.columns[col];
    }

    void setRowCount(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}