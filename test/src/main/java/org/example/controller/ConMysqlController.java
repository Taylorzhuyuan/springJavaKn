package org.example.controller;

import org.example.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConMysqlController {

    @Autowired
    private TestService testService;

    /*测试mysql数据库连接*/
    @RequestMapping("/mysql")
    public void testMysql() {
        Integer id = 1;
        Integer count = testService.countInfo(1);
    }
}
