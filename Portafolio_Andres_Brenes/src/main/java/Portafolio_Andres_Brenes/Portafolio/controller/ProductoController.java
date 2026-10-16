package Portafolio_Andres_Brenes.Portafolio.controller;

import Portafolio_Andres_Brenes.Portafolio.domain.Producto;
import Portafolio_Andres_Brenes.Portafolio.service.CategoriaService;
import Portafolio_Andres_Brenes.Portafolio.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService,
            CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        return "producto/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Producto producto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Revisá los datos del formulario");
            return "redirect:/producto/listado";
        }
        productoService.save(producto);
        redirectAttributes.addFlashAttribute("todoOk", "Producto guardado");
        return "redirect:/producto/listado";
    }
    @GetMapping("/modifica/{idProducto}")
    public String modifica(@PathVariable Integer idProducto,
            Model model,
            RedirectAttributes redirectAttributes) {
        var producto = productoService.getProducto(idProducto);
        if (producto.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "El producto no existe");
            return "redirect:/producto/listado";
        }
        model.addAttribute("producto", producto.get());
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        return "producto/modifica";
    }
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idProducto,
            RedirectAttributes redirectAttributes) {
        try {
            productoService.delete(idProducto);
            redirectAttributes.addFlashAttribute("todoOk", "Producto eliminado");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/producto/listado";
    }
}