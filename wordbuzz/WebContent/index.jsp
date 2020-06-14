<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="searchEngine.SearchFiles"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<style>
	
img {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
body, html {
  height: 100%;
  width:100%;
}
</style>
</head>


	<body class="bgb" >
	
	
	
	<div class="container">
		<img src="images/WordBuzz.png" alt="Search Engine Logo" class="center" />
		<form>
			<div class="row">
				<div class="col-lg-6">	
				    <div class="form-group">
				      	<label for="keyword">Enter Your Search Here</label>
				    	<input type="text" id="keyword" name="keyword" class="form-control"/>
				    </div>
				</div>
				<div class="col-lg-6">
					<div class="form-group">
				      	<label for="NRecords">Select the Number Of Pages To Display:</label>
				      	<select class="form-control center" id="NRecords" name="NRecords">
				       		<option>10</option>
				        	<option>20</option>
				        	<option>30</option>
				        	<option>40</option>
							<option>50</option>
				        	<option>60</option>
				        	<option>70</option>
				        	<option>80</option>
							<option>90</option>
				        	<option>100</option>			        
				      </select>
				    </div>
				</div>
			</div>
			<div class="row text-center">
			<input type="submit"  class="btn btn-warning" value="Search" />
			</div>	
	  	</form>
	</div>
	<br />

		
		<div  class="bgb">
			<%
				if(
						request.getParameter("keyword")!=null && !request.getParameter("keyword").equals("")
						&& request.getParameter("NRecords")!=null && !request.getParameter("NRecords").equals("")
						){
					List<Entry<String, Integer>>sortedFileContentCount = new SearchFiles("D:\\ACC_SearchEngine\\src\\webpages").search(request.getParameter("keyword"), Integer.parseInt(request.getParameter("NRecords")));
					if(sortedFileContentCount!=null && sortedFileContentCount.size()!=0){
						out.write("<div class='container'>");
						out.write("<hr />");
						out.write("<br />");
						out.write("<table class='table table-striped'>");
						out.write("<thread>");
						out.write("<tr>");
						out.write("<th>File Name</th>");
						out.write("<th>Count</th>");
						out.write("</tr>");
						out.write("</thread>");
						for(Entry<String, Integer> entry : sortedFileContentCount){
							out.write("<tbody>");
							out.write("<tr>");
							out.write("<td>" + entry.getKey() + "</td>");
							out.write("<td>" + entry.getValue() + "</td>");
							out.write("</tr>");
							out.write("<tbody>");
						}
						out.write("</table>");
						out.write("</div>");
					}
					else{
						//System.out.println("suggest started");
						List<String> SuggestedList=new SearchFiles("D:\\ACC_SearchEngine\\src\\webpages").suggest(request.getParameter("keyword"));
						out.write("<div class='container'>");
						out.write("<hr>");
						out.write("<br/>");
						out.write("<table class='table table-striped'>");
						out.write("<thread>");
						out.write("<tr>");
						out.write("<th class='tableChildClass'>Suggested Words</th>");
						out.write("</tr>");
						out.write("</thread>");
						for(String word:SuggestedList){
							out.write("<tbody>");
							out.write("<tr>");
							out.write("<td class='tableChildClass'>" + word + "</td>");
							out.write("</tr>");
							out.write("</tbody>");
						}
						out.write("</table>");
						out.write("</div>");
					}
				}
			%>
		</div>



</body></html>