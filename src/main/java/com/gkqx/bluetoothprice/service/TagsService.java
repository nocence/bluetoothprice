package com.gkqx.bluetoothprice.service;

import com.gkqx.bluetoothprice.model.FontTag;
import com.gkqx.bluetoothprice.model.Tags;

import java.util.List;

public interface TagsService {

    Integer insertTag(Tags tags);

    Tags getOneTagByMacAddress(Tags tags);

    List<FontTag> getAllTagsToFont(FontTag fontTag);

    List<Tags> getAllTags(String wifiIp);
}
