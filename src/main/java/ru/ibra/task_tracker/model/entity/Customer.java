package ru.ibra.task_tracker.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.PackagePrivate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@PackagePrivate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "task_tracker", name = "customer")
public class Customer {

    @Id
    @Column(name = "id")
//    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String username;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categories;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;
}
