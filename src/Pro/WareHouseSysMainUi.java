package Pro;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class WareHouseSysMainUi extends JFrame implements ActionListener {

    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton searchButton;
    private JTextField searchText;
    private Vector<String> columnNames = new Vector<>();
    private WareHouseDao wd = WareHouseDao.getInstance();
    private Font font1 = new Font("黑体", Font.BOLD, 18);
    private Font font2 = new Font("宋体", Font.PLAIN, 16);
    private JTable table;
    private WareHouseTableModel tablemodel;
    private Index parent;
    private static Pro.WareHouseSysMainUi wareHouseSysMainUi;

    private WareHouseSysMainUi(Index parent) {
        this.parent = parent;
        init();
    }

    public static Pro.WareHouseSysMainUi getInstance(Index parent) {
        if (wareHouseSysMainUi == null) {
            wareHouseSysMainUi = new Pro.WareHouseSysMainUi(parent);
        }
        return wareHouseSysMainUi;
    }

    public void init() {
        tablemodel = getTableModel();
        table = new JTable(tablemodel);
    }

    public JPanel getTools() {
        JPanel jp = new JPanel();
        setLayout(new GridLayout(1, 5));
        deleteButton = new JButton("删除");
        deleteButton.setActionCommand("DELETE");
        deleteButton.addActionListener(this);
        addButton = new JButton("添加");
        addButton.setActionCommand("ADD");
        addButton.addActionListener(this);
        updateButton = new JButton("更新");
        updateButton.setActionCommand("UPDATE");
        updateButton.addActionListener(this);
        searchButton = new JButton("搜索");
        searchButton.setActionCommand("SEARCH");
        searchButton.addActionListener(this);
        searchText = new JTextField(10);
        searchText.setFont(font2);
        jp.add(searchButton);
        jp.add(searchText);
        jp.add(deleteButton);
        jp.add(addButton);
        jp.add(updateButton);
        return jp;
    }

    public Container getMainUi() {

//        JPanel c=new JPanel();     只可以获取部分
        Container c = getContentPane();   //获取当前全部布局
        c.setLayout(new BorderLayout());  //KEY,否则达不到预计
        table.setSelectionMode(2);  //随意选择多个
        table.setAutoResizeMode(2);
        table.setFont(font2);
        table.setRowHeight(20);

        final JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(font1);
        c.add(tableHeader, BorderLayout.NORTH);
        c.add(table);

        return c;
    }

    public String[] getselectWareHouse() {

        int row = table.getSelectedRow();
        String id = (String) table.getValueAt(row, 0);
        String name = (String) table.getValueAt(row, 1);
        String address=(String)table.getValueAt(row,2);
        String c_t = (String) table.getValueAt(row, 3);
        String u_t = (String) table.getValueAt(row, 4);
        String[] warehouse = {id,name,address,c_t,u_t};
        return warehouse;
    }

    public WareHouseTableModel getTableModel() {
        columnNames = new Vector<>();
        columnNames.add("仓库编号");
        columnNames.add("仓库名");
        columnNames.add("仓库地址");
        columnNames.add("创建时间");
        columnNames.add("更新日期");

        WareHouseTableModel tableModel1 = new WareHouseTableModel(WareHouseTableHandle.getValues(), columnNames);
//        System.out.println(tableModel1.getRowCount());
        return tableModel1;
    }

    public void refreshWareHouseDatas() {
        Vector<Vector<String>> data = WareHouseTableHandle.getValues();
        this.tablemodel.setDataVector(data, columnNames);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "DELETE":
                final int operate = JOptionPane.showConfirmDialog(parent, "确定要删除该条记录吗？",
                        "确定", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (operate == JOptionPane.YES_OPTION) {

                    final int[] selectedRows = this.table.getSelectedRows();
                    for (int row : selectedRows) {
                        int id = Integer.valueOf((String) this.table.getValueAt(row, 0));
                        WareHouse wareHouse=new WareHouse(id);
                        int i =wd.deleteWareHouse(wareHouse);
                    }
                }
                this.refreshWareHouseDatas();
                break;
            case "ADD":
                WareHouseEditDialog wareHouseEditDialog1=new WareHouseEditDialog(this, "添加图书");
                wareHouseEditDialog1.setVisible(true);
                this.refreshWareHouseDatas();
                break;
            case "UPDATE":
                System.out.println("update");
                WareHouseEditDialog wareHouseEditDialog2=new WareHouseEditDialog(this, "更新图书");
                wareHouseEditDialog2.setVisible(true);
                this.refreshWareHouseDatas();
                break;
            case "SEARCH":
                String keyword = searchText.getText();
                Vector<WareHouse> wareHouses = wd.selectWareHouse(keyword);
                Vector<Vector<String>> data = WareHouseTableHandle.convertTable(wareHouses);
                this.tablemodel.setDataVector(data, columnNames);
                break;
            default:
                break;
        }
    }
}






