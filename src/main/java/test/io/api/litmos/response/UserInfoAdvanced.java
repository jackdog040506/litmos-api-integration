package test.io.api.litmos.response;

import java.util.Locale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoAdvanced extends UserInfo {

	private String FullName;
	private Boolean DisableMessages;
	private String Skype;// ": "",
	private String PhoneWork;// ": "",
	private String PhoneMobile;// ": "",
	private String LastLogin;// ": "2022-06-13T01:26:15Z",
	private String LoginKey;// "https:\/\/tsmcuat.litmos.com.au\/login.aspx?loginkey=615a98a5-0856-4840-8100-4631d983015a",
	private Boolean IsCustomUsername;// ": false,
	private String Password;// ": "",
	private Boolean SkipFirstLogin;// ": true,
	private String TimeZone;// ": "Taipei Standard Time",
	private String SalesforceId;// ": null,
	private Integer OriginalId;// ": 4049856,
	private String Street1;// ": "",
	private String Street2;// ": "",
	private String City;// ": "",
	private String State;// ": "",
	private String PostalCode;// ": "",
	private String Country;// ": "",
	private String CompanyName;// ": "",
	private String JobTitle;// ": "",
	private String CustomField1;// ": "",
	private String CustomField2;// ": "",
	private String CustomField3;// ": "",
	private String CustomField4;// ": "",
	private String CustomField5;// ": "",
	private String CustomField6;// ": "",
	private String CustomField7;// ": "",
	private String CustomField8;// ": "",
	private String CustomField9;// ": "",
	private String CustomField10;// ": "",
	private Locale Culture;// ": "en-US",
	private String SalesforceContactId;// ": null,
	private String SalesforceAccountId;// ": null,
	private String CreatedDate;// ": "2022-06-10T05:32:03Z",
	private String Points;// ": 0,
	private String ManagerId;// ": "0",
	private String ManagerName;// ": "",
	private Boolean EnableTextNotification;// ": false,
	private String Website;// ": "",
	private String Twitter;// ": "",
	private String ExpirationDate;// ": "",
	private String JobRole;// ": "",
	private String ExternalEmployeeId;// ": "",
	private String ProfileType;// ": "External"
}
