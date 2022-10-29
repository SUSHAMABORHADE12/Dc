package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="DC_CASES")
@Entity
public class DcCasesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Integer caseId;
	
	private Long caseNum;
	
	private Integer appId;
	
	private Integer planId;
}
