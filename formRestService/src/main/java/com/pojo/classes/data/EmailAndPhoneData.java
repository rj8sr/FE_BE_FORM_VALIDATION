package com.pojo.classes.data;

import com.pojo.classes.EmailAndPhone;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class EmailAndPhoneData {

	static List<EmailAndPhone> list1 = new ArrayList<>();

	public static void fillAuthHistoryList() {

		if (list1.isEmpty()) {
			list1.add(new EmailAndPhone("1230000001", "rajat@gmail.com", "7000000081"));
			list1.add(new EmailAndPhone("1230000002", "saurabh@gmail.com", "7000000082"));

		}
	}

	public static String getData(String id) {
		fillAuthHistoryList();
		JSONArray list = new JSONArray();
		for (EmailAndPhone entry : list1) {
			String key = entry.getId();

			if (key.equals(id)) {
				String email = entry.getEmail();
				String number = entry.getNumber();
				JSONObject obj = new JSONObject();
				obj.put("email", email);
				obj.put("number", number);
				list.add(obj);

			}
		}
		return list.toJSONString();
	}

	public static boolean getValidatingData(String id, String email, String number) {

		fillAuthHistoryList();
		for (EmailAndPhone entry : list1) {
			String key = entry.getId();
			if (id.equals(key)) {
				if (entry.getEmail().equals(email) && entry.getNumber().equals(number)) {
					return true;
				} else {
					return false;
				}

			}
		}
		return false;

	}

}
