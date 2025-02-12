package hub.policy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dao.PolicyTypeDao;
import hub.policy.dto.AddPolicyTypeReqDto;
import hub.policy.dto.PolicyTypeDto;
import hub.policy.entities.PolicyType;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Service
@Transactional
public class PolicyTypeServiceImpl implements PolicyTypeService {

	@Autowired
	private PolicyTypeDao policyTypeDao;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public PolicyTypeDto addPolicyType( AddPolicyTypeReqDto policyObj) {
		System.out.println(policyObj.getInsuranceType());
	    if (policyObj == null || policyObj.getInsuranceType() == null || policyObj.getDescription() == null) {
	        throw new IllegalArgumentException("Insurance Type and Description cannot be null.");
	    }
        
	    PolicyType type = new PolicyType();
	    type.setInsuranceType(policyObj.getInsuranceType());
	    type.setDescription(policyObj.getDescription());

	    PolicyType savedType = policyTypeDao.save(type);

	    return mapper.map(savedType, PolicyTypeDto.class);
	}


	@Override
	public PolicyTypeDto updatePolicyTypeById(Long id,AddPolicyTypeReqDto updateObj) {
		 if(policyTypeDao.existsById(id)) {
			 PolicyType typeObj=mapper.map(updateObj, PolicyType.class);
			 policyTypeDao.save(typeObj);
			 return mapper.map(typeObj,PolicyTypeDto.class);
		 }
		throw new ResourceNotFoundException("Invalid User id");
	}

	@Override
	public String deletePolicyTypeById(Long id) {
	    return policyTypeDao.findById(id)
	        .map(policyType -> {
	            policyTypeDao.delete(policyType);
	            return "Policy Type deleted successfully.";
	        })
	        .orElseThrow(() -> new ResourceNotFoundException("Invalid Policy Type ID: " + id));
	}

	@Override
	public List<PolicyTypeDto> getAllPolicyTypes() {
		return policyTypeDao.findAll().stream().map(user -> mapper.map(user, PolicyTypeDto.class))
				.collect(Collectors.toList());
	}
	

	@Override
	public PolicyTypeDto getPolicyTypeById(Long id) {
		PolicyType typeObj=policyTypeDao.findById(id).orElseThrow(()->new ResourceNotFoundException("Invalid id"));
		return mapper.map(typeObj, PolicyTypeDto.class);
	}

}
