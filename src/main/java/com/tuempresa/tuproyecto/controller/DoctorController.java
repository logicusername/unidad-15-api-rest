package com.tuempresa.tuproyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tuempresa.tuproyecto.model.Doctor;
import com.tuempresa.tuproyecto.repository.DoctorRepository;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> obtenerDoctores() {
        return doctorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Doctor obtenerDoctorPorId(@PathVariable Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Doctor crearDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @PutMapping("/{id}")
    public Doctor actualizarDoctor(@PathVariable Long id, @RequestBody Doctor doctorActualizado) {
        Doctor doctorExistente = doctorRepository.findById(id).orElse(null);
        if (doctorExistente != null) {
            // Actualizamos el doctor existente con la información del doctor actualizado
            doctorExistente.setNombre(doctorActualizado.getNombre());
            doctorExistente.setEspecialidad(doctorActualizado.getEspecialidad());
            return doctorRepository.save(doctorExistente);
        } else {
            return null; // Manejo de error: doctor no encontrado
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarDoctor(@PathVariable Long id) {
        doctorRepository.deleteById(id);
    }
}