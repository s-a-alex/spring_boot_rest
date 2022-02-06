package getphos.spring.spring_boot_rest.test;

import getphos.spring.spring_boot_rest.entity.Singer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestClientConfig.class})
public class RestClientTest {

	final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
	private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/rest/singer/listdata";
	private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/rest/singer/{id}";
	private static final String URL_CREATE_SINGER = "http://localhost:8080/rest/singer/";
	private static final String URL_UPDATE_SINGER = "http://localhost:8080/rest/singer/{id}";
	private static final String URL_DELETE_SINGER = "http://localhost:8080/rest/singer/{id}";

	@Autowired RestTemplate restTemplate;

	@Before
	public void setUp() {
		assertNotNull(restTemplate);
	}

	@Test
	public void testFindAll() {
		logger.info("--> Testing retrieve all singers");
		Singer[] singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singer[].class);
		assertTrue(singers.length == 14);
		listSingers(singers);
	}

	@Test
	public void testFindById() {
		logger.info("--> Testing retrieve a singer by id : 1");
		Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1);
		assertNotNull(singer);
		logger.info(singer.toString());
	}

	@Test
	public void testUpdate() {
		logger.info("--> Testing update singer by id : 1");
		Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1);
		singer.setFirstName("John Clayton");
		restTemplate.put(URL_UPDATE_SINGER, singer, 1);
		logger.info("Singer update successfully: " + singer);
	}

	@Test
	public void testDelete() {
		logger.info("--> Testing delete singer by id : 3");
		restTemplate.delete(URL_DELETE_SINGER, 3);
		Singer[] singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singer[].class);
		Arrays.asList(singers).forEach(s-> { if(s.getId() == 3) assertFalse(true);});
		listSingers(singers);
	}

	@Test
	public void testCreate() {
		logger.info("--> Testing create singer");
		Singer singerNew = new Singer();
		singerNew.setFirstName("BB");
		singerNew.setLastName("King");
		singerNew.setBirthDate(new GregorianCalendar(1940, 8, 16).getTime());
		singerNew = restTemplate.postForObject(URL_CREATE_SINGER, singerNew, Singer.class);

		logger.info("Singer created successfully: " + singerNew);

		Singer[] singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singer[].class);
		listSingers(singers);
	}

	private void listSingers(Singer[] singers) {
		Arrays.asList(singers).forEach(s -> logger.info(s.toString()));
	}
}
