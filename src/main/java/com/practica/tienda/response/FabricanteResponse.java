package com.practica.tienda.response;

import com.practica.tienda.model.Fabricante;

import java.util.List;

public class FabricanteResponse {

    private List<Fabricante> fabricantes;

    public List<Fabricante> getFabricantes() {
        return fabricantes;
    }

    public void setFabricantes(List<Fabricante> fabricantes) {
        this.fabricantes = fabricantes;
    }
}
