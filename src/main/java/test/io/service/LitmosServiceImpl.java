package test.io.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.io.api.litmos.LitmosApiClient;
import test.io.api.litmos.response.CourseInfo;
import test.io.api.litmos.response.TeamsInfo;
import test.io.api.litmos.response.UserInfoAdvanced;
import test.io.config.LitmosConfig;
import test.io.entity.ThirdPartyBinding;
import test.io.entity.ThirdPartyProviderScopes;
import test.io.repository.ThirdPartyBindingRepository;

@Service
public class LitmosServiceImpl implements LitmosService {

	@Autowired
	private ThirdPartyBindingRepository thirdPartyBindingRepository;

	@Autowired
	private LitmosConfig litmosConfig;

	@Autowired
	private LitmosApiClient litmosApiClient;

	@Override
	public List<CourseInfo> getUserCoursesByEmail(String email) {
		ThirdPartyProviderScopes scope = ThirdPartyProviderScopes.LITMOS_USER;
		Optional<ThirdPartyBinding> optional = thirdPartyBindingRepository
				.findByIdIdentifyTypeAndIdIdentifyValueAndIdProviderTypeAndIsDelete(scope.getIdentifyType(), email,
						scope.getProviderType(), "N");
		if (optional.isPresent()) {
			ThirdPartyBinding thirdPartyBinding = optional.get();
			List<CourseInfo> courseInfos = litmosApiClient
					.executeCourse(api -> api
							.getUserCourses(thirdPartyBinding.getIdentifyThirdParty(), litmosConfig.getSource()));
			return courseInfos;
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public UserInfoAdvanced getUserInfoByUserId(String email) {
		ThirdPartyProviderScopes scope = ThirdPartyProviderScopes.LITMOS_USER;
		Optional<ThirdPartyBinding> optional = thirdPartyBindingRepository
				.findByIdIdentifyTypeAndIdIdentifyValueAndIdProviderTypeAndIsDelete(scope.getIdentifyType(), email,
						scope.getProviderType(), "N");
		if (optional.isPresent()) {
			ThirdPartyBinding thirdPartyBinding = optional.get();
			UserInfoAdvanced userInfoAdvanced = litmosApiClient
					.executeUser(
							api -> api.getUser(thirdPartyBinding.getIdentifyThirdParty(), litmosConfig.getSource()));
			return userInfoAdvanced;
		} else {
			return null;
		}
	}

	@Override
	public TeamsInfo getTeamInfoByTeamId(String teamId) {
		ThirdPartyProviderScopes scope = ThirdPartyProviderScopes.LITMOS_TEAM;
		Optional<ThirdPartyBinding> optional = thirdPartyBindingRepository
				.findByIdIdentifyTypeAndIdIdentifyValueAndIdProviderTypeAndIsDelete(scope.getIdentifyType(), teamId,
						scope.getProviderType(), "N");
		if (optional.isPresent()) {
			ThirdPartyBinding thirdPartyBinding = optional.get();
			TeamsInfo teamsInfo = litmosApiClient
					.executeUser(api -> api
							.getTeamsById(thirdPartyBinding.getIdentifyThirdParty(), litmosConfig.getSource()));
			return teamsInfo;
		} else {
			return null;
		}
	}

	@Override
	public void assignUserToTeam(String email, String teamId) {
		ThirdPartyBinding user = null, team = null;
		ThirdPartyProviderScopes scope = ThirdPartyProviderScopes.LITMOS_USER;
		Optional<ThirdPartyBinding> optional = thirdPartyBindingRepository
				.findByIdIdentifyTypeAndIdIdentifyValueAndIdProviderTypeAndIsDelete(scope.getIdentifyType(), email,
						scope.getProviderType(), "N");
		user = optional.orElse(null);
		scope = ThirdPartyProviderScopes.LITMOS_TEAM;
		optional = thirdPartyBindingRepository
				.findByIdIdentifyTypeAndIdIdentifyValueAndIdProviderTypeAndIsDelete(scope.getIdentifyType(), teamId,
						scope.getProviderType(), "N");
		team = optional.orElse(null);
		if (null != user && null != team) {
			litmosApiClient
					.assignUserToTeams(user.getIdentifyThirdParty(), Arrays.asList(team.getIdentifyThirdParty()),
							litmosConfig.getSource());
		}
	}

}
