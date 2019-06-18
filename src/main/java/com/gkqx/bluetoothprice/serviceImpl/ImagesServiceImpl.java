package com.gkqx.bluetoothprice.serviceImpl;

import com.gkqx.bluetoothprice.mapper.ImagesMapper;
import com.gkqx.bluetoothprice.model.Goods;
import com.gkqx.bluetoothprice.model.GoodsImages;
import com.gkqx.bluetoothprice.model.Images;
import com.gkqx.bluetoothprice.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public Integer insertImageInfomation(Images images) {
        return imagesMapper.insertImageInfomation(images);
    }

    @Override
    public List<GoodsImages> getAllImages(Images images, Goods goods) {
        return imagesMapper.getAllImages(images,goods);
    }

    @Override
    public Images getImage(Images images) {
        return imagesMapper.getImage(images);
    }

    @Override
    public Integer deleteImageByImageName(Images images) {
        return imagesMapper.deleteImageByImageName(images);
    }
}
