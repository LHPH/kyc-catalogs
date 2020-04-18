package com.kyc.catalogs.config;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

@Component
public class ErrorAttributesConfig extends DefaultErrorAttributes {
	
	public static final Logger LOGGER = LogManager.getLogger(ErrorAttributesConfig.class);
	 
	@Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
		
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
		LOGGER.error("Ocurrio un error al procesar la peticion en {}",errorAttributes.get("path"));
		LOGGER.error("{}",errorAttributes.get("trace"));
		
		errorAttributes.remove("trace");
		
		return errorAttributes;
	}

}
