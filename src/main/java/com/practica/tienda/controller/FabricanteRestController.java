package com.practica.tienda.controller;

import com.practica.tienda.model.Fabricante;
import com.practica.tienda.response.FabricanteResponseRest;
import com.practica.tienda.service.IFabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FabricanteRestController {

    @Autowired
    private IFabricanteService fabricanteService;

    @GetMapping("/fabricantes")
    public ResponseEntity<FabricanteResponseRest> consultarFabricantes(){
        return fabricanteService.buscarFaabricantes();
    }

    @GetMapping("/fabricantes/{id}")
    public  ResponseEntity<FabricanteResponseRest> consultarFabricantePorId(@PathVariable Long id){
        return fabricanteService.buscarFabricantePorId(id);
    }

    @PostMapping("/fabricantes")
    public ResponseEntity<FabricanteResponseRest> crearFabricante(@RequestBody Fabricante request){
        return fabricanteService.crearFabricante(request);
    }

    @PutMapping("/fabricantes/{id}")
    public  ResponseEntity<FabricanteResponseRest> actualizarFabricante(@PathVariable Long id, @RequestBody Fabricante request){
        return fabricanteService.actualizarFabricante(id, request);
    }

    @DeleteMapping("/fabricantes/{id}")
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(@PathVariable Long id){
        return fabricanteService.eliminarFabricante(id);
    }
}
