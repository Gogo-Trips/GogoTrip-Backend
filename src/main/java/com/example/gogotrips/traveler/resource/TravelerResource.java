package com.example.gogotrips.traveler.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TravelerResource extends AuditModel {
    private String name;

    private String email;

    private String informationCard;
}
