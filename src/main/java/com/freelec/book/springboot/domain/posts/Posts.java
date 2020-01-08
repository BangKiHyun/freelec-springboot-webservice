package com.freelec.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 자동 생성
@Entity //테이블과 링크될 클래스임을 나타냄
public class Posts {

    @Id//필드의 PK 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)//PK의 생성 규칙을 나타냄, IDENTITY로 하면 auto increment
    private long id;

    //Column은 굳이 선언하지 않더라도 해당 클래스는 모두 칼럼이됨
    //기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    @Column(length = 500, nullable = false)
    private String title;

    //columnDefinition DB의 컬럼 정보를 직접 바꾸고 싶을 때
    //여기서는 VARCHAR대신 TEXT로 변경
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
