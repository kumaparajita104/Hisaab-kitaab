package com.example.hissabkitaab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "group_table")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;
//    @ManyToOne(fetch =FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    private User user;
    @OneToOne(mappedBy = "group",cascade = CascadeType.ALL,orphanRemoval = true)
    Trip trip;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_groups",joinColumns = @JoinColumn(name = "group_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "user_id",referencedColumnName = "id"))
    private List<User> users;
    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL,orphanRemoval = true)
    List<Expenses>expenses;

}
