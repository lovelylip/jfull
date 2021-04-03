package vn.silk.me.service;

import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.silk.me.service.dto.ChiTieuDTO;

/**
 * Service Interface for managing {@link vn.silk.me.domain.ChiTieu}.
 */
public interface ChiTieuService {
    /**
     * Save a chiTieu.
     *
     * @param chiTieuDTO the entity to save.
     * @return the persisted entity.
     */
    Mono<ChiTieuDTO> save(ChiTieuDTO chiTieuDTO);

    /**
     * Partially updates a chiTieu.
     *
     * @param chiTieuDTO the entity to update partially.
     * @return the persisted entity.
     */
    Mono<ChiTieuDTO> partialUpdate(ChiTieuDTO chiTieuDTO);

    /**
     * Get all the chiTieus.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Flux<ChiTieuDTO> findAll(Pageable pageable);

    /**
     * Returns the number of chiTieus available.
     * @return the number of entities in the database.
     *
     */
    Mono<Long> countAll();

    /**
     * Get the "id" chiTieu.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Mono<ChiTieuDTO> findOne(String id);

    /**
     * Delete the "id" chiTieu.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    Mono<Void> delete(String id);
}
