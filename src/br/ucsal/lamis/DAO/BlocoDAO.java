package br.ucsal.lamis.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.lamis.model.Bloco;
import br.ucsal.lamis.util.BancoUtil;

public class BlocoDAO {
	public static List<Bloco> getBlocos() {
		List<Bloco> blocos = new ArrayList<Bloco>();
		Connection con = BancoUtil.getConnection(); 
		try { 
			 String sql="select * from blocos;";
			 PreparedStatement pstmt= con.prepareStatement(sql);
			 ResultSet resultSet=pstmt.executeQuery();
			 
			 while(resultSet.next()){ 
				 Bloco bloco= new Bloco();
				 bloco.setId(resultSet.getInt("bloco_id"));
				 bloco.setNome(resultSet.getString("nome"));
				 blocos.add(bloco);
			 } 
			 resultSet.close(); 
			 pstmt.close(); 
		 } catch (SQLException e) { 
			 e.printStackTrace(); 
		 } 
		return blocos;
	}
	
	public Bloco obterBloco(Integer id) {
		Bloco bloco = null;
		
		return bloco;
	}
}
