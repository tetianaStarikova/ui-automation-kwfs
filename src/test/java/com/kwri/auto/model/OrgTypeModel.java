package com.kwri.auto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * The type Org type model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrgTypeModel {
    private Integer id;
    private String name;
    @JsonAlias("parent_org_type_id")
    private int parentOrgTypeId;
}
