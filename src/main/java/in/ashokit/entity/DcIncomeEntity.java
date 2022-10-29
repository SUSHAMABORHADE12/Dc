package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name="DC_INCOME")
@Entity
public class DcIncomeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeId;	
	
	private Long caseNum;
	
	private Integer empIncome;
	
	private Integer rentIncome;
	
	private Integer propertyIncome;	

}
