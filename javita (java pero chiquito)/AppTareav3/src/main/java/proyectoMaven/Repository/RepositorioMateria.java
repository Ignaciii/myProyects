package proyectoMaven.Repository;

import proyectoMaven.DAO.MateriaDao;
import proyectoMaven.Models.Alumno;
import proyectoMaven.Models.Materia;
import java.util.List;

public class RepositorioMateria {

    public RepositorioMateria(MateriaDao materiaDao) {
        this.materiaDao = materiaDao;
        this.materias = materiaDao.mostrarMateriasDAO();

    }

    private MateriaDao materiaDao;
    private List<Materia> materias;

    public Boolean agregarMateria(Materia materia) {
        if (materia != null && materiaDao.agregarMateriaBD(materia)) {
            materias.add(materia);
            return true;
        }
        return false;

    }

    public Boolean eliminarMateria(int codigoMateria) {
        if (materiaDao.cambiarEstadoMateriaDAO(codigoMateria)) {
            materias.removeIf(c -> c.getCodigoMateria() == codigoMateria);
            return true;
        }
        return false;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public String mostrarMaterias() {
        if (!materias.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Materia m : materias) {
                sb.append(m.toString()).append("\n");
            }
            return sb.toString();
        }
        return "No hay materias cargadas aun!!!";
    }

    public Boolean actualizarMateria(Materia materia) {
        return materiaDao.actualizarMateria(materia);
    }
}
