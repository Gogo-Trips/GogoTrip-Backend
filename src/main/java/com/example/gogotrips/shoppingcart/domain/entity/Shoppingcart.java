package com.example.gogotrips.shoppingcart.domain.entity;

import com.example.gogotrips.plan.domain.entity.Plan;
import com.example.gogotrips.shared.model.AuditModel;
import com.example.gogotrips.traveler.domain.entity.Traveler;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "shoppingcart")
@AllArgsConstructor
@NoArgsConstructor
public class Shoppingcart extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoppingcart_id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "shoppingcart_id")
    private Collection<Plan> plans;

    @OneToOne
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;

    @NotNull
    private Integer amount;
}
