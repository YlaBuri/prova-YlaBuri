package br.ucsal.lamis.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ucsal.lamis.model.Usuario;
import br.ucsal.lamis.util.BancoUtil;

public class UsuarioDAO {
	
	public static boolean autenticar(Usuario usuario) {
		boolean retorno = false;
		Connection con = BancoUtil.getConnection(); 
		try { 
			 String sql="select * from usuarios where login=? and senha=?;";
			 PreparedStatement pstmt= con.prepareStatement(sql);
			 pstmt.setString(1, usuario.getLogin());
			 pstmt.setString(2, usuario.getSenha());
			 ResultSet resultSet=pstmt.executeQuery();
			 
			 if(resultSet.next()){ 
				 retorno = true; 
			 } 
			 resultSet.close(); 
			 pstmt.close(); 
		 } catch (SQLException e) { 
			 e.printStackTrace(); 
		 } 
		
		return retorno;
	}
	
	
}

