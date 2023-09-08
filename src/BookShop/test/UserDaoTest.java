package BookShop.test;

import BookShop.dao.UserDao;
import BookShop.dao.impl.UserDaoImpl;
import BookShop.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null,"Hines2","2003","2587688776@qq.com")));

    }

    @Test
    public void queryByUsernameAndPassword() {
        if(userDao.queryByUsernameAndPassword("Hines","2003") == null )
        {
            System.out.println("用户名或密码错误");
        }
        else {
            System.out.println("成功！");
        }
    }
}