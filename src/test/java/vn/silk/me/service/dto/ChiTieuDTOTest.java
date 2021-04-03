package vn.silk.me.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.silk.me.web.rest.TestUtil;

class ChiTieuDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTieuDTO.class);
        ChiTieuDTO chiTieuDTO1 = new ChiTieuDTO();
        chiTieuDTO1.setId("id1");
        ChiTieuDTO chiTieuDTO2 = new ChiTieuDTO();
        assertThat(chiTieuDTO1).isNotEqualTo(chiTieuDTO2);
        chiTieuDTO2.setId(chiTieuDTO1.getId());
        assertThat(chiTieuDTO1).isEqualTo(chiTieuDTO2);
        chiTieuDTO2.setId("id2");
        assertThat(chiTieuDTO1).isNotEqualTo(chiTieuDTO2);
        chiTieuDTO1.setId(null);
        assertThat(chiTieuDTO1).isNotEqualTo(chiTieuDTO2);
    }
}
