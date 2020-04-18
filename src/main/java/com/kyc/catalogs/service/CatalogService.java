package com.kyc.catalogs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyc.catalogs.entity.ServicesDTO;
import com.kyc.catalogs.enums.CatalogEnum;
import com.kyc.catalogs.helpers.CatalogHelper;
import com.kyc.catalogs.repositories.AuthorizedBanksRepository;
import com.kyc.catalogs.repositories.PaymenthMethodsRepository;
import com.kyc.catalogs.repositories.ServiceStatusRepository;
import com.kyc.catalogs.repositories.ServicesRepository;
import com.kyc.catalogs.repositories.StateCountryRepository;
import com.kyc.catalogs.util.FunctionsUtil;

@Service
public class CatalogService {

	@Autowired
	private AuthorizedBanksRepository banksRepository;
	
	@Autowired
	private PaymenthMethodsRepository paymenthsMethodRepository;
	
	@Autowired
	private ServicesRepository servicesRepository;
	
	@Autowired
	private ServiceStatusRepository servicesStatusRepository;
	
	@Autowired
	private StateCountryRepository stateCountryRepository;
	
	@Autowired
	private CatalogHelper catalogHelper;
	
	
	public Object getCatalog(String cadCatalog) {
		
		CatalogEnum catalog = CatalogEnum.getInstanceByName(cadCatalog);
		
		switch(catalog) {
		
			case SERVICES:
				
				List<ServicesDTO> services=servicesRepository.findAll();
				return services.stream().map(e-> catalogHelper.mapAsModel(e)).collect(Collectors.toList());
				
			case SERVICES_STATUS:
				break;
			case AUTHORIZED_BANKS:
				break;
			case PAYMENTH_METHODS:
				break;
			case STATES_COUNTRY:
				break;
		    default:
		    	return null;
		}
		return null;
	}
	
	public Object getCriteria(String cadCatalog,String criteria) {
		
		CatalogEnum catalog = CatalogEnum.getInstanceByName(cadCatalog);
		
		switch(catalog) {
		
			case SERVICES:
				
				ServicesDTO service=servicesRepository.getServiceById(FunctionsUtil.strMustInteger(criteria));
				return catalogHelper.mapAsModel(service);
				
			case SERVICES_STATUS:
				break;
			case AUTHORIZED_BANKS:
				break;
			case PAYMENTH_METHODS:
				break;
			case STATES_COUNTRY:
				break;
		    default:
		    	return null;
		}

		return null;
	}
	
}
