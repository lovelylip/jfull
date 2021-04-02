package vn.silk.me.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.silk.me.service.dto.DmCqbhDTO;

/**
 * Service Interface for managing {@link vn.silk.me.domain.DmCqbh}.
 */
public interface DmCqbhService {
    /**
     * Save a dmCqbh.
     *
     * @param dmCqbhDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<DmCqbhDTO> save(DmCqbhDTO dmCqbhDTO);

    /**
     * Partially updates a dmCqbh.
     *
     * @param dmCqbhDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<DmCqbhDTO> partialUpdate(DmCqbhDTO dmCqbhDTO);

    /**
     * Get all the dmCqbhs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<DmCqbhDTO> findAll(Pageable pageable);

    /**
     * Returns the number of dmCqbhs available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" dmCqbh.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<DmCqbhDTO> findOne(String id);

    /**
     * Delete the "id" dmCqbh.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
