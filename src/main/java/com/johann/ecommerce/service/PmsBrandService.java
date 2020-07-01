package com.johann.ecommerce.service;

import com.johann.ecommerce.mbg.model.PmsBrand;

import java.util.List;

public interface PmsBrandService {

    // create
    int createBrand(PmsBrand pmsBrand);

    // read
    List<PmsBrand> getAllBrands();

    List<PmsBrand> getAllBrands(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);

    // update
    int updateBrand(Long id, PmsBrand pmsBrand);

    // delete
    int deleteBrand(Long id);


}
