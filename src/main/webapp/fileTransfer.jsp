<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<title>File Transfer demo</title>
</head>
<body>
	<h1>File Upload invoking Jersey</h1>	 
	<form id="uploadFileForm" action="http://localhost:8181/test-jersey-webapp/webresources/uploadFile" method="post" enctype="multipart/form-data">
	   <p>Add a User:<input name="user" type="text" /> </p>
	   <p>Select a file : <input type="file" name="file" size="45" /></p> 
	   <input name="upload" type="submit" value="Upload It" />
	   
   		<input type="hidden" id="device" name="device" value="web"/> 
	</form>
</body>
</html>