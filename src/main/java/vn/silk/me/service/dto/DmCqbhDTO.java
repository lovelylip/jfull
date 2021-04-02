package vn.silk.me.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link vn.silk.me.domain.DmCqbh} entity.
 */
public class DmCqbhDTO implements Serializable {

    private String id;

    @NotNull(message = "must not be null")
    private String ma;

    @NotNull(message = "must not be null")
    private String ten;

    @NotNull(message = "must not be null")
    private String maCha;

    private String tenCha;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaCha() {
        return maCha;
    }

    public void setMaCha(String maCha) {
        this.maCha = maCha;
    }

    public String getTenCha() {
        return tenCha;
    }

    public void setTenCha(String tenCha) {
        this.tenCha = tenCha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmCqbhDTO)) {
            return false;
        }

        DmCqbhDTO dmCqbhDTO = (DmCqbhDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, dmCqbhDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmCqbhDTO{" +
            "id='" + getId() + "'" +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", maCha='" + getMaCha() + "'" +
            ", tenCha='" + getTenCha() + "'" +
            "}";
    }
}
