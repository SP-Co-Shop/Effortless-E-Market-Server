package com.example.coshop.Entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","name"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Long id;

    @Column(name = "c_name")
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "c_p_id")
    private Category parent;

    @Column(name = "c_depth")
    private Long depth;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public Category(String name){
        this.name = name;
        this.depth = 1L;
        this.parent = null;
    }

    public Category(String name,Category parent){
        this.name = name;
        this.parent = parent;
        this.depth = parent.getDepth()+1;
    }


}
