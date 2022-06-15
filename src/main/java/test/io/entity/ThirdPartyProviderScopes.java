package test.io.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ThirdPartyProviderScopes {
	LITMOS_USER("LITMOS", "USERID"),
	LITMOS_TEAM("LITMOS", "TEAMID");

	private String providerType;
	private String identifyType;
}
