package project.Spiny.dao;

import project.Spiny.entity.DataFieldType;

import java.util.List;

public interface DataFieldTypeDao {
    List<DataFieldType> getAllDataFieldType();
    DataFieldType getDataFieldTypeById(long id);
}
