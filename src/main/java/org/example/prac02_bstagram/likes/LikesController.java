package org.example.prac02_bstagram.likes;

import lombok.RequiredArgsConstructor;
import org.example.prac02_bstagram.common.model.BaseResponse;
import org.example.prac02_bstagram.likes.model.LikesDto;
import org.example.prac02_bstagram.user.model.AuthUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/likes")
@RestController
public class LikesController {
    private final LikesService feedService;

    @GetMapping("/{feedIdx}")
    public ResponseEntity likes(
            @AuthenticationPrincipal AuthUserDetails user,
            @PathVariable Long feedIdx) {
       feedService.likes(user, feedIdx);

       return ResponseEntity.ok(BaseResponse.success(true));
    }
}
