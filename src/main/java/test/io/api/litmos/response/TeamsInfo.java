package test.io.api.litmos.response;

import lombok.Data;

@Data
public class TeamsInfo {
	private String Id;
	private String Name;
	private String TeamCodeForBulkImport;
	private String ParentTeamId;
	private String Description;

}
