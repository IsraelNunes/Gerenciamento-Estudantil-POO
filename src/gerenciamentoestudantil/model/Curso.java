package gerenciamentoestudantil.model;
import java.util.ArrayList;
import java.util.List;

public class Curso {
    private String nomeCurso;
    private int cargaHoraria;
    private Professor professor;
    private List<Estudante> estudantes;

    public Curso(String nomeCurso, int cargaHoraria){
        this.nomeCurso = nomeCurso;
        this.cargaHoraria = cargaHoraria;
        this.estudantes = new ArrayList<>();
    }

    // Getters e Setters
    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void adicionarEstudante(Estudante estudante) {
        estudantes.add(estudante);
    }
}
