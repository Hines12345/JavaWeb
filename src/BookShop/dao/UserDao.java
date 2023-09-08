package BookShop.dao;

import BookShop.pojo.User;

public interface UserDao {

    /*
    根据用户名查询用户信息
    （如果返回null，说明没有这个用户。反之亦然）
     */
    public User queryUserByUsername(String name);

    /**
     * 保存用户信息
     */
    public int saveUser(User user);

    /**
     * 根据用户名和密码查询用户信息
     */
    public User queryByUsernameAndPassword(String name,String password);
}
