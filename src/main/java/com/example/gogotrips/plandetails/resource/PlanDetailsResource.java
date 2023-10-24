package com.example.gogotrips.plandetails.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PlanDetailsResource extends AuditModel {
    private Float price;

    private String type;

    private Integer amount;
}
