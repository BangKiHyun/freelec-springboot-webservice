package com.freelec.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//<테이블, PK 속성>
//자동으로 CRUD 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
