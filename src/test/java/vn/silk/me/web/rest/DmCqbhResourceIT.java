package vn.silk.me.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import vn.silk.me.IntegrationTest;
import vn.silk.me.domain.DmCqbh;
import vn.silk.me.repository.DmCqbhRepository;
import vn.silk.me.service.dto.DmCqbhDTO;
import vn.silk.me.service.mapper.DmCqbhMapper;

/**
 * Integration tests for the {@link DmCqbhResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient
@WithMockUser
class DmCqbhResourceIT {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_CHA = "AAAAAAAAAA";
    private static final String UPDATED_MA_CHA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_CHA = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CHA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/dm-cqbhs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private DmCqbhRepository dmCqbhRepository;

    @Autowired
    private DmCqbhMapper dmCqbhMapper;

    @Autowired
    private WebTestClient webTestClient;

    private DmCqbh dmCqbh;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmCqbh createEntity() {
        DmCqbh dmCqbh = new DmCqbh().ma(DEFAULT_MA).ten(DEFAULT_TEN).maCha(DEFAULT_MA_CHA).tenCha(DEFAULT_TEN_CHA);
        return dmCqbh;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DmCqbh createUpdatedEntity() {
        DmCqbh dmCqbh = new DmCqbh().ma(UPDATED_MA).ten(UPDATED_TEN).maCha(UPDATED_MA_CHA).tenCha(UPDATED_TEN_CHA);
        return dmCqbh;
    }

    @BeforeEach
    public void initTest() {
        dmCqbhRepository.deleteAll().block();
        dmCqbh = createEntity();
    }

    @Test
    void getAllDmCqbhs() {
        // Initialize the database
        dmCqbhRepository.save(dmCqbh).block();

        // Get all the dmCqbhList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(dmCqbh.getId()))
            .jsonPath("$.[*].ma")
            .value(hasItem(DEFAULT_MA))
            .jsonPath("$.[*].ten")
            .value(hasItem(DEFAULT_TEN))
            .jsonPath("$.[*].maCha")
            .value(hasItem(DEFAULT_MA_CHA))
            .jsonPath("$.[*].tenCha")
            .value(hasItem(DEFAULT_TEN_CHA));
    }

    @Test
    void getDmCqbh() {
        // Initialize the database
        dmCqbhRepository.save(dmCqbh).block();

        // Get the dmCqbh
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, dmCqbh.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(dmCqbh.getId()))
            .jsonPath("$.ma")
            .value(is(DEFAULT_MA))
            .jsonPath("$.ten")
            .value(is(DEFAULT_TEN))
            .jsonPath("$.maCha")
            .value(is(DEFAULT_MA_CHA))
            .jsonPath("$.tenCha")
            .value(is(DEFAULT_TEN_CHA));
    }

    @Test
    void getNonExistingDmCqbh() {
        // Get the dmCqbh
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
