package com.mt.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;

    private String name;
    private Double price;
    private String describe;
    private String images;
    @Temporal(TemporalType.DATE)
    @Column(name = "Created_at")
    private Date create_at = new Date();

    @ManyToOne
    @JoinColumn(name = "Categoryid")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<ProductPromotion> productPromotions;

    @OneToMany(mappedBy = "product")
    private List<FavoriteProduct> favoriteProducts;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<SizeProduct> sizeProducs;

    // Getters and Setters
}
