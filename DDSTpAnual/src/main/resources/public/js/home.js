$(document).ready(function(){
  $(".panelMenu").click(function() {
    console.log("aca");
    document.location.href = $(this).attr('id');
  });
});