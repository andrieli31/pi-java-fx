package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Categoria;
import modelo.TipoAcessoLogin;
import modelo.Vendedor;

public class CategoriaDAO implements ICategoriaDAO {

	public ArrayList<Categoria> listar() {

		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

		ArrayList<Categoria> categorias = new ArrayList<>();

		String query = "SELECT * FROM categoria";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Long idCategoria = rs.getLong("id_categoria");
				String nome = rs.getString("categoria");

				Categoria cat = new Categoria();
				cat.setCategoria(nome);
				cat.setIdCategoria(idCategoria);
				categorias.add(cat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.fecharConexao();
		}

		return categorias;

	}

	public boolean inserir(Categoria c) {

		// instancia a classe
		Conexao a = Conexao.getInstancia();

		// ABRE conexao com banco
		Connection con = a.conectar();

		String query = "INSERT INTO categoria( categoria) VALUES( ?); ";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, c.getCategoria());

			// consolida a execução do comando no banco
			ps.executeUpdate();

			// fecha a conexão com banco

			a.fecharConexao();
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false; // retorna falso caso falha na inserção
		}
		return true; // retorna true se insercao bem sucedida
	}

	public boolean excluir(Categoria ca) {
		if (ca.getIdCategoria() == null) {
			return false; // Não é possível excluir uma categoria com id nulo
		}

		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

		String query = "DELETE FROM categoria WHERE id_categoria = ?";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, ca.getIdCategoria());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				c.fecharConexao();
				return true; // se bem sucedida
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			c.fecharConexao();
		}
		return false; // se bem falha na exclusão
	}


	public boolean atualizar(Categoria ca) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();

		String query = "UPDATE categoria SET categoria = ? WHERE id_categoria = ?";

		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, ca.getCategoria());
			preparedStatement.setLong(2, ca.getIdCategoria());

			int rowsUpdated = preparedStatement.executeUpdate();

			return rowsUpdated > 0; // Retorna verdadeiro se a atualização foi bem-sucedida
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // Retorna falso em caso de falha na atualização
		} finally {
			c.fecharConexao(); // Garante que a conexão será fechada
		}
	}



}
