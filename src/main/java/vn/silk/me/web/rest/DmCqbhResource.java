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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;
import vn.silk.me.domain.DmCqbh;
import vn.silk.me.domain.User;
import vn.silk.me.repository.DmCqbhRepository;
import vn.silk.me.security.AuthoritiesConstants;
import vn.silk.me.service.DmCqbhService;
import vn.silk.me.service.dto.AdminUserDTO;
import vn.silk.me.service.dto.DmCqbhDTO;
import vn.silk.me.service.mapper.DmCqbhMapper;
import vn.silk.me.web.rest.errors.BadRequestAlertException;
import vn.silk.me.web.rest.errors.EmailAlreadyUsedException;
import vn.silk.me.web.rest.errors.LoginAlreadyUsedException;

/**
 * REST controller for managing {@link vn.silk.me.domain.DmCqbh}.
 */
@RestController
@RequestMapping("/api")
public class DmCqbhResource {

    private final Logger log = LoggerFactory.getLogger(DmCqbhResource.class);

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DmCqbhService dmCqbhService;

    private final DmCqbhRepository dmCqbhRepository;

    private final DmCqbhMapper dmCqbhMapper;

    public DmCqbhResource(DmCqbhService dmCqbhService, DmCqbhRepository dmCqbhRepository, DmCqbhMapper dmCqbhMapper) {
        this.dmCqbhService = dmCqbhService;
        this.dmCqbhRepository = dmCqbhRepository;
        this.dmCqbhMapper = dmCqbhMapper;
    }

    @PostMapping("/dm-cqbhs")
    public Mono<ResponseEntity<DmCqbh>> createUser(@Valid @RequestBody DmCqbhDTO dmCqbhDTO) {
        log.debug("REST request to save User : {}", dmCqbhDTO);

        if (dmCqbhDTO.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID", "dmCqbh", "idexists");
            // Lowercase the user login before comparing with database
        }
        Mono<DmCqbh> dmCqbhMoMo = dmCqbhRepository.save(dmCqbhMapper.toEntity(dmCqbhDTO));
        return dmCqbhMoMo.map(
            dmCqbh -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/dm-cqbhs/" + dmCqbhDTO.getMa()))
                        .headers(HeaderUtil.createAlert(applicationName, "dmCqbh.created", dmCqbh.getMa()))
                        .body(dmCqbh);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
        );
    }

    @PutMapping("/dm-cqbhs")
    public Mono<ResponseEntity<DmCqbhDTO>> updateUser(@Valid @RequestBody DmCqbhDTO dmCqbhDTO) {
        log.debug("REST request to update User : {}", dmCqbhDTO);
        return dmCqbhRepository
            .findOneByMa(dmCqbhDTO.getMa())
            .filter(dmCqbh -> !dmCqbh.getId().equals(dmCqbhDTO.getId()))
            .hasElement()
            .flatMap(
                maExists -> {
                    if (Boolean.TRUE.equals(maExists)) {
                        return Mono.error(new LoginAlreadyUsedException());
                    }
                    return dmCqbhService.partialUpdate(dmCqbhDTO);
                }
            )
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
            .map(
                dmCqbh ->
                    ResponseEntity.ok().headers(HeaderUtil.createAlert(applicationName, "dmCqbh.updated", dmCqbhDTO.getMa())).body(dmCqbh)
            );
    }

    /**
     * {@code GET  /dm-cqbhs} : get all the dmCqbhs.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dmCqbhs in body.
     */
    @GetMapping("/dm-cqbhs")
    public Mono<ResponseEntity<List<DmCqbhDTO>>> getAllDmCqbhs(Pageable pageable, ServerHttpRequest request) {
        log.debug("REST request to get a page of DmCqbhs");
        return dmCqbhService
            .countAll()
            .zipWith(dmCqbhService.findAll(pageable).collectList())
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
     * {@code GET  /dm-cqbhs/:id} : get the "id" dmCqbh.
     *
     * @param id the id of the dmCqbhDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dmCqbhDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/dm-cqbhs/{id}")
    public Mono<ResponseEntity<DmCqbhDTO>> getDmCqbh(@PathVariable String id) {
        log.debug("REST request to get DmCqbh : {}", id);
        Mono<DmCqbhDTO> dmCqbhDTO = dmCqbhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dmCqbhDTO);
    }
}
