package com.java.gavinandre.controller.propertyeditor;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gavinandre on 17-1-6.
 */
//自定义属性编辑器实现controller公用
public class CustomPropertyEditor implements PropertyEditorRegistrar {

    @Override
    public void registerCustomEditors(PropertyEditorRegistry binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"), true));
    }
}
