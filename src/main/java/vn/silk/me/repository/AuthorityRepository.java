package vn.silk.me.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import vn.silk.me.domain.Authority;

/**
 * Spring Data MongoDB repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends ReactiveMongoRepository<Authority, String> {}
