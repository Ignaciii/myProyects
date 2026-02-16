package proyectoMaven.Repository;

import java.util.Iterator;
import java.util.List;

import proyectoMaven.DAO.AlumnoDao;
import proyectoMaven.Models.Alumno;

public class Repositorio implements Iterable<Alumno> {
    public Repositorio(List<Alumno> alumnos, AlumnoDao alumnoDao) {
        this.alumnos = alumnos;
        this.alumnoDAO = alumnoDao;
    }

    public Repositorio(AlumnoDao alumnoDAO) {
        this.alumnos = alumnoDAO.mostrarAlumnosDAO();
        this.alumnoDAO = alumnoDAO;
    }

    private List<Alumno> alumnos;
    private AlumnoDao alumnoDAO;

    public void agregarAlumno(Alumno alumno) {
        if (alumnoDAO.agregarAlumnoBD(alumno))
            alumnos.add(alumno);
        else {
            System.out.println("No se borro de la base de datos y tampoco de memoria!!!");
        }

    }

    public Iterator<Alumno> iterator() {
        return alumnos.iterator();
    }

    public List<Alumno> getListadoAlumnos() {
        alumnos = alumnoDAO.mostrarAlumnosDAO();
        return alumnos.stream().toList();
    }

    // DONE
    public Boolean repetido(int legajo) {
        return this.alumnos.stream().anyMatch(s -> s.getLegajo() == (legajo));
    }

    public void borrarAlumno(int legajo) {
        if (alumnoDAO.deleteAlumnoDAO(legajo)) {
            alumnos.removeIf(a -> a.getLegajo() == legajo);

        }

    }

}