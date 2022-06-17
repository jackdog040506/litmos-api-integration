package test.io.api.litmos.request;

import lombok.Data;

@Data
public class CreateUserRequest {
	private String Id;
	private String UserName;
	private String FirstName;
	private String LastName;
	private String FullName;
	private String Email;
	private String AccessLevel="Learner";
	private String DisableMessages="false";
	private String Active="true";
	private String IsCustomUsername="true";
	private String SkipFirstLogin="false";
	private String TimeZone="";
}
