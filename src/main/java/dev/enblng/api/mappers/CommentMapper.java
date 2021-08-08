package dev.enblng.api.mappers;

import dev.enblng.api.dto.CommentTO;
import dev.enblng.api.entities.CommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentMapper {
    @Mapping(target = "creationTime", ignore = true)
    CommentEntity toEntity(final CommentTO comment);

    CommentTO toTransferObject(final CommentEntity comment);
}
