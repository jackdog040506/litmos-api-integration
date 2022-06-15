package test.io.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class EntityBase {

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastModified;

	private String createdBy;

	private String modifiedBy;

	private String isDelete;

}
