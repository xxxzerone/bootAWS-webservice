package com.boot.book.springboot.service.posts;

import com.boot.book.springboot.domain.posts.Posts;
import com.boot.book.springboot.domain.posts.PostsRepository;
import com.boot.book.springboot.web.dto.PostsListResponseDto;
import com.boot.book.springboot.web.dto.PostsResponseDto;
import com.boot.book.springboot.web.dto.PostsSaveRequestDto;
import com.boot.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    /*
        # @Transactional(readOnly=true)
        * readOnly=true 옵션을 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선된다.
     */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                /*
                    * 람다식 사용
                    * 실제로 다음과 같습니다.
                      .map(posts -> new PostsListResponseDto(posts))
                 */
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        /*
            # postsRepository.delete(posts)
            * JpaRepository에서 이미 delete 메소드를 지원하고 있으니 이를 활용합니다.
            * 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수도 있습니다.
            * 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제합니다.
         */
        postsRepository.delete(posts);
    }
}
