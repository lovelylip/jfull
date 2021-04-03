package vn.silk.me.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.silk.me.web.rest.TestUtil;

class ChiTieuTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChiTieu.class);
        ChiTieu chiTieu1 = new ChiTieu();
        chiTieu1.setId("id1");
        ChiTieu chiTieu2 = new ChiTieu();
        chiTieu2.setId(chiTieu1.getId());
        assertThat(chiTieu1).isEqualTo(chiTieu2);
        chiTieu2.setId("id2");
        assertThat(chiTieu1).isNotEqualTo(chiTieu2);
        chiTieu1.setId(null);
        assertThat(chiTieu1).isNotEqualTo(chiTieu2);
    }
}
