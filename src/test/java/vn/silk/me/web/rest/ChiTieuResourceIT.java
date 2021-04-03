package vn.silk.me.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static vn.silk.me.web.rest.TestUtil.sameInstant;
import static vn.silk.me.web.rest.TestUtil.sameNumber;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
import vn.silk.me.domain.ChiTieu;
import vn.silk.me.repository.ChiTieuRepository;
import vn.silk.me.service.dto.ChiTieuDTO;
import vn.silk.me.service.mapper.ChiTieuMapper;

/**
 * Integration tests for the {@link ChiTieuResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient
@WithMockUser
class ChiTieuResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_DUNG = "AAAAAAAAAA";
    private static final String UPDATED_NOI_DUNG = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TIEN = new BigDecimal(1);
    private static final BigDecimal UPDATED_TIEN = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_NGAY_CHI_TIEU = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_CHI_TIEU = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/chi-tieus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ChiTieuRepository chiTieuRepository;

    @Autowired
    private ChiTieuMapper chiTieuMapper;

    @Autowired
    private WebTestClient webTestClient;

    private ChiTieu chiTieu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTieu createEntity() {
        ChiTieu chiTieu = new ChiTieu()
            .type(DEFAULT_TYPE)
            .noiDung(DEFAULT_NOI_DUNG)
            .tien(DEFAULT_TIEN)
            .ngayChiTieu(DEFAULT_NGAY_CHI_TIEU)
            .ghiChu(DEFAULT_GHI_CHU);
        return chiTieu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChiTieu createUpdatedEntity() {
        ChiTieu chiTieu = new ChiTieu()
            .type(UPDATED_TYPE)
            .noiDung(UPDATED_NOI_DUNG)
            .tien(UPDATED_TIEN)
            .ngayChiTieu(UPDATED_NGAY_CHI_TIEU)
            .ghiChu(UPDATED_GHI_CHU);
        return chiTieu;
    }

    @BeforeEach
    public void initTest() {
        chiTieuRepository.deleteAll().block();
        chiTieu = createEntity();
    }

    @Test
    void getAllChiTieus() {
        // Initialize the database
        chiTieuRepository.save(chiTieu).block();

        // Get all the chiTieuList
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
            .value(hasItem(chiTieu.getId()))
            .jsonPath("$.[*].type")
            .value(hasItem(DEFAULT_TYPE))
            .jsonPath("$.[*].noiDung")
            .value(hasItem(DEFAULT_NOI_DUNG))
            .jsonPath("$.[*].tien")
            .value(hasItem(sameNumber(DEFAULT_TIEN)))
            .jsonPath("$.[*].ngayChiTieu")
            .value(hasItem(sameInstant(DEFAULT_NGAY_CHI_TIEU)))
            .jsonPath("$.[*].ghiChu")
            .value(hasItem(DEFAULT_GHI_CHU));
    }

    @Test
    void getChiTieu() {
        // Initialize the database
        chiTieuRepository.save(chiTieu).block();

        // Get the chiTieu
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, chiTieu.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(chiTieu.getId()))
            .jsonPath("$.type")
            .value(is(DEFAULT_TYPE))
            .jsonPath("$.noiDung")
            .value(is(DEFAULT_NOI_DUNG))
            .jsonPath("$.tien")
            .value(is(sameNumber(DEFAULT_TIEN)))
            .jsonPath("$.ngayChiTieu")
            .value(is(sameInstant(DEFAULT_NGAY_CHI_TIEU)))
            .jsonPath("$.ghiChu")
            .value(is(DEFAULT_GHI_CHU));
    }

    @Test
    void getNonExistingChiTieu() {
        // Get the chiTieu
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }
}
