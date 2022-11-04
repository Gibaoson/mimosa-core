$(document).ready(function () {
  test();
  load_redirect();
});

function load_redirect() {
  $(".nav-redirect").click(function (e) {
    e.preventDefault();

    let href = $(this).attr("href");
    $("#navbar-input").val("https://WelcomeAttacker" + href);
    $("#challenge-form").submit();
  });
}

function dom_success(response) {
  console.log(response);
  $(".site-content").html(response.data.page);
  load_redirect();
  test();
  default_challenge_success(response);
}

function dom_error(response) {
  default_challenge_error(response);
}

function test() {
  var username =
    document.getElementById("navbar-input").value.indexOf("Name=") + 5;
  if (username != 4) {
    var user = document
      .getElementById("navbar-input")
      .value.substring(username);
    console.log(user)
    document.getElementById("user").innerHTML = user;
  }
}
