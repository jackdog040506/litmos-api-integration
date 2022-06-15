package test.io.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ThirdPartyBindingPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String identifyType;

	private String identifyValue;

	private String providerType;

}
