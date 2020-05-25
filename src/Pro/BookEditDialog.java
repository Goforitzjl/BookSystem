package Pro;

import com.mysql.cj.xdevapi.JsonArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;


public class BookEditDialog extends JDialog implements ActionListener {

    String name;
    String authors;
    String category;
    String barcode;
    String publisher;
    Float price;
    Date publish_time;
    Date create_time;
    Date update_time;

    JLabel namelabel;
    JTextField nametext;
    JLabel authorslabel;
    JTextField authorstext;
    JLabel categorylabel;
    JTextField categorytext;
    JLabel barcodelabel;
    JTextField barcodetext;
    JLabel publisherlabel;
    JTextField publishertext;
    JLabel pricelabel;
    JTextField pricetext;
    JLabel publishtimelabel;
    JTextField publishtimetext;
    JLabel createtimelabel;
    JTextField createtimetext;
    JLabel updatetimelabel;
    JTextField updatetimetext;

    JButton save;
    JButton cancel;

    Index parent;
    String title;
    int id;

    public BookEditDialog(Index parent, String title) {
        super(parent, title, true);
        this.parent = parent;
        this.title = title;
        init();
    }

    public void init() {

        namelabel = new JLabel("图书名");
        nametext = new JTextField();
        authorslabel = new JLabel("作者");
        authorstext = new JTextField();
        categorylabel = new JLabel("种类");
        categorytext = new JTextField();
        barcodelabel = new JLabel("条形码");
        barcodetext = new JTextField();
        publisherlabel = new JLabel("出版社");
        publishertext = new JTextField();
        pricelabel = new JLabel("价格");
        pricetext = new JTextField();
        publishtimelabel = new JLabel("出版时间");
        publishtimetext = new JTextField();
        createtimelabel = new JLabel("创建时间");
        createtimetext = new JTextField();
        updatetimelabel = new JLabel("更新时间");
        updatetimetext = new JTextField();

        if (this.title.equals("更新图书")) {
            String[] book = this.parent.getselectbook();
            this.id = Integer.valueOf(book[9]);
            nametext.setText(book[0]);
            authorstext.setText(book[1]);
            categorytext.setText(book[2]);
            barcodetext.setText(book[3]);
            publishertext.setText(book[4]);
            pricetext.setText(book[5]);
            publishtimetext.setText(book[6]);
            createtimetext.setText(book[7]);
            updatetimetext.setText(book[8]);
        }
        buildGUI();
    }


    public void buildGUI() {
//        getContentPane()   ------>???
        setBounds(600, 200, 400, 320);
        JPanel contentpanel = new JPanel();
        GroupLayout contentpanelLayout = new GroupLayout(contentpanel);
        contentpanel.setLayout(contentpanelLayout);
        contentpanelLayout.setAutoCreateGaps(true);
        contentpanelLayout.setAutoCreateContainerGaps(true);

        save = new JButton("保存");
        save.setActionCommand("SAVE");
        save.addActionListener(this);
        cancel = new JButton("取消");
        cancel.setActionCommand("CANCEL");
        cancel.addActionListener(this);
        JPanel toolpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        toolpanel.add(save);
        toolpanel.add(cancel);

        GroupLayout.SequentialGroup hGroup = contentpanelLayout.createSequentialGroup();
        hGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(namelabel).addComponent(authorslabel).addComponent(categorylabel)
                .addComponent(barcodelabel).addComponent(publisherlabel).addComponent(pricelabel)
                .addComponent(publishtimelabel).addComponent(createtimelabel).addComponent(updatetimelabel));
        hGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(nametext).addComponent(authorstext).addComponent(categorytext)
                .addComponent(barcodetext).addComponent(publishertext).addComponent(pricetext)
                .addComponent(publishtimetext).addComponent(createtimetext).addComponent(updatetimetext)
                .addComponent(toolpanel));
        contentpanelLayout.setHorizontalGroup(hGroup);

        GroupLayout.SequentialGroup vGroup = contentpanelLayout.createSequentialGroup();
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(namelabel).addComponent(nametext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(authorslabel).addComponent(authorstext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(categorylabel).addComponent(categorytext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(barcodelabel).addComponent(barcodetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(publisherlabel).addComponent(publishertext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(pricelabel).addComponent(pricetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(publishtimelabel).addComponent(publishtimetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(createtimelabel).addComponent(createtimetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(updatetimelabel).addComponent(updatetimetext));
        vGroup.addGroup(contentpanelLayout.createParallelGroup()
                .addComponent(toolpanel));
        contentpanelLayout.setVerticalGroup(vGroup);


        add(contentpanel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "SAVE":
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                int count = -1;
                name = nametext.getText();
                authors = authorstext.getText();
                category = categorytext.getText();
                barcode = barcodetext.getText();
                publisher = publishertext.getText();
                price = Float.parseFloat(pricetext.getText());
                try {
                    publish_time = sdf.parse(publishtimetext.getText());
                    create_time = sdf.parse(createtimetext.getText());
                    update_time = sdf.parse(updatetimetext.getText());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(this, "你输入的日格式有误(yyyy-MM-dd)",
                            "提示", JOptionPane.ERROR_MESSAGE);
                }
                if (this.title.equals("更新图书")) {
                    Book book1 = new Book(this.id,name, authors, category, barcode, publisher, price, publish_time, create_time, update_time);
                    count=new BooksDao().updateBook(book1);
                }
                else if (this.title.equals("添加图书")) {
                    Book book2 = new Book(name, authors, category, barcode, publisher, price, publish_time, create_time, update_time);
                    count = new BooksDao().addBook(book2);  //
                }


                if (count != 1) {
                    JOptionPane.showMessageDialog(this, "保存失败", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "保存成功", "提示", JOptionPane.PLAIN_MESSAGE);
                    this.parent.refreshDatas();
                    this.dispose();
                }
                break;
            case "CANCEL":
                this.dispose();
                break;
            default:
                break;

        }
    }


}
