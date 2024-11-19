package gerenciamentoestudantil.view;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class MenuAluno {
    private static final String ARQUIVO = "alunos.txt";

    public static void main(String[] args) {
        mostrarMenuAluno();
    }

    public static void mostrarMenuAluno() {
        JFrame frame = new JFrame("Menu Aluno");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Menu Aluno", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titulo);

        JButton btnCadastrar = new JButton("Cadastrar Aluno");
        JButton btnConsultar = new JButton("Consultar Aluno");
        JButton btnSair = new JButton("Sair");

        panel.add(btnCadastrar);
        panel.add(btnConsultar);
        panel.add(btnSair);

        btnCadastrar.addActionListener(e -> cadastrarAluno(frame));
        btnConsultar.addActionListener(e -> consultarAluno(frame));
        btnSair.addActionListener(e -> frame.dispose());

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void cadastrarAluno(JFrame parent) {
        JTextField nomeField = new JTextField();
        JTextField idadeField = new JTextField();
        JTextField matriculaField = new JTextField();

        Object[] campos = {
                "Nome:", nomeField,
                "Idade:", idadeField,
                "Matrícula:", matriculaField
        };

        int option = JOptionPane.showConfirmDialog(parent, campos, "Cadastrar Aluno", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText().trim();
                int idade = Integer.parseInt(idadeField.getText().trim());
                String matricula = matriculaField.getText().trim();

                if (nome.isEmpty() || matricula.isEmpty()) {
                    JOptionPane.showMessageDialog(parent, "Nome e Matrícula são obrigatórios!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Gravar no arquivo
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
                    writer.write(matricula + ";" + nome + ";" + idade);
                    writer.newLine();
                }
                JOptionPane.showMessageDialog(parent, "Aluno cadastrado com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parent, "Idade inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(parent, "Erro ao gravar os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void consultarAluno(JFrame parent) {
        String busca = JOptionPane.showInputDialog(parent, "Digite o Nome ou Matrícula do aluno:");
        if (busca == null || busca.trim().isEmpty()) {
            return;
        }

        ArrayList<String[]> alunos = new ArrayList<>();
        boolean encontrado = false;
        String[] alunoEncontrado = null;

        // Ler o arquivo
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados[0].equalsIgnoreCase(busca) || dados[1].equalsIgnoreCase(busca)) {
                    encontrado = true;
                    alunoEncontrado = dados;
                } else {
                    alunos.add(dados);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent, "Erro ao ler o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(parent, "Aluno não encontrado.");
            return;
        }

        // Mostrar opções de edição ou exclusão
        String[] opcoes = {"Editar", "Excluir", "Cancelar"};
        int escolha = JOptionPane.showOptionDialog(parent, "Aluno encontrado:\n" +
                        "Matrícula: " + alunoEncontrado[0] + "\n" +
                        "Nome: " + alunoEncontrado[1] + "\n" +
                        "Idade: " + alunoEncontrado[2],
                "Opções", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoes, opcoes[2]);

        if (escolha == 0) { // Editar
            JTextField nomeField = new JTextField(alunoEncontrado[1]);
            JTextField idadeField = new JTextField(alunoEncontrado[2]);

            Object[] campos = {
                    "Nome:", nomeField,
                    "Idade:", idadeField
            };

            int option = JOptionPane.showConfirmDialog(parent, campos, "Editar Aluno", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                alunoEncontrado[1] = nomeField.getText().trim();
                alunoEncontrado[2] = idadeField.getText().trim();
                alunos.add(alunoEncontrado);
                gravarArquivo(alunos);
                JOptionPane.showMessageDialog(parent, "Aluno atualizado com sucesso!");
            }
        } else if (escolha == 1) { // Excluir
            gravarArquivo(alunos);
            JOptionPane.showMessageDialog(parent, "Aluno excluído com sucesso!");
        }
    }

    public static void gravarArquivo(ArrayList<String[]> alunos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO))) {
            for (String[] aluno : alunos) {
                writer.write(String.join(";", aluno));
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar os dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

