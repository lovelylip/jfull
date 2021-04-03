package vn.silk.me.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;
import vn.silk.me.repository.ChiTieuRepository;
import vn.silk.me.service.ChiTieuService;
import vn.silk.me.service.dto.ChiTieuDTO;
import vn.silk.me.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link vn.silk.me.domain.ChiTieu}.
 */
@RestController
@RequestMapping("/api")
public class ChiTieuResource {

    private final Logger log = LoggerFactory.getLogger(ChiTieuResource.class);

    private final ChiTieuService chiTieuService;

    private final ChiTieuRepository chiTieuRepository;

    public ChiTieuResource(ChiTieuService chiTieuService, ChiTieuRepository chiTieuRepository) {
        this.chiTieuService = chiTieuService;
        this.chiTieuRepository = chiTieuRepository;
    }

    /**
     * {@code GET  /chi-tieus} : get all the chiTieus.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chiTieus in body.
     */
    @GetMapping("/chi-tieus")
    public Mono<ResponseEntity<List<ChiTieuDTO>>> getAllChiTieus(Pageable pageable, ServerHttpRequest request) {
        log.debug("REST request to get a page of ChiTieus");
        return chiTieuService
            .countAll()
            .zipWith(chiTieuService.findAll(pageable).collectList())
            .map(
                countWithEntities -> {
                    return ResponseEntity
                        .ok()
                        .headers(
                            PaginationUtil.generatePaginationHttpHeaders(
                                UriComponentsBuilder.fromHttpRequest(request),
                                new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                            )
                        )
                        .body(countWithEntities.getT2());
                }
            );
    }

    /**
     * {@code GET  /chi-tieus/:id} : get the "id" chiTieu.
     *
     * @param id the id of the chiTieuDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chiTieuDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chi-tieus/{id}")
    public Mono<ResponseEntity<ChiTieuDTO>> getChiTieu(@PathVariable String id) {
        log.debug("REST request to get ChiTieu : {}", id);
        Mono<ChiTieuDTO> chiTieuDTO = chiTieuService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chiTieuDTO);
    }
}
