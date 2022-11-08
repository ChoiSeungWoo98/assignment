package com.example.assignment.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootTest
@Slf4j
public class MyBatisConfigTests {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Test
    public void dataSourceTest(){
        try
            (
                Connection conn = dataSource.getConnection();
            )
        {
            log.info("----------------------------");
            log.info("data : "+ conn);
            log.info("----------------------------");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void sqlSessionTest(){
            log.info("----------------------------");
            log.info("factory : "+ sqlSessionFactory);
        log.info("----------------------------");

        try
                    (
                            SqlSession sqlSession = sqlSessionFactory.openSession(true);
                            Connection conn = sqlSession.getConnection();
                    )
            {
                log.info("----------------------------");
                log.info("sql Session" + sqlSession);
                log.info("----------------------------");
                log.info("sql Conn" + conn);

            }catch (Exception e){
            e.printStackTrace();
        }
    }
}
