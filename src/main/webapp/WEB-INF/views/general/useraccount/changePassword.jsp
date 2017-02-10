<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						<fmt:message key="form.changePassword.Title" />
						<small><fmt:message key="form.changePassword.Subtitle" /></small>
					</h2>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">

					<sf:form class="form-horizontal form-label-left"
						action="./setNewPass" method="post" commandName="newPasswordDto">

						<p>
							<sf:errors path="*" cssClass="error" />
						</p>
						<span class="section"></span>

						<div class="item form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12"
								for="password"><fmt:message
									key="form.RemindPass2.NewPassword" /> <span class="required">*</span>
							</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<sf:input id="password" name="password" type="password"
									class="form-control col-md-7 col-xs-12" required="required"
									path="password" />
							</div>
						</div>
						<div class="item form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12"
								for="passwordVerify"><fmt:message
									key="form.RemindPass2.PasswordVerify" /> <span
								class="required">*</span> </label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<sf:input id="passwordVerify" name="passwordVerify"
									type="password" class="form-control col-md-7 col-xs-12"
									required="required" path="passwordVerify"
									data-validate-linked="password" />
							</div>
						</div>
						<sf:input type="hidden" path="userId" />
						<div class="ln_solid"></div>
						<div class="form-group">
							<div class="col-md-6 col-md-offset-3">
								<button id="send" type="submit" class="btn btn-success">
									<fmt:message key="general.Submit" />
								</button>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- form validation -->
<c:set var="localeCode" value="${pageContext.response.locale}" />
<script
	src="${ pageContext.request.contextPath }/resources/views/general/js/validator/validator.js"></script>
<script>
	// initialize the validator function
	validator.message['date'] = 'not a real date';
	validator.language = '${localeCode}';

	// validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
	$('form').on('blur', 'input[required], input.optional, select.required', validator.checkField).on('change', 'select.required', validator.checkField).on('keypress', 'input[required][pattern]', validator.keypress);

	$('.multi.required').on('keyup blur', 'input', function() {
		validator.checkField.apply($(this).siblings().last()[0]);
	});

	// bind the validation to the form submit event
	//$('#send').click('submit');//.prop('disabled', true);

	$('form').submit(function(e) {
		e.preventDefault();
		var submit = true;
		// evaluate the form using generic validaing
		if (!validator.checkAll($(this))) {
			submit = false;
		}

		if (submit)
			this.submit();
		return false;
	});

	/* FOR DEMO ONLY */
	$('#vfields').change(function() {
		$('form').toggleClass('mode2');
	}).prop('checked', false);

	$('#alerts').change(function() {
		validator.defaults.alerts = (this.checked) ? false : true;
		if (this.checked)
			$('form .alert').remove();
	}).prop('checked', false);
</script>