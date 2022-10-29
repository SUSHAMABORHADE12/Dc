package in.ashokit.service;

import java.util.Map;

import in.ashokit.bindings.ChildRequest;
import in.ashokit.bindings.DcSummary;
import in.ashokit.bindings.Education;
import in.ashokit.bindings.Income;
import in.ashokit.bindings.PlanSelection;

public interface DcService {

	/*
	 * public Integer search(Integer appId);
	 * 
	 * public List<String> getPlanNames();
	 * 
	 * public boolean savePlan(PlanMasterEntity plan);
	 * 
	 * public boolean saveIncomeDetails(DcIncomeEntity income,Integer caseNum);
	 * 
	 * public boolean saveEducationDetails(DcEducationEntity education,Integer
	 * caseNum);
	 * 
	 * //public boolean saveChildrenDetails(DcChildrensEntity childrens,Integer
	 * caseNum);
	 * 
	 * public boolean saveChildrensDetails(List<DcChildrensEntity>
	 * childrenslist,Integer caseNum);
	 * 
	 * public List<DcIncomeEntity> getAllCitizenIncomeDetails();
	 * 
	 * public List<DcEducationEntity> getAllCitizenEducationDetails();
	 * 
	 * public List<DcChildrensEntity> getAllCitizenChildrenDetails();
	 */

	public Long loadCaseNum(Integer appId);

	public Map<Integer, String> getPlanNames();

	public Long savePlanSelection(PlanSelection planSelection);

	public Long saveIncomeData(Income income);

	public Long saveEducation(Education education);

	public DcSummary getSummary(Long caseNum);

	public Long saveChildrens(ChildRequest request);

}
