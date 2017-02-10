<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="">
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12">
			<div class="x_panel">
				<div class="x_title">
					<h2>
						<fmt:message key="form.ChangeRole.Title" />
						<small><fmt:message key="form.ChangeRole.Subtitle" /></small>
					</h2>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">

					<form class="form-horizontal form-label-left" action="./changeRole"
						method="post">

						<span class="section"></span>

						<div class="item form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12"
								for="role"><fmt:message key="form.ChangeRole.Role" /> <span
								class="required">*</span> </label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<select id="role" name="role"
									class="form-control col-md-7 col-xs-12" required="required">
									<option value="1">ROLE_USER</option>
									<option value="2">ROLE_ADMIN</option>
									<sec:authorize access="hasRole('ROLE_SUPERADMIN')">
										<option value="3">ROLE_SUPERADMIN</option>
									</sec:authorize>
								</select>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="ln_solid"></div>
						<div class="form-group">
							<div class="col-md-6 col-md-offset-3">
								<button id="send" type="submit" class="btn btn-success">
									<fmt:message key="general.Submit" />
								</button>
							</div>
						</div>
					</form>
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