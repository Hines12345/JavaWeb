package BookShop.service.impl;

import BookShop.dao.UserDao;
import BookShop.dao.impl.BaseDao;
import BookShop.dao.impl.UserDaoImpl;
import BookShop.pojo.User;
import BookShop.service.UserService;

public class UserServiceImpl implements UserService {

    //Dao对象连接数据库(通过 接口 Dao对象 = new 接口实现对象   来创建连接数据库的对象)
    private UserDao userDao = new UserDaoImpl();

    //注册界面：要把注册的信息传到数据库中（所有要有Dao对象）
    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    //登陆
    @Override
    public User login(User user) {
        return userDao.queryByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if(userDao.queryUserByUsername(username) == null)
        {
            return false;
        }
        return true;
    }
}
