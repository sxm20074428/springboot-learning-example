package com.sxm.springboot;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("filter")
public class DruidFilterTestCase {

    @Resource
    private DruidDataSource dataSource;

    @Test
    public void test() {
        List<Filter> filters = dataSource.getProxyFilters();
        assertThat(filters.size()).isEqualTo(4);
    }
}
