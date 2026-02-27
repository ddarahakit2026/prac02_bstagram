package org.example.prac02_bstagram.feed.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.prac02_bstagram.likes.model.Likes;
import org.example.prac02_bstagram.user.model.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User user;

    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY)
    private List<Likes> likesList;

    @OneToMany(mappedBy = "feed", fetch = FetchType.LAZY)
    private List<FeedImage> feedImageList;
}
