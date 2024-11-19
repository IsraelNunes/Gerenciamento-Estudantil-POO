package gerenciamentoestudantil.view;

import javax.swing.*;
import java.awt.*;

public class TelaInicial {
    public static void main(String[] args) {
        mostrarMenuPrincipal();
    }

    public static void mostrarMenuPrincipal() {
        JFrame frame = new JFrame("Tela Inicial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Menu Principal", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titulo);

        JButton btnAluno = new JButton("Gerenciar Alunos");
        JButton btnProfessor = new JButton("Gerenciar Professores");
        JButton btnCurso = new JButton("Gerenciar Cursos");
        JButton btnSair = new JButton("Sair do Programa");

        panel.add(btnAluno);
        panel.add(btnProfessor);
        panel.add(btnCurso);
        panel.add(btnSair);

        // Adicionar ações aos botões
        btnAluno.addActionListener(e -> {
            frame.dispose(); // Fecha o menu principal
            MenuAluno.mostrarMenuAluno(); // Abre o Menu Aluno
        });

        btnProfessor.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Gerenciamento de Professores ainda não implementado."));
        btnCurso.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Gerenciamento de Cursos ainda não implementado."));
        btnSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0); // Encerra o programa
            }
        });

        frame.add(panel);
        frame.setVisible(true);
    }
}
