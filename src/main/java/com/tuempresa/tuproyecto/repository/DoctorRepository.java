public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findByEspecialidad(String especialidad);
}