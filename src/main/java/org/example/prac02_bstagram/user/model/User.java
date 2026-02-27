package org.example.prac02_bstagram.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.example.prac02_bstagram.feed.model.Feed;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String email;
    private String name;
    @Setter

    private String password;
    @Setter
    private boolean enable;
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Feed> feedList;
}
