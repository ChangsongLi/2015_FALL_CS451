<!DOCTYPE HTML>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-US" xml:lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<style>
    ul { display:table; margin:0 auto;}
</style>
    
<script language="javascript" type="text/javascript">
var timerid = 0;
var images = new Array(	"parkdine.jpg",
			"follow.jpg",
			"FlyFromHome.jpg");
var countimages = 0;
function startTime()
{
	if(timerid)
	{
		timerid = 0;
	}
	var tDate = new Date();
	
	if(countimages == images.length)
	{
		countimages = 0;
	}
	if(tDate.getSeconds() % 5 == 0)
	{
		document.getElementById("changePic").src = images[countimages];
	}
	countimages++;
	
	timerid = setTimeout("startTime()", 1000);
}
</script>
    
    
<title>La Crosse Regional Airport</title>
<meta name="description" content="Just another Open Designs template." />
<meta name="robots" content="noodp,noydir" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" id="child-theme-css" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" id="responsive-main-css-css" href="css/responsive-main.min.css" type="text/css" media="all" />
<link rel="stylesheet" id="responsive-css-css" href="css/responsive.css" type="text/css" media="all" />
<link rel="stylesheet" id="tb_styles-css" href="css/tb-styles.min.css" type="text/css" media="all" />

<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
  jQuery(window).scroll(function (event) {
	  	
		var top = jQuery('#popular-upcoming').offset().top - jQuery(document).scrollTop();;
		// what the y position of the scroll is
		var y = jQuery(this).scrollTop();
		// whether that's below the form
		if (y >= top)  {
		// if so, add the active class to popular-upcoming and remove from content
		jQuery('.page-nav-popular-posts').addClass('active');
		jQuery('.page-nav-top-posts').removeClass('active');
		} else {
		// otherwise remove it
		jQuery('.page-nav-popular-posts').removeClass('active');
		jQuery('.page-nav-top-posts').addClass('active');
	   }
  });
  
  jQuery(document).ready(function (){
  jQuery('#popular-scroll').click(function (){
            //jQuery(this).animate(function(){
                jQuery('html, body').animate({
                    scrollTop: jQuery('#popular-upcoming').offset().top
                     }, 2000);
            //});
        });
		
		jQuery('#feature-scroll').click(function (){
            //jQuery(this).animate(function(){
                jQuery('html, body').animate({
                    scrollTop: jQuery('#inner').offset().top
                     }, 2000);
            //});
        });
		  });
	  </script>
</head>

<body class="home blog header-full-width full-width-content" onload="startTime();">
  <div id="header">
  <div class="site-header">
    <h1 class="site-header-logo-container">
    <a href="/"><span class="image-replace">La Crosse</span>
    <img src="logo.png" width="222" height="36" id="bigg-logo" alt="Bigg" /></a>
    </h1>
      
            <ul id="page-nav" class="horizontal-list" style="font-size:17px;">
<li class="page-nav-top-posts active" ><a href="javascript:void(0)"  id="feature-scroll" class="page-anchor-link"><font face="Arial">Flight Arrival</font></a></li>

                <li class="page-nav-popular-posts"><a href="javascript:void(0)" id="popular-scroll" class="page-anchor-link"><font  face="Arial">Flight Departure</font></a></li>

</ul>


<div id="site-header-bigg-social">
<ul class="horizontal-list">
<li><a href="https://twitter.com/opendesigns" target="_blank" class="bigg-social-twitter bigg-social-icon image-replace">Twitter</a></li>
<li><a href="http://www.facebook.com/opendesigns/" target="_blank" class="bigg-social-fb bigg-social-icon image-replace">Facebook</a></li>
<li><a href="https://plus.google.com/101703942483092652776/posts" target="_blank" class="bigg-social-gplus bigg-social-icon image-replace">Google+</a></li>
</ul>
      
    </div>  
    </div>
  </div>
  <div id="wrap">
<div id="inner">
<div class="wrap">
<div id="content-sidebar-wrap">
				
				<div id="content" class="hfeed">
				<div class="post-5 post type-post status-publish format-standard hentry category-featured category-parent-category-i entry feature feature">

                    <a href="#" title="Welcome to LSE Airport"><img id = "changePic" width="660" height="370" src="FlyFromHome.jpg" class="alignleft post-image" alt="1" /></a>		<h2 class="entry-title" style="color:#606060"><b> No Stress Experience.</b></h2> 
		
				<div class="entry-content" >
			<p style="color:#606060">The La Crosse Regional Airport offers close parking, easy check-in, convenient daily flights to major hubs, free WiFi, and a comfortable atmosphere to make your travel experience a pleasure.</p>
		</div><!-- end .entry-content -->
		
	
	</div><!-- end .postclass -->
			</div><!-- end #content -->
		
<div id="popular-upcoming" class="stories-container sixcol">

<div class="stories-section-header">
<h2 class="stories-section-header-hed" style="color:#606060" >Proudly served by:</h2>
<h3 class="stories-section-header-subhed"> <img src="airline/Delta.jpg" width="100" height="100" alt="delta" />
    <img src="airline/British%20Airways.gif" width="100" height="100" alt="ba" />
    <img src="airline/Iberia.gif" width="100" height="100" alt="ib" />
    <img src="airline/Qatar%20Airways.jpg" width="100" height="100" alt="delta" />
    <img src="airline/logo%20AA.jpg" style="top:20px; position: absolute" width="90" height="70" alt="aa" />
    <img src="airline/KLM.png" style="top:20px; left:540px; position: absolute" width="77" height="70" alt="klm" />
    </h3>
</div>

    
<div align="center">
	<table border="0"  bgcolor="#f1f1f1" align="center" width="200%" id="table1">
		<tbody><tr>
			<td colspan="7" align="center"><font size="5"><b><br>Flight Arrival 
			Schedule</b></font></td>
		</tr>
		<tr>
			<td align="center" style="width: 14%" class="auto-style1"><strong>
			Airline</strong></td>
			<td align="center" style="width: 14%"><font size="5"><b>Flight No.</b></font></td>
			<td align="center" width="24%"><font size="5"><b>Arriving From</b></font></td>
			<td align="center" width="13%"><strong><span class="auto-style1">
			Scheduled Arrival Time</span></strong></td>
			<td align="center" width="13%"><font size="5"><b>Expected Arrival 
			Time</b></font></td>
			<td align="center" width="13%"><font size="5"><b>Gate</b></font></td>
			<td align="center" width="15%"><font size="5"><b>Status</b></font></td>
		</tr>
		<tr>
			<td style="width: 14%">American Airlines</td>
			<td style="width: 14%">&nbsp;AA 3345</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">9:30 A.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;9:30 A.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" class="auto-style6">&nbsp;Landed</td>
		</tr>
		<tr>
			<td style="width: 14%">British Airways</td>
			<td style="width: 14%">&nbsp;BA 6764</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">9:30 A.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">9:30 A.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" class="auto-style5">&nbsp;Landed</td>
		</tr>
		<tr>
			<td style="width: 14%">Delta</td>
			<td style="width: 14%">&nbsp;DL 4648</td>
			<td width="24%">&nbsp;Minneapolis</td>
			<td width="13%" align="right">11:50 A.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;12:50&nbsp; P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" class="auto-style3">&nbsp;Delayed</td>
		</tr>
		<tr>
			<td style="width: 14%">American Airlines</td>
			<td style="width: 14%">&nbsp;AA 3497</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">1:31 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">1:31 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">Etihad Airways</td>
			<td style="width: 14%">&nbsp;ET 3294&nbsp;</td>
			<td width="24%">&nbsp;Chicago&nbsp;</td>
			<td width="13%" align="right">1:31 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">1:31 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000">&nbsp;<strong>On-Time</strong></td>
		</tr>
		<tr>
			<td style="width: 14%">Delta</td>
			<td style="width: 14%">&nbsp;DL 4549</td>
			<td width="24%">&nbsp;Minneapolis</td>
			<td width="13%" align="right">4:55 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;5:50 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" class="auto-style3">&nbsp;Delayed</td>
		</tr>
		<tr>
			<td style="width: 14%">KLM</td>
			<td style="width: 14%">&nbsp;KL 6705&nbsp;</td>
			<td width="24%">&nbsp;Minneapolis&nbsp;</td>
			<td width="13%" align="right">4:55 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;5:50 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" class="auto-style4">&nbsp; <strong>Delayed</strong></td>
		</tr>
		<tr>
			<td style="width: 14%">Air France</td>
			<td style="width: 14%">&nbsp;AF 2180</td>
			<td width="24%">&nbsp;Minneapolis&nbsp;</td>
			<td width="13%" align="right">4:55 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;5:50 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" class="auto-style4">&nbsp;<strong>Delayed</strong></td>
		</tr>
		<tr>
			<td style="width: 14%">American Airlines</td>
			<td style="width: 14%">&nbsp;AA 3347 </td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">9:41 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;9:41 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">Qatar Airways</td>
			<td style="width: 14%">&nbsp;QT 5270 </td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">9:41 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;9:41 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">British Airways</td>
			<td style="width: 14%">&nbsp;BA 6842 </td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">9:41 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;9:41 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">Iberia</td>
			<td style="width: 14%">&nbsp;IB 4087</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">9:41 P.M.&nbsp;&nbsp;&nbsp;&nbsp; </td>
			<td width="13%" align="right">&nbsp;9:41 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
	</tbody>
    </table>
    
    <br>
    <br>
    
    <div align="center">
	<table border="0"  bgcolor="#f1f1f1" align="center" width="200%" id="table2">
        <br>
		<tbody><tr>
			<td colspan="7" align="center"><font size="5"><b>
                <br>Flight Departure 
			Schedule</b></font></td>
		</tr>
		<tr>
			<td align="center" style="width: 14%" class="auto-style1"><strong>
			Airline</strong></td>
			<td align="center" style="width: 14%"><font size="5"><b>Flight No.</b></font></td>
			<td align="center" width="24%"><font size="5"><b>Departing To</b></font></td>
			<td align="center" width="13%"><strong><span class="auto-style1">
			Scheduled Departure Time</span></strong></td>
			<td align="center" width="13%"><font size="5"><b>Expected Departure 
			Time</b></font></td>
			<td align="center" width="13%"><font size="5"><b>Gate</b></font></td>
			<td align="center" width="15%"><font size="5"><b>Status</b></font></td>
		</tr>
		<tr>
			<td style="width: 14%">American Airlines</td>
			<td style="width: 14%">&nbsp;AA 3366</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">&nbsp;6:20 A.M.&nbsp; &nbsp;</td>
			<td width="13%" align="right">&nbsp;6:24 A.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" class="auto-style6">&nbsp;Departed</td>
		</tr>
		<tr>
			<td style="width: 14%">Etihad Airways</td>
			<td style="width: 14%">&nbsp;ET 3376</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">6:20 A.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">6:24 A.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" class="auto-style5">&nbsp;Departed</td>
		</tr>
		<tr>
			<td style="width: 14%">6:20 Am.</td>
			<td style="width: 14%">&nbsp;BA 2487</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">6:20 A.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">6:24 A.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" class="auto-style5">&nbsp;Departed</td>
		</tr>
		<tr>
			<td style="width: 14%">Delta</td>
			<td style="width: 14%">&nbsp;DL 4577</td>
			<td width="24%">&nbsp;Minneapolis</td>
			<td width="13%" align="right">7:00 A.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;7:00&nbsp;A.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" class="auto-style5">&nbsp;Departed</td>
		</tr>
		<tr>
			<td style="width: 14%">American Airlines</td>
			<td style="width: 14%">&nbsp;AA 3345</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">10:53 A.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">11:03 A.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" class="auto-style3">&nbsp;Delayed</td>
		</tr>
		<tr>
			<td style="width: 14%">Iberia</td>
			<td style="width: 14%">&nbsp;IB 4489</td>
			<td width="24%">&nbsp;Chicago&nbsp;</td>
			<td width="13%" align="right">10:53 A.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">11:03 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" class="auto-style4">&nbsp;<strong>Delayed</strong></td>
		</tr>
		<tr>
			<td style="width: 14%">Delta</td>
			<td style="width: 14%">&nbsp;DL 4648</td>
			<td width="24%">&nbsp;Minneapolis</td>
			<td width="13%" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 12:45 
			P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;12:45 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">KLM</td>
			<td style="width: 14%">&nbsp;KL 6703&nbsp;</td>
			<td width="24%">&nbsp;Minneapolis&nbsp;</td>
			<td width="13%" align="right">12:45 P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;12:45 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" bgcolor="#008000">&nbsp;<strong>On-Time&nbsp;</strong></td>
		</tr>
		<tr>
			<td style="width: 14%">Air France</td>
			<td style="width: 14%">&nbsp;AF 2197</td>
			<td width="24%">&nbsp;Minneapolis&nbsp;</td>
			<td width="13%" align="right">12:45 P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;12:45 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" bgcolor="#008000">&nbsp;<strong>On-Time&nbsp;</strong></td>
		</tr>
		<tr>
			<td style="width: 14%">American Airlines</td>
			<td style="width: 14%">&nbsp;AA 3497 </td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">2:35 P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;2:35 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">Qatar Airways</td>
			<td style="width: 14%">&nbsp;QT 5611 </td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">2:35 P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;2:35 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">British Airways</td>
			<td style="width: 14%">&nbsp;BA 1779 </td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">2:35 P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;2:35 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">Iberia</td>
			<td style="width: 14%">&nbsp;IB 4535</td>
			<td width="24%">&nbsp;Chicago</td>
			<td width="13%" align="right">2:35 P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;2:35 P.M.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A1</td>
			<td width="15%" bgcolor="#008000"><b>&nbsp;On-Time</b></td>
		</tr>
		<tr>
			<td style="width: 14%">Delta</td>
			<td style="width: 14%">&nbsp;DL 4549</td>
			<td width="24%">Minneapolis</td>
			<td width="13%" align="right">5:40 P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" class="auto-style7">Cancelled</td>
		</tr>
		<tr>
			<td style="width: 14%">KLM</td>
			<td style="width: 14%">&nbsp;KL 6705</td>
			<td width="24%">Minneapolis</td>
			<td width="13%" align="right">5:40 P.M.&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="right">&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="13%" align="center">A2</td>
			<td width="15%" class="auto-style7">Cancelled</td>
		</tr>
		</tbody></table>
</div>
    
<br> 
<br> 
</div>

</div>


	</div><!-- end .wrap --></div><!-- end #inner --> 
<div id="bigg-footer">
<div class="wrap">
    
       
        
		        <div class="footer-copyright clear">
<span style="font-size:14px" id="footer-copyright-year">© 2015 La Crosse Regional Airport</span></a>
</div>
        </div>
</div>
 
</div><!-- end #wrap -->


</div>
</body>
</html>