package br.com.opensig.fiscal.server.sped.fiscal.blocoC;

import java.util.List;

import br.com.opensig.comercial.shared.modelo.ComEcfNotaProduto;
import br.com.opensig.fiscal.server.sped.ARegistro;

public class RegistroC321 extends ARegistro<DadosC321, List<ComEcfNotaProduto>> {

	@Override
	protected DadosC321 getDados(List<ComEcfNotaProduto> dados) throws Exception {
		DadosC321 d = new DadosC321();
		for (ComEcfNotaProduto np : dados) {
			d.setCod_item(np.getProdProduto().getProdProdutoId() + "");
			d.setQtd(np.getComEcfNotaProdutoQuantidade());
			d.setUnid(np.getProdEmbalagem().getProdEmbalagemNome());
			d.setVl_item(somarDoubles(d.getVl_item(), np.getComEcfNotaProdutoLiquido()));
			d.setDesc(somarDoubles(d.getDesc() , np.getComEcfNotaProdutoDesconto()));
			if (np.getComEcfNotaProdutoIcms() != null) {
				d.setVl_bc_icms(d.getVl_item());
				d.setVl_icms(d.getVl_item() * np.getComEcfNotaProdutoIcms() / 100);
			}
			d.setVl_pis(d.getVl_item() * np.getProdProduto().getProdPis().getProdPisAliquota() / 100);
			d.setVl_cofins(d.getVl_item() * np.getProdProduto().getProdCofins().getProdCofinsAliquota() / 100);
		}
		
		return d;
	}
}
