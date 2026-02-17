package proyectoMaven.Repository;

import proyectoMaven.DAO.ProfesorDao;
import proyectoMaven.Models.Alumno;
import proyectoMaven.Models.Profesor;
import java.util.List;

public class RepositorioProfesor {
    public RepositorioProfesor(ProfesorDao profesorDao) {
        this.profesorDao = profesorDao;
        this.profesores = profesorDao.mostrarProfesoresDao();
    }

    private ProfesorDao profesorDao;
    private List<Profesor> profesores;

    public Boolean agregarProfesor(Profesor profesor) {
        if (profesor != null && profesorDao.agregarProfesorDao(profesor)) {
            profesores.add(profesor);
            return true;
        }
        return false;
    }

    public Boolean borrarProfesor(int numeroMatricula) {
        if (profesorDao.cambiarEstadoProfesorDao(numeroMatricula)) {
            profesores.removeIf(p -> p.getNumeroMatricula() == numeroMatricula);
            return true;
        }
        return false;
    }

    public List<Profesor> mostraProfesores() {
        return profesores;
    }

    public Boolean actualizarProfesor(Profesor profesor) {
        return profesorDao.actualizarProfesor(profesor);
    }
}
