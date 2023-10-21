package com.example.gogotrips.comment.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CommentResource extends AuditModel {
    private String contenido;
    private Integer calificacion;
}
