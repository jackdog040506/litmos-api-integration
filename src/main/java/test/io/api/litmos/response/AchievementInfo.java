package test.io.api.litmos.response;

import java.util.Map;

import lombok.Data;

@Data
public class AchievementInfo {

	private String UserId;// ": "string [max length 50]",
	private String Title;// ": "string [max length 512]",
	private String Description;// ": "string",
	private String AchievementDate;// ": "datetime [iso 8601 format]",
	private String CourseId;// ": "string [max length 50]",
	private Map<String, String> CompliantTillDate;
	// ": {
	// "-nil": "true",
	// "#text": "datetime [iso 8601 format]"
	// },
	private String Score;// ": "string [max length 50]",
	private String Result;// ": "string [max length 50]",
	private String Type;// ": "string [max length 255]",
	private String FirstName;// ": "string [max length 50]",
	private String LastName;// ": "string [max length 50]",
	private Integer AchievementId;// ": "integer",
	private Integer CertificateId;// ": "integer"

//	UserId – The person that gained the achievement
//	Title – The title of the course or achievement
//	Description – The description of the achievement
//	AchievementDate – The date & time the achievement was gained
//	CourseId – If the achievement was completing a course in Litmos this is the course that was completed
//	CompliantTillDate – If populated this indicates when a person is compliant till.
//	Score – If populated this indicates the person score when gaining the achievement
//	Result – A free text description of the achievement status. e.g., Completed
//	Type – The type of achievement e.g., Online Course
//	AchievementId – The Id of the Achievement
//	CertificateId – The Id of the Certificate
}
