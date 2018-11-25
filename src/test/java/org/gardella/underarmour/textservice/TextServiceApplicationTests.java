package org.gardella.underarmour.textservice;

import org.gardella.underarmour.textservice.models.MessageRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TextServiceApplicationTests {


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testGets() {

		assertThat(
				restTemplate.getForObject("http://localhost:" + port + "/chat/2", String.class)
				).contains("{}");

		assertThat(
				restTemplate.getForObject("http://localhost:" + port + "/chats/larry", String.class)
				).contains("[]");

	}

	@Test
	public void testPost() {

		String postUrl = "http://localhost:" + port + "/chat";

		MessageRequest messageRequest = new MessageRequest();
		messageRequest.setUsername("moe");
		messageRequest.setText("This is a test message for moe.");

		HttpEntity<MessageRequest> request = new HttpEntity<>(messageRequest);
		String resp = restTemplate.postForObject(postUrl, request, String.class);
		assertThat(resp).contains("id");
	}

}
