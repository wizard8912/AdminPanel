package pl.pniedziela.annotations;

import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotEmptyAllValidator implements ConstraintValidator<NotEmptyAll, Object> {
	private String firstFieldName;
	private String secondFieldName;

	public void initialize(final NotEmptyAll constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
	}

	public boolean isValid(final Object value, final ConstraintValidatorContext context) {
		try {
			final Object firstObj = BeanUtils.getProperty(value, firstFieldName);
			final Object secondObj = BeanUtils.getProperty(value, secondFieldName);

			boolean notEmptyFirst = firstObj != null && !firstObj.toString().equals("0");
			boolean notEmptySecond = secondObj != null && !secondObj.equals("");

			return notEmptyFirst || notEmptySecond;
		} catch (final Exception ignore) {
			// ignore
		}
		return true;
	}
}