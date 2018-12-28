package application.model;

import java.math.BigDecimal;

public class Accounting {

    private String id;
    private Integer contaContabil;
    private Integer data;
    private BigDecimal valor;

    public Accounting(Integer contaContabil, Integer data, BigDecimal valor) {
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
