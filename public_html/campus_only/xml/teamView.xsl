<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

<xsl:template match="/Teams">

<html>
<body>
<h2>Team List</h2>
<ul>
<li>This list is to show information of team members.</li>
<li>After modifying team, you need to re-enter the "View Team List" page to view the list.</li>
</ul>

	<table border="1">
		<tr bgcolor="#9acd32">
		<th align="left">ID</th>
		<th align="left">Forename</th>
		<th align="left">Surname</th>
		<th align="left">Email</th>
		<th align="left">Region</th>
		<th align="left">Degree</th>
		<th align="left">Level</th>
		</tr>
		<xsl:apply-templates/>
	</table>
	
<br/>
<br/>
</body>
</html>
</xsl:template>
	
<xsl:template match="Team">
	<thead>
		<tr>
		<div align="left">
		<th  bgcolor="acd6ff" colspan="4"><xsl:value-of select="@identity"/></th>
		</div>
		</tr>
	</thead>
	<xsl:apply-templates/>
</xsl:template>

<xsl:template match="Team/Student">
<tr>
<td><xsl:value-of select="@id"/></td>
<td><xsl:value-of select="Forename"/></td>
<td><xsl:value-of select="Surname"/></td>
<td><xsl:value-of select="Email"/></td>
<td><xsl:value-of select="Region"/></td>
<td><xsl:value-of select="Degree"/></td>
<td><xsl:value-of select="Level"/></td>
</tr>
</xsl:template>

<xsl:template match="Drop">
	<thead>
		<tr bgcolor="#bebebe">
		<div align="left">
		<th colspan="4"><xsl:value-of select="@identity"/></th>
		</div>
		</tr>
	</thead>
	<xsl:apply-templates/>
</xsl:template>

<xsl:template match="Drop/Student">
<tr>
<td><xsl:value-of select="@id"/></td>
<td><xsl:value-of select="Forename"/></td>
<td><xsl:value-of select="Surname"/></td>
<td><xsl:value-of select="Email"/></td>
<td><xsl:value-of select="Region"/></td>
<td><xsl:value-of select="Degree"/></td>
<td><xsl:value-of select="Level"/></td>
</tr>
</xsl:template>

</xsl:stylesheet>