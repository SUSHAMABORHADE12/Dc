package in.ashokit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.bindings.Child;
import in.ashokit.bindings.ChildRequest;
import in.ashokit.bindings.DcSummary;
import in.ashokit.bindings.Education;
import in.ashokit.bindings.Income;
import in.ashokit.bindings.PlanSelection;
import in.ashokit.entity.CitizenAppEntity;
import in.ashokit.entity.DcCasesEntity;
import in.ashokit.entity.DcChildrensEntity;
import in.ashokit.entity.DcEducationEntity;
import in.ashokit.entity.DcIncomeEntity;
import in.ashokit.entity.PlanMasterEntity;
import in.ashokit.repo.CitizenAppRepo;
import in.ashokit.repo.DcCasesRepo;
import in.ashokit.repo.DcChildrensRepo;
import in.ashokit.repo.DcEducationRepo;
import in.ashokit.repo.DcIncomeRepo;
import in.ashokit.repo.PlanMasterRepo;

@Service
public class DcServiceImpl implements DcService {

	@Autowired
	private PlanMasterRepo planRepo;

	@Autowired
	private DcChildrensRepo childRepo;

	@Autowired
	private DcEducationRepo educationRepo;

	@Autowired
	private DcIncomeRepo incomeRepo;

	@Autowired
	private DcCasesRepo dcCaseRepo;

	@Autowired
	private CitizenAppRepo appRepo;

	@Override
	public Long loadCaseNum(Integer appId) {

		/*
		 * DcCasesEntity findByAppId = dcCaseRepo.findByAppId(appId); if (findByAppId !=
		 * null) { return findByAppId.getCaseNum(); } return null;
		 */

		Optional<CitizenAppEntity> app = appRepo.findById(appId);

		if (app.isPresent()) {
			DcCasesEntity entity = new DcCasesEntity();
			entity.setAppId(appId);

			entity = dcCaseRepo.save(entity);

			return entity.getCaseNum();
		}
		return 0L;
	}

	@Override
	public Map<Integer, String> getPlanNames() {

		List<PlanMasterEntity> findAll = planRepo.findAll();
		Map<Integer, String> plansMap = new HashMap<>();

		for (PlanMasterEntity entity : findAll) {
			plansMap.put(entity.getPlanId(), entity.getPlanName());
		}
		return plansMap;
	}

	@Override
	public Long savePlanSelection(PlanSelection planSelection) {

		/*DcCasesEntity entity = new DcCasesEntity();

		entity.setPlanId(planSelection.getPlanId());
		entity.setAppId(planSelection.getAppId());

		entity = dcCaseRepo.save(entity);

		if (entity.getCaseNum() != null) {
			return entity.getCaseNum();
		}
		return null;*/
		
		Optional<DcCasesEntity> findById=dcCaseRepo.findById(planSelection.getCaseNum());
		if(findById.isPresent()) {
			DcCasesEntity dcCaseEntity = findById.get();
			dcCaseEntity.setPlanId(planSelection.getPlanId());
			dcCaseRepo.save(dcCaseEntity);
			return planSelection.getCaseNum();
			
		}
                  return null;
	}

	@Override
	public Long saveIncomeData(Income income) {

		DcIncomeEntity entity = new DcIncomeEntity();
		BeanUtils.copyProperties(income, entity);

		incomeRepo.save(entity);

		return income.getCaseNum();

	}

	@Override
	public Long saveEducation(Education education) {

		DcEducationEntity entity = new DcEducationEntity();
		BeanUtils.copyProperties(education, entity);

		educationRepo.save(entity);

		return education.getCaseNum();
	}

	@Override
	/*
	 * public Long saveChildrens(List<Child> childs) {
	 * 
	 * for(Child c:childs) { DcChildrensEntity entity = new DcChildrensEntity();
	 * 
	 * BeanUtils.copyProperties(c, entity);
	 * 
	 * childRepo.save(entity); }
	 * 
	 * return childs.get(0).getCaseNum();
	 * 
	 * }
	 */

	public Long saveChildrens(ChildRequest request) {

		List<Child> childs = request.getChilds();
		Long caseNum=request.getCaseNum();
		for (Child c : childs) {
			DcChildrensEntity entity = new DcChildrensEntity();

			BeanUtils.copyProperties(c, entity);
			entity.setCaseNum(caseNum);

			childRepo.save(entity);
		}

		return request.getCaseNum();

	}

	@Override
	public DcSummary getSummary(Long caseNum) {

		String planName = "";
		DcIncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);
		DcEducationEntity educationEntity = educationRepo.findByCaseNum(caseNum);
		List<DcChildrensEntity> childsEntity = childRepo.findByCaseNum(caseNum);

		Optional<DcCasesEntity> dcCase = dcCaseRepo.findById(caseNum);
		if (dcCase.isPresent()) {
			Integer planId = dcCase.get().getPlanId();
			Optional<PlanMasterEntity> plan = planRepo.findById(planId);
			if (plan.isPresent()) {
				planName = plan.get().getPlanName();
			}
		}

		DcSummary summary = new DcSummary();
		summary.setPlanName(planName);

		Income income = new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setIncome(income);

		Education edu = new Education();
		BeanUtils.copyProperties(educationEntity, edu);
		summary.setEducation(edu);

		List<Child> childs = new ArrayList<>();
		for (DcChildrensEntity entity : childsEntity) {
			Child ch = new Child();
			BeanUtils.copyProperties(entity, ch);
			childs.add(ch);

		}
		summary.setChilds(childs);

		return summary;
	}

}
