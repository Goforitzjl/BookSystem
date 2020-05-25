package Pro;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class Index extends JFrame implements ActionListener {
    public JButton deleteButton;
    public JButton addButton;
    public JButton updateButton;
    public JButton searchButton;
    public JTextField searchText;
    public BookTableModel tableModel;
    public JTable table;
    public BooksDao bd = new BooksDao();
    public Font font1 = new Font("黑体", Font.BOLD, 18);
    public Font font2 = new Font("宋体", Font.PLAIN, 16);
    public Vector<String> columnNames = new Vector<>();

    public static void main(String[] args) {
        new Index().start();
    }

    public void start() {
        setTitle("首界面");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1260, 750);
        setLocation(183, 13);
        Container c = getContentPane();

        this.tableModel = getTableModel();
        this.table = new JTable(this.tableModel);


        JSplitPane vsplitpane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        vsplitpane1.setDividerLocation(75);
        vsplitpane1.setDividerSize(5);


        JSplitPane hsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        hsplitpane.setDividerLocation(165);
        hsplitpane.setDividerSize(5);


        JSplitPane vsplitpane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        vsplitpane2.setDividerLocation(60);
        vsplitpane2.setDividerSize(5);

        c.add(vsplitpane1, BorderLayout.CENTER);
        vsplitpane1.setTopComponent(new FirstArea().getFirstArea());
        vsplitpane1.setBottomComponent(hsplitpane);
        hsplitpane.setLeftComponent(new SecondArea().getSecondArea());
        hsplitpane.setRightComponent(vsplitpane2);
//
        vsplitpane2.setTopComponent(getThirdArea());
        vsplitpane2.setBottomComponent(getFourthArea());

//        vsplitpane2.setTopComponent(new JLabel("3"));
//        vsplitpane2.setBottomComponent(new JLabel("4"));


//        vsplitpane2.setBottomComponent(ThridArea.getInstance().getFourthArea());
        setVisible(true);

    }


    public JPanel getThirdArea() {
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
//        searchButton.setBounds(50, 25, 60, 25);
//        searchText.setBounds(120, 25, 200, 25);
//        deleteButton.setBounds(800, 25, 60, 25);
//        addButton.setBounds(880, 25, 60, 25);
//        updateButton.setBounds(960, 25, 60, 25);
        jp.add(searchButton);
        jp.add(searchText);
        jp.add(deleteButton);
        jp.add(addButton);
        jp.add(updateButton);
        return jp;
    }


    public Container getFourthArea() {
        JPanel c=new JPanel();
        this.table.setSelectionMode(2);  //随意选择多个
        this.table.setAutoResizeMode(2);
        this.table.setFont(font2);
        this.table.setRowHeight(20);

        final JTableHeader tableHeader = this.table.getTableHeader();
        tableHeader.setFont(font1);
        c.add(tableHeader, BorderLayout.NORTH);
        c.add(table, BorderLayout.CENTER);

        return c;
    }


    public BookTableModel getTableModel() {
        columnNames = new Vector<>();
        columnNames.add("图书编号");
        columnNames.add("图书名");
        columnNames.add("作者");
        columnNames.add("种类");
        columnNames.add("条形码");
        columnNames.add("出版社");
        columnNames.add("价格");
        columnNames.add("出版日期");
        columnNames.add("创建日期");
        columnNames.add("更新日期");

        BookTableModel tableModel1 = new BookTableModel(TableDao.getValues(), columnNames);
//        System.out.println(tableModel1.getRowCount());
        return tableModel1;
    }


//    class Action implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "DELETE":
                final int operate = JOptionPane.showConfirmDialog(this, "确定要删除该条记录吗？",
                        "确定", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (operate == JOptionPane.YES_OPTION) {

                    final int[] selectedRows = table.getSelectedRows();
                    for (int row : selectedRows) {
                        int id = Integer.valueOf((String) table.getValueAt(row, 0));
                        Book book = new Book(id);
                        int i = bd.deleteBook(book);

                    }
                }
                refreshDatas();
                break;
            case "ADD":
                BookEditDialog bookEditDialog = new BookEditDialog(this, "添加图书");
                bookEditDialog.setVisible(true);
                break;
            case "UPDATE":
                System.out.println("update");
                BookEditDialog bookEditDialog1 = new BookEditDialog(this, "更新图书");
                bookEditDialog1.setVisible(true);
                break;
            case "SEARCH":
                String keyword = searchText.getText();
                Vector<Book> books = bd.selectBook(keyword);
                Vector<Vector<String>> data = TableDao.convertTable(books);
                tableModel.setDataVector(data, columnNames);
                break;
            default:
                break;
        }
    }

    public void refreshDatas() {
        Vector<Book> books = bd.getBookList();
        Vector<Vector<String>> data = TableDao.convertTable(books);
        tableModel.setDataVector(data, columnNames);
    }



    public String[] getselectbook(){

        int row = table.getSelectedRow();
        String id=(String)table.getValueAt(row,0);
        String name = (String) table.getValueAt(row, 1);
        String authors = (String) table.getValueAt(row, 2);
        String category = (String) table.getValueAt(row, 3);
        String barcode = (String) table.getValueAt(row, 4);
        String publisher = (String) table.getValueAt(row, 5);
        String price = (String)table.getValueAt(row,6);
        String p_t=(String)table.getValueAt(row,7);
        String c_t=(String)table.getValueAt(row,8);
        String u_t=(String)table.getValueAt(row,9);
        String[] book={name,authors,category,barcode,publisher,price,p_t,c_t,u_t,id};

        return book;
    }
}



