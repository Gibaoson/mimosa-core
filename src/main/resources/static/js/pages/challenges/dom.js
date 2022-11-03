$(document).ready(function () {
	test();
	load_redirect();
	console.log("DOMJS");
  });
  
  function load_redirect() {
	console.log("DOMJS-Redirect");
	$(".nav-redirect").click(function (e) {
	  e.preventDefault();
  
	  let href = $(this).attr("href");
	  $("#navbar-input").val("https://WelcomeAttacker" + href);
	  console.log(href);
	  $("#challenge-form").submit();
	});
  }
  
  function dom_success(response) {
	console.log(response.attempt)
	$(".site-content").html(response.attempt.data.page);
	load_redirect();
	// attach_search();
	default_challenge_success(response);
  }
  
  function dom_error(response) {
	default_challenge_error(response);
  }
  
  function test() {
	var username = document.getElementById("navbar-input").value.indexOf("Name=") + 5;
	if (username != 4) {
	  var user = unescape(document.getElementById("navbar-input").value.substring(username));
	  document.getElementById("user").append(user);
	}
  }
  