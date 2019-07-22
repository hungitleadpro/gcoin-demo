package com.feature.gcoin.common.util;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ModelMapperUtil {
    public static <D> D map(Object object, Class<D> classObject){
        if(object==null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(object, classObject);
    }
    public static <D> List<D> maps(List<Object> objects, Class<D> classObject){
        if(objects==null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        List<D> lst = new ArrayList<D>();
        for (Object object: objects) {
            lst.add(modelMapper.map(object, classObject));
        }
        return lst;
    }
}
