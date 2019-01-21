package com.sxm.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

/**
 * When your application’s startup is really slow, you may consider lazy initialization for all managed beans to improve your development experience.
 * Yet, sometime there are beans which should always be initialized, even if eager initialization has been globally disabled with @ComponentScan(lazyInit = true).
 * That’s when @Lazy(false) comes in.
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/18 0018 下午 15:49
 * @since 0.1
 */
@Lazy(false)
@Component
class AlwaysEagerResourceLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlwaysEagerResourceLoader.class);

    private List<Term> resources;

    @PostConstruct
    public void initHeavyLoading() throws InterruptedException {
        LOGGER.info("AlwaysEagerResourceLoader Loading start");
        // time-consuming execution
        Thread.sleep(10_000);
        resources = loadResources();
        LOGGER.info("AlwaysEagerResourceLoader Loading end");
    }

    private List<Term> loadResources() {
        // load resources e.g. from local app resources or web service
        return new LinkedList<>();
    }

    List<Term> getResources() {
        return resources;
    }

    // ...

}
