package Pro;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class BookSysMainUi extends JFrame implements ActionListener {

    private JButton deleteButton;
    private JButton addButton;
    private JButton updateButton;
    private JButton searchButton;
    private JTextField searchText;
    private Vector<String> columnNames = new Vector<>();
    private BooksDao bd = BooksDao.getInstance();
    private Font font1 = new Font("黑体", Font.BOLD, 18);
    private Font font2 = new Font("宋体", Font.PLAIN, 16);
    private JTable table;
    private BookTableModel tablemodel;
    private Index parent;
    private static BookSysMainUi bookSysMainUi;
    private int row;

    private BookSysMainUi(Index parent){
        this.parent=parent;
        init();     //!!!!!!!!!!如果放在index，那么每次都会new一个新的Jtable，导致无法被选择
    }

    public static BookSysMainUi getInstance(Index parent){
        if(bookSysMainUi==null){
            bookSysMainUi=new BookSysMainUi(parent);
        }
        return bookSysMainUi;
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

    public String[] getselectbook() {

        int row = this.row;
        String id = (String) table.getValueAt(row, 0);
        String name = (String) table.getValueAt(row, 1);
        String authors = (String) table.getValueAt(row, 2);
        String category = (String) table.getValueAt(row, 3);
        String barcode = (String) table.getValueAt(row, 4);
        String publisher = (String)table.getValueAt(row, 5);
        String price = (String) table.getValueAt(row, 6);
        String p_t = (String) table.getValueAt(row, 7);
        String c_t = (String) table.getValueAt(row, 8);
        String u_t = (String) table.getValueAt(row, 9);
        String[] book = {name, authors, category, barcode, publisher, price, p_t, c_t, u_t, id};

        return book;
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

        BookTableModel tableModel1 = new BookTableModel(BookTableHandle.getValues(), columnNames);
//        System.out.println(tableModel1.getRowCount());
        return tableModel1;
    }

    public void refreshBookDatas() {
//        Vector<Book> books = bd.getBookList();
//        Vector<Vector<String>> data = BookTableHandle.convertTable(books);
        Vector<Vector<String>> data = BookTableHandle.getValues();
        this.tablemodel.setDataVector(data, columnNames);

    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "DELETE":
                this.row=table.getSelectedRow();
                System.out.println("bookrowd:"+this.row);
                final int operate = JOptionPane.showConfirmDialog(parent, "确定要删除该条记录吗？",
                        "确定", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (operate == JOptionPane.YES_OPTION) {

                    final int[] selectedRows = this.table.getSelectedRows();
                    for (int row : selectedRows) {
                        int id = Integer.valueOf((String) this.table.getValueAt(row, 0));
                        Book book = new Book(id);
                        int i = bd.deleteBook(book);
                    }
                }
                this.refreshBookDatas();
                break;
            case "ADD":
                BookEditDialog bookEditDialog = new BookEditDialog(this, "添加图书");
                bookEditDialog.setVisible(true);
                this.refreshBookDatas();
                break;
            case "UPDATE":
                this.row=this.table.getSelectedRow();
                System.out.println("bookrowu:"+this.row);
                System.out.println("update");
                BookEditDialog bookEditDialog1 = new BookEditDialog(this, "更新图书");
                bookEditDialog1.setVisible(true);
                this.refreshBookDatas();
                break;
            case "SEARCH":
                String keyword = searchText.getText();
                Vector<Book> books = bd.selectBook(keyword);
                Vector<Vector<String>> data = BookTableHandle.convertTable(books);
                this.tablemodel.setDataVector(data, columnNames);
                break;
            default:
                break;
        }
    }





    //    public BookTableModel getBookTableModel(){
//        return this.tablemodel;
//    }
//
//    public Vector<String> getColumnNames(){
//        return this.columnNames;
//    }
}
