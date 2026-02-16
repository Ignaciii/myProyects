package proyectoMaven.Repository;

import java.util.List;
import proyectoMaven.Models.Curso;
import proyectoMaven.DAO.CursoDao;

public class RepositorioCurso {
    public RepositorioCurso() {
    }

    public RepositorioCurso(CursoDao cursoDao) {
        this.cursos = cursoDao.mostrarCursosDAO();
        this.cursoDao = cursoDao;
    }

    private List<Curso> cursos;
    private CursoDao cursoDao;

    public Boolean agregarCurso(Curso curso) {
        if (curso != null && cursoDao.agregarCursoBD(curso)) {
            cursos.add(curso);
            return true;
        }
        return false;
    }

    public Boolean borrarCurso(int id) {
        if (cursoDao.cambiarEstadoCursoDAO(id)) {
            cursos.removeIf(c -> c.getId() == id);
            return true;
        }
        return false;
    }

    public List<Curso> getListadoCursos() {
        return cursos;
    }
}
