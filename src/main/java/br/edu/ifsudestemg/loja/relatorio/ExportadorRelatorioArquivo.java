package br.edu.ifsudestemg.loja.relatorio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ExportadorRelatorioArquivo {
    public void salvar(String conteudo, String caminho) {
        try {
            Path arquivo = Path.of(caminho);
            if (arquivo.getParent() != null) {
                Files.createDirectories(arquivo.getParent());
            }
            Files.writeString(arquivo, conteudo);
            System.out.println("Relatório salvo em " + arquivo.toAbsolutePath());
        } catch (IOException e) {
            throw new IllegalStateException("Não foi possível salvar o relatório", e);
        }
    }
}
