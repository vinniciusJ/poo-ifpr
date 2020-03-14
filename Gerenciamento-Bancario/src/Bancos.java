import java.util.ArrayList;

public class Bancos {
	 String nome, id, senha;
	 Boolean status;
	 ArrayList <Cofres> cofres = new ArrayList <>();
	 
	/* 
	 public void verificarSenha(String senha) {
		this.status = this.senha.contentEquals(senha) ? true : false;
	 }
	 public void setCofre(Cofres cofre) {
		 if(this.status) {
			 this.cofres.add(cofre);
		 }
		 else {
			 JOptionPane.showMessageDialog(null, "Operação recusada, senha inválida");
		 }
	 }
	 public void setCofre(int index) {
		 if(this.status) {
			 this.cofres.remove(index);
		 }
		 else {
			 JOptionPane.showMessageDialog(null, "Operação recusada, senha inválida");

		 }
	 }
	 public void setMoedas(int index, double valorConversao) {
		if(this.status) {
			this.cofres.get(index).conversao = valorConversao;
		}
	}
	 public ArrayList<Cofres> getCofres(){
		 if(this.status) {
			 return this.cofres;
		 }
		 else {
			 return null;
		 }
	 }
	 */
}
