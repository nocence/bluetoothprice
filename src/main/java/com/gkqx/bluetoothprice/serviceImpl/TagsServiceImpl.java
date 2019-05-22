package com.gkqx.bluetoothprice.serviceImpl;

import com.gkqx.bluetoothprice.mapper.TagsMapper;
import com.gkqx.bluetoothprice.model.FontTag;
import com.gkqx.bluetoothprice.model.Tags;
import com.gkqx.bluetoothprice.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public Integer insertTag(Tags tags) {
        return tagsMapper.insertTag(tags);
    }

    @Override
    public Tags getOneTagByMacAddress(Tags tags) {
        return tagsMapper.getOneTagByMacAddress(tags);
    }

    @Override
    public List<FontTag> getAllTagsToFont(FontTag fontTag) {
        return tagsMapper.getAllTagsToFont(fontTag);
    }

    @Override
    public List<Tags> getAllTags(String  wifiIp) {
        return tagsMapper.getAllTags(wifiIp);
    }
}
