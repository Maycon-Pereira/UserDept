package com.imagine.userdept.domain;

public class DeletaDepartmentResponse {

	private String mensagem;
	private boolean deletado;

	public boolean isDeletado() {
		return deletado;
	}

	public void setDeletado(boolean removeu) {
		this.deletado = removeu;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
