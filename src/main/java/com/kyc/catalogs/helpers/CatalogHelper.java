package com.kyc.catalogs.helpers;

import org.springframework.stereotype.Component;

import com.kyc.catalogs.entity.ServicesDTO;
import com.kyc.catalogs.model.ServicesVO;

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

}
