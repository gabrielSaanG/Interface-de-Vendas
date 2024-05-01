/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import bd.BancoDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import pojo.Cliente;

public class ClientesDao {
    private final String SQL_INCLUIR = "INSERT INTO Cliente VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String SQL_ALTERAR = "UPDATE Cliente SET nome = ?, cpf = ?, rg = ?, logradouro = ?, numero = ?,  cidade = ?, bairro = ?, estado = ?, complemento = ? WHERE codigo = ?";
    private final String SQL_EXCLUIR = "DELETE FROM Cliente WHERE codigo = ?";
    public static final String SQL_CONSULTAR = "SELECT * FROM Cliente WHERE codigo = ?";
    public static final String SQL_PESQUISAR = "SELECT * FROM Cliente";
    private Cliente cliente;
   
    
    public ClientesDao(Cliente cliente){
        this.cliente = cliente;
    }
    
    public boolean inserir(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_INCLUIR);
            ps.setInt(1, cliente.getCodigo());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getCpf());
            ps.setString(4, cliente.getRg());
            ps.setString(5, cliente.getLogradouro());
            ps.setString(6, cliente.getNumero());
            ps.setString(7, cliente.getCidade());
            ps.setString(8, cliente.getBairro());
            ps.setString(9, cliente.getEstado());
            ps.setString(10, cliente.getComplemento());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível incluir o cliente.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean alterar(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_ALTERAR);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getRg());
            ps.setString(4, cliente.getLogradouro());
            ps.setString(5, cliente.getNumero());
            ps.setString(6, cliente.getCidade());
            ps.setString(7, cliente.getBairro());
            ps.setString(8, cliente.getEstado());
            ps.setString(9, cliente.getComplemento());
            ps.setInt(10, cliente.getCodigo());
            ps.executeUpdate();
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o cadastro do cliente.");
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean excluir(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_EXCLUIR);
            ps.setInt(1, cliente.getCodigo());
            ps.executeUpdate();
            return true;
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível excluir o cadastro do cliente.");
            return false;
        }
    }
    
    public boolean consultar(){
        try{
            PreparedStatement ps = BancoDados.getConexao().prepareStatement(SQL_CONSULTAR);
            ps.setInt(1, cliente.getCodigo());
            ResultSet rs = ps.executeQuery();
            if (rs == null){
                return false;
            }
            if (rs.next()){
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setRg(rs.getString("rg"));
                cliente.setLogradouro(rs.getString("logradouro"));
                cliente.setNumero(rs.getString("numero"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setEstado(rs.getString("estado"));
                cliente.setComplemento(rs.getString("complemento"));
            }
            return true;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Não foi possível consultar o cadastro do cliente.");
            return false;
        }
    }
    
    
}
