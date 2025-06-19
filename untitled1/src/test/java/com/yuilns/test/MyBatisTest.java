package com.yuilns.test;

import com.yuilns.mapper.ReaderMapper;
import com.yuilns.pojo.Reader1;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {
    @Test
    public void testSelectAll() throws IOException {
        //1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取Mapper接口的代理对象
        ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

        //4.执行方法
        List<Reader1> readers = readerMapper.selectAll();
        System.out.println(readers);

        sqlSession.close();
    }
    @Test
    public void testSelectById() throws IOException {
        //接收参数
        int id=1;

        //1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取Mapper接口的代理对象
        ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

        //4.执行方法
        Reader1 reader = readerMapper.selectById(id);
        System.out.println(reader);

        sqlSession.close();
    }
    @Test
    public void testSelectByCondition() throws IOException {
        //接收参数
        int age=18;
        String gender="男";
        String readername="张";

        readername="%"+ readername +"%";

        //封装参数
        Map map=new HashMap();
        map.put("gender",gender);
        map.put("age",age);
//        map.put("readername",readername);

        //1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取Mapper接口的代理对象
        ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

        //4.执行方法
        List<Reader1> readers = readerMapper.selectByCondition(map);
        System.out.println(readers);

        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        //接收参数
        int age=18;
        String gender="男";
        String name="伍六七";
        String idcard="114893";
        String tel="1329190";

//        readername="%"+ readername +"%";

        //封装对象
        Reader1 reader=new Reader1();
        reader.setReader_name(name);
        reader.setAge(age);
        reader.setGender(gender);
        reader.setIdcard_number(idcard);
        reader.setTel(tel);

        //封装参数
//        Map map=new HashMap();
//        map.put("gender",gender);
//        map.put("age",age);
//        map.put("readername",readername);

        //1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3.获取Mapper接口的代理对象
        ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

        //4.执行方法
        readerMapper.add(reader);

        //提交事务


        sqlSession.close();
    }

    @Test
    public void testAdd2() throws IOException {
        //接收参数
        int age=18;
        String gender="男";
        String name="伍六七";
        String idcard="114893";
        String tel="1329190";

//        readername="%"+ readername +"%";

        //封装对象
        Reader1 reader=new Reader1();
        reader.setReader_name(name);
        reader.setAge(age);
        reader.setGender(gender);
        reader.setIdcard_number(idcard);
        reader.setTel(tel);

        //1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3.获取Mapper接口的代理对象
        ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

        //4.执行方法
        readerMapper.add(reader);
        Integer readerId = reader.getReader_id();
        System.out.println(readerId);

        //提交事务
//        sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        //接收参数
        int age=22;
        String gender="男";
        String name="伍六七";
        String idcard="114893";
        String tel="13291922";
        int Reader_id=18;
//        readername="%"+ readername +"%";

        //封装对象
        Reader1 reader=new Reader1();
        reader.setReader_name(name);
        reader.setAge(age);
        reader.setGender(gender);
        reader.setIdcard_number(idcard);
        reader.setTel(tel);
        reader.setReader_id(Reader_id);

        //1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3.获取Mapper接口的代理对象
        ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

        //4.执行方法
        int count = readerMapper.update(reader);
        System.out.println(count);

        sqlSession.close();
    }

    @Test
    public void testDeleteById() throws IOException {
        //接收参数
        int id=19;

        //1.获取SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取sqlsession对象
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3.获取Mapper接口的代理对象
        ReaderMapper readerMapper = sqlSession.getMapper(ReaderMapper.class);

        //4.执行方法
        readerMapper.deleteById(id);

        sqlSession.close();
    }
}
