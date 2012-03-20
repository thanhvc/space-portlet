$(function() {

  // Load initial spaces
  $(".space-container").each(function() {
    var spaceUrl = this.SpaceApplication().loadSpaces();
    var container = this.$find(".space-container");
    container.load(spaceUrl);
  });

});