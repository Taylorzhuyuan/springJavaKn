package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    int countInfo(Integer id);
}
