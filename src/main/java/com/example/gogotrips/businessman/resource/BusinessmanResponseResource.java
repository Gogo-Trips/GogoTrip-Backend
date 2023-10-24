package com.example.gogotrips.businessman.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessmanResponseResource extends AuditModel {
    private String ruc;

    private String companyName;

    private String email;

    private Integer phone;

    private String services;

    private String direction;
}
