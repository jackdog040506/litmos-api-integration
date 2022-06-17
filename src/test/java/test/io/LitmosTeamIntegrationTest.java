package test.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import test.io.api.litmos.response.TeamsInfo;
import test.io.entity.ThirdPartyBinding;
import test.io.entity.ThirdPartyBindingPK;
import test.io.entity.ThirdPartyProviderScopes;
import test.io.repository.ThirdPartyBindingRepository;
import test.io.service.LitmosService;

@SpringBootTest
public class LitmosTeamIntegrationTest extends TestBase {
	@Autowired
	ThirdPartyBindingRepository thirdPartyBindingRepository;
	@Autowired
	LitmosService litmosService;

	@BeforeEach
	public void before() {
		{
			ThirdPartyProviderScopes scope = ThirdPartyProviderScopes.LITMOS_TEAM;
			ThirdPartyBindingPK pk = new ThirdPartyBindingPK();
			pk.setIdentifyType(scope.getIdentifyType());
			pk.setIdentifyValue(TEAM_ID);
			pk.setProviderType(scope.getProviderType());
			ThirdPartyBinding thirdPartyBinding = new ThirdPartyBinding();
			thirdPartyBinding.setId(pk);
			thirdPartyBinding.setIdentifyThirdParty(TEAM);
			thirdPartyBinding.setDisplayName(TEAM_ID);
			thirdPartyBinding.setIsDelete("N");
			thirdPartyBindingRepository.save(thirdPartyBinding);
		}
		{
			ThirdPartyProviderScopes scope = ThirdPartyProviderScopes.LITMOS_USER;
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

	}

	@Test
	public void test_getTeamInfoByTeamId() {
		TeamsInfo teamsInfo = litmosService.getTeamInfoByTeamId(TEAM_ID);
		assertThat(teamsInfo.getId()).isEqualTo(TEAM);
	}

	@Test
	public void test_assignUserToTeam() {
		litmosService.assignUserToTeam(USER_ID, TEAM_ID);
	}
}
