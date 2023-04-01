package com.practica.tienda.service;


import com.practica.tienda.model.Fabricante;
import com.practica.tienda.model.dao.IFabricanteDao;
import com.practica.tienda.response.FabricanteResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class FabricanteServiceImpl implements IFabricanteService{

    private static final Logger log = Logger.getLogger(FabricanteServiceImpl.class.getName());

    @Autowired
    private IFabricanteDao fabricanteDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarFaabricantes() {
        log.info("Inicio buscar fabricantes");
        FabricanteResponseRest response = new FabricanteResponseRest();
        try {
            List<Fabricante>listFabricantes = (List<Fabricante>) fabricanteDao.findAll();
            response.getFabricanteResponse().setFabricantes(listFabricantes);
            response.setMetadata("OK", "200", "Lista fabricantes");
        } catch (Exception e) {
            log.info("Error al buscar fabricantes" + e.getMessage());
            e.getStackTrace();
            response.setMetadata("ERROR", "-1", "Error al buscar fabricantes");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<FabricanteResponseRest> buscarFabricantePorId(Long id){
        log.info("Inicio buscar fabricante por id");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();

        try {
            Optional<Fabricante> fabricante = fabricanteDao.findById(id);
            if (fabricante.isPresent()) {
                list.add(fabricante.get());
                response.getFabricanteResponse().setFabricantes(list);
                response.setMetadata("OK", "200", "Fabricante encontrado");
            } else {
                response.setMetadata("OK", "200", "Fabricante no encontrado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            log.info("Error al buscar fabricante por id" + e.getMessage());
            e.getStackTrace();
            response.setMetadata("ERROR", "-1", "Error al buscar fabricante por id");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<FabricanteResponseRest> crearFabricante(Fabricante fabricante) {
        log.info("Inicio crear fabricante");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();

        try {
            Fabricante fabricante1 = fabricanteDao.save(fabricante);

            if (fabricante1 != null) {
                list.add(fabricante1);
                response.getFabricanteResponse().setFabricantes(list);
                response.setMetadata("OK", "200", "Fabricante creado");
            } else {
                response.setMetadata("OK", "200", "Fabricante no creado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            log.info("Error al crear fabricante" + e.getMessage());
            e.getStackTrace();
            response.setMetadata("ERROR", "-1", "Error al crear fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public  ResponseEntity<FabricanteResponseRest> actualizarFabricante(Long id, Fabricante fabricante){
        log.info("Inicio actualizar fabricante");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();

        try{
            Optional<Fabricante> fabricanteEncontrado = fabricanteDao.findById(id);

            if (fabricanteEncontrado.isPresent()) {
                fabricanteEncontrado.get().setNombre(fabricante.getNombre());
                Fabricante fabricateActualizado = fabricanteDao.save(fabricanteEncontrado.get());

                if (fabricateActualizado != null) {
                    list.add(fabricateActualizado);
                    response.getFabricanteResponse().setFabricantes(list);
                    response.setMetadata("OK", "200", "Fabricante actualizado");
                } else {
                    response.setMetadata("OK", "200", "Fabricante no actualizado");
                    return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
                }
            } else {
                response.setMetadata("OK", "200", "Fabricante no actualizado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            log.info("Error al actualizar fabricante" + e.getMessage());
            e.getStackTrace();
            response.setMetadata("ERROR", "-1", "Error al actualizar fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<FabricanteResponseRest> eliminarFabricante(Long id){
        log.info("Inicio eliminar fabricante");
        FabricanteResponseRest response = new FabricanteResponseRest();
        List<Fabricante> list = new ArrayList<>();

        try{
            Optional<Fabricante> fabricanteEncontrado = fabricanteDao.findById(id);

            if (fabricanteEncontrado.isPresent()) {
                fabricanteDao.delete(fabricanteEncontrado.get());
                list.add(fabricanteEncontrado.get());
                response.getFabricanteResponse().setFabricantes(list);
                response.setMetadata("OK", "200", "Fabricante eliminado");
            } else {
                response.setMetadata("OK", "-1", "Fabricante no eliminado");
                return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.info("Error al eliminar fabricante" + e.getMessage());
            e.getStackTrace();
            response.setMetadata("ERROR", "-1", "Error al eliminar fabricante");
            return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<FabricanteResponseRest>(response, HttpStatus.OK);

    }

}
