package test.io.api.litmos.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CourseInfo {
	private String Id;// ": "string [max length 50]",
	private String Code;// ": "string [max length 20]",
	private String Name;// ": "string [max length 255]",
	private Boolean Active;// ": "true/false",
	private Boolean Complete;// ": "true/false",
	private BigDecimal PercentageComplete;// ": "decimal",
	private String AssignedDate;// ": "YYYY-MM-DDTHH:MM:SS.SSS",
	private String StartDate;// ": "YYYY-MM-DDTHH:MM:SS.SSS",
	private String DateCompleted;// ": "YYYY-MM-DDTHH:MM:SSS",
	private String UpToDate;// ": "true/false",
	private String Overdue;// ": "true/false",
	private String CompliantTill;// ": "YYYY-MM-DDTHH:MM:SS.SSS",
	private String IsLearningPath;// ": "true/false",
	private String CourseCreatedDate;// ": "YYYY-MM-DDTHH:MM:SS.SSS",
	private String CourseCreator;// ": "string [max length 50]",
	private Integer OriginalId;// ": "integer",
	private String ResultId;// ": "string [max length 50]",
	private String AccessTillDate;// ": "YYYY-MM-DDTHH:MM:SS.SSS" Certificate

}
