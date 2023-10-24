package com.example.gogotrips.shoppingcart.resource;

import com.example.gogotrips.shared.model.AuditModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShoppingcartResource  extends AuditModel {
    private Integer amount;
}
