package vn.silk.me.service.mapper;

import org.mapstruct.*;
import vn.silk.me.domain.*;
import vn.silk.me.service.dto.DmCqbhDTO;

/**
 * Mapper for the entity {@link DmCqbh} and its DTO {@link DmCqbhDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DmCqbhMapper extends EntityMapper<DmCqbhDTO, DmCqbh> {}
