package Pro;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

public class Demo {

    public static void main(String[] args) {

//
//        LUser user = new LUser(1, 20, "zhang",
//                "1234", "男", "13551860651",
//                "四川成都",true);


//        LUser user = new LUser(2,21, "lvxinran",
//                "654321", "女", "19983411198",
//                "乌鲁木齐",true);
//        LusersDao uo=new LusersDao();
//        int i=uo.addUser(user);
//        System.out.println(i);
//
//        boolean flag = uo.checkUser(user);
//        System.out.println(flag);

//        Vector<LUser> users =uo.UserList();
//        Iterator it = users.iterator();
//        while(it.hasNext()){
//            LUser user=(LUser)it.next();
//            System.out.println(user.getName());
//
//        }
//
//        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df.format(new Date()));
//        Date p_t=new Date();
//        System.out.println(p_t);
//        Date c_t=new Date(2018,06,9);
//        Date u_t=new Date(2020,10,1);
//
//
//        final Book book = new Book("PHP1", "ERIC MATTHES", "程序设计", "978-7-115-42802-8", "人民邮电出版社", 89.0f, c_t, p_t, u_t);
//        final BooksDao booksDao = BooksDao.getInstance();
//        final int i = booksDao.addBook(book);
//        System.out.println(i);
//
//        final Date date = new Date();
//        final Timestamp timestamp = new Timestamp(date.getTime());
//        System.out.println(timestamp);
//        final Book book = new Book(2,"PHP1", "ERIC MATTHES", "程序设计", "978-7-115-42802-8", "人民邮电出版社", 89.0f, c_t, p_t, u_t);

//        final Vector<Book> books = booksDao.selectBook("MA");
//        final Vector<Book> books = booksDao.getBookList();
//        Iterator it=books.iterator();
//        while(it.hasNext()){
//            Book book1 = (Book)it.next();
//            System.out.println(book1.getBook_name());
//        }
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date=new Date();
//
//        System.out.println("date:"+date);
        Float a=3.14f;
        Float b=5.12f;
        Float c=8.26f;
        System.out.println(a+b==c);
    }
}
