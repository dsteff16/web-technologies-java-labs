package com.unibuc.demo.service;

import com.unibuc.demo.dto.ShopDTO;
import com.unibuc.demo.mapper.ProductMapper;
import com.unibuc.demo.mapper.ShopMapper;
import com.unibuc.demo.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final ProductMapper productMapper;
    private final ShopMapper shopMapper;
    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ProductMapper productMapper, ShopMapper shopMapper, ShopRepository shopRepository) {
        this.productMapper = productMapper;
        this.shopMapper = shopMapper;
        this.shopRepository = shopRepository;
    }

    public List<ShopDTO> getAllShop() {
        return shopRepository.getAll().stream().map(shopMapper::convertShopDTOFrom).collect(Collectors.toList());
    }

    public ShopDTO getShopByCUI(String CUI) {
        return shopMapper.convertShopDTOFrom(this.shopRepository.getByCUI(CUI).get());
    }

    public void createShop(ShopDTO shopDTO) {
        this.shopRepository.createShop(this.shopMapper.convertShopFrom(shopDTO));
    }

    public void updateShop(String CUI, ShopDTO shopDTO) {
        this.shopRepository.updateShop(CUI, this.shopMapper.convertShopFrom(shopDTO));
    }

    public void addNewProduct(String CUI) {
        this.shopRepository.addNewProduct(CUI);
    }
}
