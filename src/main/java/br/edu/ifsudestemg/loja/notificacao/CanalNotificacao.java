package br.edu.ifsudestemg.loja.notificacao;

import br.edu.ifsudestemg.loja.modelo.Cliente;

import java.time.LocalDateTime;

public interface CanalNotificacao {
    void enviar(Cliente cliente, String assunto, String mensagem);

    boolean enviarComAnexo(Cliente cliente, String assunto, String mensagem, String caminhoAnexo);

    boolean agendar(Cliente cliente, String assunto, String mensagem, LocalDateTime data);

    boolean confirmarLeitura(String identificadorMensagem);
}
