package com.example.gogotrips.publication.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PublicationResource extends AuditModel {
    private String content;
    private String title;
}
