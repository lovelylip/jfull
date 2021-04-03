package vn.silk.me.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChiTieuMapperTest {

    private ChiTieuMapper chiTieuMapper;

    @BeforeEach
    public void setUp() {
        chiTieuMapper = new ChiTieuMapperImpl();
    }
}
