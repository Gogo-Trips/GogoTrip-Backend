package com.example.gogotrips.publication.domain.entity;

import com.example.gogotrips.shared.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "publications")
@AllArgsConstructor
@NoArgsConstructor
public class Publication extends AuditModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String content;

}
