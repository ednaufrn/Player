package br.imd.modelo;

public class RetornoLogin {
    private String codigo;
    private UsuarioComum usuarioComum;
    private UsuarioVip usuarioVip;
    
    
	public RetornoLogin(String codigo, UsuarioComum usuarioComum, UsuarioVip usuarioVip) {
		this.codigo = codigo;
		this.usuarioComum = usuarioComum;
		this.usuarioVip = usuarioVip;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public UsuarioComum getUsuarioComum() {
		return usuarioComum;
	}
	public void setUsuarioComum(UsuarioComum usuarioComum) {
		this.usuarioComum = usuarioComum;
	}
	public UsuarioVip getUsuarioVip() {
		return usuarioVip;
	}
	public void setUsuarioVip(UsuarioVip usuarioVip) {
		this.usuarioVip = usuarioVip;
	}
}