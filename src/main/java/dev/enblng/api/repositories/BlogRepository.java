package dev.enblng.api.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BlogRepository<T, ID extends Serializable> extends Repository<T, ID> {
    Optional<T> getById(ID id);

    <S extends T> S save(S entity);
}
