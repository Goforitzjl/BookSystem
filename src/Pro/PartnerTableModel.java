package Pro;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class PartnerTableModel extends DefaultTableModel {


    private Vector<Vector<String>> data = new Vector<>();
    private Vector<String> name = new Vector<>();

    public PartnerTableModel(Vector<Vector<String>> data, Vector<String> name) {
        //!!父类在构造方法，否则0000
        super(data, name);
        this.data = data;
        this.name = name;
    }

    public PartnerTableModel() {

    }

    public Vector<Vector<String>> getData() {
        return data;
    }

    public void setData(Vector<Vector<String>> data) {
        this.data = data;
    }

    public Vector<String> getName() {
        return name;
    }

    public void setName(Vector<String> name) {
        this.name = name;
    }
}


