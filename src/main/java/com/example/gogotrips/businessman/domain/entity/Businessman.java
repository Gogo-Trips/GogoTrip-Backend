package com.example.gogotrips.businessman.domain.entity;

import com.example.gogotrips.forum.domain.entity.Forum;
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
@Table(name = "businessmans")
@AllArgsConstructor
@NoArgsConstructor
public class Businessman extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "businessmans_id")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "businessmans_forums",
            joinColumns = @JoinColumn(name = "businessmans_id"),
            inverseJoinColumns = @JoinColumn(name = "forum_id")
    )
    private Collection<Forum> forums;

    @NotEmpty
    private String ruc;

    @NotEmpty
    private String companyName;

    @NotEmpty
    private String email;

    @NotNull
    private Integer phone;

    @NotEmpty
    private String services;

    @NotEmpty
    private String direction;
}
