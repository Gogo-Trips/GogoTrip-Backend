package com.example.gogotrips.publication.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationResponseResource extends AuditModel {
    private Long id;

    private String content;
}
