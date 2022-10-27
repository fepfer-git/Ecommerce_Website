package com.example.ecommerce_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "product_detail")
public class ProductDetail {

    @Id
    @SequenceGenerator(
            name = "productDetail_sequence",
            sequenceName = "productDetail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "productDetail_sequence"
    )
    @Column(name = "product_detail_id", updatable = false)
    private int productDetailId;

    @Column(name = "stock", nullable = false, columnDefinition = "Int")
    private int stock;

    @Column(name = "price", nullable = false, columnDefinition = "Numeric")
    private double price;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
    private String status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "product_id",
            foreignKey = @ForeignKey(name = "product_productDetail_fk")
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "size_id",
            nullable = false,
            referencedColumnName = "size_id",
            foreignKey = @ForeignKey(name = "size_productDetail_fk")
    )
    private Size size;

    @OneToMany(mappedBy = "productDetail")
    private List<OrderDetail> orderDetails;

}
