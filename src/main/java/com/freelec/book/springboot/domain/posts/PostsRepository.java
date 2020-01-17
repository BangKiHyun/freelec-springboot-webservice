package com.freelec.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//<테이블, PK 속성>
//자동으로 CRUD 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

    //제공되지 않는 메소드는 직접 작성해서 사용가능
    //가독성이 더 좋아짐
    @Query("select p from Posts p order by p.id DESC ")
    List<Posts> findAllDesc();
}
