package dev.enblng.api.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(schema = "blog", name = "posts")
@Getter
@Setter
public class PostEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;


    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "update_time")
    @UpdateTimestamp
    private ZonedDateTime updateTime;

    @Column(name = "creation_time", nullable = false)
    @CreationTimestamp
    private ZonedDateTime creationTime;

}
