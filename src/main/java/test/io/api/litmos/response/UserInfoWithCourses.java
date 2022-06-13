package test.io.api.litmos.response;

import java.util.List;

import lombok.Data;

@Data
public class UserInfoWithCourses {
	private String Id;
	private List<CourseInfo> Courses;
}
