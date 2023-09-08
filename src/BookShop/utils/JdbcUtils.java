package BookShop.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;


import javax.imageio.stream.ImageInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {

        try {
            Properties properties = new Properties();
            //读取jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }





        //获取数据库连接池的连接
        public static Connection getConnection()
        {
            Connection connection = conns.get();

            if(connection == null)
            {
                try {
                    connection = dataSource.getConnection();
                    conns.set(connection); //保存到ThreadLocal对象中
                    connection.setAutoCommit(false); //设置为手动管理事务

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            return connection;
        }

    /**
     * 提交事务，并关闭连接
     */
    public static void commitAndClose()
    {
        Connection connection = conns.get();
        if (connection != null)
        {
            try {
                connection.commit();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        conns.remove();
    }

    /**
     * 回滚事务，并关闭连接
     */
    public static void rollbackAndClose()
        {
            Connection connection = conns.get();
            if (connection != null)
            {
                try {
                    connection.rollback(); //回滚事务

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }finally {
                    try {
                        connection.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            conns.remove();
        }

    }
