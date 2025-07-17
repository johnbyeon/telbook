package service;

import dto.TelDto;

import java.util.List;

public interface CrudInterface {
    int InsertData(TelDto dto);
    int UpdateData(TelDto dto);
    int deleteData(int id);
    List<TelDto> getListAll();
    TelDto FindById(int id);
    List<TelDto> searchList(String keyword);

}
