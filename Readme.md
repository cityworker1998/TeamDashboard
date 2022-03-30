# 关于Team Dashboard的介绍

需求分析：

<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/Team%20Dashboard%20Xmind.jpg" style="zoom:25%"/>

***

功能模块划分：

<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/Team%20Dashboard%20Xmind.jpg" style="zoom:25%"/>

***

技术需要：

* HTML
* CGI Script
* Executable Jar (Java)
* XML, XSL
* JavaScript, LinuxOS, Apache Server，正则表达式等

<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/Technology.png" style ="zoom:25%">

***

<<<<<<< HEAD
数据的转化和处理过程：
=======
<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/Submit%20Pair.png" style="zoom:33%;"/>
>>>>>>> 87a0b643fcc056d40a5ac7d9aa5e67da0d7c18a8

<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/Data%20Flow.png" style ="zoom:25%">

***

XML文件在功能模块执行过程中的输入和输入过程：

<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/File%20Flow.png" style ="zoom:25%">

***

### 1.Submit Pair 提交二人小组信息:

​	包括正则表达式验证邮箱，重复邮箱与后台文件对比验证流程

​	表单示例 :

<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/Submit%20Pair.png" style="zoom:33%;"/>

​	**生成`pair.xml`**	

​	生成的XML文件结构如下：

```xml
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
```

***

### 2.Generate Team 生成小组:

首先获取当前pair数量，避免生成小组的结果最后的小组为空缺

当前pair数量满足公式
$$
pair*2/size=一个整数
$$
设定好生成规则，包括cohort（多个年级）,diversity（来自不同国家地区）,size（小组人数：4或者6）

**由`pair.xml`生成`teams.xml`**

***

### 3.Allocate Assessor 分配测试的小组：

每个小组成员都会分配到一个小组来打分，如果一个小组有4个人，代表一个小组会被4名同学打分，最终通过一定的算法和规则得到小组所获得的分数。

**由`teams.xml`会生成以下三个文件**

* **`allocatedTeams.xml`**用于Manage Team功能
* **`allocatedTeams_team.xml`**用于View Team List功能
* **`allocatedTeams_assessor.xml`**用于View Assessor List功能

***

### 4.View Team List  查看小组分配结果：

**`allocatedTeams_team.xml`**解析**`teamView.xsl`**，最终在网页上展示

<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/View%20Team%20List.png" style="zoom:33%;"/>

### 5.View Assessor List 查看测试同学的分配结果 ：

**`allocatedTeams_assessor.xml`**解析**`assessorView.xsl`**，最终在网页上展示

<img src="https://github.com/DocYangxm/TeamDashboard/blob/master/Image/View%20Assessor%20List.png" style="zoom:33%;"/>

### 6.Manage Team 更新小组成员：

* 删除某位同学Drop out
  * Drop out (stu9): `action=drop&identity=&email1=&email2=hjin4@sheffield.ac.uk` 
* 修改某位同学所在的小组Change Team
  * action=change&identity=Team10&email1=slu8%40sheffield.ac.uk&email2=

**更新`allocatedTeams.xml`并生成新的`allocatedTeams_team.xml`，`allocatedTeams_assessor.xml`**

