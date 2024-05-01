
package dao;

import bd.BancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Produto;

public class ProdutosDao {
    private final String SQL_INCLUIR = "INSERT INTO Produto VALUES (?, ?, ?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE Produto SET nome = ?, quantidade = ?, precoCompra = ?, precoVenda = ? WHERE codigo = ?";
    private final String SQL_EXCLUIR = "DELETE FROM Produto WHERE codigo = ?";
    public static final String SQL_CONSULTAR = "SELECT * FROM Produto WHERE codigo = ?";
    public static final String SQL_PESQUISAR = "SELECT * FROM Produto";
    private Produto produto;
    public ProdutosDao(Produto produto){
        this.produto = produto;
    }
    
    public boolean inserir(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, produto.getCodigo());
            ps.setString(2, produto.getNome());
            ps.setInt(3, produto.getQuantidade());
            ps.setDouble(4, produto.getPrecoCompra());
            ps.setDouble(5, produto.getPrecoVenda());
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível incluir o produto.");
            return false;
        }
    }
    
    public boolean alterar(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_ALTERAR);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getQuantidade());
            ps.setDouble(3, produto.getPrecoCompra());
            ps.setDouble(4, produto.getPrecoVenda());
            ps.setInt(5, produto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o produto.");
            return false;
        }
    }
    
    public boolean excluir(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, produto.getCodigo());
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível excluir o produto.");
            return false;
        }
    }
    
    public boolean consultar(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, produto.getCodigo());
            ResultSet rs = ps.executeQuery();
            if (rs == null){
                return false;
            }
            if (rs.next()){
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPrecoCompra(rs.getDouble("precoCompra"));
                produto.setPrecoVenda(rs.getDouble("precoVenda"));
                return true;
            } else{
                JOptionPane.showMessageDialog(null, "Produto não encontrado (" + produto.getCodigo() + ")") ;
                return false;
            }
        } catch( Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível consultar o produto.");
            return false;
        }
    } 
}