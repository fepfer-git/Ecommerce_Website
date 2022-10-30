package com.example.ecommerce_website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "[rating]")
public class Rating {

    @Id
    @SequenceGenerator(
            name = "rating_sequence",
            sequenceName = "rating_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_sequence"
    )
    @Column(name = "rating_id", updatable = false)
    private int ratingId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "product_id",
            foreignKey = @ForeignKey(name = "product_rating_fk"))
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "rating_user_fk"))
    private User user;

    @Column(name = "rating", nullable = false, columnDefinition = "INT")
    private int rating;

    @Column(name = "comment", nullable = false, columnDefinition = "TEXT")
    private String comment;

    @Column(name = "rating_date", nullable = false, columnDefinition = "Date")
    private Date ratingDate;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(10)")
    private String status;
}
