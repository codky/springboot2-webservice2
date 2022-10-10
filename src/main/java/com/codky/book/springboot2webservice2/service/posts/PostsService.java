package com.codky.book.springboot2webservice2.service.posts;

import com.codky.book.springboot2webservice2.domain.posts.Posts;
import com.codky.book.springboot2webservice2.domain.posts.PostsRepository;
import com.codky.book.springboot2webservice2.web.dto.PostsListResponseDto;
import com.codky.book.springboot2webservice2.web.dto.PostsResponseDto;
import com.codky.book.springboot2webservice2.web.dto.PostsSaveRequestDto;
import com.codky.book.springboot2webservice2.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
    
    @Transactional(readOnly = true) // readOnly=true: 트랜잭션 범위는 유지하되, 조회기능만 남겨 조회속도가 개선
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream() // postsRepository 결과로 넘어온 Posts의 Stream을 
                .map(PostsListResponseDto::new) // map 으로 PostsListResponseDto 변환
                .collect(Collectors.toList()); // List로 반환
    }

    @Transactional
    public void delete (Long id) {
        // 존재하는 Posts인지 확인을 위해 엔티티 조회
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // JpaRepository에서 이미 delete 메소드를 지원하고 있다.
        // 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드로 id를 찾아 삭제할 수도 있다.
        postsRepository.delete(posts);
    }
}
