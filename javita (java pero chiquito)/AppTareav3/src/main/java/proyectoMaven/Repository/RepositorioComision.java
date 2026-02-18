package proyectoMaven.Repository;

import java.util.List;
import proyectoMaven.Models.Comision;
import proyectoMaven.DAO.ComisionDao;

public class RepositorioComision {
    public RepositorioComision() {
    }

    public RepositorioComision(ComisionDao comisionDao) {
        this.comisiones = comisionDao.mostrarComisionesDao();
        this.comisionDao = comisionDao;
    }

    private List<Comision> comisiones;
    private ComisionDao comisionDao;

    public Boolean agregarComision(Comision comision) {
        if (comision != null && comisionDao.agregarComisionDao(comision)) {
            comisiones.add(comision);
            return true;
        }
        return false;
    }

    public Boolean borrarComision(int id) {
        if (comisionDao.cambiarEstadoComisionDao(id)) {
            if (comisiones.removeIf(c -> c.getId() == id))
                return true;
            return false;
        }
        return false;
    }

    public List<Comision> getListadoComisiones() {
        return comisiones;
    }

    public String mostrarListadoComisiones() {
        StringBuilder sb = new StringBuilder();
        if (!comisiones.isEmpty()) {
            for (Comision c : comisiones) {
                sb.append(c.toString()).append("\n");
            }
            return sb.toString();
        }
        return "No hay comisiones cargadas aun!!!";
    }

}
