package br.edu.ifsudestemg.loja.notificacao;

import br.edu.ifsudestemg.loja.modelo.Cliente;

import java.time.LocalDateTime;

public class NotificadorSms implements CanalNotificacao {
    @Override
    public void enviar(Cliente cliente, String assunto, String mensagem) {
        System.out.println("SMS para " + cliente.getTelefone() + ": " + assunto + " - " + mensagem);
    }

    @Override
    public boolean enviarComAnexo(Cliente cliente, String assunto, String mensagem, String caminhoAnexo) {
        enviar(cliente, assunto, mensagem + " (consulte o anexo no site)");
        return false;
    }

    @Override
    public boolean agendar(Cliente cliente, String assunto, String mensagem, LocalDateTime data) {
        return false;
    }

    @Override
    public boolean confirmarLeitura(String identificadorMensagem) {
        return false;
    }
}
