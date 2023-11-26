package com.kyc.catalogs.model.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PostalCodeData {

    @JsonProperty("postal_code")
    private String postalCode;
    @JsonProperty("id_state")
    private Integer idState;
    @JsonProperty("state")
    private String state;
    @JsonProperty("id_city")
    private Integer idCity;
    @JsonProperty("city")
    private String city;
    @JsonProperty("neighborhoods")
    private List<NeighborhoodData> neighborhoods;
}