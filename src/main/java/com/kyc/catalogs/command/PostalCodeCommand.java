package com.kyc.catalogs.command;

import com.kyc.catalogs.mappers.PostalCodeConverter;
import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.model.web.PostalCodeData;
import com.kyc.catalogs.ws.postalcodes.types.GetPostalCodeRequest;
import com.kyc.catalogs.ws.postalcodes.types.GetPostalCodeResponse;
import com.kyc.core.soap.SoapClient;
import com.kyc.core.soap.model.RequestSoapData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("POSTAL_CODE_WS_COMMAND")
public class PostalCodeCommand implements CatalogCommand<PostalCodeData>{

    @Autowired
    private SoapClient<GetPostalCodeRequest, GetPostalCodeResponse> soapClient;

    @Autowired
    private PostalCodeConverter converter;

    @Override
    public PostalCodeData invokeSingle(CatalogInfo catalogInfo, Object id) {


        GetPostalCodeRequest request = new GetPostalCodeRequest();
        request.setPostalCode(String.valueOf(id));

        RequestSoapData<GetPostalCodeRequest> reqSoap = RequestSoapData.<GetPostalCodeRequest>builder()
                .payload(request)
                .build();

        GetPostalCodeResponse response = soapClient.executeService(reqSoap);
        return converter.convert(response);
    }
}
