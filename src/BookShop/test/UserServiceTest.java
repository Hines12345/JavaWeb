package BookShop.test;

import BookShop.pojo.User;
import BookShop.service.UserService;
import BookShop.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null,"HinesRegisUser","2003","12345@qq.com"));
        userService.registUser(new User(null,"HinesRegisUser_2","200303","12345@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "Hines", "2003", null)));
    }

    @Test
    public void existUsername() {
        if(userService.existUsername("Hines"))
        {
            System.out.println("用户名已存在");
        }
        else{
            System.out.println("用户名可用！");
        }

    }
}