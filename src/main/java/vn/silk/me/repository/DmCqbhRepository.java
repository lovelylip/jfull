package vn.silk.me.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.silk.me.domain.DmCqbh;

/**
 * Spring Data MongoDB reactive repository for the DmCqbh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmCqbhRepository extends ReactiveMongoRepository<DmCqbh, String> {
    Flux<DmCqbh> findAllBy(Pageable pageable);

    Mono<DmCqbh> findOneByMa(String ma);
}
