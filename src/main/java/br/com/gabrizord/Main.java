package br.com.gabrizord;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continuar = "s";

        while (continuar.equals("s")) {
            System.out.println("Bem-vindo! Por favor insira o CEP (Sem pontos e traço): ");
            String cep = scanner.nextLine();

            try {
                String uri = "https://viacep.com.br/ws/" + cep + "/json/";

                // Inicialize o cliente HTTP e faça a solicitação à API
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(uri)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                // Verifique o código de resposta da API
                if (response.statusCode() == 200) {

                    // Parse da resposta JSON usando Gson
                    Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
                    Endereco endereco = Endereco.fromJson(response.body());

                    System.out.println(endereco.toString());

                    // Nome do arquivo será o número de CEP
                    String nomeArquivo = cep + ".json";

                    // Cria um FileWriter para escrever os dados no arquivo JSON
                    try (FileWriter fileWriter = new FileWriter(nomeArquivo)) {
                        gson.toJson(endereco, fileWriter);
                        System.out.println("Dados do endereço salvos em " + nomeArquivo);
                    }
                } else {
                    System.out.println("Erro na requisição. Código de resposta: " + response.statusCode());
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Erro durante a solicitação HTTP: " + e.getMessage());
            }

            System.out.println("Deseja consultar outro CEP? (s/n): ");
            continuar = scanner.nextLine().toLowerCase();
        }
    }
}