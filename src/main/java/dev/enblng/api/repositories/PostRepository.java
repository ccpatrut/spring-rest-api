package dev.enblng.api.repositories;

import dev.enblng.api.entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.UUID;

public interface PostRepository extends BlogRepository<PostEntity, UUID> {

    @Transactional
    Page<PostEntity> findAll(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update PostEntity p set p.title = ?1, p.content = ?2, p.author = ?3" +
            ",p.updateTime =?4  where p.id = ?5")
    int updatePost(final String title,
                   final String author,
                   final String content,
                   final ZonedDateTime updateTime,
                   final UUID postId);

    @Modifying
    @Transactional
    @Query("delete from PostEntity p where p.id=?1")
    int deleteById(final UUID id);
}
