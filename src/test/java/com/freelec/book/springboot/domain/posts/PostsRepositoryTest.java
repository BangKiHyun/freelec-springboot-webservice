package com.freelec.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    //단위 테스트가 끝날 때마다 수행되는 메소드 지정
    //보통 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용
    @After
    public void cleanup(){
        postsRepository.findAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //.save 테이블에 insert/update 쿼리 실행
        //테이블에 id 값이 있다면 update
        //없다면 insert 수행
        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("bang")
        .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //모든 데이터를 조회

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
