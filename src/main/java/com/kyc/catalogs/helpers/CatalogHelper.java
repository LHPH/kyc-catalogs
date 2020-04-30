package com.kyc.catalogs.helpers;

import org.springframework.stereotype.Component;

import com.kyc.catalogs.entity.AuthorizedBanksDTO;
import com.kyc.catalogs.entity.PaymenthMethodsDTO;
import com.kyc.catalogs.entity.ServiceStatusDTO;
import com.kyc.catalogs.entity.ServicesDTO;
import com.kyc.catalogs.entity.StatesCountryDTO;
import com.kyc.catalogs.model.AuthorizedBanksVO;
import com.kyc.catalogs.model.PaymenthMethodsVO;
import com.kyc.catalogs.model.ServiceStatusVO;
import com.kyc.catalogs.model.ServicesVO;
import com.kyc.catalogs.model.StatesCountryVO;

@Component
public class CatalogHelper {
	
	
	public ServicesVO mapAsModel(ServicesDTO serviceDTO) {
		
		if(serviceDTO!=null) {
			
			ServicesVO serviceVO = new ServicesVO();
			serviceVO.setId(serviceDTO.getId());
			serviceVO.setName(serviceDTO.getName());
			serviceVO.setCost(serviceDTO.getCost());
			
			return serviceVO;
		}
		
		return null;
	}
	
	public AuthorizedBanksVO mapAsModel(AuthorizedBanksDTO bankDTO) {
	   
	   if(bankDTO != null) {
	      
	      AuthorizedBanksVO bankVO = new AuthorizedBanksVO();
	      bankVO.setId(bankDTO.getId());
	      bankVO.setCveBank(bankDTO.getCveBank());
	      bankVO.setName(bankDTO.getName());
	      
	      return bankVO;
	   }
	   return null;
	   
	}
	
	public PaymenthMethodsVO mapAsModel(PaymenthMethodsDTO methodDTO) {
	   
	   if(methodDTO!=null) {
	      
	      PaymenthMethodsVO methodVO = new PaymenthMethodsVO();
	      methodVO.setId(methodDTO.getId());
	      methodVO.setMethod(methodDTO.getMethod());
	      methodVO.setGenericReference(methodDTO.getGenericReference());
	      
	      return methodVO;
	   }
	   return null;
	}
	
	public ServiceStatusVO mapAsModel(ServiceStatusDTO statusDTO) {
	   
	   if(statusDTO != null) {
	      
	      ServiceStatusVO statusVO = new ServiceStatusVO();
	      statusVO.setId(statusDTO.getId());
	      statusVO.setDescription(statusDTO.getDescription());
	      
	      return statusVO;
	   }
	   return null;
	}
	
	public StatesCountryVO mapAsModel(StatesCountryDTO statesDTO) {
	   
	   if(statesDTO!=null) {
	       
	      StatesCountryVO statesVO = new StatesCountryVO();
	      statesVO.setId(statesDTO.getId());
	      statesVO.setCode(statesDTO.getCode());
	      statesVO.setDescription(statesDTO.getDescription());
	      
	      return statesVO;
	   }
	   return null;
	}

}
