package dev.enblng.api.repositories;

import dev.enblng.api.entities.CommentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.stream.Stream;

public interface CommentRepository extends BlogRepository<CommentEntity, UUID> {

    @Transactional
    Stream<CommentEntity> findAllByPostIdOrderByCreationTime(final UUID postId, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update CommentEntity c  set" +
            " c.title=?1, c.content=?2, c.updateTime=?3 where  c.id=?4")
    int updateComment(final String title,
                      final String content,
                      final ZonedDateTime updateTime,
                      final UUID id);


    int deleteById(UUID id);
}
