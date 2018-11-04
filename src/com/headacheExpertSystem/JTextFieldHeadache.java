package com.headacheExpertSystem;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JTextFieldHeadache extends JTextField {
	private static final long serialVersionUID = 1L;
	private static final String BLANK = "";
	private static final int DEF_PRECISION = 2;
	public static final int NUMERIC = 2;
	public static final String FM_NUMERIC = "0123456789";

	private int maxLength = 0;
	private int format = NUMERIC;
	private String negativeChars = BLANK;
	private String allowedChars = null;
	private int precision = 0;

	protected PlainDocument numberFieldFilter;

	public JTextFieldHeadache() {
		this(10, NUMERIC);
	}

	public JTextFieldHeadache(int maxLen) {
		this(maxLen, NUMERIC);
	}

	public JTextFieldHeadache(int maxLen, int format) {
		setMaxLength(maxLen);
		setFormat(format);
		setHorizontalAlignment(JTextField.CENTER);

		numberFieldFilter = new JNumberFieldFilter();
		super.setDocument(numberFieldFilter);
	}

	public void setMaxLength(int maxLen) {
		if (maxLen > 0)
			maxLength = maxLen;
		else
			maxLength = 0;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setPrecision(int precision) {
		if (format == NUMERIC)
			return;

		if (precision >= 0)
			this.precision = precision;
		else
			this.precision = DEF_PRECISION;
	}

	public int getPrecision() {
		return precision;
	}

	public Number getNumber() {
		Number number = null;

		if (format == NUMERIC)
			number = new Integer(getText());
		else
			number = new Double(getText());

		return number;
	}

	public void setNumber(Number value) {
		setText(String.valueOf(value));
	}

	public int getInt() {
		return Integer.parseInt(getText());
	}

	public void setInt(int value) {
		setText(String.valueOf(value));
	}

	public float getFloat() {
		return (new Float(getText())).floatValue();
	}

	public void setFloat(float value) {
		setText(String.valueOf(value));
	}


	public void setDouble(double value) {
		setText(String.valueOf(value));
	}

	public int getFormat() {
		return format;
	}

	public void setFormat(int format) {
		switch (format) {
		case NUMERIC:
		default:
			this.format = NUMERIC;
			this.precision = 0;
			this.allowedChars = FM_NUMERIC;
			break;
		}
	}

	public void setDocument(Document document) {
	}

	class JNumberFieldFilter extends PlainDocument {
		private static final long serialVersionUID = 1L;

		public JNumberFieldFilter() {
			super();
		}

		public void insertString(int offset, String str, AttributeSet attr)
				throws BadLocationException {
			String text = getText(0, offset) + str
					+ getText(offset, (getLength() - offset));

			if (str == null || text == null)
				return;

			for (int i = 0; i < str.length(); i++) {
				if ((allowedChars + negativeChars).indexOf(str.charAt(i)) == -1)
					return;
			}

			int precisionLength = 0, dotLength = 0, minusLength = 0;
			int textLength = text.length();

			try {
				if (format == NUMERIC) {
					if (!((text.equals(negativeChars)) && (text.length() == 1)))
						new Long(text);
				}
			} catch (Exception ex) {
				return;
			}
			if (maxLength < (textLength - dotLength - precisionLength - minusLength))
				return;
			super.insertString(offset, str, attr);
		}
	}
}
