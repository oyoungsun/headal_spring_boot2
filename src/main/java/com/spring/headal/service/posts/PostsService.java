package com.spring.headal.service.posts;

import com.spring.headal.domain.Posts;
import com.spring.headal.domain.PostsRepository;
import com.spring.headal.web.dto.PostsResponseDto;
import com.spring.headal.web.dto.PostsSaveRequestDto;
import com.spring.headal.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당게시글 없음 id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new
                        IllegalArgumentException("해당게시글 없음 id="+id));

        return new PostsResponseDto(entity);
    }
}
