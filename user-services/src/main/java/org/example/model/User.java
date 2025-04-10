package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "user")
@Data
@Getter
@Setter
public class User {
    @Id
    private Long userId;
    private String name;
    private String email;
    private String phoneNo;
}
