package com.c3po;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * c3p0很容易使用的开源专业级jdbc数据库缓冲池。 它是sourceforge上的一个开源项目， 项目在
 * http://sourceforge.net/projects/c3p0 他的众多特性这里就不一一介绍了。 比较爽的一点就是
 * 当Connection归还缓冲池时，c3p0会很小心的关闭 这条连接打开的Statement和ResultSet,免去了使用时 自己动手小心翼翼的关闭。
 * c3p0使用非常简单，这里给一个例子
 * 
 * @ http://blog.csdn.net/partner4java/article/details/4826072
 * @JDBC 通过driverClass去查找相应的类(外观模式，或者是门面模式)，通过配置文件，或者直接作为加载驱动的代码，例如加载mysql的驱动：。
 * @//加载MySql的驱动类   加驱动就是外观模式，告诉你去用那个实现类(父类定义好规则)，这个实现类负责具体的业务实现。
 * @//  Class.forName("com.mysql.jdbc.Driver") ;   
 * @数据连接池 只是对连接进行了一次封装，通过JDBC的属性配置和初始化连接池
 * @spring 通过配置文件了解到池子由 ComboPooledDataSource类初始化，然后从他中获得连接(外观模式，或者是门面模式)
 * @之后的操作， 连接有了。spring封装了模板，通过模板进行sql操作。
 */
public final class C3p0Source {
    private C3p0Source() {
    }

    private static ComboPooledDataSource dataSource;
    static {
        try {
            dataSource = new ComboPooledDataSource();
            Properties properties = new Properties();
            InputStream is = C3p0Source.class.getClassLoader().getResourceAsStream("c3p0.properties");
            properties.load(is);
            // dataSource.setProperties(properties);
            dataSource.setUser(properties.getProperty("user"));
            dataSource.setPassword(properties.getProperty("password"));
            dataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
            dataSource.setDriverClass(properties.getProperty("driverClass"));

            dataSource.setInitialPoolSize(Integer.parseInt(properties.getProperty("initialPoolSize")));
            dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("minPoolSize")));
            dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("maxPoolSize")));
            dataSource.setMaxIdleTime(Integer.parseInt(properties.getProperty("maxIdleTime")));
            dataSource.setIdleConnectionTestPeriod(Integer.parseInt(properties.getProperty("idleConnectionTestPeriod")));
            dataSource.setMaxConnectionAge(Integer.parseInt(properties.getProperty("maxConnectionAge")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void realseSource(ResultSet rs, Statement st, Connection conn) throws SQLException {
        try {
            if (rs != null)
                rs.close();
        } finally {
            try {
                if (st != null)
                    st.close();
            } finally {
                if (conn != null)
                    conn.close();
            }
        }
    }
}