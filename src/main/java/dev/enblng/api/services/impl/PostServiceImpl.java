package dev.enblng.api.services.impl;

import dev.enblng.api.dto.PostTO;
import dev.enblng.api.entities.PostEntity;
import dev.enblng.api.mappers.PostMapper;
import dev.enblng.api.repositories.PostRepository;
import dev.enblng.api.services.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(
            final PostRepository postRepository,
            final PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    @Transactional
    public PostTO save(final PostTO post) {
        final PostEntity entity = postMapper.toEntity(post);
        log.debug(post.toString());
        entity.setId(UUID.randomUUID());
        final PostEntity savedEntity = postRepository.save(entity);
        return postMapper.toTransferObject(savedEntity);
    }

    @Override
    public PostTO getById(final UUID id) {
        final PostEntity entity = postRepository.getById(id)
                .orElseThrow(EntityNotFoundException::new);
        return postMapper.toTransferObject(entity);
    }

    @Override
    public List<PostTO> findAll(final Pageable pageable) {
        return postRepository.findAll(pageable).get()
                .map(postMapper::toTransferObject)
                .collect(Collectors.toList());
    }

    @Override
    public void update(final PostTO post) {
        postRepository.updatePost(post.getTitle(),
                post.getAuthor(),
                post.getContent(),
                ZonedDateTime.now(),
                post.getId());
    }

    @Override
    public void delete(final UUID id) {
        postRepository.deleteById(id);
    }
}
