package com.kwri.auto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * The type Org licences model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgLicencesModel {
    private BrokerModel broker;
    @JsonAlias("state_prov")
    private StateProvModel stateProv;
    @JsonAlias("broker_kw_uid")
    private int brokerKwUid;
    private int id;
    private String number;
    private String expiration;
}
