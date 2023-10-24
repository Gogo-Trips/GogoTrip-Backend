package com.example.gogotrips.traveler.domain.entity;

import com.example.gogotrips.plan.domain.entity.Plan;
import com.example.gogotrips.publication.domain.entity.Publication;
import com.example.gogotrips.shared.model.AuditModel;
import com.example.gogotrips.shoppingcart.domain.entity.Shoppingcart;
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
@Table(name = "traveler")
@AllArgsConstructor
@NoArgsConstructor
public class Traveler extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "traveler_id")
    private Long id;

    @OneToMany(mappedBy = "traveler")
    private Collection<Publication> publications;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "travelers_plans",
            joinColumns = @JoinColumn(name = "traveler_id"),
            inverseJoinColumns = @JoinColumn(name = "plan_id")
    )
    private Collection<Plan> plans;

    @OneToOne(mappedBy = "traveler")
    private Shoppingcart shoppingCart;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String informationCard;

}
