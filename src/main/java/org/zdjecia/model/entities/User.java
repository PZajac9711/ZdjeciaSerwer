package org.zdjecia.model.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_password")
    private String userPassword;

    public User(String userName,String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
