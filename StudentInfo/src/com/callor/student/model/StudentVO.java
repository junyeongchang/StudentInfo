package com.callor.student.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentVO {
	int stNum;
	String stName;
	String stDept;
	int intGrade;
	String stTel;

	@Override
	public String toString() {
		String result = "";
		result += String.format("%d:", stNum);
		result += String.format("%s:", stName);
		result += String.format("%s:", stDept);
		result += String.format("%d:", intGrade);
		result += String.format("%s\n", stTel);
		return result;
	}
}
