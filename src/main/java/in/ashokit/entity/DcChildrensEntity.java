package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "DC_CHILDRENS")
@Entity
public class DcChildrensEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer childrenId;

	private String childrenName;

	private Integer childrenAge;

	private Integer childrenSsn;
	
	private Long caseNum;

}
