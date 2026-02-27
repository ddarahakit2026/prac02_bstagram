package org.example.prac02_bstagram.feed;

import org.example.prac02_bstagram.feed.model.FeedImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedImageRepository extends JpaRepository<FeedImage, Long> {
}
