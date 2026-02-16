package proyectoMaven.Repository;

import java.util.List;

import proyectoMaven.DAO.AlumnoDao;
import proyectoMaven.Models.Alumno;

public class RepositorioAlumno {
    public RepositorioAlumno(List<Alumno> alumnos, AlumnoDao alumnoDao) {
        this.alumnos = alumnos;
        this.alumnoDAO = alumnoDao;
    }

    public RepositorioAlumno(AlumnoDao alumnoDAO) {
        this.alumnos = alumnoDAO.mostrarAlumnosDAO();
        this.alumnoDAO = alumnoDAO;
    }

    private List<Alumno> alumnos;
    private AlumnoDao alumnoDAO;

    public Boolean agregarAlumno(Alumno alumno) {
        if (alumno != null && alumnoDAO.agregarAlumnoBD(alumno)) {
            alumnos.add(alumno);
            return true;

        }
        return false;

    }

    public List<Alumno> getListadoAlumnos() {
        return alumnos;
    }

    // DONE
    public Boolean repetido(int legajo) {
        return this.alumnos.stream().anyMatch(s -> s.getLegajo() == (legajo));
    }

    public Boolean borrarAlumno(int legajo) {
        if (alumnoDAO.cambiarEstadoAlumnoDAO(legajo)) {
            alumnos.removeIf(a -> a.getLegajo() == legajo);
            return true;

        }
        return false;

    }

}