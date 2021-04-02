package vn.silk.me.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.silk.me.web.rest.TestUtil;

class DmCqbhTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmCqbh.class);
        DmCqbh dmCqbh1 = new DmCqbh();
        dmCqbh1.setId("id1");
        DmCqbh dmCqbh2 = new DmCqbh();
        dmCqbh2.setId(dmCqbh1.getId());
        assertThat(dmCqbh1).isEqualTo(dmCqbh2);
        dmCqbh2.setId("id2");
        assertThat(dmCqbh1).isNotEqualTo(dmCqbh2);
        dmCqbh1.setId(null);
        assertThat(dmCqbh1).isNotEqualTo(dmCqbh2);
    }
}
