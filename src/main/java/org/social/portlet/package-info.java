@Application(
    name = "SpaceApplication",
    defaultController = org.social.portlet.Controller.class)
@Bindings({
  @Binding(value = SpaceService.class, implementation = GateInMetaProvider.class),
  @Binding(value = IdentityManager.class, implementation = GateInMetaProvider.class),
  @Binding(value = Identity.class, implementation = IdentityProvider.class)
})
@Assets(
  scripts = {
    @Script(src = "js/jquery-1.7.1.js"),
    @Script(src = "js/less-1.2.2.min.js"),
    @Script(src = "js/bootstrap.js"),
    @Script(src = "js/bootstrap-collapse.js"),
    @Script(src = "js/bootstrap-tooltip.js"),
    @Script(src = "js/bootstrap-popover.js"),
    @Script(src = "js/space.js")
  },
  stylesheets = {
    @Stylesheet(src = "css/gatein.less")
  }
)
package org.social.portlet;

import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.space.spi.SpaceService;
import juzu.Application;
import juzu.plugin.binding.Binding;
import juzu.plugin.binding.Bindings;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import org.social.portlet.providers.GateInMetaProvider;
import org.social.portlet.providers.IdentityProvider;

