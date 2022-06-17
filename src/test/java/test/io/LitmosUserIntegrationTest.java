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
import test.io.entity.ThirdPartyProviderScopes;
import test.io.repository.ThirdPartyBindingRepository;
import test.io.service.LitmosService;

@SpringBootTest
public class LitmosUserIntegrationTest extends TestBase {
	@Autowired
	ThirdPartyBindingRepository thirdPartyBindingRepository;
	@Autowired
	LitmosService litmosService;
//	private static final String COURSE = "sq2jTILT-b81";

	@BeforeEach
	public void before() {
		ThirdPartyProviderScopes scope = ThirdPartyProviderScopes.LITMOS_TEAM;
		ThirdPartyBindingPK pk = new ThirdPartyBindingPK();
		pk.setIdentifyType(scope.getIdentifyType());
		pk.setIdentifyValue(USER_ID);
		pk.setProviderType(scope.getProviderType());
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
