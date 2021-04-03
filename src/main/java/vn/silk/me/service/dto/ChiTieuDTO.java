package vn.silk.me.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link vn.silk.me.domain.ChiTieu} entity.
 */
public class ChiTieuDTO implements Serializable {

    private String id;

    @NotNull(message = "must not be null")
    private String type;

    @NotNull(message = "must not be null")
    private String noiDung;

    @NotNull(message = "must not be null")
    private BigDecimal tien;

    @NotNull(message = "must not be null")
    private ZonedDateTime ngayChiTieu;

    private String ghiChu;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public BigDecimal getTien() {
        return tien;
    }

    public void setTien(BigDecimal tien) {
        this.tien = tien;
    }

    public ZonedDateTime getNgayChiTieu() {
        return ngayChiTieu;
    }

    public void setNgayChiTieu(ZonedDateTime ngayChiTieu) {
        this.ngayChiTieu = ngayChiTieu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTieuDTO)) {
            return false;
        }

        ChiTieuDTO chiTieuDTO = (ChiTieuDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, chiTieuDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ChiTieuDTO{" +
            "id='" + getId() + "'" +
            ", type='" + getType() + "'" +
            ", noiDung='" + getNoiDung() + "'" +
            ", tien=" + getTien() +
            ", ngayChiTieu='" + getNgayChiTieu() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            "}";
    }
}
