package com.boot.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
    # extends JpaRepository
    * JpaRepository<Entity Class, PK Type> 상속
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
