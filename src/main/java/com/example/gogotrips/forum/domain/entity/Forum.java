package com.example.gogotrips.forum.domain.entity;

import com.example.gogotrips.businessman.domain.entity.Businessman;
import com.example.gogotrips.publication.domain.entity.Publication;
import com.example.gogotrips.shared.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "forum")
@AllArgsConstructor
@NoArgsConstructor
public class Forum extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_id")
    private Long id;

    @ManyToMany(mappedBy = "forums")
    private Collection<Publication> publications;

    @ManyToMany(mappedBy = "forums")
    private Collection<Businessman> businessmans;

    @NotEmpty
    private String title;

    @NotNull
    private Integer amountPublications;

    @NotNull
    private Integer amountPhotos;

    @NotNull
    private Integer amountComments;
}
