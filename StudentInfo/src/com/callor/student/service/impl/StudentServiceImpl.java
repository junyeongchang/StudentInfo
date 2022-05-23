package com.callor.student.service.impl;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.callor.student.model.StudentVO;
import com.callor.student.service.StudentService;

public class StudentServiceImpl implements StudentService {

	protected final List<StudentVO> studentList;
	protected Scanner scan;
	protected final String fileName;

	public StudentServiceImpl() {
		studentList = new ArrayList<>();
		scan = new Scanner(System.in);
		fileName = "src/com/callor/student/model/student.txt";
		loadList();
	}

	public void selectMenu() {
		while (true) {
			System.out.println("1. 학생정보 입력 후 저장");
			System.out.println("2. 학생정보 전체 보기");
			System.out.print("메뉴를 선택해 주세요(QUIT : 종료) > ");
			String select = scan.nextLine();

			if (select.equals("1")) {
				inputStudent();
			} else if (select.equals("2")) {
				printStudent();
			} else if (select.equals("QUIT")) {
				break;
			} else {
				System.out.println("1번과2번 또는 QUIT를 입력해 주세요");
			}
		} // end while
		System.out.println("종료되었습니다.");
	}

	@Override
	public void inputStudent() {
		while (true) {
			System.out.print("학번(QUIT : 전단계) > ");
			String stNum = scan.nextLine();
			if (stNum.equals("QUIT")) {
				break;
			}
			System.out.print("이름 > ");
			String stName = scan.nextLine();
			System.out.print("학과 > ");
			String stDept = scan.nextLine();
			System.out.print("학년 > ");
			String stGrade = scan.nextLine();
			System.out.print("전화번호 > ");
			String stTel = scan.nextLine();
			StudentVO stVO = StudentVO.builder().stNum(Integer.valueOf(stNum)).stName(stName).stDept(stDept)
					.intGrade(Integer.valueOf(stGrade)).stTel(stTel).build();
			studentList.add(stVO);
		} // end while

		FileOutputStream fileOut = null;
		BufferedOutputStream buffer = null;
		try {
			fileOut = new FileOutputStream(fileName);
			buffer = new BufferedOutputStream(fileOut);
			for (StudentVO stVO : studentList) {
				buffer.write(stVO.toString().getBytes());
			}
			buffer.flush();
			buffer.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}// end inputStudent

	@Override
	public void printStudent() {
		System.out.println("=".repeat(50));
		System.out.printf("학번\t이름\t학과\t학년\t전화번호\n");
		System.out.println("-".repeat(50));
		for (StudentVO stVO : studentList) {
			System.out.print(stVO.getStNum() + "\t");
			System.out.print(stVO.getStName() + "\t");
			System.out.print(stVO.getStDept() + "\t");
			System.out.print(stVO.getIntGrade() + "\t");
			System.out.println(stVO.getStTel());
		}
		System.out.println();
	}

	public void loadList() {
		FileReader fileReader = null;
		BufferedReader buffer = null;
		try {
			fileReader = new FileReader(fileName);
			buffer = new BufferedReader(fileReader);

			while (true) {
				String reader = buffer.readLine();
				if (reader == null) {
					break;
				}
				String stInfos[] = reader.split(":");
				StudentVO stVO = StudentVO.builder().stNum(Integer.valueOf(stInfos[0])).stName(stInfos[1])
						.stDept(stInfos[2]).intGrade(Integer.valueOf(stInfos[3])).stTel(stInfos[4]).build();
				studentList.add(stVO);
			}
			buffer.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
