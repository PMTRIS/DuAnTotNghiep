package com.mt.entity;

import javax.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "SizeProduct")
@IdClass(SizeProductKey.class)
public class SizeProduct implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "productid")
    private Integer productid;

    @Id
    @Column(name = "sizeid")
    private String sizeid;

    private Integer count;

    @ManyToOne
    @JoinColumn(name = "productid", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sizeid", insertable = false, updatable = false)
    private Size size;
}
