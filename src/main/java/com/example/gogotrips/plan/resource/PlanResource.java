package com.example.gogotrips.plan.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlanResource extends AuditModel {
    private String name;

    private String description;
}
