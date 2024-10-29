package com.mt.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@Entity
@Table(name = "Sizes")
public class Size implements Serializable {
	private static final long serialVersionUID = 1L;
    @Id
    
    private String id;

    private int size;

    @OneToMany(mappedBy = "size")
    private List<SizeProduct> sizeProducts;

    // Getters and Setters
}
