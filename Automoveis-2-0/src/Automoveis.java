import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Automoveis {
	String marca, modelo, cor;
	int ano;
	boolean status = false, combustivel = false, manutencao = false;
	static ArrayList <Automoveis> listaAutomoveis = new ArrayList <>();
	
	/*******************************************************************/
	
	public static String lerAtributosAutomovel(String tipo) {
		return JOptionPane.showInputDialog("Informe o(a) " + tipo + ":");
	}
	public Automoveis() {
		this.marca = lerAtributosAutomovel("marca");
		this.modelo = lerAtributosAutomovel("modelo");
		this.cor = lerAtributosAutomovel("cor");
		this.ano = Integer.parseInt(lerAtributosAutomovel("ano"));
	}
	public static void imprimirCarros() {
		String conteudo = "";
		for(int i = 0; i < listaAutomoveis.size(); i++) {
			conteudo += "Carro " + (i + 1) + ": \n Marca: " + listaAutomoveis.get(i).marca + " \n Modelo: " + listaAutomoveis.get(i).modelo + "\n Cor: " + listaAutomoveis.get(i).cor + "\n Ano: " + listaAutomoveis.get(i).ano + "\n \n \n";
		}
		JOptionPane.showMessageDialog(null, conteudo);
	}
	/*****************************************************************/
	
	public static void lerOpcaoMenu() {
		String escolha[] = {
				"Criar carro",
				"Mostrar Carros",
				"Modificar carro",
				"Sair"
		};
		selecionarOpcao(JOptionPane.showOptionDialog(null, "Escolha uma opção", "Automoveis", 0, JOptionPane.QUESTION_MESSAGE, null, escolha, escolha[0]), 0);
		
		lerOpcaoMenu();
	}
	public static void lerOpcaoMenuCarro(int index) {
		String escolha[] = {
				"Ligar/Desligar",
				"Abastecer",
				"Manutenir",
				"Rodar",
				"Mostrar Especificações",
				"Voltar"
		};
		selecionarOpcaoCarro(JOptionPane.showOptionDialog(null, "Escolha uma opção", "Automoveis", 0, JOptionPane.QUESTION_MESSAGE, null, escolha, escolha[0]), index);
	}
	public static void selecionarOpcao(int op, int index) {
		if(listaAutomoveis.size() != 0 || op == 0 || op == 3) {
			switch(op) {
			case 0:
				Automoveis automovel = new Automoveis();
				listaAutomoveis.add(automovel);
				break;
			case 1:
				imprimirCarros();
				break;
			case 2:
				abrirMenuCarro();
				break;
			case 3:
				System.exit(0);
				break;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Não há nenhum carro criado");
		}
	}
	
	/*****************************************************************/
	public static int escolherCarro() {
		ArrayList <String> opcoes = new ArrayList<>();
		for(int i = 0; i < listaAutomoveis.size(); i++) {
			opcoes.add(listaAutomoveis.get(i).marca + " " + listaAutomoveis.get(i).modelo);
		}
		return JOptionPane.showOptionDialog(null, "Escolha uma opção", "Automoveis", 0, JOptionPane.QUESTION_MESSAGE, null, opcoes.toArray(), opcoes.toArray()[0]);
	}
	
	public static void selecionarOpcaoCarro(int op, int index) {
		switch(op) {
		case 0:
			listaAutomoveis.get(index).mudarStatus();
			imprimirStatus(listaAutomoveis.get(index));
			break;
		case 1:
			listaAutomoveis.get(index).abastecerAutomovel();
			imprimirEstadoCombustivel(listaAutomoveis.get(index));
			break;
		case 2:
			listaAutomoveis.get(index).mudarManutencao();
			imprimirEstadoManutencao(listaAutomoveis.get(index));
			break;
		case 3:
			listaAutomoveis.get(index).rodarAutomovel();
			break;
		case 4:
			imprimirAtributosCarro(listaAutomoveis.get(index));
			break;
		case 5:
			lerOpcaoMenu();
			break;
		}
		lerOpcaoMenuCarro(index);
	}
	
	public static void abrirMenuCarro() {
		lerOpcaoMenuCarro(escolherCarro());
	}
	
	/*****************************************************************/
	
	public void mudarStatus() {
		this.status = this.status ? false : true;
	}
	public void mudarManutencao() {
		this.manutencao = !this.manutencao ? true : false;
	}
	public void abastecerAutomovel() {
		if(!this.status) {
			this.combustivel = true;
		}
		else {
			this.combustivel = false;
		}
	}
	public void rodarAutomovel() {
		if(this.status) {
			if(this.manutencao) {
				JOptionPane.showMessageDialog(null, "O carro está em manutenção");
			}
			else {
				JOptionPane.showMessageDialog(null, "O carro está andando");
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "O carro está delisgado");
		}
	}
	
	/*******************************************************************/
	
	public static void imprimirStatus(Automoveis automovel) {
		JOptionPane.showMessageDialog(null, "O carro está " + (automovel.status ? "ligado" : "desligado"));
	}
	public static void imprimirEstadoManutencao(Automoveis automovel) {
		JOptionPane.showMessageDialog(null, "O carro está " + (automovel.manutencao ? "manutenido" : " em manutencao"));
	}
	public static void imprimirEstadoCombustivel(Automoveis automovel) {
		JOptionPane.showMessageDialog(null, "O carro " + (automovel.combustivel ? "abastecido" : "não pode ser abastecido quando estiver ligado. \n Por favor desligue-o"));
	}
	public static void imprimirAtributosCarro(Automoveis automovel) {
		JOptionPane.showMessageDialog(
				null, 
				"Marca: " + automovel.marca +
				"\n Modelo: " + automovel.modelo +
				"\n Cor: " + automovel.cor +
				"\n Ano: " + Integer.toString(automovel.ano) +
				"\n Estado: " + (automovel.status ? "Ligado" : "Desligado") +
				"\n Manutenção: " + (automovel.manutencao ? "Sim" : "Não") +
				"\n Abastecido: " + (automovel.combustivel ? "Sim" : "Não")
		);
	}
	
	/*******************************************************************/
	
	public static void main(String[] args) {
		lerOpcaoMenu();
	}
}
