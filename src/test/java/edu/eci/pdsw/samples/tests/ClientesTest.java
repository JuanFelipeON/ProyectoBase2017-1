/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerItemsStub;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *
 */
public class ClientesTest {

    public ClientesTest() {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void additems1() throws ExcepcionServiciosAlquiler {

    }

    /**
     * @obj registrar un Cliente en el sistema
     * @param p el nuevo Cliente
     * @pre p!=null
     * @pos el cliente queda disponible para futuros alquileres
     * @throws ExcepcionServiciosAlquiler si el cliente ya se encuentra
     * registrado
     *
     * Pruebas del metodo de registrarCliente(Cliente p)
     *
     * Clase de Equivalencia 1: Agregar un cliente sin informacion, Tipo: Normal
     * Resultado: fallo CLase de Equivalencia 2: Agregar un cliente con la
     * minima informacion, Tipo: Normal Resultado: correcto CLase de
     * Equivalencia 3: Agregar un cliente vetado, Tipo: Normal Resultado: fallo
     * Clase de Equivalencia 4: Agregar un cliente no vetado y con una lista de
     * rentas, Tipo: Normal Resultado: correcto
     */
    @Test
    public void testCE2() throws ExcepcionServiciosAlquiler {
        ServiciosAlquiler sa = ServiciosAlquilerItemsStub.getInstance();

        Cliente c = new Cliente("Juan Ortiz", 2092815, "3178255450", "calle 134 Alcala", "onjfpeli@gmail.com");
        sa.registrarCliente(c);

        assertEquals("Agregar un cliente con la minima informacion no esta funcionando", c, sa.consultarCliente(2092815));

    }

    @Test
    public void testCE3() {
        ServiciosAlquiler sa = ServiciosAlquilerItemsStub.getInstance();

        TipoItem ti1 = new TipoItem(1, "Video");
        TipoItem ti2 = new TipoItem(2, "Juego");
        TipoItem ti3 = new TipoItem(3, "Musica");

        Map<Integer, TipoItem> tipositems = new HashMap<>();

        tipositems.put(1, ti1);
        tipositems.put(2, ti2);
        tipositems.put(3, ti3);

        Item i1 = new Item(ti1, 1, "Rango", "Rango gano un oscar a mejor animacion.", java.sql.Date.valueOf("2005-06-08"), 2000, "DVD", "Animada");
        Item i2 = new Item(ti2, 2, "Halo 5", "Halo 3 es un videojuego de disparos en primera persona desarrollado por 346 industries.", java.sql.Date.valueOf("2007-09-08"), 3000, "DVD", "Shooter");
        Item i3 = new Item(ti3, 3, "El tamborilero", "Es el mejor viilancico", java.sql.Date.valueOf("1984-01-11"), 2000, "DVD", "Navidad");

        Map<Integer, Item> items = new HashMap<>();

        items.put(1, i1);
        items.put(2, i2);
        items.put(3, i3);

        ItemRentado ir1 = new ItemRentado(i1, java.sql.Date.valueOf("2017-01-01"), java.sql.Date.valueOf("2017-03-12"));
        ItemRentado ir2 = new ItemRentado(i3, java.sql.Date.valueOf("2017-01-04"), java.sql.Date.valueOf("2017-04-7"));
        ItemRentado ir3 = new ItemRentado(i1, java.sql.Date.valueOf("2017-01-07"), java.sql.Date.valueOf("2017-07-12"));

        ArrayList<ItemRentado> list1 = new ArrayList<>();
        list1.add(ir1);
        list1.add(ir3);
        list1.add(ir1);
        try {
            Cliente c = new Cliente("Javier Silva", 2092903, "3154231645", "En la popo", "silvagay@gmail.com", false, list1);
            sa.registrarCliente(c);
            assertEquals("No deberia agregar un cliente vetado deberia mandar una excepcion", c, sa.consultarCliente(2092903));
        } catch (ExcepcionServiciosAlquiler e) {
            assertTrue(true);
        }

        
    }

}
