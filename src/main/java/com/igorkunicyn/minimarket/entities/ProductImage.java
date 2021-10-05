package com.igorkunicyn.minimarket.entities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "product_images")
@Data
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "path")
    private String path;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(){}
}
