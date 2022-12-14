package com.example.ecommerce_website.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "order_detail")
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
    @JoinColumn(
            name = "product_detail_id",
            nullable = false,
            referencedColumnName = "product_detail_id"
    )
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            nullable = false,
            referencedColumnName = "order_id")
    private Order order;

}
