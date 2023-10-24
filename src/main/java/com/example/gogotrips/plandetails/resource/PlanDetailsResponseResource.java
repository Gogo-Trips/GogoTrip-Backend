package com.example.gogotrips.plandetails.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanDetailsResponseResource extends AuditModel {
    private Long id;

    private Float price;

    private String type;

    private Integer amount;
}
