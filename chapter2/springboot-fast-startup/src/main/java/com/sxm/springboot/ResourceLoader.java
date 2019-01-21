package com.sxm.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Component
class ResourceLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceLoader.class);

    private List<Term> resources;

    @PostConstruct
    public void initHeavyLoading() throws InterruptedException {
        LOGGER.info("ResourceLoader Loading start");
        // time-consuming execution
        Thread.sleep(10_000);
        resources = loadResources();
        LOGGER.info("ResourceLoader Loading end");
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
