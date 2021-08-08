package dev.enblng.api.api;

import dev.enblng.api.dto.CommentTO;
import dev.enblng.api.services.CommentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(CommentController.PATH)
@Api(tags = "Comments")
@Slf4j
public class CommentController {
    static final String PATH = "comments";
    private final CommentService commentService;

    @Autowired
    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentTO> save(@Valid @RequestBody final CommentTO comment) {
        log.debug("saving comment: " + comment);
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody final CommentTO comment) {
        log.debug("updating comment" + comment);
        commentService.update(comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentTO>> getComments(@NotNull @RequestParam("postId") final UUID postId,
                                                       @NotNull @RequestParam("page") final int page,
                                                       @NotNull @RequestParam("size") final int size) {
        log.debug("getting comments");
        return new ResponseEntity<>(
                commentService.getByPostId(postId, PageRequest.of(page, size)),
                HttpStatus.OK
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@NotNull @RequestParam("id") final UUID id) {
        log.debug("deleting comment: " + id);
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
