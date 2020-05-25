package Pro;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ThridArea extends JFrame {
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


    private ThridArea() {
        this.tableModel = getTableModel();
        this.table = new JTable(tableModel);
//        System.out.println(this.table.getRowCount());
    }

    public JTable getTable(){
        return this.table;
    }


    public static ThridArea getInstance() {
        ThridArea thridArea = new ThridArea();
        return thridArea;
    }


    public Container start() {
        Container c = getContentPane();
 setLayout(null);
        deleteButton = new JButton("删除");
        deleteButton.setActionCommand("DELETE");
        deleteButton.addActionListener(new Action());
        addButton = new JButton("添加");
        addButton.setActionCommand("ADD");
        addButton.addActionListener(new Action());
        updateButton = new JButton("更新");
        updateButton.setActionCommand("UPDATE");
        updateButton.addActionListener(new Action());
        searchButton = new JButton("搜索");
        searchButton.setActionCommand("SEARCH");
        searchButton.addActionListener(new Action());
        searchText =new JTextField(10);
        searchText.setFont(font2);
        searchButton.setBounds(50,25,60,25);
        searchText.setBounds(120,25,200,25);
        deleteButton.setBounds(800,25,60,25);
        addButton.setBounds(880,25,60,25);
        updateButton.setBounds(960,25,60,25);
        c.add(searchButton);
        c.add(searchText);
        c.add(deleteButton);
        c.add(addButton);
        c.add(updateButton);
        return c;
    }


    public Container getFourthArea() {
        Container c = getContentPane();
        this.table.setSelectionMode(2);  //随意选择多个
        this.table.setAutoResizeMode(2);
        this.table.setFont(font2);
        this.table.setRowHeight(20);

        final JTableHeader tableHeader = this.table.getTableHeader();
        tableHeader.setFont(font1);
        c.add(table, BorderLayout.CENTER);
        c.add(tableHeader, BorderLayout.NORTH);

        return c;
    }


    private  BookTableModel getTableModel() {
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


    class Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "DELETE":
                    System.out.println("delete");
                    final int[] selectedRows = table.getSelectedRows();
                    for (int row : selectedRows) {
                        Book book = new Book(row);
                        final int i = bd.deleteBook(book);
                        if (i != 0) {
                            JOptionPane.showMessageDialog(null, "删除成功", "", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "删除失败", "", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case "ADD":
                    System.out.println("add");
                    break;
                case "UPDATE":
                    System.out.println("update");
                    break;
                case "SEARCH":
                    System.out.println("search");
                    String keyword = searchText.getText();
                    Vector<Book> books = bd.selectBook(keyword);
                    System.out.println(books.size());
                    Vector<Vector<String>> data = TableDao.convertTable(books);
                    System.out.println(data);
                    System.out.println(tableModel.getRowCount());
                    tableModel.setDataVector(data,columnNames);
                    System.out.println(tableModel.getRowCount());
                    System.out.println(table.getValueAt(0,0));
                    table.updateUI();
                    break;
                default:
                    break;
            }
        }
    }


}
