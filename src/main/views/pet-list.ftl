<html !DOCTYPE>
	<head>
		<style>
			body {
				font-family: monospace;
			}
			
			.pet-table {
				border-collapse: collapse;
			}
			
			.pet-table .header > td, .pet-table .pet-row > td  {
				border: black solid 1px;
			}
			
			td {
				padding: 5px;
			}
		</style>
	</head>
	<body>
		<h1>Pets</h1>
		<table class="pet-table">
			<tr class="header">
				<td><b>Name</b></td>
				<td>Species</td>
			</tr>
			<#list pets as pet >
				<tr class="pet-row">
					<td>
						<a href="/pets/${pet.id}">${pet.name}</a>
					</td>
					<td>${pet.species.name} (<i>${pet.sex.label} - ${pet.species.scientificName}</i>)</td>
				</tr>
			</#list>
		</table>
	</body>
</html>