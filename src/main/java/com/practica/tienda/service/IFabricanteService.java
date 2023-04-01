package com.practica.tienda.service;

import com.practica.tienda.model.Fabricante;
import com.practica.tienda.response.FabricanteResponseRest;
import org.springframework.http.ResponseEntity;

public interface IFabricanteService {

    public ResponseEntity<FabricanteResponseRest> buscarFaabricantes();
    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorId(Long id);
    public ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante);
    public ResponseEntity<FabricanteResponseRest> actualizarFabricante(Long id, Fabricante fabricante);
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id);

}
