package lv.stradini.impl.service;

import lv.stradini.interfaces.service.ValidatorService;

public class ValidatorServiceImpl implements ValidatorService {

	final static String DATE_REGEX = "[1-2][0-9]{3}-((0?[1-9])|(1[0-2]))-((0?[1-9])|(1[0-9])|(3[0-1]))";

	@Override
	public String validateEmployee(String firstName, String lastName,
			String middleInitial, String level, String workForce,
			String enterpriseID) {
		StringBuilder sb = new StringBuilder();

		if (firstName.equals("")) { sb.append("<br/>First name is mandatory"); }
		if (lastName.equals("")) { sb.append("<br/>Last name is mandatory"); }
		if (!middleInitial.equals("")) {
			if (middleInitial.length() > 1 || !middleInitial.matches("[A-Z]")) {
				sb.append("<br/>Middle initial should be a single capitol letter");
			}
		}
		return sb.toString();
	}

	@Override
	public String validateEmployeeSkill(String name, String rating) {
		StringBuilder sb = new StringBuilder();

		if (isEmptyString(name)) { sb.append("<br/>Skill name is mandatory"); }
		if (isEmptyString(rating)) {
			sb.append("<br/>Rating is mandatory");
		} else {
			try {
				Long.parseLong(rating);
			} catch(Exception e) {
				sb.append("<br/>Rating should be a positive number");
			}
		}

		return sb.toString();
	}

	@Override
	public String validateProjectRole(String startDate, String endDate) {
		StringBuilder sb = new StringBuilder();
		if (isEmptyString(startDate)) {
			sb.append("<br/>Start date can not be empty");
		} else {
			if (!startDate.matches(DATE_REGEX)) {
				sb.append("<br/>Start date format is incorrect");
			}
		}

		if (!isEmptyString(endDate)) {
			if (!endDate.matches(DATE_REGEX)) {
				sb.append("<br/>End date format is incorrect");
			}
		}

		return sb.toString();
	}

	@Override
	public String validateProject(String name, String description, String client) {
		StringBuilder sb = new StringBuilder();

		if (name.equals("")) { sb.append("<br/>Project name is mandatory"); }
		if (client.equals("")) { sb.append("<br/>Client field is mandatory"); }
		return sb.toString();
	}

	private boolean isEmptyString(String name) {
		return name.equals("");
	}

}
