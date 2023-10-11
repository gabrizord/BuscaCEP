package br.com.gabrizord;

import com.google.gson.Gson;

public class Endereco {
    private final String cep;
    private final String logradouro;
    private final String complemento;
    private final String bairro;
    private final String localidade;
    private final String uf;
    private final String ibge;
    private final String gia;
    private final String ddd;
    private final String siafi;

    public Endereco(String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String ibge, String gia, String ddd, String siafi) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getIbge() {
        return ibge;
    }

    public String getGia() {
        return gia;
    }

    public String getDdd() {
        return ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    @Override
    public String toString() {
        return "CEP: " + cep + "\nRua: " + logradouro + "\nComplemento: " + complemento + "\nBairro: " + bairro + "\nLocalidade: " + localidade + "\nUF: " + uf + "\nIBGE: " + ibge + "\nGIA: " + gia + "\nDDD: " + ddd + "\nSIAFI: " + siafi;
    }

    public static Endereco fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Endereco.class);
    }
}
