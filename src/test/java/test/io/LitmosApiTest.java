package test.io;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLStreamException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import test.io.api.litmos.LitmosApiClient;
import test.io.api.litmos.response.TeamsInfo;
import test.io.config.LitmosConfig;

public class LitmosApiTest {

	private static final String COURSE = "sq2jTILT-b81";
	private static final String DEFAULT = "default";
	LitmosApiClient litmosApiClient;
	public static final String STUDENT = "5oGLveEIx4Y1";
	public static final String TEAM_12138114 = "PCXECEK7vf01";

	@BeforeEach
	public void BeforeAdvice() {
		LitmosConfig litmosConfig = new LitmosConfig();
		litmosConfig.setUrl("https://api.litmos.com.au/v1.svc/");

		litmosApiClient = new LitmosApiClient(litmosConfig);
	}

	@Test
	public void testUser() {
		System.out.print(litmosApiClient.executeUser(api -> api.getAllUser(DEFAULT)));
	}

	@Test
	public void testUserSearch() {
		System.out.print(litmosApiClient.executeUser(api -> api.getAllUser(DEFAULT, "jackdog040506@gmail.com")));
	}

	@Test
	public void testUserSearch2() {
		System.out.print(litmosApiClient.executeUser(api -> api.getUser("teststudent", DEFAULT)));
	}

	@Test
	public void test_getUserCustomFields() {
		System.out.print(litmosApiClient.executeUser(api -> api.getUserCustomFields(STUDENT, DEFAULT)));
	}

	@Test
	public void test_setUserCustomFields() {
		System.out.print(litmosApiClient.executeUser(api -> api.getUserCustomFields(STUDENT, DEFAULT)));
	}

	@Test
	public void test_writeUserCustomFields() {
		Map<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("vendorCd", "12138114");
		System.out.print(litmosApiClient.writeCustomFieldToUser(STUDENT, parameters, DEFAULT));
	}
	////

	@Test
	public void testUserAchievements() {
		System.out.print(litmosApiClient.executeCourse(api -> {
			return api.getUserAchievements(DEFAULT, "", STUDENT);
		}));
	}

	@Test
	public void testUserCourses() {
		System.out.print(litmosApiClient.executeCourse(api -> api.getUserCourses(STUDENT, DEFAULT)));
	}

	@Test
	public void testUserCoursesByIds() throws JsonProcessingException, XMLStreamException {
		List<String> ids = Arrays.asList(STUDENT);
		System.out.print(litmosApiClient.getUserCourseByUserIds(ids, DEFAULT));
	}

	@Test
	public void test_getUserCourseSpecific() {
		System.out.print(litmosApiClient.executeCourse(api -> api.getUserCourseSpecific(STUDENT, COURSE, DEFAULT)));
	}

	@Test
	public void test_assignCoursesToUser() throws JsonProcessingException, XMLStreamException {
		List<String> ids = Arrays.asList(STUDENT);
		String userId = "FsQIi61B57g1";
		System.out.print(litmosApiClient.assignCoursesToUser(userId, ids, DEFAULT));

	}

	@Test
	public void test_createTeams() {
		TeamsInfo teamsInfo = new TeamsInfo();
		teamsInfo.setName("12138114");
		teamsInfo.setDescription("測試上課進來");
		System.out.print(litmosApiClient.createTeams(teamsInfo, DEFAULT));
	}

	@Test
	public void test_getTeamsCourses() {

		System.out.print(litmosApiClient.executeCourse(api -> api.getCourseByTeamsId(TEAM_12138114, DEFAULT)));
	}

	@Test
	public void test_assignCoursesToTeams() {
		List<String> ids = Arrays.asList(COURSE);
		System.out.print(litmosApiClient.assignCoursesToTeams(TEAM_12138114, ids, DEFAULT));
	}
}
