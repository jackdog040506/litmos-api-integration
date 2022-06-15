package test.io;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import test.io.api.litmos.response.CourseInfo;
import test.io.api.litmos.response.UserInfoAdvanced;
import test.io.entity.ThirdPartyBinding;
import test.io.entity.ThirdPartyBindingPK;
import test.io.repository.ThirdPartyBindingRepository;
import test.io.service.LitmosService;

@SpringBootTest
public class LitmosUserIntegrationTest extends TestBase {
	@Autowired
	ThirdPartyBindingRepository thirdPartyBindingRepository;
	@Autowired
	LitmosService litmosService;
//	private static final String COURSE = "sq2jTILT-b81";

	public static final String USER_ID = "teststudent@gmail.com";

	public static final String STUDENT = "5oGLveEIx4Y1";
	public static final String STUDENT_NAME = "teststudent";
	public static final String TEAM_12138114 = "PCXECEK7vf01";

	@BeforeEach
	public void before() {
		ThirdPartyBindingPK pk = new ThirdPartyBindingPK();
		pk.setIdentifyType("USERID");
		pk.setIdentifyValue(USER_ID);
		pk.setProviderType("LITMOS");
		ThirdPartyBinding thirdPartyBinding = new ThirdPartyBinding();
		thirdPartyBinding.setId(pk);
		thirdPartyBinding.setIdentifyThirdParty(STUDENT);
		thirdPartyBinding.setDisplayName(STUDENT_NAME);
		thirdPartyBinding.setIsDelete("N");
		thirdPartyBindingRepository.save(thirdPartyBinding);

	}

	@Test
	public void test_getUserInfoByUserId() {
		UserInfoAdvanced userInfoAdvanced = litmosService.getUserInfoByUserId(USER_ID);
		assertThat(userInfoAdvanced.getId()).isEqualTo(STUDENT);
	}

	@Test
	public void test_getUserCoursesByEmail() {
		List<CourseInfo> courseInfos = litmosService.getUserCoursesByEmail(USER_ID);
		assertThat(courseInfos).isNotEmpty();
	}
}
