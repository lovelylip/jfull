package vn.silk.me.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DmCqbh.
 */
@Document(collection = "dm_cqbh")
public class DmCqbh implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "must not be null")
    @Field("ma")
    @Indexed
    private String ma;

    @NotNull(message = "must not be null")
    @Field("ten")
    private String ten;

    @NotNull(message = "must not be null")
    @Field("ma_cha")
    @Indexed
    private String maCha;

    @Field("ten_cha")
    private String tenCha;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DmCqbh id(String id) {
        this.id = id;
        return this;
    }

    public String getMa() {
        return this.ma;
    }

    public DmCqbh ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return this.ten;
    }

    public DmCqbh ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaCha() {
        return this.maCha;
    }

    public DmCqbh maCha(String maCha) {
        this.maCha = maCha;
        return this;
    }

    public void setMaCha(String maCha) {
        this.maCha = maCha;
    }

    public String getTenCha() {
        return this.tenCha;
    }

    public DmCqbh tenCha(String tenCha) {
        this.tenCha = tenCha;
        return this;
    }

    public void setTenCha(String tenCha) {
        this.tenCha = tenCha;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DmCqbh)) {
            return false;
        }
        return id != null && id.equals(((DmCqbh) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DmCqbh{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", maCha='" + getMaCha() + "'" +
            ", tenCha='" + getTenCha() + "'" +
            "}";
    }
}
