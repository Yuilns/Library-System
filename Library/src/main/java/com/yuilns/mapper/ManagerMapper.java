package com.yuilns.mapper;

import com.yuilns.pojo.Manager;

import java.util.List;

public interface ManagerMapper {

    List<Manager> selectAll();

    List<Manager> selectListById(String id);
    Manager selectById(String id);

    int insertManager(Manager manager);
    int updateManager(Manager manager);
    int deleteManagerById(Integer userId);
}
