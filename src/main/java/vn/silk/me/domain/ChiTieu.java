package vn.silk.me.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A ChiTieu.
 */
@Document(collection = "chi_tieu")
public class ChiTieu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "must not be null")
    @Field("type")
    @Indexed
    private String type;

    @NotNull(message = "must not be null")
    @Field("noi_dung")
    private String noiDung;

    @NotNull(message = "must not be null")
    @Field("tien")
    private BigDecimal tien;

    @NotNull(message = "must not be null")
    @Field("ngay_chi_tieu")
    private ZonedDateTime ngayChiTieu;

    @Field("ghi_chu")
    private String ghiChu;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChiTieu id(String id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public ChiTieu type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNoiDung() {
        return this.noiDung;
    }

    public ChiTieu noiDung(String noiDung) {
        this.noiDung = noiDung;
        return this;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public BigDecimal getTien() {
        return this.tien;
    }

    public ChiTieu tien(BigDecimal tien) {
        this.tien = tien;
        return this;
    }

    public void setTien(BigDecimal tien) {
        this.tien = tien;
    }

    public ZonedDateTime getNgayChiTieu() {
        return this.ngayChiTieu;
    }

    public ChiTieu ngayChiTieu(ZonedDateTime ngayChiTieu) {
        this.ngayChiTieu = ngayChiTieu;
        return this;
    }

    public void setNgayChiTieu(ZonedDateTime ngayChiTieu) {
        this.ngayChiTieu = ngayChiTieu;
    }

    public String getGhiChu() {
        return this.ghiChu;
    }

    public ChiTieu ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTieu)) {
            return false;
        }
        return id != null && id.equals(((ChiTieu) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTieu{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", tien=" + getTien() +
            ", ngayChiTieu='" + getNgayChiTieu() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            "}";
    }
}
