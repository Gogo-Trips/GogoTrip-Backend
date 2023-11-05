package com.example.gogotrips.forum.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ForumResponseResource extends AuditModel {
    private Long id;
    private String title;

    private Integer amountPublications;

    private Integer amountPhotos;

    private Integer amountComments;
}
