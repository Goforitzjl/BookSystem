package Pro;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class PurchaseDetailSysMainUi extends JFrame implements ActionListener {

    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton draft;
    private JButton post;
    private JLabel partnerlable;
    private JLabel warehouselable;
    private JLabel amount;
    private JLabel discountamount;
    private JLabel cash;
    private JLabel bank;
    private JTextField amounttext;
    private JTextField discountamounttext;
    private JTextField cashtext;
    private JTextField banktext;
    private JComboBox<String> partner;
    private JComboBox<String> warehouse;
    private Vector<String> columnNames = new Vector<>();
    private PartnerDao wd = PartnerDao.getInstance();
    private Font font1 = new Font("黑体", Font.BOLD, 18);
    private Font font2 = new Font("宋体", Font.PLAIN, 16);
    private Font font3 = new Font("黑体", Font.BOLD, 15);
    private JTable table;
    private PurchaseDetailTableModel tablemodel;   //1
    private Index parent;
    private static Pro.PurchaseDetailSysMainUi PurchaseDetailSysMainUi;
    private String status;

    private PurchaseDetailSysMainUi(Index parent) {
        this.parent = parent;
        init();
    }

    public static Pro.PurchaseDetailSysMainUi getInstance(Index parent) {
        if (PurchaseDetailSysMainUi == null) {
            PurchaseDetailSysMainUi = new Pro.PurchaseDetailSysMainUi(parent);
        }
        return PurchaseDetailSysMainUi;
    }

    public void init() {
        tablemodel = getTableModel();
        table = new JTable(tablemodel);
    }

    public JPanel getTools() {
        JPanel jp = new JPanel();
//            setLayout(new GridLayout(1, 5));
        deleteButton = new JButton("删除");
        deleteButton.setActionCommand("DELETE");
        deleteButton.addActionListener(this);
        addButton = new JButton("添加");
        addButton.setActionCommand("ADD");
        addButton.addActionListener(this);
        updateButton = new JButton("更新");
        updateButton.setActionCommand("UPDATE");
        updateButton.addActionListener(this);
        partnerlable = new JLabel("供应商");
        partnerlable.setFont(font1);
        partner = new JComboBox<String>(new PartnerComboxModel());
        warehouselable = new JLabel("入仓库");
        warehouselable.setFont(font1);
        warehouse = new JComboBox<String>(new WareHouseComboxModel());
        jp.add(partnerlable);
        jp.add(partner);
        jp.add(warehouselable);
        jp.add(warehouse);
        jp.add(deleteButton, BorderLayout.EAST);
        jp.add(addButton, BorderLayout.EAST);
        jp.add(updateButton, BorderLayout.EAST);
        return jp;
    }

    public JPanel getMainUi() {

        JPanel c = new JPanel();     //只可以获取部分
//        Container c = getContentPane();   //获取当前全部布局
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

    public Container getButtom() {
//        JPanel jp = new JPanel();
        Container jp = getContentPane();
        setLayout(null);
        amount = new JLabel("总金额:");
        amount.setBounds(50, 10, 60, 20);
        amount.setFont(font3);
        amounttext = new JTextField();
        amounttext.setBounds(120, 10, 100, 20);
        discountamount = new JLabel("折后金额:");
        discountamount.setBounds(250, 10, 80, 20);
        discountamount.setFont(font3);
        discountamounttext = new JTextField();
        discountamounttext.setBounds(340, 10, 100, 20);
        cash = new JLabel("现金:");
        cash.setBounds(50, 40, 50, 20);
        cash.setFont(font3);
        cashtext = new JTextField();
        cashtext.setBounds(120, 40, 100, 20);
        bank = new JLabel("银行:");
        bank.setBounds(250, 40, 50, 20);
        bank.setFont(font3);
        banktext = new JTextField();
        banktext.setBounds(340, 40, 100, 20);
        draft = new JButton("草稿");
        draft.setBounds(50, 70, 70, 25);
        draft.setActionCommand("draft");
        draft.addActionListener(this);
        post = new JButton("过账");
        post.setBounds(250, 70, 70, 25);
        post.setActionCommand("post");
        post.addActionListener(this);
        jp.add(amount);
        jp.add(amounttext);
        jp.add(discountamount);
        jp.add(discountamounttext);
        jp.add(cash);
        jp.add(cashtext);
        jp.add(bank);
        jp.add(banktext);
        jp.add(draft);
        jp.add(post);
        return jp;
    }

    public String getPartnerDiscount() {
        String name = (String) partner.getSelectedItem();
        System.out.println(name);
        Float discount = PartnerDao.getInstance().getDiscountbyname(name);
        return String.valueOf(discount);
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

    public PurchaseDetailTableModel getTableModel() {
        columnNames = new Vector<>();
        columnNames.add("图书名称");
        columnNames.add("图书作者");
        columnNames.add("条形码");
        columnNames.add("预售价");
        columnNames.add("进货原价");
        columnNames.add("折扣率");
        columnNames.add("进货折扣价");
        columnNames.add("图书数量");
        columnNames.add("总金额");
        columnNames.add("折扣总金额");


        PurchaseDetailTableModel tableModel1 = new PurchaseDetailTableModel(null, columnNames);
//        System.out.println(tableModel1.getRowCount());
        return tableModel1;
    }

    public void setTablemodelValues(String[] values) {
        int row = table.getSelectedRow();
        for (int i = 0; i < values.length; i++) {
            tablemodel.setValueAt(values[i], row, i);
        }
    }

    public void addTablemodelValues(String[] values) {
        tablemodel.addRow(values);
    }

    public String[] getTablemodelValues() {
        int row = table.getSelectedRow();
        String[] values = new String[10];
        for (int i = 0; i < 10; i++) {
            values[i] = (String) tablemodel.getValueAt(row, i);
        }
        return values;
    }

    public PurchaseDetail getPurchaseDetaiValues() {
        int row = table.getSelectedRow();
        Date date = new Date();
        String partnername = (String) partner.getSelectedItem();  //
        System.out.println("partnername" + partnername);
        int partnerid = PartnerDao.getInstance().getIdbyname(partnername);
        String warehousename = (String) warehouse.getSelectedItem(); //
        System.out.println("warehousename" + warehousename);
        int warehouseid = WareHouseDao.getInstance().getIdbyname(warehousename);

        String bookname = (String) tablemodel.getValueAt(row, 0);
        String bookauthor = (String) tablemodel.getValueAt(row, 1);
        String barcode = (String) tablemodel.getValueAt(row, 2);
        Float presellprice = Float.valueOf((String) tablemodel.getValueAt(row, 3));
        int bookid = BooksDao.getInstance().getIdbyname(bookname);
        if (bookid == -1) {
            Book book = new Book(bookname, bookauthor, null, barcode, null, Float.valueOf(presellprice),
                    date, date, date);
            BooksDao.getInstance().addBook(book);
            bookid = BooksDao.getInstance().getIdbyname(bookname);
        }
        int bookquantity = Integer.valueOf((String) tablemodel.getValueAt(row, 7));
        Float bookcostprice = Float.valueOf((String) tablemodel.getValueAt(row, 4));
        Float discountprice = Float.valueOf((String) tablemodel.getValueAt(row, 6));
        Float sellamount = bookquantity * presellprice;
        Float costamount = bookquantity * bookcostprice;
        Float discountamount = bookquantity * discountprice;
        int billmainid = BillMainDao.getInstance().getMaxId();

        PurchaseDetail purchaseDetail = new PurchaseDetail(billmainid,
                partnerid, warehouseid, bookid, bookquantity, presellprice,
                bookcostprice, discountprice, sellamount, costamount, discountamount);
        return purchaseDetail;
    }

    public BillMain getBillMainValues() {
        int row = table.getSelectedRow();
        System.out.println("hererow1" + row);
        Date date = new Date();
        String partnername = (String) partner.getSelectedItem();
        int partnerid = PartnerDao.getInstance().getIdbyname(partnername);
        String warehousename = (String) warehouse.getSelectedItem();
        int inwarehouseid = WareHouseDao.getInstance().getIdbyname(warehousename);
        String type = "进货单";
        int bookquantity = Integer.valueOf((String) tablemodel.getValueAt(row, 7));
        Float bookcostprice = Float.valueOf((String) tablemodel.getValueAt(row, 4));
        Float discountprice = Float.valueOf((String) tablemodel.getValueAt(row, 6));
        Float costamount = bookquantity * bookcostprice;
        Float discountamount = bookquantity * discountprice;
        Float cash = Float.valueOf(cashtext.getText());
        Float bank = Float.valueOf(banktext.getText());
        BillMain billMain = new BillMain(partnerid, inwarehouseid, -1, type,
                bookquantity, costamount, discountamount, cash, bank, status,
                date, date, date);
        return billMain;
    }

    public BookBalance getBookBalanceValues(PurchaseDetail purchaseDetail) {
        int bookid = purchaseDetail.getBookid();
        int warehouseid = purchaseDetail.getWarehouseid();
        BookBalance bookBalance = null;
        BookBalance prebookBalance = BookBalanceDao.getInstance().getLastValuesById(bookid);
        int quantity = purchaseDetail.getQuantity();
        Float init_price = purchaseDetail.getCostprice();
        Float current_price = purchaseDetail.getDiscountcostprice();
        int init_quantity = 0;
        int current_quantity = 0;
        if (prebookBalance == null) {
            init_quantity = 0;
            current_quantity = quantity;
        } else {
            init_quantity = prebookBalance.getQuantity();
            current_quantity = init_quantity + quantity;
        }
        bookBalance = new BookBalance(bookid, warehouseid, init_quantity, init_price, current_quantity, current_price);
        return bookBalance;
    }

    public AccountBalance getAccountBalanceValues(BillMain billMain, PurchaseDetail purchaseDetail) {
        int row = table.getSelectedRow();
        int quantity = purchaseDetail.getQuantity();
        String bookname = (String) tablemodel.getValueAt(row, 0);
        String name = "进货" + bookname + quantity + "本";
        AccountBalance preaccountBalance = AccountBalanceDao.getInstance().getLastvalues();
        Float initamount = 100000f;
        if (preaccountBalance == null) {
            initamount = 100000f;
        } else {
            initamount = preaccountBalance.getCurrent_amount();
        }
        Float amount = billMain.getBillamount();
        Float currentamount = initamount - amount;
        Date c_t = new Date();
        AccountBalance accountBalance = new AccountBalance(name, initamount, currentamount, amount, c_t);
        return accountBalance;
    }

    public String checkpost(BillMain billMain) {
        String msg = "";
        Float cash = billMain.getCash();
        Float bank = billMain.getBank();
        Float discountamount = billMain.getBillamount();
        AccountBalance balance = AccountBalanceDao.getInstance().getLastvalues();
        Float amount = balance.getCurrent_amount();
        if (cash + bank != discountamount) {
            msg = "现金和银行支付总额不匹配！";
            if (amount < discountamount) {
//                msg += "财务余额不足!";
                msg = msg + "财务余额不足";
            }
        } else {
            if (amount < discountamount) {
                msg = "财务余额不足";
            }
        }
        return msg;
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "DELETE":
                final int operate = JOptionPane.showConfirmDialog(parent, "确定要删除该条记录吗？",
                        "确定", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (operate == JOptionPane.YES_OPTION) {
                    int row = table.getSelectedRow();
                    tablemodel.removeRow(row);
                }
                break;
            case "ADD":
                System.out.println("ADD");
                PurchaseDetailEditDialog PurchaseDetailEditDialog1 = new PurchaseDetailEditDialog(Pro.PurchaseDetailSysMainUi.this, "添加图书");
                PurchaseDetailEditDialog1.setVisible(true);
                break;
            case "UPDATE":
                //如果加入run,那么selectrow=-1
                System.out.println("update");
                PurchaseDetailEditDialog PurchaseDetailEditDialog2 = new PurchaseDetailEditDialog(Pro.PurchaseDetailSysMainUi.this, "更新图书");
                PurchaseDetailEditDialog2.setVisible(true);
                break;
            case "post":
                System.out.println("过账");
                status = "过账";
                BillMain billMain1 = getBillMainValues();
                String msg = checkpost(billMain1);
                PurchaseDetail purchaseDetail1 = getPurchaseDetaiValues();
                if (msg == "") {
                    int count1 = BillMainDao.getInstance().addBillMain(billMain1);
                    int count2 = PurchaseDetailDao.getInstance().addPurchaseDetail(purchaseDetail1);

                    if (count1 == -1 || count2 == -1) {
                        JOptionPane.showMessageDialog(this, "保存失败", "提示",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        BookBalance postbookbalance = getBookBalanceValues(purchaseDetail1);
                        AccountBalance postaccountbalance = getAccountBalanceValues(billMain1, purchaseDetail1);
                        int post1 = BookBalanceDao.getInstance().addBookBalance(postbookbalance);
                        int post2 = AccountBalanceDao.getInstance().addAccountBalance(postaccountbalance);
                        if (post1 == -1 || post2 == -1) {
                            JOptionPane.showMessageDialog(this, "过账失败", "提示",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "过账成功", "提示",
                                    JOptionPane.PLAIN_MESSAGE);
                        }
                    }

                } else {

                    JOptionPane.showMessageDialog(this, msg, "提示",
                            JOptionPane.ERROR_MESSAGE);
                }

                break;
            case "draft":
                System.out.println("草稿");
                status = "草稿";
                BillMain billMain = getBillMainValues();
                int count3 = BillMainDao.getInstance().addBillMain(billMain);
                PurchaseDetail purchaseDetail = getPurchaseDetaiValues();
                int count4 = PurchaseDetailDao.getInstance().addPurchaseDetail(purchaseDetail);

                if (count3 == -1 || count4 == -1) {
                    JOptionPane.showMessageDialog(this, "保存失败", "提示",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "保存成功", "提示",
                            JOptionPane.PLAIN_MESSAGE);
                }
                break;
            default:
                break;
        }
    }

    class PartnerComboxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

        String selecteditem = null;
        List<String> partners = getPartners();

        @Override
        public void setSelectedItem(Object anItem) {
            selecteditem = (String) anItem;
        }

        @Override
        public Object getSelectedItem() {
            return selecteditem;
        }

        @Override
        public int getSize() {
            return partners.size();
        }

        @Override
        public String getElementAt(int index) {
            return partners.get(index);
        }

        public List<String> getPartners() {
            Vector<Partner> partnerList = PartnerDao.getInstance().getPartnerList("'供应商'");
            List<String> listnames = new ArrayList<>();
            Iterator it = partnerList.iterator();
            while (it.hasNext()) {
                Partner partner = (Partner) it.next();
                listnames.add(partner.getName());
            }
            return listnames;
        }
    }

    class WareHouseComboxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

        String selecteditem = null;
        List<String> warehouses = getWarehouses();

        @Override
        public void setSelectedItem(Object anItem) {
            selecteditem = (String) anItem;
        }

        @Override
        public Object getSelectedItem() {
            return selecteditem;
        }

        @Override
        public int getSize() {
            return warehouses.size();
        }

        @Override
        public String getElementAt(int index) {
            return warehouses.get(index);
        }

        public List<String> getWarehouses() {
            Vector<WareHouse> warehouselist = WareHouseDao.getInstance().getWareHouseList();
            Iterator it = warehouselist.iterator();
            List<String> listnames = new ArrayList<>();
            while (it.hasNext()) {
                WareHouse wareHouse = (WareHouse) it.next();
                listnames.add(wareHouse.getWarename());
            }
            return listnames;
        }
    }
}


