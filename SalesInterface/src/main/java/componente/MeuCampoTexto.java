
package componente;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class MeuCampoTexto extends JTextField implements FocusListener, MeuComponente{
    private String dica;
    private boolean obrigatorio;
    
    public MeuCampoTexto(int colunas, String dica, boolean obrigatorio){
        this.dica = dica;
        this.obrigatorio = obrigatorio;
        setColumns(colunas);
        addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void focusLost(FocusEvent e) {
        setBackground(Color.WHITE);
    }

    @Override
    public boolean eVazio() {
        return getText().trim().isEmpty();
    }

    @Override
    public void limpar() {
        setText("");
    }

    @Override
    public void habilitar(boolean status) {
        setEnabled(status);
    }
    
    public String getDica(){
        return dica;
    }

    
    @Override
    public boolean eObrigatorio() {
        return obrigatorio;    
    }
}
