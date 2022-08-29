package com.model.phone;

import lombok.Getter;

public class OperationSystem {
    @Getter
    private String designation;
    private int version;


    public OperationSystem(String designation, int version) {
        this.designation = designation;
        this.version = version;
    }

    @Override
    public String toString() {
        return "OperationSystem{" +
                "designation='" + designation + '\'' +
                ", version=" + version +
                '}' ;
    }
}
