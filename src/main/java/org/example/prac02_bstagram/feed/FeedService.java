package org.example.prac02_bstagram.feed;

import lombok.RequiredArgsConstructor;
import org.example.prac02_bstagram.feed.model.Feed;
import org.example.prac02_bstagram.feed.model.FeedDto;
import org.example.prac02_bstagram.feed.model.FeedImage;
import org.example.prac02_bstagram.user.model.AuthUserDetails;
import org.example.prac02_bstagram.utils.UploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FeedService {
    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final UploadUtil uploadUtil;

    public List<FeedDto.FeedRes> list(AuthUserDetails user) {
        List<Feed> entityList = feedRepository.findAll();


        return entityList.stream().map((feed) -> FeedDto.FeedRes.from(feed, user)).toList();
    }

    public List<FeedDto.ImageUploadRes> uploadImage(List<MultipartFile> images) {
        List<FeedDto.ImageUploadRes> result = new ArrayList<>();
        for (MultipartFile image: images) {
            String imageUrl = saveFile(image);
            FeedImage feedImage = FeedImage.builder()
                    .imageUrl(imageUrl)
                    .feed(null)
                    .build();
            feedImage = feedImageRepository.save(feedImage);

            result.add(FeedDto.ImageUploadRes.from(feedImage));
        }

        return result;
    }

    private String saveFile(MultipartFile file) {
        String uploadPath = uploadUtil.makeFolder();

        String filePath = uploadPath + File.separator + UUID.randomUUID() + "_" +file.getOriginalFilename();
        File saveFile = new File(filePath);
        try {
            file.transferTo(saveFile);
        }catch (Exception e) {
            e.printStackTrace();
        }


        return filePath;
    }
}
