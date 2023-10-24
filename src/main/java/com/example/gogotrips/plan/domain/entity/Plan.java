package com.example.gogotrips.plan.domain.entity;

import com.example.gogotrips.plandetails.domain.entity.PlanDetails;
import com.example.gogotrips.shared.model.AuditModel;
import com.example.gogotrips.traveler.domain.entity.Traveler;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "plan")
@AllArgsConstructor
@NoArgsConstructor
public class Plan extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @ManyToMany(mappedBy = "plans")
    private Collection<Traveler> travelers;

    @OneToOne(mappedBy = "plan")
    private PlanDetails planDetails;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;
}
