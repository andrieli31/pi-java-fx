package modelo;

public class Empresa extends PessoaJuridica {

	private String razao_social;
	private String porte_empresa;

	public void setId_empresa(Long id_empresa) {
		this.id_empresa = id_empresa;
	}

	@Override
	public Long getCnpj() {
		return cnpj;
	}

	@Override
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public Long getTelefone() {
		return telefone;
	}

	@Override
	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	private String nome_fantasia;
	private Long id_empresa;
	private Long cnpj;
	private Long telefone;

	public String getRazao_social() {
		return razao_social;
	}
	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}
	public String getPorte_empresa() {
		return porte_empresa;
	}
	public void setPorte_empresa(String porte_empresa) {
		this.porte_empresa = porte_empresa;
	}
	public String getNome_fantasia() {
		return nome_fantasia;
	}
	public void setNome_fantasia(String nome_fantasia) {
		this.nome_fantasia = nome_fantasia;
	}
}
