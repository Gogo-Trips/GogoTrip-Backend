package com.example.gogotrips.businessman.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessmanResource extends AuditModel {
    private String ruc;

    private String companyName;

    private String email;

    private Integer phone;

    private String services;

    private String direction;
}
