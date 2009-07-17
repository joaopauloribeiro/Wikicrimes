package org.wikicrimes.dao;

import org.wikicrimes.model.EstatisticaPais;

public interface EstatisticaPaisDao extends GenericCrudDao {
	
	public EstatisticaPais getEstatisticaPais(Long idEstatisticaPais);
	public EstatisticaPais getEstatisticaPais(String sigla);
	public String getNomePais(String sigla);
	public String getSigla(Long idEstatisticaPais);
	public Long getQtdUsuarios(String sigla);
	public Long getQtdCrimes(String sigla);
	public Long getQtdRoubos(String sigla);
	public Long getQtdRouboPessoa(String sigla);
	public Long getQtdRouboPropriedade(String sigla);
	public Long getQtdTentativaRouboPessoa(String sigla);
	public Long getQtdTentativaRouboPropriedade(String sigla);
	public Long getQtdFurto(String sigla);
	public Long getQtdFurtoPessoa(String sigla);
	public Long getQtdFurtoPropriedade(String sigla);
	public Long getQtdTentativaFurtoPessoa(String sigla);
	public Long getQtdTentativaFurtoPropriedade(String sigla);
	public Long getQtdOutros(String sigla);
	public Long getQtdOutrosRixas(String sigla);
	public Long getQtdOutrosViolenciaDomestica(String sigla);
	public Long getQtdOutrosAbusoAutoridade(String sigla);
	public Long getQtdOutrosHomicidio(String sigla);
	public Long getQtdOutrosTentativaHomicidio(String sigla);
	public Long getQtdOutrosLatrocinio(String sigla);
	public Long getQtdTurnoUm(String sigla);
	public Long getQtdTurnoDois(String sigla);
	public Long getQtdTurnoTres(String sigla);
	public Long getQtdTurnoQuatro(String sigla);
	public Double getLatitude(String sigla);
	public Double getLongitude(String sigla);
	public Long getQtdTentativaRoubo(String sigla);
	public Long getQtdTentativaFurto(String sigla);

}
