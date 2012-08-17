$(function() {
  $(".space-container").each(function() {
    
    $(this).jzLoad("Controller.loadSpaces()", function() {
    });
      
  });
});