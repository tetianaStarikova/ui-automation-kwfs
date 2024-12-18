package com.kwri.auto.model.kwfs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The type Account history model.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountsHistoryModel {
    private List<String> name;
    private List<String> accountNumber;
    private List<String> reconciledDate;
    private List<String> id;
    private List<String> accountHeaderId;
    private List<String> lastYearAndBalance;
    private List<String> janBudget;
    private List<String> febBudget;
    private List<String> marBudget;
    private List<String> aprBudget;
    private List<String> mayBudget;
    private List<String> junBudget;
    private List<String> julBudget;
    private List<String> augBudget;
    private List<String> sepBudget;
    private List<String> octBudget;
    private List<String> novBudget;
    private List<String> decBudget;
    private List<String> p13Budget;
    private List<String> janCurrentYear;
    private List<String> febCurrentYear;
    private List<String> marCurrentYear;
    private List<String> aprCurrentYear;
    private List<String> mayCurrentYear;
    private List<String> junCurrentYear;
    private List<String> julCurrentYear;
    private List<String> augCurrentYear;
    private List<String> sepCurrentYear;
    private List<String> octCurrentYear;
    private List<String> novCurrentYear;
    private List<String> decCurrentYear;
    private List<String> p13CurrentYear;

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AccountsHistoryModel that)) {
            return false;
        }
        int sizeOfUiElements = this.accountNumber.size();
        // Custom equality check here.
        return this.name.equals(that.name.stream().limit(sizeOfUiElements).toList())
                && this.accountNumber.equals(that.accountNumber.stream().limit(sizeOfUiElements).toList())
                && this.janBudget.toString().equals(that.janBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.febBudget.toString().equals(that.febBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.marBudget.toString().equals(that.marBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.aprBudget.toString().equals(that.aprBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.mayBudget.toString().equals(that.mayBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.junBudget.toString().equals(that.junBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.julBudget.toString().equals(that.julBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.augBudget.toString().equals(that.augBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.sepBudget.toString().equals(that.sepBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.octBudget.toString().equals(that.octBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.novBudget.toString().equals(that.novBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.decBudget.toString().equals(that.decBudget.stream().limit(sizeOfUiElements).toList().toString())
                && this.p13Budget.toString().equals(that.p13Budget.stream().limit(sizeOfUiElements).toList().toString())
                && this.janCurrentYear.toString().equals(that.janCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.febCurrentYear.toString().equals(that.febCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.marCurrentYear.toString().equals(that.marCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.aprCurrentYear.toString().equals(that.aprCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.mayCurrentYear.toString().equals(that.mayCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.junCurrentYear.toString().equals(that.junCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.julCurrentYear.toString().equals(that.julCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.augCurrentYear.toString().equals(that.augCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.sepCurrentYear.toString().equals(that.sepCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.octCurrentYear.toString().equals(that.octCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.novCurrentYear.toString().equals(that.novCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.decCurrentYear.toString().equals(that.decCurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.p13CurrentYear.toString().equals(that.p13CurrentYear.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.reconciledDate.toString().equals(that.reconciledDate.stream().limit(sizeOfUiElements)
                .toList().toString())
                && this.lastYearAndBalance.toString().equals(that.lastYearAndBalance.stream().limit(sizeOfUiElements)
                .toList().toString());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
