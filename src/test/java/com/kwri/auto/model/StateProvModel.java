package com.kwri.auto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * The type State prov model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class StateProvModel {
    private Integer id;
    private String code;
    @JsonAlias("country_id")
    private int countryId;
    private String name;
}
