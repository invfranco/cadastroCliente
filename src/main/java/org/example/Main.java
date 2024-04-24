package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static final String RESET = "\033[0m";  // Resetar cor
    public static final String BOLD = "\033[1m";       // Negrito
    public static final String B = "\033[0;30m";   // Preto
    public static final String R = "\033[0;31m";     // Vermelho
    public static final String G = "\033[0;32m";   // Verde
    public static final String Y = "\033[0;33m";  // Amarelo
    public static final String BE = "\033[0;34m";    // Azul
    public static final String P = "\033[0;35m";  // Roxo
    public static final String C = "\033[0;36m";    // Ciano
    public static final String W = "\033[0;37m";   // Branco

    public static void main(String[] args) {
        CRUD crud = new CRUD();

        // Interface CLI
        while (true) {
            System.out.println(C+BOLD+"|--------------------------------------------|");
            System.out.println("|            Escolha uma opção               |");
            System.out.println("|--------------------------------------------|");
            System.out.println("|      1 ➔ Cadastrar endereço de cliente     |");
            System.out.println("|      2 ➔ Exibir Endereços                  |");
            System.out.println("|      3 ➔ Cadastrar um cliente              |");
            System.out.println("|      4 ➔ Exibir clientes                   |");
            System.out.println("|      5 ➔ Exibir Produtos                   |");
            System.out.println("|      6 ➔ Exibir historico de um cliente    |");
            System.out.println("|      7 ➔ Sair                              |");
            System.out.println("|--------------------------------------------|"+RESET);

            try {
                int opcao = Integer.parseInt(System.console().readLine());

                switch (opcao) {
                    case 1:
                        System.out.println(G+BOLD+"|--------------------------------------------|");
                        System.out.println("Qual é o País de residencia do Cliente:");
                        String pais = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Qual é Estado de residencia do Cliente:");
                        String estado = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Qual é a Cidade residencia do Cliente:");
                        String cidade = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Qual é o Bairro de residencia do Cliente:");
                        String bairro = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Qual é o Endereço de residencia do Cliente:");
                        String endereco = System.console().readLine();
                        crud.cadEndereco(pais,estado,cidade,bairro,endereco);
                        break;
                    case 2:
                        crud.verEndereco();
                        break;
                    case 3:
                        System.out.println(G+BOLD+"|--------------------------------------------|");
                        System.out.println("Digite o CPF do cliente:");
                        String cpf = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Digite o nome do cliente:");
                        String nome = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Digite o sobrenome do cliente:");
                        String sobrenome = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Digite o telefone do cliente:");
                        String telefone = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Digite o email do cliente:");
                        String email = System.console().readLine();
                        System.out.println("|--------------------------------------------|");
                        System.out.println("Digite o id de endereço do cliente");
                        Integer idEndereco = Integer.parseInt(System.console().readLine());
                        crud.cadCliente(cpf,nome,sobrenome,telefone,email,idEndereco);
                        break;
                    case 4:
                        crud.verCliente();
                        break;
                    case 5:
                        crud.verProdutos();
                        break;
                    case 6:
                        System.out.println(G+BOLD+"Qual o ID do Cliente para a consulta?");
                        String idCliente = System.console().readLine();
                        crud.verHistoricoCliente(Integer.parseInt(idCliente));
                        break;
                    case 7:
                        System.exit(0);
                    default:
                        System.out.println(R+"Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println(R+"Por favor, digite um número válido!");
            }
        }
    }
}