package com.kyc.catalogs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kyc.catalogs.entity.AuthorizedBanksDTO;
import com.kyc.catalogs.entity.PaymentMethodsDTO;
import com.kyc.catalogs.entity.ServiceStatusDTO;
import com.kyc.catalogs.entity.ServicesDTO;
import com.kyc.catalogs.entity.StatesCountryDTO;
import com.kyc.catalogs.enums.CatalogEnum;
import com.kyc.catalogs.helpers.CatalogHelper;
import com.kyc.catalogs.repositories.AuthorizedBanksRepository;
import com.kyc.catalogs.repositories.PaymentMethodsRepository;
import com.kyc.catalogs.repositories.ServiceStatusRepository;
import com.kyc.catalogs.repositories.ServicesRepository;
import com.kyc.catalogs.repositories.StateCountryRepository;
import com.kyc.catalogs.util.FunctionsUtil;

@Service
public class CatalogService {

   @Autowired
   private AuthorizedBanksRepository banksRepository;

   @Autowired
   private PaymentMethodsRepository paymenthsMethodRepository;

   @Autowired
   private ServicesRepository servicesRepository;

   @Autowired
   private ServiceStatusRepository servicesStatusRepository;

   @Autowired
   private StateCountryRepository statesCountryRepository;

   @Autowired
   private CatalogHelper catalogHelper;

   public Object getCatalog(String cadCatalog) {

      CatalogEnum catalog = CatalogEnum.getInstanceByName(cadCatalog);

      switch (catalog) {

         case SERVICES:

            List<ServicesDTO> services = servicesRepository.findAll();
            return services.stream().map(e -> catalogHelper.mapAsModel(e)).collect(Collectors.toList());

         case SERVICES_STATUS:

            List<ServiceStatusDTO> status = servicesStatusRepository.findAll();
            return status.stream().map(e -> catalogHelper.mapAsModel(e)).collect(Collectors.toList());

         case AUTHORIZED_BANKS:

            List<AuthorizedBanksDTO> banks = banksRepository.findAll();
            return banks.stream().map(e -> catalogHelper.mapAsModel(e)).collect(Collectors.toList());

         case PAYMENTH_METHODS:

            List<PaymentMethodsDTO> methods = paymenthsMethodRepository.findAll();
            return methods.stream().map(e -> catalogHelper.mapAsModel(e)).collect(Collectors.toList());

         case STATES_COUNTRY:

            List<StatesCountryDTO> statesCountry = statesCountryRepository.findAll();
            return statesCountry.stream().map(e -> catalogHelper.mapAsModel(e)).collect(Collectors.toList());

         default:
            return null;
      }
   }

   public Object getCriteria(String cadCatalog, String criteria) {

      CatalogEnum catalog = CatalogEnum.getInstanceByName(cadCatalog);

      switch (catalog) {

         case SERVICES:

            ServicesDTO service = servicesRepository.getServiceById(FunctionsUtil.strMustInteger(criteria));
            return catalogHelper.mapAsModel(service);

         case SERVICES_STATUS:

            ServiceStatusDTO status = servicesStatusRepository.getServiceStatusById(FunctionsUtil.strMustInteger(criteria));
            return catalogHelper.mapAsModel(status);

         case AUTHORIZED_BANKS:

            AuthorizedBanksDTO bank = banksRepository.getAuthorizedBankById(FunctionsUtil.strMustInteger(criteria));
            return catalogHelper.mapAsModel(bank);

         case PAYMENTH_METHODS:

            PaymentMethodsDTO method = paymenthsMethodRepository.getPaymenthMethodById(FunctionsUtil.strMustInteger(criteria));
            return catalogHelper.mapAsModel(method);

         case STATES_COUNTRY:

            StatesCountryDTO states = statesCountryRepository.getStatesCountryById(FunctionsUtil.strMustInteger(criteria));
            return catalogHelper.mapAsModel(states);
         default:
            return null;
      }
   }

}
