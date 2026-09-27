package dev.engripaye.backend.catalog;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDtos.View create(@Valid @RequestBody ProductDtos.Create request){
        return productService.create(request);

    }

    @GetMapping
    Page<ProductDtos.View> list(@RequestParam(defaultValue = "")
                                String q, @PageableDefault(size = 20, sort = "name")
                                Pageable page){
        return productService.list(q, page);
    }







}
