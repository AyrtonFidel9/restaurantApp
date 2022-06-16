package com.restaurante.app.services;

import com.restaurante.app.dto.UsuarioDTO;
import com.restaurante.app.dto.VentaDTO;
import com.restaurante.app.entity.Venta;
import com.restaurante.app.mapper.iVentaMapper;
import com.restaurante.app.repository.iPedidoRepository;
import com.restaurante.app.repository.iRestauranteRepository;
import com.restaurante.app.repository.iUsuarioRepository;
import com.restaurante.app.repository.iVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements iVentaService {
    @Autowired
    private iVentaRepository ventaRepository;
    @Autowired
    private iUsuarioRepository usuarioRepository;
    @Autowired
    private iPedidoRepository pedidoRepository;
    @Autowired
    private iRestauranteRepository restauranteRepository;
    @Autowired
    private iVentaMapper mapper;

    @Override
    public VentaDTO ingresarVenta(VentaDTO ventaDTO)
    {
        return null;
    }

    @Override
    public VentaDTO buscarVenta(int idVenta){
        return null;
    }

    @Override
    public VentaDTO actualizarVenta(int idVenta, VentaDTO ventaDTO)
    {
        return null;
    }

    @Override
    public void eliminarVenta(int idVenta)
    {
        ventaRepository.deleteById(idVenta);
    }

    @Override
    public List<VentaDTO> obtenerVentas(){
        return null;
    }



}
