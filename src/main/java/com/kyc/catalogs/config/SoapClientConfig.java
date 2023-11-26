package com.kyc.catalogs.config;

import com.kyc.catalogs.ws.postalcodes.types.GetPostalCodeRequest;
import com.kyc.catalogs.ws.postalcodes.types.GetPostalCodeResponse;
import com.kyc.core.soap.SoapClient;
import com.kyc.core.soap.interceptors.SoapMessageInterceptor;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SoapClientConfig {

    private String postalCodeUrl = "http://localhost:9007/kyc/soap/PostalCodes";

    @Bean
    public Jaxb2Marshaller marshaller(){

        List<String> packages = new ArrayList<>();
        packages.add("com.kyc.catalogs.ws.postalcodes.types");
        packages.add("com.kyc.catalogs.ws.postalcodes.common");

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths(packages.toArray(new String[0]));
        return marshaller;
    }

    @Bean
    public SoapClient<GetPostalCodeRequest, GetPostalCodeResponse> postalCodeSoapClient(WebServiceTemplateBuilder builder){

        WebServiceTemplate template = builder
                .setMarshaller(marshaller())
                .setUnmarshaller(marshaller())
                .interceptors(new SoapMessageInterceptor(true))
                .build();

        return new SoapClient<>(postalCodeUrl,template);
    }
}
