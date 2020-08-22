<html !DOCTYPE>
	<head>
		<style>
			body {
				font-family: monospace;
			}
			
			.event-types-table {
				border-collapse: collapse;
			}
			
			.event-types-table .event-types-header > td, .event-row > td  {
				border: black solid 1px;
			}
			
			.header > td {
				text-decoration: underline;
			}
			
			td {
				padding: 5px;
				vertical-align: top;
			}
		</style>
	</head>
	<body>
		<h1>Event Type Configs</h1>
		<table class="event-types-table">
			<tr class="event-types-header">
				<td><b>Event Type</b></td>
			</tr>
			<#list eventTypeConfigs as eventTypeConfig>
				<tr class="event-row">
					<td>
						<a href="/admin/events/${eventTypeConfig.id}">${eventTypeConfig.eventType.name}</a>
					</td>
				</tr>
			</#list>
		</table>
		<h1>Event Item Types</h1>
		<a href="/admin/events/itemType/create">Add new</a>
		<br/><br/>
		<table class="event-types-table">
			<tr class="event-types-header">
				<td><b>Name</b></td>
				<td><b>Unit</b></td>
			</tr>
			<#list eventItemTypes as eventItemType>
				<tr class="event-row">
					<td>${eventItemType.name}</td>
					<td>${eventItemType.unit}</td>
				</tr>
			</#list>
		</table>
	</body>
</html>