package br.edu.ifsudestemg.loja.notificacao;

import br.edu.ifsudestemg.loja.modelo.Cliente;

import java.time.LocalDateTime;

public class NotificadorEmail implements CanalNotificacao {
    @Override
    public void enviar(Cliente cliente, String assunto, String mensagem) {
        System.out.println("E-mail para " + cliente.getEmail() + " | " + assunto + ": " + mensagem);
    }

    @Override
    public boolean enviarComAnexo(Cliente cliente, String assunto, String mensagem, String caminhoAnexo) {
        enviar(cliente, assunto, mensagem + " [anexo: " + caminhoAnexo + "]");
        return true;
    }

    @Override
    public boolean agendar(Cliente cliente, String assunto, String mensagem, LocalDateTime data) {
        System.out.println("E-mail agendado para " + data + " e destinatário " + cliente.getEmail());
        return true;
    }

    @Override
    public boolean confirmarLeitura(String identificadorMensagem) {
        return identificadorMensagem != null && !identificadorMensagem.isBlank();
    }
}
