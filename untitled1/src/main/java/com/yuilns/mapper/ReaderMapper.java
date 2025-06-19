package com.yuilns.mapper;

import com.yuilns.pojo.Reader1;

import java.util.List;
import java.util.Map;

public interface ReaderMapper {
    List<Reader1> selectAll();
    /**
     * 查看详情
     */
    Reader1 selectById(int id);

    /**
     * 参数查询
     */
//    List<Reader> selectByCondition(@Param("gender")String gender, @Param("age")int age, @Param("readername") String readername);
    //List<Reader> selectByCondition(Reader brand);
    List<Reader1> selectByCondition(Map map);

    /**
     * 添加
     */

    void add2(Reader1 reader);
    int add(Reader1 reader);
    int update(Reader1 reader);

    void deleteById(int id);

    int deleteByIdCard(String idCard);
    Reader1 selectByIdCard(String idCard);
}
