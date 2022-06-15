package test.io.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Table(name = "THIRD_PARTY_BINDING")
@Entity
public class ThirdPartyBinding extends EntityBase {

	@EmbeddedId
	private ThirdPartyBindingPK id;

	private String identifyThirdParty;

	private String displayName;

}
