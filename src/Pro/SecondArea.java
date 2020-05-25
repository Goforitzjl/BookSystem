package Pro;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Enumeration;

public class SecondArea extends JFrame {


    public Container getSecondArea(){
//        JPanel jp =new JPanel(new BorderLayout());
        final Container c = getContentPane();
        DefaultMutableTreeNode root =new DefaultMutableTreeNode("图书进销存管理系统");
        DefaultMutableTreeNode Nodeone =new DefaultMutableTreeNode("基础信息维护");
        DefaultMutableTreeNode Nodetwo =new DefaultMutableTreeNode("进货单管理");
        DefaultMutableTreeNode Nodethree =new DefaultMutableTreeNode("销售单管理");
        DefaultMutableTreeNode Nodefour =new DefaultMutableTreeNode("报表");
        DefaultMutableTreeNode Nodeone1 =new DefaultMutableTreeNode("用户管理");
        DefaultMutableTreeNode Nodeone2 =new DefaultMutableTreeNode("往来单位管理");
        DefaultMutableTreeNode Nodeone3 =new DefaultMutableTreeNode("仓库管理");
        DefaultMutableTreeNode Nodeone4 =new DefaultMutableTreeNode("图书管理");
        DefaultMutableTreeNode Nodetwo1 =new DefaultMutableTreeNode("进货");
        DefaultMutableTreeNode Nodetwo2 =new DefaultMutableTreeNode("进货信息维护");
        DefaultMutableTreeNode Nodethree1 =new DefaultMutableTreeNode("销售");
        DefaultMutableTreeNode Nodethree2 =new DefaultMutableTreeNode("销售信息维护");
        DefaultMutableTreeNode Nodefour1 =new DefaultMutableTreeNode("图书销售排行");
        DefaultMutableTreeNode Nodefour2 =new DefaultMutableTreeNode("图书库存排行");

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

        final JTree tree = new JTree(root);

        TreeNode root1 = (TreeNode) tree.getModel().getRoot();
        expandTree(tree,new TreePath(root1));
        c.add(BorderLayout.WEST,tree);
        return  c;
    }

    private void expandTree(JTree tree, TreePath parent) {

        TreeNode node = (TreeNode) parent.getLastPathComponent();

        if (node.getChildCount() >= 0) {

            for (Enumeration<?> e = node.children(); e.hasMoreElements();) {

                TreeNode n = (TreeNode) e.nextElement();

                TreePath path = parent.pathByAddingChild(n);

                expandTree(tree, path);

            }

        }

        tree.expandPath(parent);

    }

}
