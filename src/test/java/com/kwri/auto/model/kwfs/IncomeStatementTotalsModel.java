package com.kwri.auto.model.kwfs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Income statement totals groups model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncomeStatementTotalsModel {
    private String currentActual;
    private String currentBudget;
    private String currentVariance;
    private String ytdActual;
    private String ytdBudget;
    private String ytdVariance;
    private String annualBudget;
    private String name;
}
