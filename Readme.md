# Instruction for Team Dashboard

XML File Path: campus_only/xml

the permission is set already.

### 1.Submit Pair:

​	email repeat check file: example_Teams.xml

​	email repeat check form: this is a test I use, and you can use other existed  emails in the file. 

    <Student id="stu1">
      <Forename>Lingxin</Forename>
      <Surname>Gu</Surname>
      <Email>lgu8@sheffield.ac.uk</Email>
      <Region>Overseas</Region>
      <Degree>MSc Advanced Software Engineering</Degree>
      <Level>Postgraduate</Level>
    </Student>
    <Student id="stu2">
      <Forename>Yan</Forename>
      <Surname>Ge</Surname>
      <Email>YGe5@sheffield.ac.uk</Email>
      <Region>Overseas</Region>
      <Degree>MSc Advanced Software Engineering</Degree>
      <Level>Postgraduate</Level>
    </Student>

submit form:

shell command `forename1=John&surname1=Smith&email1=JSmith9@sheffield.ac.uk&region1=EU&degree1=MEng+Software+Engineering&level1=Postgraduate&forename2=Emma&surname2=Dubois&email2=EDubois3@sheffield.ac.uk&region2=Overseas&degree2=MSc+Computer+Science&level2=Postgraduate`

<img src="C:\Users\Xinmeng\AppData\Roaming\Typora\typora-user-images\image-20210918230115843.png" alt="image-20210918230115843" style="zoom:33%;" />



To: xml/pair/pair_submit.xml



### 2.Generate Team:

check pair number to make the result of [pair number] * 2 / [size] is a integer

From: xml/pair/

shell command

`cohort=no&diversity=no&size=4`

To: xml/Teams.xml



### 3.Allocate Assessor

From: xml/Teams.xml

To:  

xml/allocatedTeams.xml

xml/allocatedTeams_team.xml

xml/allocatedTeams_assessor.xml

### 4.View Team List 

via the link to view [allocatedTeams_team.xml]

XSL attached might requires Firefox browser

### 5.View Assessor List

via the link to view [allocatedTeams_assessor.xml]

XSL attached might requires Firefox browser

### 6.Manage Team

From: xml/allocatedTeams.xml

​	Drop out (stu9): `action=drop&identity=&email1=&email2=hjin4@sheffield.ac.uk`    

​	Change team(stu18 to Team10): `action=change&identity=Team10&email1=slu8%40sheffield.ac.uk&email2=`

To: 

xml/allocatedTeams.xml (update!) 

xml/allocatedTeams_team.xml(update!)

xml/allocatedTeams_assessor.xml(update!)

### Then

could still access 2 lists via the link and view the updated lists.



Java program codes attached in the directory "Java_code"

