package com.kyc.catalogs.enums;

import lombok.Getter;

@Getter
public enum CatalogEnum {

   UNKNOWN("unknown"), SERVICES("services"), SERVICES_STATUS("servicesStatus"), AUTHORIZED_BANKS("banks"),
   PAYMENTH_METHODS("paymentMethods"), STATES_COUNTRY("statesCountry");

   private String name;

   private CatalogEnum(String name) {

      this.name = name;
   }

   public static CatalogEnum getInstanceByName(String name) {

      CatalogEnum value = CatalogEnum.UNKNOWN;

      for (CatalogEnum catalog : CatalogEnum.values()) {
         if (catalog.getName().equals(name)) {
            value = catalog;
         }
      }

      return value;
   }

}
