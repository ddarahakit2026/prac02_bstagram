package org.example.prac02_bstagram.feed;

import org.example.prac02_bstagram.feed.model.Feed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedRepository extends JpaRepository<Feed, Long> {
}
