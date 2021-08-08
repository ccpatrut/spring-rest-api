package dev.enblng.api.services;

import dev.enblng.api.dto.CommentTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CommentService {
    CommentTO save(final CommentTO comment);

    List<CommentTO> getByPostId(final UUID postId, Pageable pageable);

    void update(final CommentTO comment);

    void delete(final UUID id);
}
