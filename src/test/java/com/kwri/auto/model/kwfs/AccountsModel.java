package com.kwri.auto.model.kwfs;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * The type Accounts model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsModel {
    @JsonAlias("account_name")
    private String accountName;
    private String grouping;
    @JsonAlias("accountedge_account_number")
    private String accountedgeAccountNumber;
    @JsonAlias("lonewolf_account_number")
    private String lonewolfAccountNumber;
}
