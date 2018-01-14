package com.autobid.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MyBatisUtil {

    /**
     * ��ȡSqlSessionFactory
     *
     * @return SqlSessionFactory
     */
    private static SqlSessionFactory getSqlSessionFactory() {
        String resource = "mybatis-config.xml";
        InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(is);
    }

    /**
     * ��ȡSqlSession
     *
     * @return SqlSession
     */
    @SuppressWarnings("deprecation")
    public static SqlSession getSqlSession() {
        return getSqlSessionFactory().openSession();
    }

    /**
     * ��ȡSqlSession
     *
     * @param isAutoCommit true ��ʾ������SqlSession������ִ����SQL֮����Զ��ύ����
     *                     false ��ʾ������SqlSession������ִ����SQL֮�󲻻��Զ��ύ������ʱ����Ҫ�����ֶ�����sqlSession.commit()�ύ����
     * @return SqlSession
     */
    public static SqlSession getSqlSession(boolean isAutoCommit) {
        return getSqlSessionFactory().openSession(isAutoCommit);
    }
}