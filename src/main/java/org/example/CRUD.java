package org.example;
import java.sql.*;

public class CRUD {
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
    private static final String URL = "jdbc:postgresql://localhost:3000/cadastrocliente";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "emerson12";

    public void cadCliente(String cpf, String nome, String sobrenome, String telefone, String email, Integer idEndereco) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO cadastro_cliente (cpf, nome, sobrenome, telefone, email_cliente, id_endereco) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cpf);
                pstmt.setString(2, nome);
                pstmt.setString(3, sobrenome);
                pstmt.setString(4, telefone);
                pstmt.setString(5, email);
                pstmt.setInt(6, idEndereco);
                pstmt.executeUpdate();
                System.out.println(G + BOLD +"Cliente cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verEndereco() {
        String sql = "SELECT id_endereco, endereco FROM endereco_cliente";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idEndereco = rs.getInt("id_endereco");
                String endereco = rs.getString("endereco");
                System.out.println(G+BOLD+"|---------------------------------------------------------|");
                System.out.println("| ID Endereco: "+ R + BOLD + idEndereco + RESET + G +BOLD+ ", Endereco: "+ R + BOLD + endereco+ RESET + G +BOLD);
            }
        } catch (SQLException e) {
            System.out.println(G+BOLD+"Erro ao consultar endereço: " + e.getMessage());
        }
    }

    public void verCliente() {
        String sql = "SELECT id_cliente, cpf, nome, sobrenome, telefone, email_cliente, id_endereco FROM cadastro_cliente";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int id_cliente = rs.getInt("id_cliente");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                String telefone = rs.getString("telefone");
                String email_cliente = rs.getString("email_cliente");
                int id_endereco = rs.getInt("id_endereco");
                System.out.println(G + BOLD + "|---------------------------------------------------------|");
                System.out.println("| ID do cliente:"+ R + BOLD + id_cliente+ RESET + G +BOLD);
                System.out.println("| CPF do cliente: "+ R + BOLD +  cpf+ RESET + G +BOLD);
                System.out.println("| Nome do cliente: "+ R + BOLD +  nome + " " + sobrenome+ RESET + G +BOLD);
                System.out.println("| Número de telefone do cliente: "+ R + BOLD +  telefone+ RESET + G +BOLD);
                System.out.println("| Email do cliente: "+ R + BOLD +  email_cliente+ RESET + G +BOLD);
                System.out.println("| ID de endereço: "+ R + BOLD +  id_endereco+ RESET + G +BOLD);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o clientes: " + e.getMessage());
        }
    }

    public void verProdutos() {
        String sql = "SELECT id_produto, nome_produto, valor_produto, estoque_produto FROM catalogo_produtos";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int idProduto = rs.getInt("id_produto");
                int estoqueProduto = rs.getInt("estoque_produto");
                String nomeProduto = rs.getString("nome_produto");
                Double valorproduto = rs.getDouble("valor_produto");
                System.out.println(G + BOLD + "|---------------------------------------------------------|");
                System.out.println("| ID Produto: "+ R + BOLD + + idProduto+ RESET + G +BOLD);
                System.out.println("| Nome do Produto"+ R + BOLD + nomeProduto+ RESET + G +BOLD);
                System.out.println("| Valor do Produto: "+ R + BOLD +"R$" + valorproduto+ RESET + G +BOLD);
                System.out.println("| Estoque do Produto"+ R + BOLD + estoqueProduto+ RESET + G +BOLD);
            }
        } catch (SQLException e) {
            System.out.println(R + BOLD + "Erro ao consultar endereço: " + e.getMessage());
        }
    }

    public void verHistoricoCliente(int idCliente) {
        String nomeCliente = obterNomeCliente(idCliente);
        double totalCompra = 0;


        String sql = "SELECT hc.id_produto, cp.nome_produto, cp.valor_produto, cp.estoque_produto " +
                "FROM historico_cliente hc " +
                "INNER JOIN catalogo_produtos cp ON hc.id_produto = cp.id_produto " +
                "WHERE hc.id_cliente = ?";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("|---------------------------------------------------------|");
                System.out.println(G + BOLD + "Histórico de Compras para o Cliente: " + R + BOLD + nomeCliente + RESET + G);
                while (rs.next()) {
                    int idProduto = rs.getInt("id_produto");
                    String nomeProduto = rs.getString("nome_produto");
                    double valorProduto = rs.getDouble("valor_produto");
                    int estoqueProduto = rs.getInt("estoque_produto");
                    System.out.println("| ID Produto: " + R + BOLD + idProduto + RESET + G +BOLD);
                    System.out.println("| Nome do Produto: " + R + BOLD + nomeProduto + RESET + G +BOLD);
                    System.out.println("| Valor do Produto: R$" + R + BOLD + valorProduto + RESET + G +BOLD);
                    System.out.println("| Estoque do Produto: " + R + BOLD + estoqueProduto + RESET + G +BOLD);
                    totalCompra += valorProduto;
                }
            }
            System.out.println("|---------------------------------------------------------|");
            System.out.println("| Valor Total da Compra: "+ R +"R$"+ totalCompra + RESET + G);
            System.out.println(G + BOLD + "|---------------------------------------------------------|");
        } catch (SQLException e) {
            System.out.println(R + BOLD + "Erro ao consultar histórico do cliente: " + e.getMessage());
        }
    }

    private String obterNomeCliente(int idCliente) {
        String nomeCliente = "";
        String sobrenomeCliente = "";

        // Consulta SQL para obter o nome e sobrenome do cliente
        String sql = "SELECT nome, sobrenome FROM cadastro_cliente WHERE id_cliente = ?";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    nomeCliente = rs.getString("nome");
                    sobrenomeCliente = rs.getString("sobrenome");
                }
            }
        } catch (SQLException e) {
            System.out.println(R + BOLD + "Erro ao obter nome do cliente: " + e.getMessage());
        }
        return nomeCliente + " " + sobrenomeCliente;
    }

    public void cadEndereco(String pais, String estado, String cidade, String bairro, String endereco) {
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO endereco_cliente (pais, estado, cidade, bairro, endereco) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, pais);
                pstmt.setString(2, estado);
                pstmt.setString(3, cidade);
                pstmt.setString(4, bairro);
                pstmt.setString(5, endereco);
                pstmt.executeUpdate();
                System.out.println(G + BOLD + "Endereço cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}