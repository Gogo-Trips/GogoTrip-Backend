package com.example.gogotrips.plandetails.domain.entity;

import com.example.gogotrips.plan.domain.entity.Plan;
import com.example.gogotrips.shared.model.AuditModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "plandetail")
@AllArgsConstructor
@NoArgsConstructor
public class PlanDetails extends AuditModel {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @NotNull
    private Float price;

    @NotEmpty
    private String type;

    @NotNull
    private Integer amount;

}
