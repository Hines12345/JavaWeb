package BookShop.dao.impl;

import BookShop.dao.UserDao;
import BookShop.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {
    //根据用户名查询用户信息
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=?;";
        return queryForOne(User.class,sql,username);
    }

    //保存用户信息
    @Override
    public int saveUser(User user) {
        String sql = "INSERT INTO t_user(`username`,`password`,`email`) VALUES(?,?,?);";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    //根据用户名和密码查询用户信息
    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=? and password = ?;";
        return queryForOne(User.class,sql,username,password);
    }
}
