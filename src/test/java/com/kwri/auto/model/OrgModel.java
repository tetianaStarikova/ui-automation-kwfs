package com.kwri.auto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

/**
 * The type Org model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgModel {
    private Integer id;
    private String name;
    @JsonAlias("org_type_id")
    private int orgTypeId;
    @JsonAlias("parent_org_id")
    private int parentOrgId;
    @JsonAlias("parent_org_name")
    private String parentOrgName;
    @JsonAlias("state_prov_id")
    private int stateProvId;
    @JsonAlias("state_prov")
    private StateProvModel stateProv;
    @JsonAlias("country_id")
    private int countryId;
    private CountryModel country;
    @JsonAlias("org_key")
    private String orgKey;
    @JsonAlias("corporate_entity_name")
    private String corporateEntityName;
    @JsonAlias("dba_name")
    private String dbaName;
    @JsonAlias("start_dt")
    private String startDt;
    private String phone;
    private String fax;
    private String email;
    private String address1;
    private String address2;
    private String city;
    @JsonAlias("fran_connect_id")
    private String franConnectId;
    private Integer mca;
    private String latitude;
    private String longitude;
    private Integer op;
    private Integer[] tl;
    @JsonAlias("org_type")
    private OrgTypeModel orgType;
    @JsonAlias("service_postal_cd")
    private String servicePostalCd;
    @JsonAlias("postal_cd")
    private String postalCd;
    @JsonAlias("search_terms")
    private String searchTerms;
    @JsonAlias("legacy_org_id")
    private int legacyOrdId;
    @JsonAlias("legacy_team_id")
    private int legacyTeamId;
    @JsonAlias("legacy_expansion_team_id")
    private int legacyExpansionTeamId;
    @JsonAlias("team_non_kw_gci")
    private int teamNonKwGci;
    @JsonAlias("team_non_kw_units")
    private int teamNonKwUnits;
    @JsonAlias("month_end_close")
    private String monthEndClose;
    @JsonAlias("org_licenses")
    private List<OrgLicencesModel> orgLicenses;
}
