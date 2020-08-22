<html !DOCTYPE>
	<head>
		<style>
			body {
				font-family: monospace;
			}
			
			.event-table {
				border-collapse: collapse;
			}
			
			.event-table .event-header > td, .event-row > td  {
				border: black solid 1px;
			}
			
			.header > td {
				text-decoration: underline;
			}
			
			.item-row {
				border-bottom: solid black 1px 1px 0px 0px
			}
			
			td {
				padding: 5px;
				vertical-align: top;
			}
		</style>
	</head>
	<body>
		<h1>Event Type Configuration - ${eventTypeConfig.eventType.name}</h1>
		<h3>Configuration ID: ${eventTypeConfig.id}</h3>
		<h3>Event Type ID: ${eventTypeConfig.eventType.id}</h3>
		<hr/>
		<#if eventTypeConfig.eventItemConfigs?has_content>
			<table class="item-table" width="100%">
				<tr class="header">
					<td width="33%"><b>Name</b></td>
					<td width="33%"><b>Unit</b></td>
					<td width="33%"><b>Color</b></td>
				</tr>
				<#list eventTypeConfig.eventItemConfigs as eventItemConfig>
					<tr class="item-row">
						<td>${eventItemConfig.eventItemType.name}</td>
						<td>${eventItemConfig.eventItemType.unit}</td>
						<td style="background-color:#${eventItemConfig.color}">#${eventItemConfig.color}</td>
					</tr>
				</#list>
			</table>
		</#if>
	</body>
</html>