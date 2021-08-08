package dev.enblng.api.services;

import dev.enblng.api.dto.PostTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostTO save(final PostTO post);

    PostTO getById(final UUID id);

    List<PostTO> findAll(final Pageable pageable);

    void update(final PostTO post);

    void delete(final UUID id);
}
