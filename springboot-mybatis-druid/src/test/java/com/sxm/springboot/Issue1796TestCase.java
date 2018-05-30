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
@ActiveProfiles("issue-1796")
public class Issue1796TestCase {
    @Resource
    private DruidDataSource dataSource;

    @Test
    public void test() {
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:mysql://localhost:3306/springbootdb?useUnicode=true&characterEncoding=utf8");
        assertThat(dataSource.getUsername()).isEqualTo("root");
        assertThat(dataSource.getPassword()).isEqualTo("123456");
        assertThat(dataSource.getDriverClassName()).isEqualTo("com.mysql.jdbc.Driver");

        assertThat(dataSource.getMinEvictableIdleTimeMillis()).isEqualTo(100000);
        assertThat(dataSource.getMaxEvictableIdleTimeMillis()).isEqualTo(200000);
    }
}
