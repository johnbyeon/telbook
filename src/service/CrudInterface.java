package service;

import dto.TelDto;

import java.util.List;

public interface CrudInterface {
    int insertData(TelDto dto);
    int updateData(TelDto dto);
    int deleteData(int id);
    List<TelDto> getListAll();
    TelDto FindById(int id);
    List<TelDto> searchList(String keyword);

}
