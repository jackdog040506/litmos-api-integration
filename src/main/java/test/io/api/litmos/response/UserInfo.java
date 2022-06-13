package test.io.api.litmos.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = { "Id" })
public class UserInfo {
	private String Id;
	private String UserName;
	private String FirstName;
	private String LastName;
	private Boolean Active;
	private String Email;
	private String AccessLevel;
	private String Brand;
}
