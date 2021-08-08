package dev.enblng.api.mappers;

import dev.enblng.api.dto.PostTO;
import dev.enblng.api.entities.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMapper {
    @Mapping(target = "creationTime", ignore = true)
    PostEntity toEntity(final PostTO post);

    PostTO toTransferObject(final PostEntity post);
}
