package com.autobid.util;

import com.autobid.dao.UserMapper;
import com.autobid.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

/**
 * @Author Richard Zeng
 * @ClassName: JSONUtilTest
 * @Description:
 * @Date 2018/1/12 18:48
 */

public class JSONUtilTest {

    // private static SqlSessionFactory sqlSessionFactory;
    @Test
    public void testJSONToObject() throws IOException {
        String userStr = "{\"id\":1001,\"name\":\"Richard\",\"age\":37}";
        User newUser;
        newUser = (User) JSONUtil.JSONToObj(userStr, User.class);
        System.out.println("newUser:" + newUser.getName() + ",age:" + newUser.getAge());

/*        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
                .createJsonGenerator(System.out, JsonEncoding.UTF8);*/
    }

    @Test
    public void testJSONToObjectAndWriteToDb() throws IOException {
        String userStr = "{\"id\":1001,\"name\":\"Richard\",\"age\":37}";
        User newUser;
        newUser = (User) JSONUtil.JSONToObj(userStr, User.class);
        //System.out.println("newUser:"+newUser.getName()+",age:"+newUser.getAge());

/*        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
                .createJsonGenerator(System.out, JsonEncoding.UTF8);*/
    }


    @Test
    public void testSelectOneUserSession() {
        //SqlSession session = MyBatisUtil.getSqlSession(true);
/*        String resource = "mybatis-config.xml";
        InputStream is = JSONUtilTest.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);*/
        SqlSession session = MyBatisUtil.getSqlSession(true);
        String statement = "com.autobid.dao.UserMapper.getUser";
        User selectUser = session.selectOne(statement, 1);
        System.out.println(selectUser);
    }

    @Test
    public void testSelectOneUserDao() {
        UserMapper userDao = (UserMapper) MyBatisUtil.getSqlSession(true).getMapper(UserMapper.class);
        User user = userDao.getUser(2);
        System.out.println(user);
    }

    @Test
    public void testInsertUserFromJson() {
        String userStr = "{\"id\":15,\"name\":\"Richard\",\"age\":37}";
        User newUser = (User) JSONUtil.JSONToObj(userStr, User.class);
        UserMapper userDao = (UserMapper) MyBatisUtil.getSqlSession(true).getMapper(UserMapper.class);
        //System.out.println(newUser);
        System.out.println(userDao.insert(newUser));
    }

    private static int insertJDBC(User user) throws ClassNotFoundException, SQLException {
        String driverName ="com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://localhost:3306/bbd";
        String username = "root";
        String password = "12345";
        //¼ÓÔØÇý¶¯
        Driver.class.forName(driverName);
        Connection conn = DriverManager.getConnection(URL,username,password);
        int i = 0;
        String sql = "insert into users(id,name,age) values(?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setInt(3, user.getAge());
            i = pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //@Test
    public void testJDBCInsertUser() throws SQLException, ClassNotFoundException {
        String userStr = "{\"id\":16,\"name\":\"Richard\",\"age\":37}";
        User user = (User) JSONUtil.JSONToObj(userStr, User.class);
        insertJDBC(user);
    }

}