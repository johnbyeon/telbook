package view;

import dto.TelDto;
import exception.InputValidation;
import exception.MyException;
import service.TelBookService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserView {
    private Scanner sc = new Scanner(System.in);
    private TelBookService telBookService = new TelBookService();
    private InputValidation inputValidation = new InputValidation();

    public void insertView() {
        System.out.println("====전화번호 등록====");
        //한글이름 입력처리 하기

        String name = null;
        boolean nameOk = true;
        while (nameOk) {

            try {
                System.out.println("이름을 입력 하세요");
                name = sc.next();
                inputValidation.nameCheck(name);
                nameOk = false;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }
        boolean ageOk = true;
        int age = 0;
        while (ageOk) {
            try {
                System.out.println("나이를 입력하세요");
                age = sc.nextInt();
                inputValidation.ageCheck(age);
                ageOk = false;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("주소를 입력하세요");
        String address = sc.next();

        String phone = null;
        boolean phoneOk = true;
        while (phoneOk) {
            try {
                System.out.println("전화번호를 입력하세요");
                phone = sc.next();
                inputValidation.phoneCheck(phone);
                phoneOk = false;
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
        }


        //아이디를 제외한 정보 입력
        //아이디는 자동생성

        TelDto dto = new TelDto();
        dto.setName(name);
        dto.setAge(age);
        dto.setAddress(address);
        dto.setPhone(phone);
        int result = telBookService.insertData(dto);
        if (result > 0) {
            System.out.println("정상적으로 입력되었습니다.");
        } else {
            System.out.println("입력되지 않았습니다.");
        }

    }

    public void updateView() {
        findAllView();
        System.out.println("====전화번호 수정====");
        System.out.println("수정할 아이디를 입력하세요");
        //한글이름 입력처리 하기
        int updateid = sc.nextInt();

        TelDto oldDto = telBookService.FindById(updateid);
        TelDto newDto = new TelDto();
        newDto.setId(oldDto.getId());
        if (oldDto == null) {
            System.out.println("찾는 데이터가 없어요");
        } else {
            boolean yesOrNoB = true;
            while (yesOrNoB) {
                System.out.println("수정 전 이름 : " + oldDto.getName());
                System.out.println("수정 할까요?(Y|N)");
                String yesOrNo = sc.next();
                if (yesOrNo.toUpperCase().equals("Y")) {

                    while (true) {
                        try {
                            System.out.println("수정할 이름 : ");
                            newDto.setName(sc.next());
                            inputValidation.nameCheck(newDto.getName());
                            yesOrNoB = false;
                            break;
                        } catch (MyException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (yesOrNo.toUpperCase().equals("N")) {
                    newDto.setName(oldDto.getName());
                    yesOrNoB = false;
                }
            }
            yesOrNoB = true;
            while (yesOrNoB) {
                System.out.println("수정 전 나이 : " + oldDto.getAge());
                System.out.println("수정 할까요?(Y|N)");
                String yesOrNo = sc.next();
                if (yesOrNo.toUpperCase().equals("Y")) {
                    try {
                        System.out.println("수정할 나이 : ");
                        newDto.setAge(sc.nextInt());
                        inputValidation.ageCheck(newDto.getAge());
                        yesOrNoB = false;
                        break;
                    } catch (MyException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (yesOrNo.toUpperCase().equals("N")) {
                    newDto.setAge(oldDto.getAge());
                    yesOrNoB = false;
                }
            }
            yesOrNoB = true;
            while (yesOrNoB) {
                System.out.println("수정 전 주소 : " + oldDto.getAddress());
                System.out.println("수정 할까요?(Y|N)");
                String yesOrNo = sc.next();
                if (yesOrNo.toUpperCase().equals("Y")) {
                    System.out.println("수정할 주소 : ");
                    newDto.setAddress(sc.next());
                    yesOrNoB = false;
                } else if (yesOrNo.toUpperCase().equals("N")) {
                    newDto.setAddress(oldDto.getAddress());
                    yesOrNoB = false;
                }
            }
            yesOrNoB = true;
            while (yesOrNoB) {
                System.out.println("수정 전 휴대폰 : " + oldDto.getPhone());
                System.out.println("수정 할까요?(Y|N)");
                String yesOrNo = sc.next();
                if (yesOrNo.toUpperCase().equals("Y")) {
                    while (true) {
                        try {
                            System.out.println("수정할 휴대폰 : ");
                            newDto.setPhone(sc.next());
                            inputValidation.phoneCheck(newDto.getPhone());
                            yesOrNoB = false;
                            break;
                        } catch (MyException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else if (yesOrNo.toUpperCase().equals("N")) {
                    newDto.setPhone(oldDto.getPhone());
                    yesOrNoB = false;
                }
            }

            if (oldDto.getPhone().equals(newDto.getPhone()) &&
                    oldDto.getAge() == newDto.getAge() &&
                    oldDto.getName().equals(newDto.getName()) &&
                    oldDto.getAddress().equals(newDto.getAddress())) {
                System.out.println("바뀐데이터가 없습니다.");
                return;
            } else {
                if (0 < telBookService.updateData(newDto)) {
                    System.out.println("성공적으로 수정되었습니다");
                }
            }
        }
    }

    public void deleteView() {
        System.out.println("====전화번호 삭제====");
        System.out.println("삭제할 ID를 입력하세요");
        int deleteId = sc.nextInt();
        int result = telBookService.deleteData(deleteId);
        //result 값이 양수면, 성공, 그렇지 않으면 실패
        if (result > 0) {
            System.out.println("정상적으로 삭제 되었습니다.");
        } else {
            System.out.println("삭제되지 않았습니다.");
            System.out.println("관리자에게 문의하세요");
        }
    }

    public void findAllView() {
        List<TelDto> dtoList = new ArrayList<>();
        System.out.println("====전화번호 목록====");
        //서비스의 db에서 리스트 요청하기
        dtoList = telBookService.getListAll();
        dtoList
                .stream()
                .forEach(x -> System.out.println(x.getId() + "\t" + x.getName() + "\t" + x.getAge() + "\t" + x.getAddress() + "\t" + x.getPhone()));
    }

    public void searchView() {
        System.out.println("====전화번호 검색====");
        System.out.println("이름으로 검색합니다.");
        System.out.println("이름 전체나 일부를 입력하세요");
        String keyWord = sc.next();
        List<TelDto> dtoList = telBookService.searchList(keyWord);
        if (dtoList.size() == 0) {
            System.out.println("찾는 데이터가 없습니다.");
        } else {
            dtoList.stream().forEach(x -> System.out.println(x.toString()));
        }
    }
}
