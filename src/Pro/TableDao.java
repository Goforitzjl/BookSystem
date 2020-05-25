package Pro;

import java.util.Iterator;
import java.util.Vector;

public class TableDao {



    public static Vector<Vector<String>> getValues(){
        Vector<Book> bookList=new BooksDao().getBookList();
        Vector<Vector<String>> tableValues=convertTable(bookList);
        return tableValues;
    }


    public static Vector<Vector<String>> convertTable(Vector<Book> bookList){
        Vector<Vector<String>> tableValues=new Vector<>();

        Iterator it=bookList.iterator();
        while (it.hasNext()){
            Book book = (Book) it.next();
            String id=String.valueOf(book.getBook_id());
            String name=book.getBook_name();
            String authors=book.getAuthors();
            String category=book.getCategory();
            String barcode=book.getBarcode();
            String publisher=book.getPublisher();
            String price=String.valueOf(book.getPrice());
            String p_t=String.valueOf(book.getPublish_time());
            String c_t=String.valueOf(book.getCreate_time());
            String u_t=String.valueOf(book.getUpdate_time());

            Vector<String> rowValues=new Vector<>();
            rowValues.add(id);
            rowValues.add(name);
            rowValues.add(authors);
            rowValues.add(category);
            rowValues.add(barcode);
            rowValues.add(publisher);
            rowValues.add(price);
            rowValues.add(p_t);
            rowValues.add(c_t);
            rowValues.add(u_t);

            tableValues.add(rowValues);

        }
        return tableValues;
    }


}
