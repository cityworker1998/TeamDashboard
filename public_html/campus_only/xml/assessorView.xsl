<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/Teams">

<html>
<body>
	<h2>Assessor List</h2>
	<ul>
	<li>This list is to show the allocated assessors and is generated automatically.</li>
	<li>Every student in every team will be allocated to assess one team.</li>
	<li>Assessors are prohibited assessing their own teams (At least 8 teams when team size is 4 and 12 teams when size is 6).</li>
	<li>Every team will receive several grades from different teams (In general it is due to the size of team).</li>
	<li>After modifying team, you need to re-enter the "View Assessor List" page to view the list.</li>
	<li>The correspondence between assessors and teams will not change after modifying the team (including dropping out and changing their belonging team).</li>
	</ul>
	<table border="1">
		<thead>
			<tr bgcolor="#9acd32">
			<th>Name</th>
			<th>Course</th>
			<th>Email</th>
			<th>Testing</th>
			</tr>
		</thead>
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
	<tr><td>
	<xsl:value-of select="Forename"/>
	<xsl:text>  </xsl:text>
	<xsl:value-of select="Surname"/>
	</td><td>
	<xsl:value-of select="Degree"/>
	</td><td>
	<xsl:value-of select="Email"/>
	</td><td>
	<xsl:value-of select="Testing"/>
	</td></tr>
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
	<tr><td>
	<xsl:value-of select="Forename"/>
	<xsl:text>  </xsl:text>
	<xsl:value-of select="Surname"/>
	</td><td>
	<xsl:value-of select="Degree"/>
	</td><td>
	<xsl:value-of select="Email"/>
	</td><td>
	<xsl:value-of select="Testing"/>
	</td></tr>
</xsl:template>

</xsl:stylesheet>