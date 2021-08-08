package dev.enblng.api.services.impl;

import dev.enblng.api.dto.CommentTO;
import dev.enblng.api.entities.CommentEntity;
import dev.enblng.api.repositories.CommentRepository;
import dev.enblng.api.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(final ModelMapper modelMapper, final CommentRepository commentRepository) {
        this.modelMapper = modelMapper;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentTO save(final CommentTO comment) {
        final CommentEntity entity = modelMapper.map(comment, CommentEntity.class);
        entity.setId(UUID.randomUUID());
        return modelMapper.map(commentRepository.save(entity), CommentTO.CommentTOBuilder.class).build();
    }

    @Override
    @Transactional
    public List<CommentTO> getByPostId(final UUID id, final Pageable pageable) {
        return commentRepository.findAllByPostIdOrderByCreationTime(id, pageable)
                .map(commentEntity -> modelMapper.map(commentEntity,
                        CommentTO.CommentTOBuilder.class).build())
                .collect(Collectors.toList());
    }

    @Override
    public void update(final CommentTO comment) {
        commentRepository.updateComment(comment.getTitle(),
                comment.getContent(),
                ZonedDateTime.now(),
                comment.getId()
        );
    }

    @Override
    public void delete(final UUID id) {
        commentRepository.deleteById(id);
    }
}
