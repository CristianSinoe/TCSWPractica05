/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package org.uv.TCSWPractica05;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sinoe
 */
public interface RepositoryEmpleados extends JpaRepository<Empleados, Long> {
    
}
