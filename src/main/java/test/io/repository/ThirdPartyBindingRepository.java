package test.io.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import test.io.entity.ThirdPartyBinding;
import test.io.entity.ThirdPartyBindingPK;

public interface ThirdPartyBindingRepository extends JpaRepository<ThirdPartyBinding, ThirdPartyBindingPK> {

	Optional<ThirdPartyBinding> findByIdIdentifyTypeAndIdIdentifyValueAndIdProviderTypeAndIsDelete(String identifyType,
			String identifyValue, String providerType, String isDelete);
}
