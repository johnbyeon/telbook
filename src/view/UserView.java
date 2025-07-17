package view;

import dto.TelDto;
import service.TelBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private Scanner sc = new Scanner(System.in);
    private TelBookService telBookService = new TelBookService();
    public void insertView(){
        System.out.println("====전화번호 등록====");
        System.out.println("이름을 입력 하세요");
        String name = sc.next();
        System.out.println("나이를 입력하세요");
        int age = sc.nextInt();
        System.out.println("주소를 입력하세요");
        String address = sc.next();
        System.out.println("전화번호를 입력하세요");
        String phone = sc.next();

        //아이디를 제외한 정보 입력
        //아이디는 자동생성

        TelDto dto = new TelDto();
        dto.setName(name);
        dto.setAge(age);
        dto.setAddress(address);
        dto.setPhone(phone);
        int result = telBookService.insertData(dto);
        if(result > 0){
            System.out.println("정상적으로 입력되었습니다.");
        }else {
            System.out.println("입력되지 않았습니다.");
        }

    }
    public void updateView(){
        System.out.println("====전화번호 수정====");
    }
    public void deleteView(){
        System.out.println("====전화번호 삭제====");
    }
    public void findAllView(){
        List<TelDto> dtoList = new ArrayList<>();
        System.out.println("====전화번호 목록====");
        //서비스의 db에서 리스트 요청하기
        dtoList =  telBookService.getListAll();
        dtoList
                .stream()
                .forEach(x -> System.out.println(x.getId()+"\t"+x.getName()+"\t"+x.getAge()+"\t"+x.getAddress()+"\t"+x.getPhone()));
    }
    public void searchView(){
        System.out.println("====전화번호 검색====");
    }
}
