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
						<fmt:message key="form.Login.Title" />
						<small><fmt:message key="form.Login.Subtitle" /></small>
					</h2>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">

					<form class="form-horizontal form-label-left" novalidate
						action="${ pageContext.request.contextPath }/j_spring_security_check"
						method="post">

						<p>
							<fmt:message key="form.Login.ForgotPassOrRegister1" />
							<code>
								<a href="${ pageContext.request.contextPath }/register"><fmt:message
										key="form.Login.ForgotPassOrRegister2" /></a>
							</code>
							<fmt:message key="form.Login.ForgotPassOrRegister3" />
							<code>
								<a href="${ pageContext.request.contextPath }/remindPassword"><fmt:message
										key="form.Login.ForgotPassOrRegister4" /></a>
							</code>
						</p>
						<span class="section"></span>

						<div class="item form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12"
								for="name"><fmt:message key="form.Login.Username" /> <span
								class="required">*</span> </label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input id="name" name='username'
									class="form-control col-md-7 col-xs-12" required="required"
									type="text">
							</div>
						</div>
						<div class="item form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12"
								for="password"><fmt:message key="form.Login.Password" />
								<span class="required">*</span> </label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input id="password" type="password" name="password"
									class="form-control col-md-7 col-xs-12" required="required">
							</div>
						</div>
						<div class="item form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12"
								for="remember-me"><fmt:message
									key="form.Login.RememberMe" /></label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input id="remember-me" name="remember-me" type="checkbox"
									class="flat" checked="checked">
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<div class="ln_solid"></div>
						<div class="form-group">
							<div class="col-md-6 col-md-offset-3">
								<button id="send" type="submit"
									class="btn btn-success col-md-12 ">
									<fmt:message key="form.Login.Submit" />
								</button>
							</div>
						</div>
					</form>

					<div class="col-md-6 col-md-offset-3">
						<div class="g-signin2 col-md-6" data-onsuccess="onSignIn">
							<a class=" btn btn-block btn-social btn-google"> <i
								class="fa fa-google"></i> <fmt:message key='general.SignInWith' />
								Google
							</a>
						</div>
						<div class="col-md-6">
							<a class="btn btn-block btn-social btn-facebook"> <i
								class="fa fa-facebook"></i> <fmt:message
									key='general.SignInWith' /> Facebook
							</a>
						</div>
						<div class="col-md-6">
							<a class="btn btn-block btn-social btn-twitter"> <i
								class="fa fa-twitter"></i> <fmt:message key='general.SignInWith' />
								Twitter
							</a>
						</div>
						<div class="col-md-6">
							<a class="btn btn-block btn-social btn-linkedin"> <i
								class="fa fa-linkedin"></i> <fmt:message
									key='general.SignInWith' /> LinkedIn
							</a>
						</div>
					</div>

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