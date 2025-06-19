package com.yuilns;

import com.yuilns.mapper.ManagerMapper;
import com.yuilns.pojo.Manager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//Mybatis代理开发
public class MyBatisDemo2 {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession=sqlSessionFactory.openSession();

        //3.执行sql
        //List<Object> users=sqlSession.selectList("test.selectAll");
        //3.1获取UserMapper接口的代理对象
        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
        List<Manager> managers = mapper.selectAll();

        System.out.println(managers);

        sqlSession.close();
    }
}
