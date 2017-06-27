<%@page import="com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil"%>
<%@page import="com.rivetlogic.assetmanagement.configuration.AssetManagementGroupServiceConfiguration"%>
<%@include file="/init.jsp"%>

<%@page import="com.liferay.portal.kernel.util.Constants"%>

<%
	AssetManagementGroupServiceConfiguration groupConfiguration =
			ConfigurationProviderUtil.getGroupConfiguration(AssetManagementGroupServiceConfiguration.class, themeDisplay.getScopeGroupId());;

	int time_cfg = groupConfiguration.time();
	int minutes_cfg = groupConfiguration.minutes();
%>

<liferay-portlet:actionURL var="configurationURL" portletConfiguration="true">
	<portlet:param name="serviceName" value="<%= AssetManagementGroupServiceConfiguration.class.getName() %>" />
	<portlet:param name="settingsScope" value="group" />
</liferay-portlet:actionURL>


<aui:form action="<%=configurationURL%>" method="post" name="fm">
	<aui:input name="<%=Constants.CMD%>" type="hidden"
		value="<%=Constants.UPDATE%>" />
	
	<p style="font-size: medium;"><liferay-ui:message key="configuration-description" /></p>
	<aui:input name="preferences--time--" required="true" type="number"
		value="<%=time_cfg%>" />
	<aui:row>
		<aui:col>
			<aui:input name="preferences--minutes--" required="true" type="radio"
				label="Minutes" value="1" checked="<%=1 == minutes_cfg%>" />
		</aui:col>
		<aui:col>
			<aui:input name="preferences--minutes--" required="true" type="radio"
				label="Hours" value="60" checked="<%=60 == minutes_cfg%>" />
		</aui:col>
		<aui:col>
			<aui:input name="preferences--minutes--" required="true" type="radio"
				label="Days" value="1440" checked="<%=1440 == minutes_cfg%>" />
		</aui:col>
	</aui:row>
	
	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>

</aui:form>