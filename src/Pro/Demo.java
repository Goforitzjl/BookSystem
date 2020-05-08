package Pro;

import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Vector;

public class Demo {

    public static void main(String[] args) {

//
//        User user = new User(1, 20, "zhang",
//                "1234", "male", "13551860651",
//                "四川成都",true);


        User user = new User(2,21, "lvxinran",
                "654321", "female", "19983411198",
                "乌鲁木齐",true);
        UsersDao uo=new UsersDao();
//        int i=uo.addUser(user);
//        System.out.println(i);
//
        boolean flag = uo.checkUser(user);
        System.out.println(flag);

//        Vector<User> users =uo.UserList();
//        Iterator it = users.iterator();
//        while(it.hasNext()){
//            User user=(User)it.next();
//            System.out.println(user.getName());
//
//        }
    }
}
