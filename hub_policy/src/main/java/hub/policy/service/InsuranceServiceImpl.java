package hub.policy.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dao.InsuranceDao;
import hub.policy.dto.PolicyPlanAddReqDto;
import hub.policy.dto.PolicyPlanResponseDto;
import hub.policy.dto.PolicyPlanReqDto;
import hub.policy.entities.PolicyPlan;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    @Autowired
    private InsuranceDao insuranceDao;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<PolicyPlanResponseDto> getAllPolicyPlan() {
        return insuranceDao.findAll()
                .stream()
                .map(policyPlan -> mapper.map(policyPlan, PolicyPlanResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String addNewPolicyPlan(PolicyPlanAddReqDto addPolicyPlan) {
        PolicyPlan newPolicy = mapper.map(addPolicyPlan, PolicyPlan.class);
        insuranceDao.save(newPolicy);
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
            existingPolicy.setCoverageAmount(policyPlan.getCoverageAmount());
            existingPolicy.setPremium(policyPlan.getPremium());
            existingPolicy.setDuration(policyPlan.getDuration());

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
    public PolicyPlanResponseDto getSinglePolicyPlan(Long id) {
        Optional<PolicyPlan> policyPlanOpt = insuranceDao.findById(id);
        
        if (policyPlanOpt.isPresent()) {
            return mapper.map(policyPlanOpt.get(), PolicyPlanResponseDto.class);
        }
        throw new ResourceNotFoundException("Policy plan does not found "); // or throw a custom exception
    }
}
