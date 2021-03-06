package com.sxm.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomFaviconApplicationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testIfCustomeFaviconIsReturned() {
		
		HttpHeaders headers = restTemplate.headForHeaders("/favicon.ico");
		
		assertThat(headers.getContentLength()).isEqualTo(5430);

	}

}
