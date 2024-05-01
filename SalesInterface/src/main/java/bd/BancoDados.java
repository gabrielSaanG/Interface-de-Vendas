
package bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class BancoDados {
    private static Connection conexao;
    
    public static Connection getConexao(){
        try{
            if (conexao == null){
                Class.forName("org.firebirdsql.jdbc.FBDriver");
                conexao = DriverManager.getConnection("jdbc:firebirdsql://localhost/" + System.getProperty("user.dir") + "/BANCO.FDB", "sysdba", "masterkey");
            }
            return conexao;
        } catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "Nao foi possivel encontrar o driver de acesso" + "ao banco de dados.");
            return null;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possível conectar com o banco de dados." + e.getMessage());
            return null;
        }
    }
    
      
    public static List<String[]> executaQuery(String sql) throws SQLException{
        try{
            List<String[]> dados = new ArrayList();
            Statement st = getConexao().createStatement();
            ResultSet rs = st.executeQuery(sql);
            int numeroColunas = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String[] linha = new String[numeroColunas];
                for (int i = 1; i <= numeroColunas; i++){
                    linha[i - 1] = rs.getString(i);
                }
                dados.add(linha); 
            }
            return dados;
            } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Não foi possível consultar o banco de dados.");
                    return new ArrayList();
             }
        }
    
}
    