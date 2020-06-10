package Pro;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PurchaseManageSysMainUi extends JFrame implements ActionListener{


        private JButton deleteButton;
        private JButton postButton;
        private JButton updateButton;
        private JButton searchButton;
        private JTextField searchText;
        private Vector<String> columnNames = new Vector<>();
        private PurchaseManagesDao bd = PurchaseManagesDao.getInstance();
        private Font font1 = new Font("黑体", Font.BOLD, 18);
        private Font font2 = new Font("宋体", Font.PLAIN, 16);
        private JTable table;
        private PurchaseManageModel tablemodel;
        private Index parent;
        private static Pro.PurchaseManageSysMainUi PurchaseManageSysMainUi;
        private int row;

        private PurchaseManageSysMainUi(Index parent){
            this.parent=parent;
            init();     //!!!!!!!!!!如果放在index，那么每次都会new一个新的Jtable，导致无法被选择
        }

        public static Pro.PurchaseManageSysMainUi getInstance(Index parent){
            if(PurchaseManageSysMainUi==null){
                PurchaseManageSysMainUi=new Pro.PurchaseManageSysMainUi(parent);
            }
            return PurchaseManageSysMainUi;
        }

        public void init(){
            tablemodel=getTableModel();
            table=new JTable(tablemodel);
        }

        public JPanel getTools() {
            JPanel jp = new JPanel();
            setLayout(new GridLayout(1, 5));
            deleteButton = new JButton("删除");
            deleteButton.setActionCommand("DELETE");
            deleteButton.addActionListener(this);
            postButton = new JButton("过账");
            postButton.setActionCommand("POST");
            postButton.addActionListener(this);
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
            jp.add(updateButton);
            jp.add(postButton);
            return jp;
        }

        public Container getMainUi() {

//        JPanel c=new JPanel();     只可以获取部分
            Container c=getContentPane();   //获取当前全部布局
            c.setLayout(new BorderLayout());  //KEY,否则达不到预计
            table.setSelectionMode(2);  //随意选择多个
            table.setAutoResizeMode(2);
            table.setFont(font2);
            table.setRowHeight(20);

            final JTableHeader tableHeader = table.getTableHeader();
            tableHeader.setFont(font1);
            c.add(tableHeader,BorderLayout.NORTH);
            c.add(table);

            return c;
        }

        public String[] getselectPurchaseManage() {

            int row = this.row;
            String id = (String) table.getValueAt(row, 0);
            String name = (String) table.getValueAt(row, 1);
            String barcode = (String) table.getValueAt(row, 2);
            String quantity = (String) table.getValueAt(row, 3);
            String partner = (String) table.getValueAt(row, 4);
            String warehouse = (String)table.getValueAt(row, 5);
            String status = (String) table.getValueAt(row, 6);
            String amount = (String) table.getValueAt(row, 7);
            String discountamount = (String) table.getValueAt(row, 8);
            String posttime = (String) table.getValueAt(row, 9);
            String[] PurchaseManage = {name, barcode, quantity, partner,warehouse, status, amount, discountamount, posttime};
            return PurchaseManage;
        }

        public PurchaseManageModel getTableModel() {
            columnNames = new Vector<>();
            columnNames.add("图书名");
            columnNames.add("条形码");
            columnNames.add("数量");
            columnNames.add("供应商");
            columnNames.add("仓库");
            columnNames.add("表头状态");
            columnNames.add("金额");
            columnNames.add("折后金额");
            columnNames.add("过账时间");

            PurchaseManageModel tableModel1 = new PurchaseManageModel(PurchaseManageTableHandle.getValues(), columnNames);
//        System.out.println(tableModel1.getRowCount());
            return tableModel1;
        }

        public void refreshPurchaseManageDatas() {
//        Vector<PurchaseManage> PurchaseManages = bd.getPurchaseManageList();
//        Vector<Vector<String>> data = PurchaseManageHandle.convertTable(PurchaseManages);
            Vector<Vector<String>> data = PurchaseManageHandle.getValues();
            this.tablemodel.setDataVector(data, columnNames);

        }

        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "DELETE":
                    this.row=table.getSelectedRow();
                    System.out.println("PurchaseManagerowd:"+this.row);
                    final int operate = JOptionPane.showConfirmDialog(parent, "确定要删除该条记录吗？",
                            "确定", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (operate == JOptionPane.YES_OPTION) {

                        final int[] selectedRows = this.table.getSelectedRows();
                        for (int row : selectedRows) {
                            int id = Integer.valueOf((String) this.table.getValueAt(row, 0));
                            PurchaseManage PurchaseManage = new PurchaseManage(id);
                            int i = bd.deletePurchaseManage(PurchaseManage);
                        }
                    }
                    this.refreshPurchaseManageDatas();
                    break;
                case "ADD":
                    PurchaseManageEditDialog PurchaseManageEditDialog = new PurchaseManageEditDialog(this, "添加图书");
                    PurchaseManageEditDialog.setVisible(true);
                    this.refreshPurchaseManageDatas();
                    break;
                case "UPDATE":
                    this.row=this.table.getSelectedRow();
                    System.out.println("PurchaseManagerowu:"+this.row);
                    System.out.println("update");
                    PurchaseManageEditDialog PurchaseManageEditDialog1 = new PurchaseManageEditDialog(this, "更新图书");
                    PurchaseManageEditDialog1.setVisible(true);
                    this.refreshPurchaseManageDatas();
                    break;
                case "SEARCH":
                    String keyword = searchText.getText();
                    Vector<PurchaseManage> PurchaseManages = bd.selectPurchaseManage(keyword);
                    Vector<Vector<String>> data = PurchaseManageHandle.convertTable(PurchaseManages);
                    this.tablemodel.setDataVector(data, columnNames);
                    break;
                default:
                    break;
            }
        }


    }

