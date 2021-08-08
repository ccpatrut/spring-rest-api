package dev.enblng.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.UUID;

import static dev.enblng.api.dto.ApiModelPropertyValues.ONLY_FOR_GET_MESSAGE;
import static dev.enblng.api.dto.ApiModelPropertyValues.UUID_MESSAGE;

@ApiModel(description = "Class representing a post on the blog")
@Value
@AllArgsConstructor
public class PostTO {

    @ApiModelProperty(value = UUID_MESSAGE)
    UUID id;

    @ApiModelProperty(position = 1, required = true)
    @Size(min = 5, max = 255)
    @NotNull
    String title;

    @ApiModelProperty(value = ONLY_FOR_GET_MESSAGE, position = 2)
    String author;

    @ApiModelProperty(position = 3, required = true)
    @Size(min = 10)
    @NotNull
    String content;

    @ApiModelProperty(value = ONLY_FOR_GET_MESSAGE, position = 5)
    @Null
    String updateTime;

    @ApiModelProperty(value = ONLY_FOR_GET_MESSAGE, position = 4)
    @Null
    String creationTime;

    @Builder
    private static PostTO newPostTo(final UUID id,
                                    final String title,
                                    final String author,
                                    final String content,
                                    final String updateTime,
                                    final String creationTime
    ) {
        return new PostTO(id, title, author, content, updateTime, creationTime);
    }
}
