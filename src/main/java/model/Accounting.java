package model;

import java.math.BigDecimal;

public class Accounting {

    private Integer id;
    private Integer contaContabil;
    private Integer Data;
    private BigDecimal Valor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(Integer contaContabil) {
        this.contaContabil = contaContabil;
    }

    public Integer getData() {
        return Data;
    }

    public void setData(Integer data) {
        Data = data;
    }

    public BigDecimal getValor() {
        return Valor;
    }

    public void setValor(BigDecimal valor) {
        Valor = valor;
    }

    @Override
    public String toString() {
        return "Accounting{" +
                "id=" + id +
                ", contaContabil=" + contaContabil +
                ", Data=" + Data +
                ", Valor=" + Valor +
                '}';
    }
}
