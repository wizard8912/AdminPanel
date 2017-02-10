<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script>
	function detailFormatter(index, row) {

		console.log(row);
		var emailsCount = row["emailsCount"];
		var prefix = "emailsList";
		var details = [ "id", "toAddress", "subject", "createdDate", "sendDate" ];
		var detailsMsg = [ '<fmt:message key="admin.Emails.FromAddress" />',
				'<fmt:message key="admin.Emails.ToAddress" />',
				'<fmt:message key="admin.Emails.Subject" />',
				'<fmt:message key="admin.Emails.CreatedDate" />',
				'<fmt:message key="admin.Emails.SendDate" />', ];
		var html = [];

		html.push("<table style='font-size:11px; rgba(0,0,0,0.5)'>");
		html.push("<thread><tr>");
		for (j = 0; j < details.length; j++) {
			html.push("<th>")
			html.push(details[j]);
			html.push("</th>");
		}
		html.push("</tr></thread><tbody>");

		for (i = 0; i < emailsCount; i++) {
			html.push("<tr>");
			for (j = 0; j < details.length; j++) {
				html.push("<td>");
				html.push(row[prefix + "." + i + "." + details[j]]);
				html.push("</td>");
			}
			html.push("</tr>");
		}
		html.push("</tbody></table>");

		return html.join('');
	}

	function rowStyle(value, row, index) {

		if (value.emailsCount > 4) {
			return {
				classes : 'text-nowrap another-class',
				css : {
					"background" : "rgba(255,0,0,0.2)"
				}
			};
		} else if (value.emailsCount > 0) {
			return {
				classes : 'text-nowrap another-class',
				css : {
					"background" : "rgba(255,255,0,0.2)"
				}
			};
		} else {
			return {
				classes : 'text-nowrap another-class'
			}
		}
	}
</script>