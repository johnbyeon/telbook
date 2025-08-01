package service;

import db.DBConn;
import dto.TelDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelBookService implements CrudInterface {

    Connection conn = DBConn.getConnection();
    PreparedStatement psmt = null;
    String sql;
    @Override
    public int insertData(TelDto dto) {
        try {
            System.out.println("[TelBookService.InsertData]");
            sql = "INSERT INTO telbook(name,age,address,phone) " +
                    "VALUES(?,?,?,?)";
            psmt = conn.prepareStatement(sql);
            //물음표 각 자리를 Mapping 해줍니다.
            psmt.setString(1,dto.getName());
            psmt.setInt(2,dto.getAge());
            psmt.setString(3,dto.getAddress());
            psmt.setString(4,dto.getPhone());
            //쿼리실행하기
            int result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return 0;
    }

    @Override
    public int updateData(TelDto dto) {
        System.out.println("[TelBookService.UpdateData]");
        try {
            sql = "UPDATE telbook SET " +
                    "name = ?, " +
                    "age = ?, " +
                    "address = ?, " +
                    "phone = ? " +
                    "WHERE id = ?";

            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getName());
            psmt.setInt(2, dto.getAge());
            psmt.setString(3, dto.getAddress());
            psmt.setString(4, dto.getPhone());
            psmt.setInt(5, dto.getId());
            int result = psmt.executeUpdate();
            psmt.close();
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteData(int id) {
        System.out.println("[TelBookService.deleteData]");
        int result =0;
        try {
            sql = "DELETE FROM telbook WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            result = psmt.executeUpdate();
            psmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<TelDto> getListAll() {
        System.out.println("[TelBookService.getListAll]");
        //DB에서 Select 한 결과를 담을 List

        List<TelDto> dtoList = new ArrayList<>();
        ResultSet rs = null;
        try {
            sql = "SELECT * FROM telbook";
            psmt = conn.prepareStatement(sql);
            //SQL 구문 실행
            rs = psmt.executeQuery();
            //ResultSet 에 들어온 레코드를 하나씩 뽑아서
            //DtoList에 담는다.
            while(rs.next()){
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dtoList.add(dto);
            }
            //잘 들어 왔는지 확인
           // dtoList.stream().forEach(x-> System.out.println(x));
            rs.close();
            psmt.close();
        }catch (SQLException e ){
            System.out.println(e);
        }


        return dtoList;
    }

    @Override
    public TelDto FindById(int id) {
        System.out.println("[TelBookService.FindById]");
        ResultSet rs = null;
        try {
            sql = "SELECT id,name,age,address,phone FROM telbook WHERE id = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1,id);
            rs = psmt.executeQuery();
         while(rs.next()){
             TelDto dto = new TelDto();
             dto.setId(rs.getInt("id"));
             dto.setName(rs.getString("name"));
             dto.setAge(rs.getInt("age"));
             dto.setAddress(rs.getString("address"));
             dto.setPhone(rs.getString("phone"));
             return dto;
         }
        }catch (SQLException e ){
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<TelDto> searchList(String keyword) {
        System.out.println("[TelBookService.searchList]");
        List<TelDto> dtoList = new ArrayList<>();
        ResultSet rs = null;
        try {
            sql = "SELECT id,name,age,address,phone FROM telbook WHERE name LIKE ? ORDER BY name DESC";

            System.out.println(sql);
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,"%"+keyword+"%");
            //SQL 구문 실행

            rs = psmt.executeQuery();
            //ResultSet 에 들어온 레코드를 하나씩 뽑아서
            //DtoList에 담는다.
            while(rs.next()){
                TelDto dto = new TelDto();
                dto.setId(rs.getInt("id"));
                dto.setName(rs.getString("name"));
                dto.setAge(rs.getInt("age"));
                dto.setAddress(rs.getString("address"));
                dto.setPhone(rs.getString("phone"));
                dtoList.add(dto);
            }
            //잘 들어 왔는지 확인
            // dtoList.stream().forEach(x-> System.out.println(x));
            rs.close();
            psmt.close();
        }catch (SQLException e ){
            System.out.println(e);
        }
        return dtoList;
    }
}
