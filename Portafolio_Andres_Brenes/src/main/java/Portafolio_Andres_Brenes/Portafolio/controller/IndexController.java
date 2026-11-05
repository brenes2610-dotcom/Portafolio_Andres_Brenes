/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Portafolio_Andres_Brenes.Portafolio.controller;
import Portafolio_Andres_Brenes.Portafolio.domain.Producto;
import Portafolio_Andres_Brenes.Portafolio.service.CategoriaService;
import Portafolio_Andres_Brenes.Portafolio.service.ProductoService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/**
 *
 * @author brene
 */
@Controller
public class IndexController {
    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public IndexController(ProductoService productoService,
            CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/")
    public String cargarPaginaInicio(Model model) {
        model.addAttribute("productos", productoService.getProductos(true));
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        return "index";
    }

    @GetMapping("/consultas/{idCategoria}")
    public String consultarPorCategoria(@PathVariable Integer idCategoria, Model model) {
        List<Producto> productos = new ArrayList<>();
        var categoria = categoriaService.getCategoria(idCategoria);
        if (categoria.isPresent()) {
            for (Producto producto : categoria.get().getProductos()) {
                if (producto.isActivo()) {
                    productos.add(producto);
                }
            }
        }
        model.addAttribute("idCategoriaActual", idCategoria);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        return "index";
    }
}
