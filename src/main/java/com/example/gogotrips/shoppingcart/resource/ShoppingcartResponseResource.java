package com.example.gogotrips.shoppingcart.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingcartResponseResource extends AuditModel {
    private Long id;

    private Integer amount;
}
