package com.lhj8390.dashboard.model.entity;

import com.lhj8390.dashboard.model.ProductCategory;
import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String thumnail;

    private Integer amount;

    private Integer price;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    public void update(String name, Integer amount, Integer price, ProductCategory category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }
}
