package com.example.ecommerce_website.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "tbl_orderDetail")
public class OrderDetail {
    @Id
    @SequenceGenerator(
            name = "orderDetail_sequence",
            sequenceName = "orderDetail_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderDetail_sequence"
    )
    private int orderDetailId;

    @Column(name = "quantity", nullable = false, columnDefinition = "Int")
    private int quantity;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(20)")
    private String status;

    @ManyToOne
    @JoinColumn(name = "productDetailId")
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

}
