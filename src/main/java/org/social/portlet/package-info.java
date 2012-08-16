@Application
@Portlet
@Bindings({
  @Binding(value = SpaceService.class, implementation = GateInMetaProvider.class),
  @Binding(value = IdentityManager.class, implementation = GateInMetaProvider.class),
  @Binding(value = Identity.class, implementation = IdentityProvider.class)
})
@Assets(
  scripts = {
    @Script(id = "jquery", src = "js/jquery-1.7.1.min.js"),
    @Script(src = "js/less-1.2.2.min.js", depends = "jquery"),
    @Script(src = "js/bootstrap.js", depends = "jquery"),
    @Script(src = "js/bootstrap-collapse.js", depends = "jquery"),
    @Script(src = "js/bootstrap-tooltip.js", depends = "jquery"),
    @Script(src = "js/bootstrap-popover.js", depends = "jquery"),
    @Script(src = "js/space.js", depends = "juzu.ajax")
  },
  stylesheets = {
    @Stylesheet(src = "css/gatein.less")
  }
)
package org.social.portlet;

import org.social.portlet.providers.GateInMetaProvider;
import org.social.portlet.providers.IdentityProvider;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.space.spi.SpaceService;
import org.exoplatform.social.core.manager.IdentityManager;
import juzu.Application;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.binding.Binding;
import juzu.plugin.binding.Bindings;
import juzu.plugin.portlet.Portlet;
