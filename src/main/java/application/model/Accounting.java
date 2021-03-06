package application.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Accounting {

    private String id;

    @NotNull(message = "Conta Contábil field cannot be null")
    @Digits(integer = 8, fraction = 0, message = "Field Data allows Up to 8 digits (Integer)")
    private Integer contaContabil;

    @NotNull(message = "Field Data cannot be null")
    @Digits(integer = 8, fraction = 0, message = "Field Data allows Up to 8 digits (Integer)")
    private Integer data;

    @Min(0)
    @NotNull(message = "Field Valor cannot be null")
    @Digits(integer = 20, fraction = 2, message = "Field Valor allows Up to 20 digits (Integer) and 2 (decimal)")
    private BigDecimal valor;

    public Accounting() {
        super();
    }

    public Accounting(@JsonProperty("id") String id, @JsonProperty("contaContabil") Integer contaContabil,
                      @JsonProperty("data") Integer data, @JsonProperty("valor") BigDecimal valor) {
        this.id = id;
        this.contaContabil = contaContabil;
        this.data = data;
        this.valor = valor;
    }

    public Accounting(@JsonProperty("contaContabil") Integer contaContabil, @JsonProperty("data") Integer data,
                      @JsonProperty("valor") BigDecimal valor) {
        this.contaContabil = contaContabil;
        this.data = data;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(Integer contaContabil) {
        this.contaContabil = contaContabil;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Accounting{" +
                "id='" + id + '\'' +
                ", contaContabil=" + contaContabil +
                ", data=" + data +
                ", valor=" + valor +
                '}';
    }
}
