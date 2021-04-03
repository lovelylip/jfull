package vn.silk.me.service.mapper;

import org.mapstruct.*;
import vn.silk.me.domain.*;
import vn.silk.me.service.dto.ChiTieuDTO;

/**
 * Mapper for the entity {@link ChiTieu} and its DTO {@link ChiTieuDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ChiTieuMapper extends EntityMapper<ChiTieuDTO, ChiTieu> {}
