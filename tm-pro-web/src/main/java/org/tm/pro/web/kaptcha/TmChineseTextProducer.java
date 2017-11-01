package org.tm.pro.web.kaptcha;

import java.util.Random;

import org.apache.shiro.SecurityUtils;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.text.impl.ChineseTextProducer;

public class TmChineseTextProducer extends ChineseTextProducer {

	public String getText() {
		int num1 = nextInt();
		int num2 = nextInt();
		String result = "";
		String text = "";
		if (num1 > 1 && num2 > 1 && num1 % num2 == 0) {
			result = (num1 / num2) + "";
			text = num1 + " ÷  " + num2;
		} else {
			if (operator() > 0) {
				result = (num1 * num2) + "";
				text = num1 + " × " + num2;
			} else {
				result = (num1 + num2) + "";
				text = num1 + " + " + num2;
			}
		}
		SecurityUtils.getSubject().getSession(true).setAttribute(Constants.KAPTCHA_SESSION_KEY, result);
		return text;
	}

	private int nextInt() {
		Random rand = new Random();
		return rand.nextInt(20);
	}

	private int operator() {
		Random rand = new Random();
		return rand.nextInt(2);
	}

}
