import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Cofres {
	String id, senha;
	Boolean status;
	String conversao;
	private ArrayList <Moedas> moedas = new ArrayList<>();
	
	public void verificarSenha(String senha) {
		 this.status = this.senha.equals(senha) ? true : false;
	}
	public void setMoedas(Moedas moeda) {
		if(this.status) {
			this.moedas.add(moeda);
		}
		else {
			JOptionPane.showMessageDialog(null, "Operação recusada, senha inválida");
		}
	}
	public void setMoedas(int index, double valor, int idOperacao) {
		if(this.status) {
			if(idOperacao == 0) {
				this.moedas.get(index).valor += valor;
			}
			else {
				this.moedas.get(index).valor -= valor;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Operação recusada, senha inválida");
		}
	}
	public ArrayList<Moedas> getMoedas() {
		return this.moedas;
	}
}
