package com.johann.ecommerce.controller;

import com.johann.ecommerce.mbg.model.PmsBrand;
import com.johann.ecommerce.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Api(tags = "PmsBrandController", description = "商品品牌管理")
@RestController
@RequestMapping("/brands")
public class PmsBrandController {

    private PmsBrandService pmsBrandService;

    @Autowired
    public PmsBrandController(PmsBrandService pmsBrandService) {
        this.pmsBrandService = pmsBrandService;
    }

    // create brand
    @ApiOperation("添加品牌")
    @PostMapping
    public ResponseEntity<PmsBrand> post(@RequestBody PmsBrand pmsBrand) throws URISyntaxException {
        int status = pmsBrandService.createBrand(pmsBrand);
        if (status == 1)
            return ResponseEntity.created(new URI("/brands/" + pmsBrand.getId())).build();
        else {
            return ResponseEntity.badRequest().build();
        }
    }

    // read
    @ApiOperation("获取所有品牌")
    @GetMapping
    public ResponseEntity<List<PmsBrand>> get() {
        return ResponseEntity.ok(pmsBrandService.getAllBrands());
    }

    @ApiOperation("分页获取所有品牌")
    @GetMapping("/pages")
    public ResponseEntity<List<PmsBrand>> getWithPages(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        return ResponseEntity.ok(pmsBrandService.getAllBrands(pageNum, pageSize));
    }

    @ApiOperation("获取指定id的品牌")
    @GetMapping("/{id}")
    public ResponseEntity<PmsBrand> get(@PathVariable Long id) {
        PmsBrand pmsBrand = pmsBrandService.getBrand(id);
        if (pmsBrand != null) {
            return ResponseEntity.ok(pmsBrand);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // update brand
    @ApiOperation("更新指定id的品牌")
    @PutMapping("/{id}")
    public ResponseEntity<Void> put(@PathVariable Long id, @RequestBody PmsBrand pmsBrand) {
        int status = pmsBrandService.updateBrand(id, pmsBrand);
        if (status == 1) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    // delete brand
    @ApiOperation("删除指定id的品牌")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        int status = pmsBrandService.deleteBrand(id);
        if (status == 1) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
