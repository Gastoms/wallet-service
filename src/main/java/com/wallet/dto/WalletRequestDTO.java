package com.wallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WalletRequestDTO {

    private String name;
    @JsonProperty("last_name")
    private String lastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
