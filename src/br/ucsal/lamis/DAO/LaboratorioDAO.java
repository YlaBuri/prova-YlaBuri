package br.ucsal.lamis.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ucsal.lamis.model.Bloco;
import br.ucsal.lamis.model.Laboratorio;
import br.ucsal.lamis.util.BancoUtil;

public class LaboratorioDAO {
	public static List<Laboratorio> listarLaboratorios() {
		List<Laboratorio> laboratorios = new ArrayList<Laboratorio>();
		Connection con = BancoUtil.getConnection(); 
		try { 
			 String sql="select * from laboratorios;";
			 PreparedStatement pstmt= con.prepareStatement(sql);
			 ResultSet resultSet=pstmt.executeQuery();
			 
			 while(resultSet.next()){ 
				 Laboratorio lami= new Laboratorio();
				 lami.setId(resultSet.getInt("laboratorio_id"));
				 lami.setNome(resultSet.getString("nome"));
				 lami.setDescricao(resultSet.getString("descricao"));
				 lami.setBloco(BlocoDAO.obterBloco(resultSet.getInt("bloco_id")));
				 laboratorios.add(lami);
			 } 
			 resultSet.close(); 
			 pstmt.close(); 
		 } catch (SQLException e) { 
			 e.printStackTrace(); 
		 } 
		return laboratorios;
	}
	
	public Laboratorio obterLaboratorio(Integer id) {
		return null;
	}

	public static void salvarLaboratorio(Laboratorio laboratorio) {
		Connection con = BancoUtil.getConnection(); 
		try { 
			 String sql="INSERT INTO  LABORATORIOS (nome,descricao,bloco_id) VALUES (?, ?, ?);";
			 PreparedStatement pstmt= con.prepareStatement(sql);
			 pstmt.setString(1, laboratorio.getNome());
			 pstmt.setString(2, laboratorio.getDescricao());
			 pstmt.setInt(3, laboratorio.getBloco().getId());
			 pstmt.execute();
			 pstmt.close(); 
		 } catch (SQLException e) { 
			 e.printStackTrace(); 
		 } 
	}
	
	public static void alterarLaboratorio(Laboratorio laboratorio) {
		Connection con = BancoUtil.getConnection(); 
		try { 
			 String sql="UPDATE  LABORATORIOS SET nome=?,descricao=?,bloco_id=? WHERE LABORATORIO_ID=?;";
			 PreparedStatement pstmt= con.prepareStatement(sql);
			 pstmt.setString(1, laboratorio.getNome());
			 pstmt.setString(2, laboratorio.getDescricao());
			 pstmt.setInt(3, laboratorio.getBloco().getId());
			 pstmt.setInt(4, laboratorio.getId());
			 pstmt.executeUpdate();
			 pstmt.close(); 
		 } catch (SQLException e) { 
			 e.printStackTrace(); 
		 } 
	}
	

	public void inserirLaboratorio(Laboratorio laboratorio) {
		
	}

	public static void removerLaboratorio(Integer id) {
		Connection con = BancoUtil.getConnection();
		try { 
			 String sql="DELETE FROM  LABORATORIOS WHERE LABORATORIO_ID=?;";
			 PreparedStatement pstmt= con.prepareStatement(sql);
			 pstmt.setInt(1, id);
			 pstmt.executeUpdate();
			 pstmt.close(); 
		 } catch (SQLException e) { 
			 e.printStackTrace(); 
		 } 
	}
}
