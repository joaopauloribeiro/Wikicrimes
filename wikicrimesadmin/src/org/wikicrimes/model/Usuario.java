package org.wikicrimes.model;

import java.util.Date;
import java.util.Set;

public class Usuario extends BaseObject {

	private static final long serialVersionUID = 3257568390917667126L;

	private Long idUsuario;

	private String primeiroNome;
	private String ultimoNome;
	private String homepage;
	private String email;
	private String confirmacao;
	private String senha;
	private String cidade;
	private String idiomaPreferencial;
	private String receberNewsletter;
	private String estado;
	private String pais;
	private Date aniversario;
	private String ip;
	private Integer sexo;
	private String chaveConfirmacao;
	private Perfil perfil;
	private Double lat;
	private Double lng;
	private Date dataHoraRegistro;
	private EntidadeCertificadora entidadeCertificadora;
	private Set<UsuarioRedeSocial> redesSociais;
	private Long qtdCrimes;
	private Long qtdAreas;
	

	public final static String TRUE = "1";
	public final static String FALSE = "0";

	public String getChaveConfirmacao() {
		return chaveConfirmacao;
	}

	public void setChaveConfirmacao(String chaveConfirmacao) {
		this.chaveConfirmacao = chaveConfirmacao;
	}

	public Usuario() {
	}

	public Usuario(Long idUsuario) {
		this.setIdUsuario(idUsuario);
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return Returns firstName and lastName
	 */
	public String getNome() {
		return primeiroNome + " " + ultimoNome;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmacao() {
		return confirmacao;
	}

	public void setConfirmacao(String confirmacao) {
		this.confirmacao = confirmacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getAniversario() {
		return aniversario;
	}

	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Usuario)) {
			return false;
		} else {
			Usuario usuario = (Usuario) obj;
			return usuario.getIdUsuario().equals(this.getIdUsuario());
		}

	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Date getDataHoraRegistro() {
		return dataHoraRegistro;
	}

	public void setDataHoraRegistro(Date dataHoraRegistro) {
		this.dataHoraRegistro = dataHoraRegistro;
	}

	public EntidadeCertificadora getEntidadeCertificadora() {
		return entidadeCertificadora;
	}

	public void setEntidadeCertificadora(
			EntidadeCertificadora entidadeCertificadora) {
		this.entidadeCertificadora = entidadeCertificadora;
	}

	public Set<UsuarioRedeSocial> getRedesSociais() {
		return redesSociais;
	}

	public void setRedesSociais(Set<UsuarioRedeSocial> redesSociais) {
		this.redesSociais = redesSociais;
	}

	public Long getQtdCrimes() {
		return qtdCrimes;
	}

	public void setQtdCrimes(Long qtdCrimes) {
		this.qtdCrimes = qtdCrimes;
	}
	public String getIdiomaPreferencial() {
		return idiomaPreferencial;
	}

	public void setIdiomaPreferencial(String idiomaPreferencial) {
		this.idiomaPreferencial = idiomaPreferencial;
	}

	public String getReceberNewsletter() {
		return receberNewsletter;
	}

	public void setReceberNewsletter(String receberNewsletter) {
		this.receberNewsletter = receberNewsletter;
	}

	public Long getQtdAreas() {
		return qtdAreas;
	}

	public void setQtdAreas(Long qtdAreas) {
		this.qtdAreas = qtdAreas;
	}


}