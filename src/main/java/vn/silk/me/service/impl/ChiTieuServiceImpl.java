package vn.silk.me.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import vn.silk.me.domain.ChiTieu;
import vn.silk.me.repository.ChiTieuRepository;
import vn.silk.me.service.ChiTieuService;
import vn.silk.me.service.dto.ChiTieuDTO;
import vn.silk.me.service.mapper.ChiTieuMapper;

/**
 * Service Implementation for managing {@link ChiTieu}.
 */
@Service
public class ChiTieuServiceImpl implements ChiTieuService {

    private final Logger log = LoggerFactory.getLogger(ChiTieuServiceImpl.class);

    private final ChiTieuRepository chiTieuRepository;

    private final ChiTieuMapper chiTieuMapper;

    public ChiTieuServiceImpl(ChiTieuRepository chiTieuRepository, ChiTieuMapper chiTieuMapper) {
        this.chiTieuRepository = chiTieuRepository;
        this.chiTieuMapper = chiTieuMapper;
    }

    @Override
    public Mono<ChiTieuDTO> save(ChiTieuDTO chiTieuDTO) {
        log.debug("Request to save ChiTieu : {}", chiTieuDTO);
        return chiTieuRepository.save(chiTieuMapper.toEntity(chiTieuDTO)).map(chiTieuMapper::toDto);
    }

    @Override
    public Mono<ChiTieuDTO> partialUpdate(ChiTieuDTO chiTieuDTO) {
        log.debug("Request to partially update ChiTieu : {}", chiTieuDTO);

        return chiTieuRepository
            .findById(chiTieuDTO.getId())
            .map(
                existingChiTieu -> {
                    chiTieuMapper.partialUpdate(existingChiTieu, chiTieuDTO);
                    return existingChiTieu;
                }
            )
            .flatMap(chiTieuRepository::save)
            .map(chiTieuMapper::toDto);
    }

    @Override
    public Flux<ChiTieuDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ChiTieus");
        return chiTieuRepository.findAllBy(pageable).map(chiTieuMapper::toDto);
    }

    public Mono<Long> countAll() {
        return chiTieuRepository.count();
    }

    @Override
    public Mono<ChiTieuDTO> findOne(String id) {
        log.debug("Request to get ChiTieu : {}", id);
        return chiTieuRepository.findById(id).map(chiTieuMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        log.debug("Request to delete ChiTieu : {}", id);
        return chiTieuRepository.deleteById(id);
    }
}
