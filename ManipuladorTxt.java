import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;

class editor extends JFrame implements ActionListener {
    //Area de texto
    JTextArea t;
    // componente do java Swing
    JFrame f;
    // Construindo o editor
    editor()
    {
        // Crio o Jframe
        f = new JFrame("Editor de Texto");

        try{
        // Defino a apencia do meu menur
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        // Escolho um tema
        MetalLookAndFeel.setCurrentTheme(new OceanTheme());
    }
        catch (Exception ignored) {
    }

    // Atribuindo a variavel de texto
    t = new JTextArea();

    // Crio uma barra de menu
    JMenuBar mb = new JMenuBar();

    // Crio uma barra de ferramentas
    JMenu m1 = new JMenu("Arquivos");

    // Opções da barra de ferramenta
    JMenuItem mi1 = new JMenuItem("Novo");
    JMenuItem mi2 = new JMenuItem("Exibir");
    JMenuItem mi3 = new JMenuItem("Salvar");
    JMenuItem mi9 = new JMenuItem("Deletar");

    // Adiciono as ações para as barras
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

    //Barra de ferramentas extra oara fechar
    JMenuItem mc = new JMenuItem("Fechar");
        mc.addActionListener(this);
        mb.add(m1);
        mb.add(mc);

    //Aqui configuro o tamanho da janela aberta
        f.setJMenuBar(mb);
        f.add(t);
        f.setSize(500, 500);
        f.show();
}

    // As ações de configuração para cada botão da barra de ferramentas
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();

        if (s.equals("Exibir")) {
            t.getText();
        }
        else if (s.equals("Salvar")) {
            // Cria o objeto da classe JFileChooser
            JFileChooser j = new JFileChooser("d:");

            // Chamo a função showsSaveDialog para mostrar a janela para salvar
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {

                // Defino o caminho selecionado para salvar
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // Crio o arquivo para escrita
                    FileWriter wr = new FileWriter(fi, false);

                    // crio um buffered para escrita
                    BufferedWriter w = new BufferedWriter(wr);

                    // Escrevo
                    w.write(t.getText());

                    //recarrego e fecho
                    w.flush();
                    w.close();
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // Se a operação for cancelada é aberto o alerta com a mensagem
            else
                JOptionPane.showMessageDialog(f, "O usuario cancelou a operação");
        }
        else if (s.equals("Novo")) {
            // Cria o objeto da classe JFileChooser novamente
            JFileChooser j = new JFileChooser("f:");

            // Chamo a função showsSaveDialog para mostrar a janela para selecionar arquivo
            int r = j.showOpenDialog(null);

            // Se o usuario selecionar o arquivo
            if (r == JFileChooser.APPROVE_OPTION) {
                // Defino o caminho selecionado para abertura do arquivo
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    // String
                    String s1 = "", sl = "";

                    // Arquivo de leitura
                    FileReader fr = new FileReader(fi);

                    BufferedReader br = new BufferedReader(fr);

                    // Usando o sl
                    sl = br.readLine();

                    // Obtenho o que entrou no arquivo
                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }
                    // Defino o texto
                    t.setText(sl);
                }
                catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            }
            // Se a operação for cancelada é aberto o alerta com a mensagem novamente
            else
                JOptionPane.showMessageDialog(f, "O usuario cancelou a operação");
        }
        // Aqui defino a função para Deletar o texto
        else if (s.equals("Deletar")) {
            t.setText("");
        }
        // A função da barra de fechar
        else if (s.equals("Fechar")) {
            f.setVisible(false);
        }
    }
    // A classe principal "Main" definida dentro do arquivo em vez de ser salva fora.
    public static void main(String args[])
    {
        editor e = new editor();
    }
}
