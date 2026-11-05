/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portafolio_Andres_Brenes.Portafolio.controller;
import Portafolio_Andres_Brenes.Portafolio.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author brene
 */
@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    private final ProductoService productoService;

    public ConsultaController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("productos", productoService.getProductos(true));
        return "consultas/listado";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam String texto,
            @RequestParam Integer existenciasMinimas,
            Model model) {
        int minimo = Math.max(0, existenciasMinimas);
        var productos = productoService.buscarPorPalabraYStock(texto.trim(), minimo);
        model.addAttribute("productos", productos);
        model.addAttribute("texto", texto);
        model.addAttribute("existenciasMinimas", minimo);
        model.addAttribute("busquedaRealizada", true);
        return "consultas/listado";
    }
}
