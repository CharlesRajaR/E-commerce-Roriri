package com.rcr.ecommerce.Modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(length = 1000)
    private List<String> images;
    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;

    private LocalDateTime createdAt;

//    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonIgnore
//    private List<Order> orders;
}
