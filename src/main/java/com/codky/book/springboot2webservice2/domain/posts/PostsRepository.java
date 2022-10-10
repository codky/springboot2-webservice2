package com.codky.book.springboot2webservice2.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    // @Query는 SpringDataJpa에서 제공하는 기본메소드보다 가독성이 좋음
    // 규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인,복잡한 조건등으로 조회용 프레임워크를 사용한다.(querydsl,jooq,MyBatis)
    // 조회는 위3가지중 하나로 조회하고, 등록/수정/삭제는 SpringDataJpa를 통해 진행한다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
