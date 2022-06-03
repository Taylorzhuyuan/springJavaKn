package org.example.service.impl;

import org.example.mapper.TestMapper;
import org.example.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestServiceImpl implements TestService {

    Logger testLogger = LoggerFactory.getLogger("testLogger");

    @Resource
    private TestMapper testMapper;

    @Override
    public int countInfo(Integer id) {
        Integer count = testMapper.countInfo(id);
        testLogger.info("共有" + count + "条数据");
        return count;
        }
}
