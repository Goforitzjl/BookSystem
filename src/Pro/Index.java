package Pro;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.Table;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;


public class Index extends JFrame implements TreeSelectionListener{


    DefaultMutableTreeNode root;
    JTree tree;
    JSplitPane vsplitpane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    JSplitPane vsplitpane3=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    public Vector<String> columnNames = new Vector<>();
    public static Index index;

    public static void main(String[] args) {
        new Index().start();
    }

    private Index(){

    }

    public static Index getInstance(){
            if(index==null){
                index=new Index();
            }
            return index;
    }


    public void start() {
        setTitle("首界面");
//        Init();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1260, 750);
        setLocation(183, 13);
        Container c = getContentPane();

//        this.tableModel = getTableModel();
//        this.table = new JTable(this.tableModel);

        JSplitPane vsplitpane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        vsplitpane1.setDividerLocation(75);
        vsplitpane1.setDividerSize(5);


        JSplitPane hsplitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        hsplitpane.setDividerLocation(165);
        hsplitpane.setDividerSize(5);



        vsplitpane2.setDividerLocation(60);
        vsplitpane2.setDividerSize(5);

;

        c.add(vsplitpane1, BorderLayout.CENTER);
        vsplitpane1.setTopComponent(new SystemTitle().getSysTitle());
        vsplitpane1.setBottomComponent(hsplitpane);
        hsplitpane.setLeftComponent(getSysTree());

        hsplitpane.setRightComponent(vsplitpane2);

//        vsplitpane2.setTopComponent(new BookSysMainUi().getTools());
//        vsplitpane2.setBottomComponent(new BookSysMainUi().getMainUi());

        setVisible(true);

    }


    public Container getSysTree() {
//        JPanel jp =new JPanel(new BorderLayout());
        JScrollPane jScrollPane = new JScrollPane();
        root = new DefaultMutableTreeNode("图书进销存管理系统");
        DefaultMutableTreeNode Nodeone = new DefaultMutableTreeNode("基础信息维护");
        DefaultMutableTreeNode Nodetwo = new DefaultMutableTreeNode("进货单管理");
        DefaultMutableTreeNode Nodethree = new DefaultMutableTreeNode("销售单管理");
        DefaultMutableTreeNode Nodefour = new DefaultMutableTreeNode("报表");
        DefaultMutableTreeNode Nodeone1 = new DefaultMutableTreeNode("用户管理");
        DefaultMutableTreeNode Nodeone2 = new DefaultMutableTreeNode("往来单位管理");
        DefaultMutableTreeNode Nodeone3 = new DefaultMutableTreeNode("仓库管理");
        DefaultMutableTreeNode Nodeone4 = new DefaultMutableTreeNode("图书管理");
        DefaultMutableTreeNode Nodetwo1 = new DefaultMutableTreeNode("进货");
        DefaultMutableTreeNode Nodetwo2 = new DefaultMutableTreeNode("进货信息维护");
        DefaultMutableTreeNode Nodethree1 = new DefaultMutableTreeNode("销售");
        DefaultMutableTreeNode Nodethree2 = new DefaultMutableTreeNode("销售信息维护");
        DefaultMutableTreeNode Nodefour1 = new DefaultMutableTreeNode("图书销售排行");
        DefaultMutableTreeNode Nodefour2 = new DefaultMutableTreeNode("图书库存排行");

        root.add(Nodeone);
        Nodeone.add(Nodeone1);
        Nodeone.add(Nodeone2);
        Nodeone.add(Nodeone3);
        Nodeone.add(Nodeone4);

        root.add(Nodetwo);
        Nodetwo.add(Nodetwo1);
        Nodetwo.add(Nodetwo2);

        root.add(Nodethree);
        Nodethree.add(Nodethree1);
        Nodethree.add(Nodethree2);

        root.add(Nodefour);
        Nodefour.add(Nodefour1);
        Nodefour.add(Nodefour2);

        DefaultTreeModel model = new DefaultTreeModel(root);

        tree = new JTree();
        tree.setModel(model);       //JTree tree=new Jtree() 默认是defaultTreeMdel

        jScrollPane.setViewportView(tree);

        TreeNode root1 = (TreeNode) tree.getModel().getRoot();
        expandTree(tree, new TreePath(root1));

        tree.addTreeSelectionListener(this);
        return jScrollPane;

    }

    private void expandTree(JTree tree, TreePath parent) {

        TreeNode node = (TreeNode) parent.getLastPathComponent();

        if (node.getChildCount() >= 0) {

            for (Enumeration<?> e = node.children(); e.hasMoreElements(); ) {

                TreeNode n = (TreeNode) e.nextElement();

                TreePath path = parent.pathByAddingChild(n);

                expandTree(tree, path);

            }

        }

        tree.expandPath(parent);

    }


        public void valueChanged(TreeSelectionEvent e) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if (node.toString().equals("图书管理")) {
//                JOptionPane.showMessageDialog(getContentPane(), "选中图书管理", "OK", JOptionPane.INFORMATION_MESSAGE);
                BookSysMainUi bookSysMainUi = BookSysMainUi.getInstance(this);  //!!Index.getinstance()
//                bookSysMainUi.init();
                vsplitpane2.setTopComponent(bookSysMainUi.getTools());
                vsplitpane2.setBottomComponent(bookSysMainUi.getMainUi());
            } else if (node.toString().equals("用户管理")) {
                JOptionPane.showMessageDialog(getContentPane(), "选中用户管理", "OK", JOptionPane.INFORMATION_MESSAGE);

            } else if (node.toString().equals("往来单位管理")) {
//                JOptionPane.showMessageDialog(getContentPane(), "选中往来单位管理", "OK", JOptionPane.INFORMATION_MESSAGE);;
                PartnerSysMainUi partnerSysMainUi=PartnerSysMainUi.getInstance(this);
//                partnerSysMainUi.init();
                vsplitpane2.setTopComponent(partnerSysMainUi.getTools());
                vsplitpane2.setBottomComponent(partnerSysMainUi.getMainUi());

            } else if (node.toString().equals("仓库管理")) {
//                JOptionPane.showMessageDialog(getContentPane(), "选中仓库", "OK", JOptionPane.INFORMATION_MESSAGE);
                WareHouseSysMainUi wareHouseSysMainUi=WareHouseSysMainUi.getInstance(this);
//                wareHouseSysMainUi.init();
                vsplitpane2.setTopComponent(wareHouseSysMainUi.getTools());
                vsplitpane2.setBottomComponent(wareHouseSysMainUi.getMainUi());

            } else if (node.toString().equals("进货")) {
                vsplitpane3.setDividerLocation(480);
                vsplitpane3.setDividerSize(5);
                PurchaseDetailSysMainUi purchaseDetailSysMainUi=PurchaseDetailSysMainUi.getInstance(this);
                vsplitpane2.setTopComponent(purchaseDetailSysMainUi.getTools());
                vsplitpane2.setBottomComponent(vsplitpane3);
                vsplitpane3.setTopComponent(purchaseDetailSysMainUi.getMainUi());
                vsplitpane3.setBottomComponent(purchaseDetailSysMainUi.getButtom());
            } else if (node.toString().equals("进货信息维护")) {
                JOptionPane.showMessageDialog(getContentPane(), "选中进货信息维护", "OK", JOptionPane.INFORMATION_MESSAGE);

            } else if (node.toString().equals("销售")) {
                JOptionPane.showMessageDialog(getContentPane(), "选中销售", "OK", JOptionPane.INFORMATION_MESSAGE);

            } else if (node.toString().equals("销售信息维护")) {
                JOptionPane.showMessageDialog(getContentPane(), "选中信息维护", "OK", JOptionPane.INFORMATION_MESSAGE);

            } else if (node.toString().equals("图书销售排行")) {
                JOptionPane.showMessageDialog(getContentPane(), "选中图书销售排行", "OK", JOptionPane.INFORMATION_MESSAGE);

            } else if (node.toString().equals("图书库存排行")) {
                JOptionPane.showMessageDialog(getContentPane(), "选中图书库排行", "OK", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }




//    class TreeClick implements TreeSelectionListener {

//    public void refreshBookDatas() {
////        Vector<Book> books = bd.getBookList();
////        Vector<Vector<String>> data = BookTableHandle.convertTable(books);
//        Vector<Vector<String>> data = BookTableHandle.getValues();
//        this.tableModel.setDataVector(data, columnNames);
//
//    }

//                columnNames=bookSysMainUi.getColumnNames();
////                tableModel=bookSysMainUi.getBookTableModel();
////                table=new JTable(tableModel);