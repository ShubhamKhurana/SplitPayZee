package com.SplitwiseClone.SplitwiseClone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="SPLITWISE_USER")
public class User extends BaseModel{
    private String name;
    private String email;
    private String phoneNumber;

    @ManyToMany
    private List<Group> groups;
}
