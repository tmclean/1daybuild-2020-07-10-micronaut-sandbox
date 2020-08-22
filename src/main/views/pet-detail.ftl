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
		<h1>${pet.name}</h1>
		<h3>ID: ${pet.id}</h3>
		<hr/>
		<table class="event-table" width="100%">
			<tr class="event-header">
				<td><b>Date/Time</b></td>
				<td><b>Type</b></td>
				<td><b>Notes</b></td>
				<td><b>Items</b></td>
			</tr>
			<#list events as event>
				<tr class="event-row">
					<td>${event.eventTime?string["yyyy-MM-dd hh:mm:ss a"]} UTC</td>
					<td>${event.eventType.name}</td>
					<td>
						<#if event.notes??>
							${event.notes}
						</#if>
					</td>
					<td>
						<#if event.items?has_content>
							<table class="item-table" width="100%">
								<tr class="header">
									<td width="33%"><b>Type</b></td>
									<td width="33%"><b>Value</b></td>
									<td width="33%"><b>Notes</b></td>
								</tr>
								<#list event.items as item>
									<tr class="item-row">
										<td>
											${item.itemType.name}
										</td>
										<td>
											<#if item.itemType.unit != 'none'>
												${item.value} ${item.itemType.unit.label}
											</#if>
										</td>
										<td>
											<#if item.notes??>
												${item.notes}
											</#if>
										</td>
									</tr>
								</#list>
							</table>
						</#if>
					</td>
				</tr>
			</#list>
		</table>
	</body>
</html>