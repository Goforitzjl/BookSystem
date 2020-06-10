package Pro;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;


public class PartnerSysMainUi extends JFrame implements ActionListener {

    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton searchButton;
    private JTextField searchText;
    private Vector<String> columnNames = new Vector<>();
    private PartnerDao wd = PartnerDao.getInstance();
    private Font font1 = new Font("黑体", Font.BOLD, 18);
    private Font font2 = new Font("宋体", Font.PLAIN, 16);
    private JTable table;
    private PartnerTableModel tablemodel;   //1
    private Index parent;
    private static Pro.PartnerSysMainUi PartnerSysMainUi;

    private PartnerSysMainUi(Index parent) {
        this.parent = parent;
        init();
    }

    public static Pro.PartnerSysMainUi getInstance(Index parent) {
        if (PartnerSysMainUi == null) {
            PartnerSysMainUi = new Pro.PartnerSysMainUi(parent);
        }
        return PartnerSysMainUi;
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

    public String[] getselectPartner() {

        int row = table.getSelectedRow();
        System.out.println("here0");
        System.out.println(row);
        String id = (String) table.getValueAt(row, 0);

        System.out.println("here1");
        String name = (String) table.getValueAt(row, 1);
        String type = (String) table.getValueAt(row, 2);
        String discount = (String) table.getValueAt(row, 3);
        String address = (String) table.getValueAt(row, 4);
        String bank_name = (String) table.getValueAt(row, 5);
        String bank_account = (String) table.getValueAt(row, 6);
        String tax_number = (String) table.getValueAt(row, 7);
        String contact_person = (String) table.getValueAt(row, 8);
        String contact_tel = (String) table.getValueAt(row, 9);
        String c_t = (String) table.getValueAt(row, 10);
        String u_t = (String) table.getValueAt(row, 11);
        String[] partner = {id, name, type, discount, address, bank_name, bank_account, tax_number, contact_person, contact_tel, u_t, c_t};
        System.out.println("here2");
        return partner;
    }

    public PartnerTableModel getTableModel() {
        columnNames = new Vector<>();
        columnNames.add("单位编号");
        columnNames.add("单位名称");
        columnNames.add("单位类型");
        columnNames.add("折扣率");
        columnNames.add("单位地址");
        columnNames.add("开户银行");
        columnNames.add("银行账号");
        columnNames.add("税号");
        columnNames.add("联系人");
        columnNames.add("联系人电话");
        columnNames.add("更新时间");
        columnNames.add("创建时间");

        PartnerTableModel tableModel1 = new PartnerTableModel(PartnerTableHandle.getValues(), columnNames);
//        System.out.println(tableModel1.getRowCount());
        return tableModel1;
    }

    public void refreshPartnerDatas() {
        Vector<Vector<String>> data = PartnerTableHandle.getValues();
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
                        Partner Partner = new Partner(id);
                        int i = wd.deletePartner(Partner);
                    }
                }
                this.refreshPartnerDatas();
                break;
            case "ADD":
                System.out.println("ADD");
                PartnerEditDialog PartnerEditDialog1 = new PartnerEditDialog(PartnerSysMainUi.this, "添加图书");
                PartnerEditDialog1.setVisible(true);
                this.refreshPartnerDatas();
                break;
            case "UPDATE":
                //如果加入run,那么selectrow=-1
                System.out.println("update");
                PartnerEditDialog PartnerEditDialog2 = new PartnerEditDialog(PartnerSysMainUi.this, "更新图书");
                PartnerEditDialog2.setVisible(true);
                this.refreshPartnerDatas();
                break;
            case "SEARCH":
                String keyword = searchText.getText();
                Vector<Partner> Partners = wd.selectPartner(keyword);
                Vector<Vector<String>> data = PartnerTableHandle.convertTable(Partners);
                this.tablemodel.setDataVector(data, columnNames);
                break;
            default:
                break;
        }
    }
}





