package vn.silk.me.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import vn.silk.me.domain.ChiTieu;

/**
 * Spring Data MongoDB reactive repository for the ChiTieu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChiTieuRepository extends ReactiveMongoRepository<ChiTieu, String> {
    Flux<ChiTieu> findAllBy(Pageable pageable);
}
