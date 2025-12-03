/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package org.uv.TCSWPractica05;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author sinoe
 */
@RestController
@RequestMapping("/empleados")
public class ControllerEmpleados {
    
    @Autowired
    private RepositoryEmpleados repositoryEmpleados;
    
    @GetMapping()
    public List<Empleados> list() {
        return repositoryEmpleados.findAll();
    }
    
    @GetMapping("/{id}")
    public Empleados get(@PathVariable Long id) {
        Optional<Empleados> optionalEmpleado=repositoryEmpleados.findById(id);
        if (!optionalEmpleado.isEmpty())
            return optionalEmpleado.get();
        else
            return null;
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Empleados> put(@PathVariable Long id, @RequestBody Empleados emp) {
        return repositoryEmpleados.findById(id)
                .map(existing -> {
                    existing.setNombre(emp.getNombre());
                    existing.setDireccion(emp.getDireccion());
                    existing.setTelefono(emp.getTelefono());
                    repositoryEmpleados.save(existing);
                    return ResponseEntity.ok(existing);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Empleados> post(@RequestBody Empleados emp) {
        Empleados empNew=repositoryEmpleados.save(emp);
        return ResponseEntity.ok(emp);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (repositoryEmpleados.existsById(id)) {
            repositoryEmpleados.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
