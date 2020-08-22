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
		
		<script lang="javascript">
			function submitItemForm() {
				var mode = document.getElementById( "_mode" ).value;
				if( mode === 'create' ){
					var name = document.getElementById( "name" ).value;
					var unit = document.getElementById( "unit" ).value;
					var submitObj = {
						'mode': mode,
						'itemType':{
							'id': null,
							'name': name,
							'unit': unit
						}
					};
					
					console.log( submitObj );
					
					var httpReq = new XMLHttpRequest();
					httpReq.open( 'POST', '/admin/events/itemType/create', true );
					httpReq.setRequestHeader( 'Content-Type', 'application/json' );
					httpReq.onreadystatechange = () => {
						if( httpReq.readyState === XMLHttpRequest.DONE ){
							if( httpReq.status === 200 ) {
								window.location = '/admin/events';
							}
						}
					}
					httpReq.send( JSON.stringify( submitObj ) );
				}
			}
		</script>
	</head>
	<body>
		<h1>Event Item Type</h1>
		<hr/>
		<br/>
		<form name="itemTypeForm">
			<input type="hidden" id="_mode" value="${mode}"/>
			<#if mode != "create">
				<input type="hidden" id="id" value="${itemType.id}"/>
			</#if>
			Name: <input type="text" id="name"></input>
			<br/><br/>
			Unit: <select id="unit">
				<#list units as unit>
					<option value="${unit}">${unit}</option>
				</#list>
			</select>
			<br/><br/>
			<input type="button" value="Save" onClick="submitItemForm()"/></input>
		</form>
	</body>
</html>