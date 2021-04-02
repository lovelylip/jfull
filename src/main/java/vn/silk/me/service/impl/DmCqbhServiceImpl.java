package vn.silk.me.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.silk.me.domain.DmCqbh;
import vn.silk.me.repository.DmCqbhRepository;
import vn.silk.me.service.DmCqbhService;
import vn.silk.me.service.dto.DmCqbhDTO;
import vn.silk.me.service.mapper.DmCqbhMapper;

/**
 * Service Implementation for managing {@link DmCqbh}.
 */
@Service
public class DmCqbhServiceImpl implements DmCqbhService {

    private final Logger log = LoggerFactory.getLogger(DmCqbhServiceImpl.class);

    private final DmCqbhRepository dmCqbhRepository;

    private final DmCqbhMapper dmCqbhMapper;

    public DmCqbhServiceImpl(DmCqbhRepository dmCqbhRepository, DmCqbhMapper dmCqbhMapper) {
        this.dmCqbhRepository = dmCqbhRepository;
        this.dmCqbhMapper = dmCqbhMapper;
    }

    @Override
    public Mono<DmCqbhDTO> save(DmCqbhDTO dmCqbhDTO) {
        log.debug("Request to save DmCqbh : {}", dmCqbhDTO);
        return dmCqbhRepository.save(dmCqbhMapper.toEntity(dmCqbhDTO)).map(dmCqbhMapper::toDto);
    }

    @Override
    public Mono<DmCqbhDTO> partialUpdate(DmCqbhDTO dmCqbhDTO) {
        log.debug("Request to partially update DmCqbh : {}", dmCqbhDTO);

        return dmCqbhRepository
            .findById(dmCqbhDTO.getId())
            .map(
                existingDmCqbh -> {
                    dmCqbhMapper.partialUpdate(existingDmCqbh, dmCqbhDTO);
                    return existingDmCqbh;
                }
            )
            .flatMap(dmCqbhRepository::save)
            .map(dmCqbhMapper::toDto);
    }

    @Override
    public Flux<DmCqbhDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DmCqbhs");
        return dmCqbhRepository.findAllBy(pageable).map(dmCqbhMapper::toDto);
    }

    public Mono<Long> countAll() {
        return dmCqbhRepository.count();
    }

    @Override
    public Mono<DmCqbhDTO> findOne(String id) {
        log.debug("Request to get DmCqbh : {}", id);
        return dmCqbhRepository.findById(id).map(dmCqbhMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete DmCqbh : {}", id);
        return dmCqbhRepository.deleteById(id);
    }
}
