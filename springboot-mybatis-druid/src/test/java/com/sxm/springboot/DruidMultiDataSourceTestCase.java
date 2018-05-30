package com.sxm.springboot;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("multi-datasource")
public class DruidMultiDataSourceTestCase {

    @Resource
    private DruidDataSource dataSourceOne;
    @Resource
    private DruidDataSource dataSourceTwo;

    @Test
    public void testDataSourceOne() {
        assertThat(dataSourceOne.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/springbootdb?useUnicode=true&characterEncoding=utf8");
        assertThat(dataSourceOne.getUsername()).isEqualTo("root");
        assertThat(dataSourceOne.getPassword()).isEqualTo("123456");
        assertThat(dataSourceOne.getDriverClassName()).isEqualTo("com.mysql.jdbc.Driver");

        assertThat(dataSourceOne.getInitialSize()).isEqualTo(5);

        assertThat(dataSourceOne.getMaxActive()).isEqualTo(10);
        assertThat(dataSourceOne.getMaxWait()).isEqualTo(10000);
    }
    @Test
    public void testDataSourceTwo() {
        assertThat(dataSourceTwo.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/springbootdb?useUnicode=true&characterEncoding=utf8");
        assertThat(dataSourceTwo.getUsername()).isEqualTo("root");
        assertThat(dataSourceTwo.getPassword()).isEqualTo("123456");
        assertThat(dataSourceTwo.getDriverClassName()).isEqualTo("com.mysql.jdbc.Driver");

        assertThat(dataSourceTwo.getInitialSize()).isEqualTo(5);

        assertThat(dataSourceTwo.getMaxActive()).isEqualTo(20);
        assertThat(dataSourceTwo.getMaxWait()).isEqualTo(20000);
    }

}
