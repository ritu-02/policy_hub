package hub.policy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dao.PolicyPlanDao;
import hub.policy.dao.PolicyTypeDao;
import hub.policy.dto.PolicyPlanAddReqDto;
import hub.policy.dto.PolicyPlanResDto;
import hub.policy.dto.PolicyTypeRequestDto;
import hub.policy.dto.PolicyPlanReqDto;
import hub.policy.entities.InsuranceType;
import hub.policy.entities.PolicyPlan;
import hub.policy.entities.PolicyType;

@Service
@Transactional
public class PolicyPlanServiceImpl implements PolicyPlanService {
    @Autowired
    private PolicyPlanDao insuranceDao;
    @Autowired
    private PolicyTypeDao typeDao;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PolicyPlanResDto> getAllPolicyPlan() {
        return insuranceDao.findAll()
                .stream()
                .map(policyPlan -> mapper.map(policyPlan, PolicyPlanResDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addNewPolicyPlan(PolicyPlanAddReqDto request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }

        // Find PolicyType by ID
        PolicyType policyType = typeDao.findById(request.getPolicyTypeId())
                .orElseThrow(() -> new IllegalArgumentException("PolicyType not found for ID: " + request.getPolicyTypeId()));

        // Create new PolicyPlan
        PolicyPlan policyPlan = new PolicyPlan();
        policyPlan.setPolicyPlanName(request.getPolicyPlanName());
        policyPlan.setDescription(request.getDescription());
        policyPlan.setEligibility(request.getEligibility());
        policyPlan.setPremium(request.getPremium());
        policyPlan.setSumInsured(request.getSumInsured());
        policyPlan.setTerm(request.getTerm());
        policyPlan.setPolicyType(policyType);

        // Add to PolicyType's list (Bidirectional)
        policyType.addPolicyPlan(policyPlan);

        // Save both entities
        typeDao.save(policyType);

        return "Policy Plan added successfully!";
    }


    @Override
    public String updatePolicyPlan(Long policyPlanId, PolicyPlanReqDto policyPlan) {
        Optional<PolicyPlan> existingPolicyOpt = insuranceDao.findById(policyPlanId);
        
        if (existingPolicyOpt.isPresent()) {
            PolicyPlan existingPolicy = existingPolicyOpt.get();
            
            // Update fields
            existingPolicy.setDescription(policyPlan.getDescription());
            existingPolicy.setPolicyPlanName(policyPlan.getPolicyPlanName());
            existingPolicy.setSumInsured(0);
            existingPolicy.setPremium(policyPlan.getPremium());
            existingPolicy.setTerm(policyPlan.getTerm());
            existingPolicy.setEligibility(policyPlan.getEligibility());

            insuranceDao.save(existingPolicy);
            return "Updated Policy Plan successfully!";
        }
        return "Policy Plan not found!";
    }

    @Override
    public String deletePolicyPlan(Long id) {
        if (insuranceDao.existsById(id)) {        
            insuranceDao.deleteById(id);
            return "Policy Plan deleted successfully!";
        }
        return "Policy Plan not found!";
    }

    @Override
    public PolicyPlanResDto getSinglePolicyPlan(Long id) {
        Optional<PolicyPlan> policyPlanOpt = insuranceDao.findById(id);
        
        if (policyPlanOpt.isPresent()) {
            return mapper.map(policyPlanOpt.get(), PolicyPlanResDto.class);
        }
        throw new ResourceNotFoundException("Policy plan does not found "); // or throw a custom exception
    }

	@Override
	public List<PolicyPlanResDto> getPolicyPlanByType(String type) {
		 PolicyType policyType= typeDao.findByInsuranceType(InsuranceType.valueOf(type.toUpperCase())).orElseThrow(()->new ResourceNotFoundException(" policy type  not exist"));
		   
		 List<PolicyPlan> policyPlans = insuranceDao.findByPolicyTypeId( policyType.getTypeId());
		 List<PolicyPlanResDto> policyPlanDtos = policyPlans.stream()
				    .map(plan -> new PolicyPlanResDto(plan)) // ✅ Correct mapping
				    .collect(Collectors.toList());
		 policyPlans.forEach(plan -> 
		    System.out.println("Plan: " + plan.getPolicyPlanId() + ", Type ID: " + 
		    (plan.getPolicyType() != null ? plan.getPolicyType().getTypeId() : "NULL"))
		);

        return policyPlanDtos;
	
	}
}
