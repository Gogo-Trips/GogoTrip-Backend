package com.example.gogotrips.traveler.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelerResponseResource extends AuditModel {
    private Long id;

    private String name;

    private String email;

    private String informationCard;
}
