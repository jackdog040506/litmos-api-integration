package test.io.service;

import java.util.List;

import test.io.api.litmos.response.CourseInfo;
import test.io.api.litmos.response.TeamsInfo;
import test.io.api.litmos.response.UserInfoAdvanced;

public interface LitmosService {

	List<CourseInfo> getUserCoursesByEmail(String email);

	UserInfoAdvanced getUserInfoByUserId(String email);

	TeamsInfo getTeamInfoByTeamId(String team);

	void assignUserToTeam(String email, String team);

}
