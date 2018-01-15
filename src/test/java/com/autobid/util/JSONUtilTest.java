package com.autobid.util;

import com.autobid.mapping.UsersMapper;
import com.autobid.model.Users;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

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
        Users newUser;
        newUser = (Users) JSONUtil.JSONToObj(userStr,Users.class);
        System.out.println("newUser:"+newUser.getName()+",age:"+newUser.getAge());

/*        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
                .createJsonGenerator(System.out, JsonEncoding.UTF8);*/
    }

    @Test
    public void testJSONToObjectAndWriteToDb() throws IOException {
        String userStr = "{\"id\":1001,\"name\":\"Richard\",\"age\":37}";
        Users newUser;
        newUser = (Users) JSONUtil.JSONToObj(userStr,Users.class);
        //System.out.println("newUser:"+newUser.getName()+",age:"+newUser.getAge());

/*        ObjectMapper objectMapper = new ObjectMapper();
        JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
                .createJsonGenerator(System.out, JsonEncoding.UTF8);*/
    }


    @Test
    public void testSelectOneUserSession(){
        //SqlSession session = MyBatisUtil.getSqlSession(true);
/*        String resource = "mybatis-config.xml";
        InputStream is = JSONUtilTest.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession(true);*/
        SqlSession session = MyBatisUtil.getSqlSession(true);
        String statement = "com.autobid.mapping.UsersMapper.getUser";
        Users selectUser = session.selectOne(statement,1);
        System.out.println(selectUser);
    }

    @Test
    public void testSelectOneUserDao(){
        UsersMapper userDao = (UsersMapper) MyBatisUtil.getSqlSession(true).getMapper(UsersMapper.class);
        Users user = userDao.getUser(1);
        System.out.println(user);
    }

    @Test
    public void testInsertUserFromJson(){
        String userStr = "{\"id\":5,\"name\":\"Richard\",\"age\":37}";
        Users newUser = (Users) JSONUtil.JSONToObj(userStr,Users.class);
        UsersMapper userDao = (UsersMapper) MyBatisUtil.getSqlSession(true).getMapper(UsersMapper.class);
        System.out.println(newUser);
        userDao.insert(newUser);
    }
}
