package com.kwri.auto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

/**
 * The type Broker model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrokerModel {
    @JsonAlias("first_name")
    private String firstName;
    @JsonAlias("kw_uid")
    private int kwUid;
    @JsonAlias("last_name")
    private String lastName;
    private String photo;
}
