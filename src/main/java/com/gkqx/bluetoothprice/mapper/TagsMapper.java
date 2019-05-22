package com.gkqx.bluetoothprice.mapper;

import com.gkqx.bluetoothprice.model.FontTag;
import com.gkqx.bluetoothprice.model.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagsMapper {

    Integer insertTag(Tags tags);

    Tags getOneTagByMacAddress(Tags tags);

    List<FontTag> getAllTagsToFont(FontTag fontTag);

    List<Tags> getAllTags(@Param("wifiIp") String  wifiIp);
}
