import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main {
	static ArrayList <Bancos> bancos = new ArrayList<>();
	private static String[] opcoesMenuInicial = {"Criar Banco", "Mostrar Bancos Criados", "Escolher Banco", "Excluir Banco", "Sair"};
	private static String[] opcoesMenuCofre = {"Criar Cofre", "Mostrar Cofres Criados", "Escolher Cofre", "Excluir Cofre", "Voltar"};
	private static String[] acoes = {"Depositar", "Sacar", "Saldo", "Cotação Atual", "Voltar"};
	
	//--------------------------------------------------------------------------------------//
	public static int lerOpcao(String message, String idMenu, Object[] opcoes) {
		return JOptionPane.showOptionDialog(null, message, idMenu, 0, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
	}
	public static String lerResposta(String message) {
		return JOptionPane.showInputDialog(message);
	}
	//-----------------------------------------------------------------------------------------//
	public static void voltarMenuBanco() {
		selecionarOpcaoMenuInicial(lerOpcao("Escolha uma opção", "Gerenciador de bancos", opcoesMenuInicial));
	}
	public static void voltarMenuCofre(int idBanco) {
		selecionarOpcaoMenuCofre(idBanco, lerOpcao("Escolha uma opção: ", formatarOpcoes().toArray()[idBanco].toString(), opcoesMenuCofre));
	}
	public static void voltarAcoesCofre(int idBanco, int idCofre) {
		selecionarAcaoCofre(idBanco, idCofre, lerOpcao("Escolha uma opção", formatarOpcoes(idBanco).get(idCofre).toString(), acoes));
	}
	//-----------------------------------------------------------------------------------------//
	// id == 0 -> Menu do Banco; id == 1 -> Menu dos cofres; id == 2 -> Menu das moedas
	
	public static void selecionarOpcaoMenuInicial(int opcaoEscolhida) {
		switch(opcaoEscolhida) {
		case 0:
			criarBanco();
			break;
		case 1:
			imprimirBancos();
			break;
		case 2:
			if(bancos.size() != 0) {
				int index = lerOpcao("Escolha qual banco deseja abrir: ", "Bancos Cadastrados", formatarOpcoes().toArray());
				selecionarOpcaoMenuCofre(index, lerOpcao("Escolha uma opção:", formatarOpcoes().toArray()[index].toString(), opcoesMenuCofre));
			}
			else {
				JOptionPane.showMessageDialog(null, "Nenhum banco foi criado ainda");
			}
			break;
		case 3:
			if(bancos.size() != 0) {
				int index = lerOpcao("Escolha qual banco deseja excluir: ", "Bancos Cadastrados", formatarOpcoes().toArray());
				excluir(index);
			}
			else {
				JOptionPane.showMessageDialog(null, "Nenhum banco foi criado ainda");
				voltarMenuBanco();
			}
		case 4:
			System.exit(0);
		}
		voltarMenuBanco();
	}
	
	public static void selecionarOpcaoMenuCofre(int index, int opEscolhida) {
		switch(opEscolhida) {
		case 0:
			criarCofre(index);
			break;
		case 1:
			imprimirCofre(index);
			break;
		case 2:
			selecionarAcaoCofre(index, lerOpcao("Escolha um cofre: ", "Selecionar Cofre", formatarOpcoes(index).toArray()), lerOpcao("Escolha uma opçao: ", "Açoes", acoes));
			break;
		case 3:
			excluir(index, lerOpcao("Escolha qual cofre deseja excluir: ", "Cofres Cadastrados", formatarOpcoes(index).toArray()), lerResposta("Informe sua senha: "));
			break;
		default:
			voltarMenuBanco();
		}
		voltarMenuCofre(index);
	}
	public static void selecionarAcaoCofre(int idBanco, int idCofre, int op) {
		String[] tipoMoedas = {"Dolar", "Euro", "Real"};
		switch(op) {
		case 0:
			depositarDinheiro(idBanco, idCofre, lerOpcao("Escolha seu tipo de moeda: ", "Moedas aceitadas pelo Banco", tipoMoedas), Double.parseDouble(lerResposta("Digite a quantidade a ser depositada: ")));
			voltarAcoesCofre(idBanco, idCofre);
			break;
		case 1:
			sacarDinheiro(idBanco, idCofre, lerOpcao("Escolha seu tipo de moeda: ", "Moedas aceitadas pelo Banco", tipoMoedas), Double.parseDouble(lerResposta("Digite a quantidade a ser depositada: ")));
			voltarAcoesCofre(idBanco, idCofre);
		case 2:
			imprimirSaldo(idBanco, idCofre, lerResposta("Informe sua senha:"));
			voltarAcoesCofre(idBanco, idCofre);
			break;
		case 3:
			imprimirCotacaoAtual();
			voltarAcoesCofre(idBanco, idCofre);
			break;
		case 4:
			voltarMenuCofre(idBanco);
			break;
		}
		voltarAcoesCofre(idBanco, idCofre);
	}
	public static void depositarDinheiro(int idBanco, int idCofre, int tipoMoeda, double valor) {
		Moedas m = new Moedas();
		boolean teste = false;
		
		for(int i = 0; i < bancos.get(idBanco).cofres.get(idCofre).getMoedas().size(); i++) {
			String tipo = bancos.get(idBanco).cofres.get(idCofre).getMoedas().get(i).tipo;
			if(tipo.equals(m.tipoCotacao[tipoMoeda])) {
				bancos.get(idBanco).cofres.get(idCofre).setMoedas(i, valor, 0);
				teste = true;
			}
		}
		if(!teste) {
			for(int i = 0; i < m.tipoCotacao.length; i++) {
				if(i == tipoMoeda) {
					m.tipo = m.tipoCotacao[tipoMoeda];
				}
			}
			if(valor < 0.0) {
				JOptionPane.showMessageDialog(null, "Impossível adicionar um valor negativo");
			}
			else {
				m.valor = valor;
			}
			adicionar(idBanco, idCofre, lerResposta("Informe sua senha: "), m);
		}
	}
	public static void sacarDinheiro(int idBanco, int idCofre, int tipoMoeda, double valor) {
		Moedas m = new Moedas();
		for(int i = 0; i < bancos.get(idBanco).cofres.get(idCofre).getMoedas().size(); i++) {
			if(bancos.get(idBanco).cofres.get(idCofre).getMoedas().get(i).tipo.equals(m.tipoCotacao[tipoMoeda])) {
				if(bancos.get(idBanco).cofres.get(idCofre).getMoedas().get(i).valor < valor) {
					JOptionPane.showMessageDialog(null, "A valor a ser sacado é maior do que saldo atual");
				}
				else {
					bancos.get(idBanco).cofres.get(idCofre).setMoedas(i, valor, 1);
				}
			}
		}
	}
	public static void imprimirSaldo(int idBanco, int idCofre, String senha) {
		String conteudo = "SALDO ATUAL \n ------------------------------------\n";
		if(bancos.get(idBanco).cofres.get(idCofre).getMoedas().size() != 0) {
			for(int i = 0; i < bancos.get(idBanco).cofres.get(idCofre).getMoedas().size(); i++) {
				conteudo +=  (bancos.get(idBanco).cofres.get(idCofre).getMoedas().get(i).tipo == "Dolar" ? "$" : (bancos.get(idBanco).cofres.get(idCofre).getMoedas().get(i).tipo == "Euro" ? "€" : "R$") + bancos.get(idBanco).cofres.get(idCofre).getMoedas().get(i).valor) +
							"\n";
			}
			JOptionPane.showMessageDialog(null, conteudo);
		}
		else {
			JOptionPane.showMessageDialog(null, "Cofre vazio");
		}
	}
	public static void imprimirCotacaoAtual() {
		Conversao conversor = new Conversao();
		JOptionPane.showMessageDialog(null, ("Cotação Atual (Moeda base - Dólar) \n" + conversor.toString()));
	}
	
	//------------------------------------------------------------------------------------------------------------//
	public static void criarBanco() {
		Bancos banco = new Bancos();
		String[] bancosCredenciados = {"Itaú", "Banco do Brasil", "Bradesco", "Caixa", "Santander", "Sicoob", "Banrisul", "Cancelar"};
		int op = lerOpcao("Escolha uma Empresa Bancária: ", "Criar Novo Banco", bancosCredenciados);
		
		for(int i = 0; i < bancosCredenciados.length; i++) {
			if(op != 7) {
				if(i == op) {
					banco.nome = bancosCredenciados[i];
				}
			}
			else {
				selecionarOpcaoMenuInicial(lerOpcao("Escolha uma opção: ", "Gerenciador de Bancos", opcoesMenuInicial));
			}
		}
		banco.id = criarID(banco.toString());
		adicionar(banco);
	}
	public static void imprimirBancos() {
		String conteudo = "";
		if(bancos.size() != 0) {
			for(int i = 0; i < bancos.size(); i++) {
				conteudo += bancos.get(i).nome + " " + bancos.get(i).id + "\n";
			}
		}
		else {
			conteudo = "Nenhum banco foi criado ainda";
		}
		JOptionPane.showMessageDialog(null, conteudo);
	}
	public static ArrayList<String> formatarOpcoes(){
		ArrayList<String> opBancos = new ArrayList<>();
		for(int i = 0; i < bancos.size(); i++) {
			opBancos.add(bancos.get(i).nome + " " + bancos.get(i).id);
		}
		return opBancos;
	}
	public static ArrayList <String> formatarOpcoes(int idBanco){
		ArrayList <String> opCofres = new ArrayList<>();
		for(int i = 0; i < bancos.get(idBanco).cofres.size(); i++) {
			opCofres.add("Cofre " + bancos.get(idBanco).cofres.get(i).id);
		}
		return opCofres;
	}
	//-------------------------------------------------------------------------------------------------------------//
	public static void criarCofre(int idBanco) {
		Cofres cofre = new Cofres();
		cofre.id = "C" + bancos.get(idBanco).cofres.size() + 1;
		cofre.senha = criarSenha(idBanco, lerResposta("Crie uma senha (Cofre " + cofre.id + "): "));
		adicionar(cofre, idBanco);
	}
	public static void imprimirCofre(int idBanco) {
		String conteudo = "";
		if(bancos.get(idBanco).cofres.size() != 0) {
			for(int i = 0; i < bancos.get(idBanco).cofres.size(); i++) {
				conteudo += "Cofre " + bancos.get(idBanco).cofres.get(i).id  + "\n";
			}
		}
		else {
			conteudo = "Nenhum cofre foi criado ainda";
		}
		JOptionPane.showMessageDialog(null, conteudo);
	}
	//-------------------------------------------------------------------------------------------------------------//
		
	//-------------------------------------------------------------------------------------------------------------//
	public static String criarID(String codigo) {
		int pos = 0;
		for(int i = 0; i < codigo.length(); i++) {
			if(codigo.charAt(i) == '@') {
				pos = i + 1;
			}
		}
		return codigo.substring(pos, (pos + 4)).toUpperCase();
	}
	public static String criarSenha(int idBanco, String senha) {
		String teste = "", senhaValida = "";
		teste = lerResposta("Digite sua senha novamente: ");
		if(teste.equals(senha)) {
			senhaValida = senha;
		}
		else {
			// Erro tipo 0 -> Senhas imcompativeis
			if(retornarErros(0) == 0) {
				criarSenha(idBanco, lerResposta("Digite novamente a senha: "));
			}
			else {
				voltarMenuCofre(idBanco);
			}
		}
		return senhaValida;
	}
	public static int retornarErros(int tipo) {
		int op = 0;
		switch(tipo) {
		case 0:
			String[] opcoes = {"Tentar Novamente", "Cancelar"};
			op = lerOpcao("Senhas incompátiveis", "ERRO", opcoes);
			break;
		}
		return op;
	}
	//-------------------------------------------------------------------------------------------------------------//
	public static void adicionar(Bancos banco) {
		bancos.add(banco);
	}
	public static void adicionar(Cofres cofre, int idBanco) {
		bancos.get(idBanco).cofres.add(cofre);
	}
	public static void adicionar(int idBanco, int idCofre, String senha, Moedas moeda) {
		bancos.get(idBanco).cofres.get(idCofre).verificarSenha(senha);
		bancos.get(idBanco).cofres.get(idCofre).setMoedas(moeda);
	}
	public static void excluir(int idBanco) {
		bancos.remove(idBanco);
	}
	public static void excluir(int idBanco, int idCofre, String senha) {
		if(bancos.get(idBanco).cofres.get(idCofre).senha.equals(senha)) {
			bancos.get(idBanco).cofres.remove(idCofre);
		}
		else {
			if(retornarErros(0) == 0) {
				excluir(idBanco, idCofre, JOptionPane.showInputDialog("Informe sua senha novamente: "));
			}
			else {
				voltarMenuCofre(idBanco);
			}
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------//
	public static void main(String[] args) {
		selecionarOpcaoMenuInicial(lerOpcao("Seja bem-vindo(a) para o gerenciador de Bancos", "Gerenciador de Bancos", opcoesMenuInicial));
	}
}
