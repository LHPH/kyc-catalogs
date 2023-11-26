package com.kyc.catalogs.mappers;

import com.kyc.catalogs.model.web.NeighborhoodData;
import com.kyc.catalogs.model.web.PostalCodeData;
import com.kyc.catalogs.ws.postalcodes.types.GetPostalCodeResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostalCodeConverter implements Converter<GetPostalCodeResponse, PostalCodeData> {

    @Override
    public PostalCodeData convert(GetPostalCodeResponse source) {

        PostalCodeData data = new PostalCodeData();

        data.setPostalCode(source.getData().getPostalCode());
        data.setIdState(source.getData().getState().getId().intValue());
        data.setState(source.getData().getState().getName());
        data.setIdCity(source.getData().getCity().getId().intValue());
        data.setCity(source.getData().getCity().getName());

        List<NeighborhoodData> neighborhoods = source.getData()
                .getNeighborhoods()
                .getNeighborhood()
                .stream()
                .map(e -> new NeighborhoodData(e.getId().intValue(),e.getName()))
                .collect(Collectors.toList());

        data.setNeighborhoods(neighborhoods);

        return data;
    }
}
