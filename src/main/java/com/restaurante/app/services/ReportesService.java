package com.restaurante.app.services;

import com.restaurante.app.dto.ReportePropinasDTO;
import com.restaurante.app.repository.iVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReportesService {
    @Autowired
    private iVentaRepository ventaRepository;

    public List<ReportePropinasDTO> obtenerReporte()
    {
        ArrayList<Object> results = null;
        results = ventaRepository.rpropinas();
        List<ReportePropinasDTO> reporte = new ArrayList<>();

        int i = 0;

        Iterator itr = results.iterator();
        while(itr.hasNext()) {
            Object[] arrObj = (Object[])itr.next();

            ReportePropinasDTO e = new ReportePropinasDTO();

            for(Object obj:arrObj) {
                switch (i){
                    case 0:
                        e.setIdUsuario((int)obj);
                        System.out.println("id :"+e.getIdUsuario());
                        break;
                    case 1:
                        e.setNombre((String) obj);
                        System.out.println("nombre: "+obj);
                        break;
                    case 2:
                        e.setApellido((String) obj);
                        System.out.println("apellido: "+obj);
                        break;
                    case 3:
                        e.setPropina((BigDecimal) obj);
                        System.out.println("Total propina: "+obj);
                        break;
                    case 4:
                        e.setFecha((Date) obj);
                        System.out.println("Fecha: "+obj);
                        break;
                }
                i++;
            }
            reporte.add(e);
            i=0;
        }
        return reporte;
    }
}
